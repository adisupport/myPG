package com.mypg.contollerAdvices;

import com.mypg.exceptions.RoomAlreadyExist;
import com.mypg.models.Room;
import com.mypg.models.RoomStatus;
import com.mypg.services.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/owner/room")
public class RoomController {
    RoomService roomService;
    String errorMessage="";
    RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    @GetMapping()
    public String room(Model model) {
        log.debug("ROOM CONTROLLER CALLED");
        List<Room> rooms = roomService.getRooms();

        model.addAttribute("pageTitle","ROOM PAGE");
        model.addAttribute("rooms",rooms);
        model.addAttribute("room",new Room());
        model.addAttribute("pageName","rooms");
        model.addAttribute("status", Arrays.stream(RoomStatus.values()).toList());
        model.addAttribute("errormessage",errorMessage);
        return "owner/index";
    }


    @PostMapping()
    public String handleForm(Room room,Model model){
        Integer number = room.getNumber();
        Integer floor = room.getFloor();
        Integer noOfBeds = room.getNoOfBeds();
        String type = room.getType();
        RoomStatus status = room.getStatus();
        try {
            roomService.addRoom(number,floor,noOfBeds,type,status);
        }catch (RoomAlreadyExist e){
            // Handle Room Not Exist Exception
            errorMessage = "Room with number" + number+" is already exist.";

        }catch (Exception e){
            errorMessage = e.getMessage();
        }
        return "redirect:/owner/room";
    }
}
