package com.mypg.services;

import com.mypg.dtos.BookingDTO;
import com.mypg.dtos.HomeResponseDTO;
import com.mypg.exceptions.InvoiceException;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.exceptions.RoomFilledException;
import com.mypg.models.Booking;
import com.mypg.models.Guest;
import com.mypg.models.Room;
import com.mypg.repo.BookingRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingService{
    GuestService guestService;
    RoomService roomService;
    InvoiceService invoiceService;
    BookingRepo bookingRepo;
    BookingService(RoomService roomService, InvoiceService invoiceService, BookingRepo bookingRepo,GuestService guestService){
        this.roomService = roomService;
        this.invoiceService = invoiceService;
        this.bookingRepo = bookingRepo;
        this.guestService=guestService;

    }
    @Transactional
    public void createBooking(BookingDTO dto) throws NoSuchRoom, InvoiceException, RoomFilledException {
        System.out.println("------------DTO Received to Booking Service------------------");
        Guest guest;
        try{
            guest = guestService.saveGuestWithRoom(dto,dto.getRoomNumber());
            System.out.println("------------------Guest Save Successfully----------------");
            Booking booking  = new Booking();
            booking.setRoom(guest.getRoom());
            booking.setRent(dto.getRent());
            booking.setSecurityMoney(dto.getSecurityMoney());
            booking.setGuest(guest);
            booking.setBookingDate(LocalDate.now());
            booking.setCheckIn(dto.getCheckInDate());
            booking.setAdvanceRentPayment(dto.getAdvanceRentPayment());
            System.out.println("-----------------Creating Invoice-----------------------");
            invoiceService.createInvoice(
                    dto.getRent(),
                    dto.getAdvanceRentPayment(),
                    dto.getSecurityMoney(),
                    "",
                    LocalDate.now().getMonth(),
                    LocalDate.now(),
                    guest
                    );
                    System.out.println("----------------Creating Invoice Successfully ----------------");
            System.out.println("-----------------Saving Booking Details---------------------");
            bookingRepo.save(booking);
            System.out.println("-------------Booking Save Successfully------------------");

        }catch (NoSuchRoom e){
            throw new NoSuchRoom("Room With Room Number"+dto.getRoomNumber()+" does not exist");
        }

    }
    public List<Booking> getAllBooking(){
        return bookingRepo.findAll();
    }

    public List<Booking> getBookingByRoom(Room room){
        return bookingRepo.findByRoom(room);
    }
    public List<Booking> getBookingOfMonth(LocalDate date){
        LocalDate firstDay = date.withDayOfMonth(1);
        LocalDate lastDay = date.withDayOfMonth(date.lengthOfMonth());
        return bookingRepo.findByCheckInBetween(firstDay,lastDay);
    }
    public List<Booking> newBookingList(){
        List<Booking> bookings =  bookingRepo.findAll();
        List<Booking> newBooking = new ArrayList<>();
        for(Booking booking:bookings){
            if(booking.getCheckIn().getMonth() == LocalDate.now().getMonth()){
                newBooking.add(booking);
            }
        }
        return newBooking;
    }
    public HomeResponseDTO getHomeResponseDTO(){
        HomeResponseDTO homeResponseDTO = new HomeResponseDTO();
        List<Booking> bookings = newBookingList();
        List<Room> availableRoom = roomService.getAllAvailableRooms();
        List<Guest> stayingGuest = guestService.getAllStayingGuest();
        List<Guest> checkoutGuest = guestService.listOfGuestCheckOut();

        homeResponseDTO.setBookings(bookings);
        homeResponseDTO.setAllBookings(bookingRepo.findAll());
        homeResponseDTO.setAvailableRoom(availableRoom);
        homeResponseDTO.setCheckoutGuest(checkoutGuest);
        homeResponseDTO.setStayingGuest(stayingGuest);
        return homeResponseDTO;
    }
}
