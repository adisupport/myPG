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
import com.mypg.repo.GuestRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService{
    private final GuestRepo guestRepo;
    GuestService guestService;
    RoomService roomService;
    InvoiceService invoiceService;
    BookingRepo bookingRepo;
    BookingService(RoomService roomService, InvoiceService invoiceService, BookingRepo bookingRepo, GuestService guestService, GuestRepo guestRepo){
        this.roomService = roomService;
        this.invoiceService = invoiceService;
        this.bookingRepo = bookingRepo;
        this.guestService=guestService;
        this.guestRepo = guestRepo;
    }
    @Transactional
    public void createBooking(BookingDTO dto) throws NoSuchRoom, InvoiceException, RoomFilledException {
        System.out.println("------------DTO Received to Booking Service------------------");
        try{
            Optional<Guest> optionalguest = guestService.getGuestByMobile(dto.getMobile());
            Guest guest = null;
            if(optionalguest.isEmpty()){
                guest = guestService.saveGuestWithRoom(dto,dto.getRoomNumber());
            }else{
                guest = optionalguest.get();
            }

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
            if(guest.getInvoices()==null || guest.getInvoices().isEmpty()){
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
            }
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
