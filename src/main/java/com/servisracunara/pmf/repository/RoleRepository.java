package com.servisracunara.pmf.repository;

import com.servisracunara.pmf.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
