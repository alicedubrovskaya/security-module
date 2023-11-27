package com.dubrouskaya.secret.service;

import com.dubrouskaya.secret.model.UserEntity;
import com.dubrouskaya.secret.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("UserEntity not found!");
        }

        String[] authorities = user.getUserAuthorities().split(";");

        return User.withUsername(user.getUserName())
                .password(user.getUserPassword())
                .authorities(authorities)
                .build();
    }
}
