package spring.hotel.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import spring.hotel.common.persistance.to.Account;
import spring.hotel.model.Oauth2ClientResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

/**
 * Determines User Info by decoding information from the OAuth Access Token
 * (Json Web Token format).
 * 
 * @author Richard_Pal
 */
public class CorporateJsonWebTokenUserInfoDecodingServices extends AbstractUserInfoTokenServices {

  private static final String WEBTOKENATTR_EMAIL = "email";
  private static final String WEBTOKENATTR_FULLNAME = "unique_name";
  private static final String WEBTOKENATTR_USERID = "upn"; // == e-mail

  protected final Log logger = LogFactory.getLog(getClass());

  public CorporateJsonWebTokenUserInfoDecodingServices(Oauth2ClientResources oauth2ClientResources) {
    super(oauth2ClientResources);
  }

  @Override
  protected Map<String, Object> getPrincipalDetails(String accessToken) {
    notNull(accessToken, "access token can not be null");

    logger.debug("access token: " + accessToken);
    Jwt jwt = JwtHelper.decode(accessToken);

    String jwtClaims = jwt.getClaims();
    logger.debug("claims: " + jwtClaims);

    return getPrincipalMap(jwtClaims);
  }

  private Map<String, Object> getPrincipalMap(String jwtClaims) {

    notNull(jwtClaims, "Json Web Token claim string can not be null");

    Map<String, Object> resultMap = new HashMap<>();

    try {
      ObjectMapper mapper = new ObjectMapper();

      // convert JSON string to Map
      resultMap = mapper.readValue(jwtClaims, new TypeReference<Map<String, String>>() {
      });

    } catch (IOException e) {
      throw new UnableToExtractPrincipalInfoFromJsonWebTokenException(e);
    }
    return resultMap;
  }

  protected Account buildAuthenticatedPrincipalFrom(Map<String, Object> principalDetailsMap) {

    final Account principal = super.buildAuthenticatedPrincipalFrom(principalDetailsMap);
    principal.setMail(extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_EMAIL, principalDetailsMap));
    principal.setDisplayName(extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_FULLNAME, principalDetailsMap));

    String externalUserId = extractAndAssertMandatoryUserDetailValue(WEBTOKENATTR_USERID, principalDetailsMap);
    String internalUserId = buildPrincipalInternalId(externalUserId);
    principal.setAccountId(internalUserId);

    return principal;
  }

//  protected List<GrantedAuthority> determineGrantedAuthoritiesFor(Account principal) {
//    List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(Authorities.ROLE_USER,
//        Authorities.AUTHENTICATED_VIA_CORPORATE_SITE);
//    return grantedAuthorities;
//  }

  class UnableToExtractPrincipalInfoFromJsonWebTokenException extends RuntimeException {
    private static final long serialVersionUID = 1080837820030279676L;

    public UnableToExtractPrincipalInfoFromJsonWebTokenException(Throwable t) {
      super(t);
    }
  }

}
