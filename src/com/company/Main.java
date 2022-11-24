package com.company;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private AddressBook addressBook = new AddressBook();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        Main main = new Main();
        main.addContactToAddressBook();
        main.printAddressBook();
        main.editUserDetails();
    }

    private void editUserDetails() {
        System.out.println("Please Enter First And Last Name to Edit Contact");
        String firstName = scanner.next();
        String lastName = scanner.next();
        boolean flag = isContactEditable(firstName, lastName);
        if (!flag) {
            System.out.println("Contact Doesn't Exists With the given First Name and Last Name");
            return;
        }
        updateUserDetailsToContactList();
    }

    private void updateUserDetailsToContactList() {
        System.out.println("Please Enter first Name");
        String firstName = scanner.next();
        System.out.println("Please Enter last Name");
        String lastName = scanner.next();
        System.out.println("Please Enter city");
        String city = scanner.next();
        System.out.println("Please Enter State");
        String state = scanner.next();
        System.out.println("Please Enter zip code");
        String zipCode = scanner.next();
        System.out.println("Please Enter phoneNumber");
        String phoneNumber = scanner.next();
        System.out.println("Please Enter Email");
        String email = scanner.next();
        Contact c2 = new Contact(firstName, lastName, city, state, zipCode, phoneNumber, email);
        addressBook.editContactDetails(c2);
    }

    private boolean isContactEditable(String firstName, String lastName) {
        return addressBook.checkIfContactExistsUsingName(firstName, lastName);
    }

    private void printAddressBook() {
        System.out.println(addressBook);
    }

    private void addContactToAddressBook() {
        Contact contact = new Contact("Mazhar", "Ali", "Hyderabad", "500008", "8125629427", "syedmazharali742@gmail.com", "syedmazharali742@gmail.com");
        addressBook.addContact(contact);
    }
}
