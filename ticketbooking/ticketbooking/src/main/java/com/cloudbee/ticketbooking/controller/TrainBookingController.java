package com.cloudbee.ticketbooking.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cloudbee.ticketbooking.dto.Ticket;
import com.cloudbee.ticketbooking.exception.InvalidSeatSelectionException;
import com.cloudbee.ticketbooking.exception.InvalidTicketRequestException;
import com.cloudbee.ticketbooking.exception.TicketNotFoundException;
import com.cloudbee.ticketbooking.exception.UserNotFoundException;
import com.cloudbee.ticketbooking.service.TrainService;

@RestController
@RequestMapping("/train")
public class TrainBookingController {

    private final TrainService trainService = new TrainService();

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Ticket ticket) {
        System.out.println("TrainBookingController :: purchaseTicket called with ticket : " + ticket.toString());
        try {
            return ResponseEntity.ok(trainService.purchaseTicket(ticket));
        } catch(InvalidTicketRequestException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch(InvalidSeatSelectionException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/ticket-details")
    public ResponseEntity<?> getTicketDetails(@RequestParam String userName) {
        System.out.println("TrainBookingController :: getTicketDetails called with userName : " + userName);
        try {
            return ResponseEntity.ok(trainService.getTicketDetails(userName));
        } catch(TicketNotFoundException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @GetMapping("/users-and-seats")
    public ResponseEntity<?> getUsersAndSeats(@RequestParam String seatSection) {
        System.out.println("TrainBookingController :: getUsersAndSeats called with seatSection : " + seatSection);
        try {
            return ResponseEntity.ok(trainService.getUsersAndSeats(seatSection));
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @DeleteMapping("/remove-user")
    public ResponseEntity<?> removeUser(@RequestParam String userName) {
        System.out.println("TrainBookingController :: removeUser called with userName : " + userName);
        try {
            trainService.removeUser(userName);
            return ResponseEntity.ok("User removed succesfully");
        } catch(UserNotFoundException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @PutMapping("/modify-seat")
    public ResponseEntity<?> modifyUserSeat(@RequestParam String userName, @RequestParam String newSeatSection) {
        System.out.println("TrainBookingController :: modifyUserSeat called with userName : " + userName + " | newSeatSection : " + newSeatSection);
        try {
            trainService.modifyUserSeat(userName, newSeatSection);
            return ResponseEntity.ok("Sear Modified succesfully");
        } catch(TicketNotFoundException exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

}
