package ua.donskykh.mybookstore.domain;

import org.springframework.stereotype.Component;

import javax.servlet.Registration;
import java.io.Serializable;
import java.util.Objects;

public class RegistrationModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;
    private Address billing;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getBilling() {
        return billing;
    }

    public void setBilling(Address billing) {
        this.billing = billing;
    }

    @Override
    public String toString() {
        return "RegistrationModel{" + "user=" + user + ", billing=" + billing + '}';
    }
}
