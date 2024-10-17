package com.mypg.services;

import com.mypg.dtos.BookingDTO;
import com.mypg.exceptions.NoSuchRoom;
import com.mypg.models.Booking;
import com.mypg.models.Guest;
import com.mypg.repo.BookingRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


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
    public void createBooking(BookingDTO dto) throws NoSuchRoom {
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
            booking.setCheckIn(dto.getCheckInDate());
            booking.setAdvanceRentPayment(dto.getAdvanceRentPayment());
            System.out.println("-----------------Creating Invoice-----------------------");
            invoiceService.createInvoice(dto.getRent(),dto.getAdvanceRentPayment(), LocalDate.now(),guest);
            System.out.println("----------------Creating Invoice Successfully ----------------");
            System.out.println("-----------------Saving Booking Details---------------------");
            bookingRepo.save(booking);
            System.out.println("-------------Booking Save Successfully------------------");

        }catch (NoSuchRoom e){
            throw new NoSuchRoom("Room With Room Number"+dto.getRoomNumber()+" does not exist");
        }

    }
}
