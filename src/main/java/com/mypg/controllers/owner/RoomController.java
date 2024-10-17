package com.mypg.controllers.owner;

import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomAlreadyExist;
import com.mypg.models.Room;
import com.mypg.models.RoomStatus;
import com.mypg.services.RoomService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/owner/room")
public class RoomController {
    RoomService roomService;
    RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    private static final Logger log = LoggerFactory.getLogger(RoomController.class);

    @GetMapping()
    public String room(Model model,HttpSession session) {
        log.debug("ROOM CONTROLLER CALLED");
        String errorMessage = "";
        if(session.getAttribute("errorMessage")!=null)
             errorMessage= session.getAttribute("errorMessage").toString();
        List<Room> rooms = roomService.getRooms();

        model.addAttribute("pageTitle","ROOM PAGE");
        model.addAttribute("rooms",rooms);
        model.addAttribute("room",new Room());
        model.addAttribute("pageName","room");
        model.addAttribute("status", Arrays.stream(RoomStatus.values()).toList());
        model.addAttribute("error",errorMessage);
        return "owner/index";
    }

    @PostMapping()
    public String handleForm(Room room,Model model,HttpSession session){
        Integer number = room.getNumber();
        Integer floor = room.getFloor();
        Integer noOfBeds = room.getNoOfBeds();
        String type = room.getType();
        RoomStatus status = room.getStatus();
        Integer noOfEmptyBeds = room.getNoOfBedEmpty();
        try {
            roomService.addRoom(number,floor,noOfBeds,type,status,noOfEmptyBeds);
        }catch (RoomAlreadyExist e){
            // Handle Room Not Exist Exception
            session.setAttribute("errorMessage","Room with number" + number+" is already exist.");
        }catch (Exception e){
            session.setAttribute("errorMessage",e.getMessage());
        }
        return "redirect:/owner/room";
    }
    @GetMapping("/{id}")
    public String getRoomDetails(@PathVariable Integer id, Model model) throws NoSuchRoom {
        Room room = roomService.getRoom(id);
        model.addAttribute("room",room);
        return "/owner/pages/view_room";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String noSuchRoom(NoSuchElementException exception,Model model){
        return "/owner/exception";
    }

    @ExceptionHandler(NoSuchRoom.class)
    public String noSuchRoom(NoSuchRoom exception, Model model, HttpSession session){
        session.setAttribute("errorMessage","error: Room,You are looking for not exists");
        return "/owner/room";
    }
}
