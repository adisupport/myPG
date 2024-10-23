package com.mypg.dtos;

import com.mypg.models.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class GuestResponseDTO extends Guest {
    Long roomValidityInDays;
}
