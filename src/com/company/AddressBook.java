package com.company;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<Contact> contactList;
    private Integer editIndex;

    AddressBook() {
        contactList = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if (isContactListed(contact)) {
            System.out.println("Contact Already Exists");
            return;
        }
        contactList.add(contact);
    }

    public boolean checkIfContactExistsUsingName(String firstName, String lastName) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getFirstName().equals(firstName) && contactList.get(i).getLastName().equals(lastName)) {
                editIndex = i;
                return true;
            }
        }
        return false;
    }

    public void editContactDetails(Contact contact) {
        if (editIndex != null) {
            contactList.set(editIndex, contact);
            editIndex = null;
        }
    }

    public void deleteContactDetails() {
        if (editIndex != null) {
            contactList.remove(contactList.get(editIndex));
            editIndex = null;
        }
    }

    private boolean isContactListed(Contact contact) {
        for (Contact x : contactList) {
            if (x.equals(contact)) {
                System.out.println("Contact Already Exists");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "contactList=" + contactList +
                '}';
    }
}
