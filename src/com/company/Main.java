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
        main.printAddressBook();
        main.deleteUserDetails();
        main.printAddressBook();
    }

    private void deleteUserDetails() {
        System.out.println("Please Enter First And Last Name to Delete Contact");
        if (!takeInputAndValidateContact()) {
            System.out.println("Contact Doesn't Exists With the given First Name and Last Name");
            return;
        }
        deleteUserDetailsFromContactDetails();
    }

    private void deleteUserDetailsFromContactDetails() {
        addressBook.deleteContactDetails();
    }

    private void editUserDetails() {
        System.out.println("Please Enter First And Last Name to Edit Contact");
        if (!takeInputAndValidateContact()) {
            System.out.println("Contact Doesn't Exists With the given First Name and Last Name");
            return;
        }
        updateUserDetailsToContactList();
    }

    private void updateUserDetailsToContactList() {
        addressBook.editContactDetails(takeInputFromUserAndCreateContact());
    }

    private boolean isContactEditable(String firstName, String lastName) {
        return addressBook.checkIfContactExistsUsingName(firstName, lastName);
    }

    private void printAddressBook() {
        System.out.println(addressBook);
    }

    private void addContactToAddressBook() {
        addressBook.addContact(takeInputFromUserAndCreateContact());
    }
    private boolean takeInputAndValidateContact(){
        String firstName = scanner.next();
        String lastName = scanner.next();
        return isContactEditable(firstName, lastName);
    }
    private Contact takeInputFromUserAndCreateContact(){
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
        return new Contact(firstName, lastName, city, state, zipCode, phoneNumber, email);
    }
}
