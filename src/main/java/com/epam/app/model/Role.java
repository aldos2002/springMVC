package com.epam.app.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Almas_Doskozhin
 * on 5/11/2016.
 */
@Component
@Scope("session")
public class Role {
    String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
