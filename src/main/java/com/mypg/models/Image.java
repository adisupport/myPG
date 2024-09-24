package com.mypg.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image extends BaseModel {
    String imgURL;
}
