package com.epam.electives.security;

import com.epam.electives.dao.UserDao;
import com.epam.electives.dao.impl.UserDaoImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // get user by username
        UserDaoImpl userDao = new UserDaoImpl();
        String password = userDao.findUserByLogin(username).getPassword();
        if (null == password) {
            throw new UsernameNotFoundException("User " + username + " not exists");
        }
        // get user roles and build user authorities
        List<String> roles = userDao.getUserRoles(username);
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new GrantedAuthorityImpl(role));
        }
        // instanciate Spring Security class User
        return new UserDetailsImpl(username, password, authorities);
    }
}
