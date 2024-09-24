package com.mypg.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Profile extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String address;
    String phone;
    String email;
    String idProof;
    @OneToOne
    User user;
}
