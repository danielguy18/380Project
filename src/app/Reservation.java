package app;

import java.time.*;

/**
 * The Reservation class represents a reservation made by a customer for a hotel
 * room.
 * It contains information about the check-in and check-out dates, the customer,
 * and the room number.
 * @author NAME
 */
public class Reservation {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Customer customer;
    private int room_number;

    /**
     * Default constructor for Reservation class.
     * Initializes all fields to null or -1.
     */
    public Reservation() {
        this.checkInDate = null;
        this.checkOutDate = null;
        this.customer = null;
        this.room_number = -1;
    }

    /**
     * Constructor for Reservation class.
     * Initializes all fields to the specified values.
     * 
     * @param checkInDate  The check-in date for the reservation.
     * @param checkOutDate The check-out date for the reservation.
     * @param customer     The customer who made the reservation.
     * @param room_number  The room number for the reservation.
     */
    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Customer customer, int room_number) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.customer = customer;
        this.room_number = room_number;
    }

    /**
     * Returns the room number for the reservation.
     * 
     * @return The room number for the reservation.
     */
    public int getRoomNumber() {
        return this.room_number;
    }

    /**
     * Sets the room number for the reservation.
     * 
     * @param room_number The room number for the reservation.
     */
    public void setRoomNumber(int room_number) {
        this.room_number = room_number;
    }

    /**
     * Returns the customer who made the reservation.
     * 
     * @return The customer who made the reservation.
     */
    public Customer getCustomer() {
        return this.customer;
    }

    /**
     * Sets the customer who made the reservation.
     * 
     * @param customer The customer who made the reservation.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Returns the check-in date for the reservation.
     * 
     * @return The check-in date for the reservation.
     */
    public LocalDate getCheckInDate() {
        return this.checkInDate;
    }

    /**
     * Sets the check-in date for the reservation.
     * 
     * @param date The check-in date for the reservation.
     */
    public void setCheckInDate(LocalDate date) {
        this.checkInDate = date;
    }

    /**
     * Returns the check-out date for the reservation.
     * 
     * @return The check-out date for the reservation.
     */
    public LocalDate getCheckOutDate() {
        return this.checkOutDate;
    }

    /**
     * Sets the check-out date for the reservation.
     * 
     * @param date The check-out date for the reservation.
     */
    public void setCheckOutDate(LocalDate date) {
        this.checkOutDate = date;
    }

    /**
     * Checks if the reservation is complete.
     * A reservation is considered complete if it has a customer, room number,
     * check-in date, and check-out date.
     * 
     * @param rsvp The reservation to check.
     * @return True if the reservation is complete, false otherwise.
     */
    public boolean isComplete(Reservation rsvp) {
        if (rsvp.customer == null) {
            return false;
        }
        if (rsvp.room_number == -1) {
            return false;
        }
        if (rsvp.checkInDate == null) {
            return false;
        }
        if (rsvp.checkOutDate == null) {
            return false;
        }
        return true;
    }

    /**
     * Saves the reservation to a file.
     * 
     * @param rsvp The reservation to save.
     * @return The confirmation number for the reservation, or 0 if the reservation
     *         is not complete.
     */
    public int saveReservation(Reservation rsvp) {
        int confirmation = -1;
        if (!(isComplete(rsvp) == true)) {
            return 0;
        }

        return confirmation;
        // TODO save to file

    }

    /**
     * Retrieves a reservation with the specified confirmation number.
     * 
     * @param confirmation The confirmation number for the reservation to retrieve.
     * @return The reservation with the specified confirmation number.
     */
    // TODO
    public Reservation getRSVP(int confirmation) {
        Reservation r = new Reservation();
        return r;
    }
}
