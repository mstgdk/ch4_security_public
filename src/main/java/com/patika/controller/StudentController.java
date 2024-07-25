package com.patika.controller;


import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RolesAllowed({"ROLE_ADMIN"})
@RequestMapping("/students")
public class StudentController {

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/save")
    public ResponseEntity<String> save(){
        return ResponseEntity.ok("save API çalıştı");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public ResponseEntity<String> delete(){
        return ResponseEntity.ok("delete API çalıştı");
    }

    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    @GetMapping("/update")
    public ResponseEntity<String> update(){
        return ResponseEntity.ok("update API çalıştı");
    }

}
