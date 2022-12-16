package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBookDirectory {
    private static Map<String, AddressBook> addressBookMap;
    private static Map<String, List<Contact>> cityPersonsMap;
    private static Map<String, List<Contact>> statePersonsMap;

    public AddressBookDirectory() {
        addressBookMap = new HashMap<>();
        cityPersonsMap = new HashMap<>();
        statePersonsMap = new HashMap<>();
    }

    public List<Contact> findByCity(String cityName) {
        List<Contact> contactList = new ArrayList<>();
        addressBookMap.entrySet().forEach(key -> {
            List<Contact> list = key.getValue().getContactList().stream().filter(y -> y.getCity().equals(cityName)).collect(Collectors.toList());
            if (list != null) {
                contactList.addAll(list);
            }
        });
        return contactList;
    }

    public void addCityWiseContacts() {
        Set<String> cities = new HashSet<>();
        addressBookMap.entrySet().forEach(k -> {
            k.getValue().getContactList().forEach(y -> cities.add(y.getCity()));
        });
        cities.stream().forEach(k -> cityPersonsMap.put(k, findByCity(k)));
    }

    public void addStateWiseContacts() {
        Set<String> states = new HashSet<>();
        addressBookMap.entrySet().forEach(k -> {
            k.getValue().getContactList().forEach(y -> {
                states.add(y.getState());
                System.out.println(y.getState() + "   ");
            });
        });
        states.stream().forEach(k -> statePersonsMap.put(k, findByState(k)));
    }

    public void printStatePersonsMap() {
        System.out.println("State to Persons List");
        statePersonsMap.entrySet().forEach(k -> {
            System.out.println(k.getKey() + ":");
            k.getValue().forEach(y -> {
                System.out.println(y.getFirstName() + " " + y.getLastName());
            });
            System.out.println();
        });
    }

    public void printCityPersonsMap() {
        System.out.println("City to Persons List");
        cityPersonsMap.entrySet().forEach(k -> {
            System.out.println(k.getKey() + ":");
            k.getValue().forEach(y -> {
                System.out.println(y.getFirstName() + " " + y.getLastName());
            });
            System.out.println();
        });
    }

    public List<Contact> findByState(String stateName) {
        List<Contact> contactList = new ArrayList<>();
        addressBookMap.entrySet().forEach(key -> {
            List<Contact> list = key.getValue().getContactList().stream().filter(y -> y.getState().equals(stateName)).collect(Collectors.toList());
            if (list != null) {
                contactList.addAll(list);
            }
        });
        return contactList;
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

    public Long getNoOfPersonsInACity(String cityName) {
        return cityPersonsMap.entrySet().stream().filter(k -> k.getKey().equals(cityName)).count();
    }

    public Long getNoOfPersonsInAState(String stateName) {
        return statePersonsMap.entrySet().stream().filter(k -> k.getKey().equals(stateName)).count();
    }

    @Override
    public String toString() {
        return "AddressBookDirectory{" +
                "addressBookMap=" + addressBookMap +
                '}';
    }
}
