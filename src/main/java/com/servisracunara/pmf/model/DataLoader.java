package com.servisracunara.pmf.model;

import java.util.Arrays;
import java.util.HashSet;

import com.servisracunara.pmf.repository.RoleRepository;
import com.servisracunara.pmf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) {
            return;
        }

        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        createAdminIfNotFound();

        alreadySetup = true;
    }

    private void createAdminIfNotFound() {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User admin = userRepository.findByUsername("admin");

        if (admin == null) {
            User user = new User();

            user.setUsername("admin");
            user.setFullName("Webmaster");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setEmail("admin@gmail.com");
            user.setRoles(new HashSet<>(Arrays.asList(adminRole)));

            userRepository.save(user);
        }
    }

    private Role createRoleIfNotFound(String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }

}

