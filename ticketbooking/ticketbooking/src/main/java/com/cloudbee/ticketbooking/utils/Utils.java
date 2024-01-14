package com.cloudbee.ticketbooking.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.cloudbee.ticketbooking.dto.Ticket;
import com.cloudbee.ticketbooking.exception.InvalidTicketRequestException;

public class Utils {
    
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,16}$";

    
    public static void validateTicketRequest(Ticket ticket) throws InvalidTicketRequestException {
        
        if(ticket == null) {
            throw new InvalidTicketRequestException("Ticket Request is not Valid to process");
        }

        if(StringUtils.isEmpty(ticket.getFrom())) {
            throw new InvalidTicketRequestException("From Details Cannot be empty");
        }

        if(StringUtils.isEmpty(ticket.getTo())) {
            throw new InvalidTicketRequestException("To Details Cannot be empty");
        }

        if(StringUtils.isEmpty(ticket.getSeatSection())) {
            throw new InvalidTicketRequestException("Seat Section Cannot be empty");
        }

        if(StringUtils.isEmpty(ticket.getUserEmail())) {
            throw new InvalidTicketRequestException("User Email Cannot be empty");
        }

        if(!isValidEmail(ticket.getUserEmail())) {
            throw new InvalidTicketRequestException("User Email is Not Valid");
        }

        if(StringUtils.isEmpty(ticket.getUserName())) {
            throw new InvalidTicketRequestException("User Name Cannot be empty");
        }

        if(!isValidUsername(ticket.getUserName())) {
            throw new InvalidTicketRequestException("User Name is Not Valid");
        }

        if(ticket.getPrice() == null) {
            throw new InvalidTicketRequestException("Price Cannot be empty");
        }
    }

    private static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }
}
