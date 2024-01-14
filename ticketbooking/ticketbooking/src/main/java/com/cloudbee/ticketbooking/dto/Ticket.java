package com.cloudbee.ticketbooking.dto;

public class Ticket {

    private String from;
    private String to;
    private String userName;
    private String userEmail;
    private Double price;
    private String seatSection;
    private int seatNumber;

    public Ticket(){}

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSeatSection() {
        return seatSection;
    }

    public void setSeatSection(String seatSection) {
        this.seatSection = seatSection;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket [from=" + from + ", to=" + to + ", userName=" + userName + ", userEmail=" + userEmail
                + ", price=" + price + ", seatSection=" + seatSection + ", seatNumber=" + seatNumber + "]";
    }

}
