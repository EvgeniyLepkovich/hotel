package spring.hotel.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.hotel.common.persistance.dao.AccountDao;
import spring.hotel.common.persistance.dao.RoleDao;
import spring.hotel.common.persistance.to.Account;
import spring.hotel.common.persistance.to.Role;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Fene4ka_ on 13.11.2016.
 */
@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountDao.getAccountByUsername(username);
        List<GrantedAuthority> auths = getListAuth(account.getRoles());
        return getUserDetails(account, auths);
    }

    private UserDetails getUserDetails(Account account, List<GrantedAuthority> auths){
        return new User(account.getUsername(), account.getPassword(), true, true, true, true, auths);
    }

    private List<GrantedAuthority> getListAuth(List<Role> roles){
        List<GrantedAuthority> auths = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getNameRole().toUpperCase())).collect(Collectors.toList());
        return auths;
    }
}
