package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBookDirectory {
    private static Map<String, AddressBook> addressBookMap;

    AddressBookDirectory() {
        addressBookMap = new HashMap<>();
    }

    public Map<String, AddressBook> getAddressBookMap() {
        return addressBookMap;
    }

    public boolean checkForAddressBook(String name) {
        return addressBookMap.containsKey(name);
    }

    public void addAddressBook(String name, AddressBook addressBook) {
        addressBookMap.put(name, addressBook);
    }

    public AddressBook getAddressBookOfContact(Contact contact) {
        List<AddressBook> addressBookList = new ArrayList<>();
        addressBookMap.entrySet().forEach(key -> {
            List<Contact> list = key.getValue().getContactList().stream().filter(y -> y.getFirstName().equals(contact.getFirstName()) && y.getLastName().equals(contact.getLastName())).collect(Collectors.toList());
            if (list != null && addressBookList.size() == 0) {
                addressBookList.add(key.getValue());
            }
        });
        return addressBookList.get(0);
    }

    public Contact checkIfNameExistsInTheDirectory(String firstName, String lastName) {
        List<Contact> contactList = new ArrayList<>();
        addressBookMap.entrySet().forEach(key -> {
            List<Contact> list = key.getValue().getContactList().stream().filter(y -> y.getFirstName().equals(firstName) && y.getLastName().equals(lastName)).collect(Collectors.toList());
            if (list != null) {
                contactList.addAll(list);
                return;
            }
        });
        return contactList.size() == 0 ? null : contactList.get(0);
    }

    @Override
    public String toString() {
        return "AddressBookDirectory{" +
                "addressBookMap=" + addressBookMap +
                '}';
    }
}
