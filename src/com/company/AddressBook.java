package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contactList;

    AddressBook() {
        contactList = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        for (Contact x : contactList) {
            if (x.equals(contact)) {
                System.out.println("Contact Already Exists");
                return;
            }
        }
        contactList.add(contact);
    }
}
