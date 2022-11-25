package com.company;

import java.util.HashMap;
import java.util.Map;

public class AddressBookDirectory {
    private Map<String, AddressBook> addressBookMap;

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

    @Override
    public String toString() {
        return "AddressBookDirectory{" +
                "addressBookMap=" + addressBookMap +
                '}';
    }
}
