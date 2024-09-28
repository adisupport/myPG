package com.mypg.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{
    String username;
    String password;
    Boolean isBlocked;
    String role;
}
