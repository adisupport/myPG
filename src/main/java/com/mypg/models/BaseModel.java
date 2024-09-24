package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Date createdAt;
    Date updatedAt;
    Date deletedAt;
    Boolean isDeleted;
}
