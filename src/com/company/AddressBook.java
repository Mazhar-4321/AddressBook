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

    @Deprecated
    public boolean checkIfContactExistsUsingName(String firstName, String lastName) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    public Contact checkIfContactExistsUsingNameAndReturnContact(String firstName, String lastName) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                return contact;
            }
        }
        return null;
    }

    public void editContactDetails(Contact contact) {
        if (editIndex != null) {
            contactList.set(editIndex, contact);
            editIndex = null;
        }
    }

    @Deprecated
    public void deleteContactDetails() {
        if (editIndex != null) {
            contactList.remove(contactList.get(editIndex));
            editIndex = null;
        }
    }

    public void deleteContactDetails(Contact contact) {
        contactList.remove(contact);
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
