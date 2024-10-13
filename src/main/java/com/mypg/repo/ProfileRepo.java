package com.mypg.repo;

import com.mypg.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    Optional<Profile> findByMobile(Long mobile);
    Optional<Profile> findByEmail(String email);
    Optional<Profile> findByState(String state);
}
