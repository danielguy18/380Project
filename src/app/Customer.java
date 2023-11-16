package app;

import java.time.*;

/**
 * The Customer class represents a customer with a first name, last name, email,
 * address, and birthday.
 * 
 * @author NAME
 */
public class Customer {
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private LocalDate birthday;

    /**
     * Constructs a new Customer object with null values for all fields.
     */
    public Customer() {
        this.first_name = null;
        this.last_name = null;
        this.email = null;
        this.address = null;
        this.birthday = null;
    }

    /**
     * Returns the first name of the customer.
     * 
     * @return the first name of the customer
     */
    public String getFirstName() {
        return this.first_name;
    }

    /**
     * Sets the first name of the customer.
     * 
     * @param fname the new first name of the customer
     */
    public void setFirstName(String fname) {
        this.first_name = fname;
    }

    /**
     * Returns the last name of the customer.
     * 
     * @return the last name of the customer
     */
    public String getLastName() {
        return this.last_name;
    }

    /**
     * Sets the last name of the customer.
     * 
     * @param lname the new last name of the customer
     */
    public void setLastName(String lname) {
        this.last_name = lname;
    }

    /**
     * Returns the email address of the customer.
     * 
     * @return the email address of the customer
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the email address of the customer.
     * 
     * @param email the new email address of the customer
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the address of the customer.
     * 
     * @return the address of the customer
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address of the customer.
     * 
     * @param address the new address of the customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the birthday of the customer.
     * 
     * @return the birthday of the customer
     */
    public LocalDate getBirthday() {
        return this.birthday;
    }

    /**
     * Sets the birthday of the customer.
     * 
     * @param bday the new birthday of the customer
     */
    public void setBirthday(LocalDate bday) {
        this.birthday = bday;
    }

    /**
     * Returns a string representation of the customer, including their full name,
     * email, birthday, and address. The string representation is formatted as
     * "{@link #first_name} {@link #last_name}<br></br>
     * {@link #email}<br></br>
     * {@link #birthday}<br></br>
     * {@link #address}"
     * 
     * @return a string representation of the customer
     * 
     */
    public String toString() {
        String name = this.first_name + " " + this.last_name + "\n" + this.getEmail() + "\n" + this.birthday.toString()
                + "\n" + this.address;
        return name;
    }

}
