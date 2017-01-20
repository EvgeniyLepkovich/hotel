package spring.hotel.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import spring.hotel.common.persistance.to.Account;
import spring.hotel.model.Authorities;
import spring.hotel.model.Oauth2ClientResources;

import java.util.List;
import java.util.Map;

/**
 * Wraps Spring Boot's {@link #wrappedUserInfoTokenServices} to authenticate via
 * Social Network site, and then extracts user details.
 * 
 * @author Richard_Pal
 */
public class SocialUserInfoTokenServicesWrapper extends AbstractUserInfoTokenServices {

  protected UserInfoTokenServices wrappedUserInfoTokenServices;

  private static final String WEBTOKENATTR_EMAIL = "email";
  private static final String WEBTOKENATTR_FULLNAME = "first_name";
  private static final String WEBTOKENATTR_USERID = "id";
  private static final String WEBTOKENATTR_DISPLAYNAME = "displayName";

  public SocialUserInfoTokenServicesWrapper(Oauth2ClientResources oauth2ClientResources,
      UserInfoTokenServices wrappedUserInfoTokenServices) {
    super(oauth2ClientResources);
    this.wrappedUserInfoTokenServices = wrappedUserInfoTokenServices;
  }

  @Override
  protected Map<String, Object> getPrincipalDetails(String accessToken) {

    final OAuth2Authentication a = authenticateFrom(accessToken);

    final Authentication userAuthentication = a.getUserAuthentication();
    final Map<String, Object> principalDetailsMap = (Map<String, Object>) userAuthentication.getDetails();
    return principalDetailsMap;
  }

  protected OAuth2Authentication authenticateFrom(String accessToken) {
    OAuth2Authentication a = wrappedUserInfoTokenServices.loadAuthentication(accessToken);
    return a;
  }

  @Override
  // TODO: this function could be made Auth-Provider specific in different
  // implementations
  protected Account buildAuthenticatedPrincipalFrom(Map<String, Object> principalDetailsMap) {

    final Account principal = super.buildAuthenticatedPrincipalFrom(principalDetailsMap);

    try {
      principal.setMail(extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_EMAIL, principalDetailsMap));
    } catch (MissingMandatoryTokenAttributeException e) {
      try {
        // fallback for Google:
        @SuppressWarnings("unchecked")
        List<Map<String, String>> emailList = (List<Map<String, String>>) principalDetailsMap.get("emails");

        if (emailList == null || emailList.isEmpty()) {
          throw new MissingMandatoryTokenAttributeException("emails is empty");
        }

        principal.setMail(emailList.get(0).get("value"));
      } catch (Exception ee) {
        throw new MissingMandatoryTokenAttributeException("email, emails");
      }

    }
    try {
      principal.setDisplayName(extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_FULLNAME, principalDetailsMap));
    } catch (MissingMandatoryTokenAttributeException e) {
      // fallback for Google:
      principal.setDisplayName(extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_DISPLAYNAME, principalDetailsMap));
    }

    String externalUserId = extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_USERID, principalDetailsMap);
    String internalUserId = buildPrincipalInternalId(externalUserId);
    principal.setAccountId(internalUserId);

    return principal;
  }

  @Override
  protected List<GrantedAuthority> determineGrantedAuthoritiesFor(Account principal) {
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(Authorities.ROLE_USER,
        Authorities.AUTHENTICATED_VIA_SOCIAL_SITE);
    return grantedAuthorities;
  }

}
