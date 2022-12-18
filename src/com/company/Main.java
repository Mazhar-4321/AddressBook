package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private final int ADD_CONTACT = 1;
    private final int EDIT_CONTACT = 2;
    private final int DELETE_CONTACT = 3;
    private final int PRINT_ADDRESS_BOOK = 4;
    private final int ADD_MULTIPLE_CONTACTS = 5;
    private final int ADD_ADDRESS_BOOK = 6;
    private final int PRINT_ADDRESS_BOOK_DIRECTORY = 7;
    public AddressBook addressBook = new AddressBook();
    public AddressBookDirectory addressBookDirectory = new AddressBookDirectory();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");
        Main main = new Main();
        main.giveUserChoicesToOperate(main);
    }

    //Add Contact to Address Book
    private boolean addContactToAddressBook(AddressBook addressBook) {
        return addressBook.addContact(takeInputFromUserAndCreateContact(null));
    }

    public boolean addContactToAddressBook(AddressBook addressBook, Contact contact) {
        return addressBook.addContact(contact);
    }

    // Edit Contact
    private void editUserDetails() {
        if (addressBookDirectory.getAddressBookMap().size() == 0) {
            System.out.println("Invalid Option As There Are No Address Books , First Create Address book and Try this option");
            return;
        }
        System.out.println("Please Enter First And Last Name to Edit Contact");
        Contact contact = takeInputAndValidateContact();
        if (contact == null) {
            System.out.println("Contact Doesn't Exists With the given First Name and Last Name");
            return;
        }
        updateUserDetailsToContactList(contact);
    }

    private void updateUserDetailsToContactList(Contact contact) {
        System.out.println("Select The Option to Edit: 1 for Editing First Name , 2 for Last Name, 3 For City,4 for State , 5 for zip," +
                "6 for Phone Number , 7 for email,8 for Exit ");
        Integer option = 0;
        while (true) {
            if ((option = ReadInput.getInt()) != null) {
                break;
            }
            System.out.println("Invalid Entry , Enter Valid Number");
        }
        while (true) {
            switch (option) {
                case 1:
                    System.out.println("Enter First Name");
                    contact.setFirstName(scanner.next());
                    break;
                case 2:
                    System.out.println("Enter Last Name");
                    contact.setLastName(scanner.next());
                    break;
                case 3:
                    System.out.println("Enter City Name");
                    contact.setCity(scanner.next());
                    break;
                case 4:
                    System.out.println("Enter State Name");
                    contact.setState(scanner.next());
                    break;
                case 5:
                    System.out.println("Enter Zip Code");
                    contact.setZip(scanner.next());
                    break;
                case 6:
                    System.out.println("Enter Phone Number");
                    contact.setPhoneNumber(scanner.next());
                    break;
                case 7:
                    System.out.println("Enter Email");
                    contact.setEmail(scanner.next());
                    break;
                case 8:
                    return;

            }
            System.out.println("Re enter One Of The Options or Press 8 to exit");
            while (true) {
                if ((option = ReadInput.getInt()) != null) {
                    break;
                }
                System.out.println("Invalid Entry , Enter Valid Number");
            }
        }
    }

    // Delete Contact
    private void deleteUserDetails() {
        if (addressBookDirectory.getAddressBookMap().size() == 0) {
            System.out.println("Invalid Option As There Are No Address Books , First Create Address book and Try this option");
            return;
        }
        System.out.println("Please Enter First And Last Name to Delete Contact");
        Contact contact = takeInputAndValidateContact();
        if (contact == null) {
            System.out.println("Contact Doesn't Exists With the given First Name and Last Name");
            return;
        }
        deleteUserDetailsFromContactDetails(contact);
    }

    private void deleteUserDetailsFromContactDetails(Contact contact) {
        addressBookDirectory.getAddressBookOfContact(contact).deleteContact(contact);
    }

    public boolean deleteUserDetailsFromContactDetailsWithReturnValue(Contact contact) {
        return addressBookDirectory.getAddressBookOfContact(contact).deleteContact(contact);
    }

    //  Print Address Book
    private void printAddressBook() {
        List<String> addressBookNames = new ArrayList<>();
        addressBookDirectory.getAddressBookMap().entrySet().forEach(key -> addressBookNames.add(key.getKey()));
        if (addressBookNames.size() == 0) {
            System.out.println("No Address Book Found, First Create One and try this option");
            return;
        }
        System.out.println("Please Select One Of The Address book Names From Below To Proceed");
        System.out.println(addressBookNames);
        String addressBookName = scanner.next();
        if (addressBookNames.contains(addressBookName)) {
            System.out.println(addressBookDirectory.getAddressBookMap().get(addressBookName));
            return;
        }
        System.out.println("Invalid Name");
    }

    // Validate Contact
    public Contact isContactEditable(String firstName, String lastName) {
        return addressBookDirectory.checkIfNameExistsInTheDirectory(firstName, lastName);
    }

    // Input Contact
    private Contact takeInputAndValidateContact() {
        String firstName = scanner.next();
        String lastName = scanner.next();
        return validateContact(firstName, lastName);
    }

    public Contact validateContact(String firstName, String lastName) {
        return isContactEditable(firstName, lastName);
    }

    private Contact checkIfContactWithNameExists(String firstName, String lastName) {
        return addressBookDirectory.checkIfNameExistsInTheDirectory(firstName, lastName);
    }

    private Contact takeInputFromUserAndCreateContact(Contact contact) {
        System.out.println("Please Enter first Name");
        String firstName = scanner.next();
        System.out.println("Please Enter last Name");
        String lastName = scanner.next();
        while (checkIfContactWithNameExists(firstName, lastName) != null) {
            System.out.println("One Of The Contacts With First Name and Last Name Already Exists");
            System.out.println("Please Enter first Name");
            firstName = scanner.next();
            System.out.println("Please Enter last Name");
            lastName = scanner.next();
        }
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
        if (contact == null)
            return new Contact(firstName, lastName, city, state, zipCode, phoneNumber, email);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setCity(city);
        contact.setState(state);
        contact.setZip(zipCode);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
        return contact;
    }

    // Choice selection
    private void giveUserChoicesToOperate(Main main) {
        while (true) {
            System.out.println("Press 1 to Add Contact , Press 2 to Edit Contact, Press 3 to Delete Contact," +
                    " Press 4 to Print Address Book , \n Press 6 to Add Multiple Address Book ,Press 7 to Print Address Book Directory  and Press any number to exit");
            Integer option = 0;
            while (true) {
                if ((option = ReadInput.getInt()) != null) {
                    break;
                }
                System.out.println("Invalid Entry , Enter Valid Number");
            }
            switch (option) {
                case ADD_CONTACT:
                    main.addContactToAddressBook();
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

    private void addContactToAddressBook() {
        List<String> addressBookNames = new ArrayList<>();
        addressBookDirectory.getAddressBookMap().entrySet().forEach(key -> addressBookNames.add(key.getKey()));
        if (addressBookNames.size() == 0) {
            System.out.println("No Address Book Found, First Create One and try this option");
            return;
        }
        System.out.println("Please Select One Of The Address book Names From Below To Proceed");
        System.out.println(addressBookNames);
        String addressBookName = scanner.next();
        while (!addressBookNames.contains(addressBookName)) {
            System.out.println("Invalid Name");
            System.out.println("Type Address Book Name Again");
            addressBookName = scanner.next();
        }
        addContactToAddressBook(addressBookDirectory.getAddressBookMap().get(addressBookName));
    }

    private void printAddressBookDirectory() {
        System.out.println(addressBookDirectory);
    }

    private void addAddressBook() {
        System.out.println("How Many Address Books You Want to Add");
        Integer number = 0;
        while (true) {
            if ((number = ReadInput.getInt()) != null) {
                break;
            }
            System.out.println("Invalid Entry , Enter Valid Number");
        }
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
        Integer number = 0;
        while (true) {
            if ((number = ReadInput.getInt()) != null) {
                break;
            }
            System.out.println("Invalid Entry , Enter Valid Number");
        }
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
