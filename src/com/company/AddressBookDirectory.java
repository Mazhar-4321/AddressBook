package com.company;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
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
        return cityPersonsMap.get(cityName);
    }

    public void addCityWiseContacts() {
        cityPersonsMap = addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getCity));
    }

    public void addStateWiseContacts() {
        statePersonsMap = addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getState));
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
        return statePersonsMap.get(stateName);
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
        return addressBookMap.values()
                .stream()
                .filter(addressBook -> addressBook.getContactList().stream().filter(c -> c.equals(contact)).count() != 0)
                .findFirst()
                .get();
    }

    public Contact checkIfNameExistsInTheDirectory(String firstName, String lastName) {
        Optional<Contact> optionalContact = addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .filter(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName))
                .findFirst();
        return optionalContact.isPresent() ? optionalContact.get() : null;
    }

    public int getNoOfPersonsInACity(String cityName) {
        return cityPersonsMap.get(cityName).size();
    }

    public int getNoOfPersonsInAState(String stateName) {
        return statePersonsMap.get(stateName).size();
    }

    public void sortAddressBookByPersonsName(String addressBookName) {
        AddressBook addressBook = addressBookMap.get(addressBookName);
        if (addressBook == null) {
            System.out.println("Invalid Name");
            return;
        }
        List<Contact> collect = addressBook.getContactList()
                .stream()
                .sorted((contact1, contact2) -> (contact1.getFirstName() + " " + contact1.getLastName().toLowerCase()).compareTo(contact2.getFirstName() + " " + contact2.getLastName()))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    public void sortAddressBookByCity(String addressBookName){
        AddressBook addressBook = addressBookMap.get(addressBookName);
        if (addressBook == null) {
            System.out.println("Invalid Name");
            return;
        }
        List<Contact> collect = addressBook.getContactList()
                .stream()
                .sorted(Comparator.comparing(contact -> (contact.getCity().toLowerCase())))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    public void sortAddressBookByState(String addressBookName){
        AddressBook addressBook = addressBookMap.get(addressBookName);
        if (addressBook == null) {
            System.out.println("Invalid Name");
            return;
        }
        List<Contact> collect = addressBook.getContactList()
                .stream()
                .sorted(Comparator.comparing(contact -> (contact.getState().toLowerCase())))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
    public void sortAddressBookByZip(String addressBookName){
        AddressBook addressBook = addressBookMap.get(addressBookName);
        if (addressBook == null) {
            System.out.println("Invalid Name");
            return;
        }
        List<Contact> collect = addressBook.getContactList()
                .stream()
                .sorted(Comparator.comparing(contact -> (contact.getZip().toLowerCase())))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "AddressBookDirectory{" +
                "addressBookMap=" + addressBookMap +
                '}';
    }
}
