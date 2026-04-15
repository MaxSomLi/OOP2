package com.max.avia2spring.service;

import com.max.avia2spring.model.CrewMember;
import com.max.avia2spring.repository.CrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${admin.login}")
    private String adminLogin;

    @Value("${admin.password}")
    private String adminPassword;

    private final CrewMemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (adminLogin.equals(username)) {
            return User.withUsername(adminLogin).password("{noop}" + adminPassword).roles("2").build();
        }
        CrewMember crew = repository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("Not found."));
        return User.withUsername(crew.getName()).password("{bcrypt}" + crew.getPassword()).roles(crew.isAdmin() ? "1" : "0").build();
    }
}
