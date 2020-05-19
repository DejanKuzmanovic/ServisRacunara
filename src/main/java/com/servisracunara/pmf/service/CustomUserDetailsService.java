package com.servisracunara.pmf.service;

import com.servisracunara.pmf.model.Role;
import com.servisracunara.pmf.model.User;
import com.servisracunara.pmf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
@Transactional
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exist " + username);
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), true, true, true, true, getAuthorities(user.getRoles()));
    }

    private Set<GrantedAuthority> getAuthorities(final Collection<Role> roles) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        roles.forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));

        return grantedAuthorities;
    }

}

