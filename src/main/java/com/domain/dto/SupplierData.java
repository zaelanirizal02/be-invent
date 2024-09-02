package com.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class SupplierData {

    @NotEmpty (message = "name is required")
    private String name;

    @NotEmpty (message = "address is required")
    private String address;

    @NotEmpty (message = "email is required")
    @Email(message = "email is not valid")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
