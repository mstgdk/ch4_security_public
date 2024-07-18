package com.patika.service;

import com.patika.entities.Role;
import com.patika.entities.enums.RoleType;
import com.patika.message.ErrorMessage;
import com.patika.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByType(RoleType roleType) {
        Role role =  roleRepository.findByType(roleType).orElseThrow(()->
                new RuntimeException(String.format(
                        ErrorMessage.ROLE_NOT_FOUND_EXCEPTION, roleType.name())));
        return role;
    }
}
