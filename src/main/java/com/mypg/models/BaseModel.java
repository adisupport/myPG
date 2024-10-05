package com.mypg.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @CreatedDate
    Date createdAt;
    @LastModifiedDate
    Date updatedAt;
    @LastModifiedDate
    Date deletedAt;
    Boolean isDeleted;
}
