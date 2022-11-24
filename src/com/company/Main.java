package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        Contact contact = new Contact("Mazhar", "Ali", "Hyderabad", "500008", "8125629427", "syedmazharali742@gmail.com","syedmazharali742@gmail.com");
        AddressBook addressBook = new AddressBook();
        addressBook.addContact(contact);

    }
}
