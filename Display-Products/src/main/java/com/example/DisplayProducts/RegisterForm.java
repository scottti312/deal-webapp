package com.example.DisplayProducts;

public class RegisterForm {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    String clientId = "7cuj1pu58j6n7i1sv7tfhknq8g";
    String secretKey = "1m9k7peq0cdd3t92bpm26skvderdg5ikd546fbvrqda11j0mlls3";
    String userName = firstName + lastName;

    public RegisterForm() {
        super();
    }

    public RegisterForm(String firstName, String lastName, String emailAddress, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
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

}
