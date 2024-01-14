package com.cloudbee.ticketbooking.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cloudbee.ticketbooking.dto.Ticket;
import com.cloudbee.ticketbooking.exception.InvalidSeatSelectionException;
import com.cloudbee.ticketbooking.exception.InvalidTicketRequestException;
import com.cloudbee.ticketbooking.exception.TicketNotFoundException;
import com.cloudbee.ticketbooking.exception.UserNotFoundException;
import com.cloudbee.ticketbooking.utils.Utils;

@Service
public class TrainService {

    private Map<String, Ticket> tickets = new HashMap<>();
    private int nextSeatNumberA = 1;
    private int nextSeatNumberB = 1;

    public Ticket purchaseTicket(Ticket ticketRequest) throws InvalidTicketRequestException, InvalidSeatSelectionException {
        System.out.println("TrainService :: purchaseTicket called with ticketRequest : " + ticketRequest.toString());

        Utils.validateTicketRequest(ticketRequest);

        Ticket ticket = new Ticket();
        ticket.setFrom(ticketRequest.getFrom());
        ticket.setTo(ticketRequest.getTo());
        ticket.setUserName(ticketRequest.getUserName());
        ticket.setUserEmail(ticketRequest.getUserEmail());
        ticket.setPrice(ticketRequest.getPrice());
        ticket.setSeatSection(ticketRequest.getSeatSection());

        if ("A".equalsIgnoreCase(ticketRequest.getSeatSection())) {
            ticket.setSeatNumber(nextSeatNumberA++);
        } else if ("B".equalsIgnoreCase(ticketRequest.getSeatSection())) {
            ticket.setSeatNumber(nextSeatNumberB++);
        } else {
            throw new InvalidSeatSelectionException("Seat Selection is Invalid!!! It should be either A or B");
        }

        tickets.put(ticketRequest.getUserName(), ticket);

        return ticket;
    }

    public Ticket getTicketDetails(String userName) throws TicketNotFoundException {
        System.out.println("TrainService :: getTicketDetails called with userName : " + userName);
        Ticket ticket = tickets.get(userName);
        if(ticket == null) {
            throw new TicketNotFoundException("Ticket not found for given UserName : " + userName);
        }
        return ticket;
    }

    public Map<String, Ticket> getUsersAndSeats(String seatSection) {
        System.out.println("TrainService :: getUsersAndSeats called with seatSection : " + seatSection);
        Map<String, Ticket> result = new HashMap<>();
        for (Ticket ticket : tickets.values()) {
            if (ticket.getSeatSection().equalsIgnoreCase(seatSection)) {
                result.put(ticket.getUserName(), ticket);
            }
        }
        return result;
    }

    public void removeUser(String userName) throws UserNotFoundException {
        System.out.println("TrainService :: removeUser called with userName : " + userName);

        if(!tickets.keySet().contains(userName)) {
            throw new UserNotFoundException("Unable to remove user. Since User with userName : " + userName + " not found ");
        }

        tickets.remove(userName);
    }

    public boolean modifyUserSeat(String userName, String newSeatSection) throws TicketNotFoundException {
        System.out.println("TrainService :: modifyUserSeat called with userName : " + userName + " | newSeatSection : " + newSeatSection);
        Ticket ticket = tickets.get(userName);
        if (ticket == null) {
            throw new TicketNotFoundException("Ticket not found for given UserName : " + userName);
        }

        // Remove from the current section
        if ("A".equalsIgnoreCase(ticket.getSeatSection())) {
            nextSeatNumberA--;
        } else {
            nextSeatNumberB--;
        }

        // Assign a new seat in the requested section
        ticket.setSeatSection(newSeatSection);
        if ("A".equalsIgnoreCase(newSeatSection)) {
            ticket.setSeatNumber(nextSeatNumberA++);
        } else {
            ticket.setSeatNumber(nextSeatNumberB++);
        }

        return true;
    }
}
