package com.company;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private final int ADD_CONTACT = 1;
    private final int EDIT_CONTACT = 2;
    private final int DELETE_CONTACT = 3;
    private final int PRINT_ADDRESS_BOOK = 4;
    private final int ADD_MULTIPLE_CONTACTS = 5;
    private final int ADD_ADDRESS_BOOK = 6;
    private final int PRINT_ADDRESS_BOOK_DIRECTORY = 7;
    private AddressBook addressBook = new AddressBook();
    private AddressBookDirectory addressBookDirectory = new AddressBookDirectory();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        Main main = new Main();
        main.giveUserChoicesToOperate(main);
    }

    //Add Contact to Address Book
    private void addContactToAddressBook(AddressBook addressBook) {
        addressBook.addContact(takeInputFromUserAndCreateContact());
    }

    // Edit Contact
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

    // Delete Contact
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

    //  Print Address Book
    private void printAddressBook() {
        System.out.println(addressBook);
    }

    // Validate Contact
    private boolean isContactEditable(String firstName, String lastName) {
        return addressBook.checkIfContactExistsUsingName(firstName, lastName);
    }

    // Input Contact
    private boolean takeInputAndValidateContact() {
        String firstName = scanner.next();
        String lastName = scanner.next();
        return isContactEditable(firstName, lastName);
    }

    private Contact takeInputFromUserAndCreateContact() {
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

    // Choice selection
    private void giveUserChoicesToOperate(Main main) {
        while (true) {
            System.out.println("Press 1 to Add Contact , Press 2 to Edit Contact, Press 3 to Delete Contact," +
                    " Press 4 to Print Address Book , Press 5 to Add Multiple Contacts At a Time , Press 6 to Add Multiple Address Book ,Press 7 to Print Address Book Directory  and Press any number to exit");
            int option = scanner.nextInt();
            switch (option) {
                case ADD_CONTACT:
                    main.addContactToAddressBook(this.addressBook);
                    break;
                case EDIT_CONTACT:
                    main.editUserDetails();
                    break;
                case DELETE_CONTACT:
                    main.deleteUserDetails();
                    break;
                case PRINT_ADDRESS_BOOK:
                    printAddressBook();
                    break;
                case ADD_MULTIPLE_CONTACTS:
                    main.addMultipleContactsToAddressBook(0);
                    break;
                case ADD_ADDRESS_BOOK:
                    main.addAddressBook();
                    break;
                case PRINT_ADDRESS_BOOK_DIRECTORY:
                    main.printAddressBookDirectory();
                    break;
                default:
                    return;
            }
        }
    }

    private void printAddressBookDirectory() {
        System.out.println(addressBookDirectory);
    }

    private void addAddressBook() {
        System.out.println("Enter a Number to add Adress Books");
        int number = scanner.nextInt();
        int i = 1;
        while (i <= number) {
            System.out.printf("Enter Address Book %d information\n", i);
            System.out.println("Enter a Unique Key to add address Book");
            String key = scanner.next();
            if (addressBookDirectory.checkForAddressBook(key)) {
                System.out.println("The name already exists with one of the Address Books");
                continue;
            }
            i++;
            addressBookDirectory.addAddressBook(key, addMultipleContactsToAddressBook(1));
        }


    }

    private AddressBook addMultipleContactsToAddressBook(int offset) {
        System.out.println("Enter Number of Contacts You Want to add to Address Book");
        int number = scanner.nextInt();
        AddressBook addressBook = new AddressBook();
        for (int i = 1; i <= number; i++) {
            System.out.printf("Enter Contact Details of Person %d\n", i);
            if (offset == 0) {
                addContactToAddressBook(this.addressBook);
            }
            if (offset == 1) {
                addContactToAddressBook(addressBook);
            }
        }
        if (offset == 0) {
            return this.addressBook;
        }
        return addressBook;
    }
}
