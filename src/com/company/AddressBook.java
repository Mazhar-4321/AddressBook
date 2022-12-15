package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressBook {
    private List<Contact> contactList;
    private Integer editIndex;

    AddressBook() {
        contactList = new ArrayList<>();
    }

    public List<Contact> getContactList() {
        return contactList;
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
        Optional<Contact> optional = contactList.stream().filter(x -> x.getFirstName().equals(firstName) && x.getLastName().equals(lastName)).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

    @Deprecated
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
        return contactList.stream().filter(x -> x.equals(contact)).count() != 0;
    }

    public void deleteContact(Contact contact) {
        contactList.remove(contact);
    }

    @Override
    public String toString() {
        return "AddressBook{" +
                "contactList=" + contactList +
                '}';
    }
}
