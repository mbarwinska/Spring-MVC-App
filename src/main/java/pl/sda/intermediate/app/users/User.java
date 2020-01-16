package pl.sda.intermediate.app.users;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {

    private long serialVersionUID = 134587564634L;

    private String firstName;
    private String lastName;
    private String eMail;
    private String passwordHash;
    private String city;
    private String country;
    private String zipCode;
    private String street;
    private String birthDate;
    private String pesel;
    private String phone;
    private Boolean preferEmails;
}
