package com.mypg.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mypg.models.User;

public interface UserRepo extends JpaRepository<User,Long> {

}
