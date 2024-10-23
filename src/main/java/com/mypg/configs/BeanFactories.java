package com.mypg.configs;

import com.mypg.models.Room;
import com.mypg.repo.RoomRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

//@Configuration
//public class BeanFactories {
//    RoomRepo repo;
//    BeanFactories(RoomRepo repo) {
//        this.repo = repo;
//    }
//
//    public Set<Room> getAllRoom(){
//        return new HashSet<>(repo.findAll());
//    }
//}
