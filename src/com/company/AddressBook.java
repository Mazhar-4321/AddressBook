package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressBook {
    private List<Contact> contactList;

    public AddressBook() {
        contactList = new ArrayList<>();
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public boolean addContact(Contact contact) {
        if (contact == null) {
            System.out.println("Unable to Add");
            return false;
        }
        if (isContactListed(contact)) {
            System.out.println("Contact Already Exists");
            return false;
        }
        contactList.add(contact);
        return true;

    }

    private boolean isContactListed(Contact contact) {
        return contactList.stream().filter(x -> x.equals(contact)).count() != 0;
    }

    public boolean deleteContact(Contact contact) {
        return contactList.remove(contact);
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "contactList=" + contactList +
                '}';
    }
}
