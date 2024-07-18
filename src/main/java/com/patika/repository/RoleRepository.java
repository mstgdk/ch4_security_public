package com.patika.repository;

import com.patika.entities.Role;
import com.patika.entities.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByType(RoleType roleType);

}
