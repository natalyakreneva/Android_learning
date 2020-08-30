package com.natalyakreneva.hometask03;

import androidx.annotation.Nullable;
import java.io.Serializable;
import java.util.Objects;

public class Contacts implements Serializable {
    private String name;
    private String phoneNumber;
    private int image;
    private int id;

    public Contacts(String name, String phoneNumber, int image) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    public Contacts() {
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Contacts)) {
            return false;
        } else {
            Contacts contact = (Contacts)obj;
            return Objects.equals(this.name, contact.name) && Objects.equals(this.phoneNumber, contact.phoneNumber);
        }
    }

    public int hashCode() {
        return super.hashCode();
    }
}
