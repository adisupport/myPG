package com.mypg.contollerAdvices;

import com.mypg.models.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/owner/room")
public class RoomController {
    @GetMapping()
    public String room(Model model) {
        model.addAttribute("pageTitle","ROOM PAGE");
        model.addAttribute("rooms", get5Room());
        model.addAttribute("add_room",new Room());
        return "pages/owner/room";
    }

    public List<Room> get5Room(){
        Room[] rooms = new Room[50];
        int n = 101;

        for(int i=0; i<rooms.length; i++){
            rooms[i] = new Room();
            rooms[i].setType("Standard");
            rooms[i].setNumber(n++);
            rooms[i].setFloor(2);
            rooms[i].setNoOfBedEmpty(4);
            rooms[i].setNoOfBeds(4);

        }
        return Arrays.stream(rooms).toList();
    }

}
