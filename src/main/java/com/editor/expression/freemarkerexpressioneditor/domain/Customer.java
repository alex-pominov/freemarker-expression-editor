package com.editor.expression.freemarkerexpressioneditor.domain;

public class Customer {
    private String name;
    private String address;
    private String phoneNumber;
    private String hobbies;

    public Customer(String name, String address, String phoneNumber, String hobbies) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getHobbies() {
        return hobbies;
    }
}
