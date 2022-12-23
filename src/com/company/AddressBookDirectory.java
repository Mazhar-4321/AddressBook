package com.company;

import test.CustomException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookDirectory {
    private static List<String> optionsList = Arrays.asList("name", "city", "state", "zip");
    private Map<String, AddressBook> addressBookMap;
    private Map<String, List<Contact>> cityPersonsMap;
    private Map<String, List<Contact>> statePersonsMap;

    public AddressBookDirectory() {
        addressBookMap = new HashMap<>();
        cityPersonsMap = new HashMap<>();
        statePersonsMap = new HashMap<>();
    }

    public List<Contact> findByCity(String cityName) {
        if (cityName == null) {
            return null;
        }
        return cityPersonsMap.get(cityName);
    }

    private Map<String, List<Contact>> addStateOrCityWiseContacta(String option) {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(contact -> option.equals("city") ? contact.getCity() : contact.getState()));
    }

    public void addCityWiseContacts() {
        cityPersonsMap = addStateOrCityWiseContacta("city");
    }

    public void addStateWiseContacts() {
        statePersonsMap = addStateOrCityWiseContacta("state");
    }

    public List<Contact> findByState(String stateName) {
        if (stateName == null) {
            return null;
        }
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
                .orElseThrow(() -> new CustomException("No AddressBook Associated With This Contact"));
    }

    public Contact checkIfNameExistsInTheDirectory(String firstName, String lastName) {
        return addressBookMap.values()
                .stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .filter(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName))
                .findFirst()
                .orElseThrow(() -> new CustomException("No Contact Exists With The Given First Name And Last Name"));
    }

    public int getNoOfPersonsInACity(String cityName) {
        if (cityName == null) {
            return 0;
        }
        List<Contact> contactList = cityPersonsMap.get(cityName);
        return contactList == null ? 0 : contactList.size();
    }

    public int getNoOfPersonsInAState(String stateName) {
        if (stateName == null) {
            return 0;
        }
        List<Contact> contactList = statePersonsMap.get(stateName);
        return contactList == null ? 0 : contactList.size();
    }

    public boolean deleteContactFromAddressBook(Contact contact) {
        if (contact == null) {
            return false;
        }
        AddressBook addressBook = getAddressBookOfContact(contact);
        if (addressBook == null) {
            return false;
        }
        return addressBook.deleteContact(contact);
    }

    public List<Contact> sortAddressBook(String addressBookName, String option) {
        if (addressBookName == null || option == null || !optionsList.contains(option)) {
            return null;
        }
        AddressBook addressBook = addressBookMap.get(addressBookName);
        if (addressBook == null) {
            return null;
        }
        List<Contact> contactList = addressBook.getContactList()
                .stream()
                .sorted(getComparator(option))
                .collect(Collectors.toList());
        return contactList;
    }

    private Comparator<Contact> getComparator(String option) {
        return (contact1, contact2) -> {
            if (option.equals("name")) {
                return (contact1.getFirstName() + " " + contact1.getLastName().toLowerCase()).compareTo(contact2.getFirstName() + " " + contact2.getLastName());
            }
            if (option.equals("state")) {
                return (contact1.getState().toLowerCase()).compareTo(contact2.getState().toLowerCase());
            }
            if (option.equals("city")) {
                return (contact1.getCity().toLowerCase()).compareTo(contact2.getCity().toLowerCase());
            }
            Integer i1 = Integer.parseInt(contact1.getZip());
            Integer i2 = Integer.parseInt(contact2.getZip());
            return i1.compareTo(i2);
        };
    }

    public boolean writeContactIntoFile(String fileName, Contact contact) {
        File file = new File(fileName);
        FileWriter fr = null;
        String text = contact.getFirstName() + "," + contact.getLastName() + "," + contact.getCity() +
                "," + contact.getState() + "," + contact.getZip() + "," + contact.getPhoneNumber() + ","
                + contact.getEmail();
        try {
            fr = new FileWriter(file, true);
            fr.write(text);
            fr.write("\r\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "AddressBookDirectory{" +
                "addressBookMap=" + addressBookMap +
                '}';
    }
}
