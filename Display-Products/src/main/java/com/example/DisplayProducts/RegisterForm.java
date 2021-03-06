package com.example.DisplayProducts;

public class RegisterForm {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String emailConfirmation;

    public RegisterForm() {
        super();
    }

    public RegisterForm(String firstName, String lastName, String emailAddress, String password, String emailConfirmation) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.emailConfirmation = emailConfirmation;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(String emailConfirmation){
        this.emailConfirmation = emailConfirmation;
    }
}
