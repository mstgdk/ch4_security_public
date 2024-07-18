package com.patika.entities.enums;

public enum RoleType {
    ROLE_EMPLOYEE("Employee"), // ön tarafa ROLE_CUSTOMER ismiile gitmesini istemiyorum. Güvenlik sebebiyle
    ROLE_ADMIN("Administrator");

    // client tarafına customer  ve Administrator olarak gitmesini istedik.
    private String name;

    private RoleType(String name) { //ROLE_CUSTOMER ve ROLE_ADMIN in name lerini setledik
        this.name=name;
    }


    public String getName(){
        return name;
    }
}
