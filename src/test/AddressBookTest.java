package test;

import com.company.AddressBookDirectory;
import com.company.Contact;
import org.junit.Assert;
import org.junit.Test;
import test.resources.JSONTestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddressBookTest {
    private static AddressBookDirectory addressBookDirectory = JSONTestData.getAddressBookDirectory();

    public AddressBookTest() {
    }

    @Test
    public void when_SearchByAvailableCity_MustReturnPersonsList() {
        List<Contact> observedContactList = addressBookDirectory.findByCity("Hyderabad");
        List<Contact> expectedContactList = Arrays.asList(
                new Contact("Mazhar", "Ali", "Hyderabad", "Telangana", "500008", "8125629427", "syedmazharali742@gmail.com"),
                new Contact("Jaison", "Adidela", "Hyderabad", "Telangana", "500001", "7013770364", "jaisonadidela@gmail.com")
        );
        Assert.assertArrayEquals(expectedContactList.toArray(), observedContactList.toArray());
    }

    @Test
    public void when_SearchByUnAvailableCity_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.findByCity("Firozabad");
        Assert.assertEquals(null, observedContactList);
    }

    @Test
    public void when_SearchByNullAsCity_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.findByCity(null);
        Assert.assertEquals(null, observedContactList);
    }

    @Test
    public void when_SearchForCountOfPersonsInAvailableCity_MustReturnExpectedCount() {
        int observedContactList = addressBookDirectory.getNoOfPersonsInACity("Hyderabad");
        int expectedResult = 2;
        Assert.assertEquals(expectedResult, observedContactList);
    }

    @Test
    public void when_SearchForCountOfPersonsInUnAvailableCity_MustReturnZero() {
        int observedContactList = addressBookDirectory.getNoOfPersonsInACity("Firozabad");
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, observedContactList);
    }

    @Test
    public void when_SearchForCountOfPersonsWithNullCity_MustReturnZero() {
        int observedContactList = addressBookDirectory.getNoOfPersonsInACity(null);
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, observedContactList);
    }

    @Test
    public void when_SearchForCountOfPersonsInAvailableState_MustReturnExpectedCount() {
        int observedContactList = addressBookDirectory.getNoOfPersonsInAState("Telangana");
        int expectedResult = 2;
        Assert.assertEquals(expectedResult, observedContactList);
    }

    @Test
    public void when_SearchForCountOfPersonsInUnAvailableState_MustReturnZero() {
        int observedContactList = addressBookDirectory.getNoOfPersonsInAState("Assam");
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, observedContactList);
    }

    @Test
    public void when_SearchForCountOfPersonsWithNullState_MustReturnZero() {
        int observedContactList = addressBookDirectory.getNoOfPersonsInAState(null);
        int expectedResult = 0;
        Assert.assertEquals(expectedResult, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByNameInAddressBook_MustReturnPersonsListInLexicalOrder() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("students", "name");
        List<Contact> expectedContactList = Arrays.asList(
                new Contact("Jaison", "Adidela", "Hyderabad", "Telangana", "500001", "7013770364", "jaisonadidela@gmail.com"),
                new Contact("Mazhar", "Ali", "Hyderabad", "Telangana", "500008", "8125629427", "syedmazharali742@gmail.com"),
                new Contact("Prashik", "Kamble", "akola", "Maharastra", "444001", "8806187589", "prashikkamble@gmail.com"),
                new Contact("Priyanka", "Shinde", "Nasik", "Maharastra", "420003", "9518905320", "priyankashinde@gmail.com"),
                new Contact("Ratnadip", "Bharde", "amaravathi", "Maharastra", "444601", "8983253934", "ratnadipbharde@gmail.com")
        );
        Assert.assertArrayEquals(expectedContactList.toArray(), observedContactList.toArray());
    }

    @Test
    public void when_SearchForPersonsSortedByNameInUnAvailableAddressBook_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("managers", "name");
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByNameInNullAddressBook_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook(null, "name");
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByNameAddressBookWithNullOption_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("students", null);
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByNameAddressBookWithInvalidOption_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("students", "post");
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByCityInAddressBook_MustReturnPersonsListInLexicalOrder() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("students", "city");
        List<Contact> expectedContactList = Arrays.asList(
                new Contact("Prashik", "Kamble", "akola", "Maharastra", "444001", "8806187589", "prashikkamble@gmail.com"),
                new Contact("Ratnadip", "Bharde", "amaravathi", "Maharastra", "444601", "8983253934", "ratnadipbharde@gmail.com"),
                new Contact("Mazhar", "Ali", "Hyderabad", "Telangana", "500008", "8125629427", "syedmazharali742@gmail.com"),
                new Contact("Jaison", "Adidela", "Hyderabad", "Telangana", "500001", "7013770364", "jaisonadidela@gmail.com"),
                new Contact("Priyanka", "Shinde", "Nasik", "Maharastra", "420003", "9518905320", "priyankashinde@gmail.com"));
        Assert.assertArrayEquals(expectedContactList.toArray(), observedContactList.toArray());
    }

    @Test
    public void when_SearchForPersonsSortedByCityInUnAvailableAddressBook_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("managers", "city");
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByStateInAddressBook_MustReturnPersonsListInLexicalOrder() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("students", "state");
        List<Contact> expectedContactList = Arrays.asList(
                new Contact("Prashik", "Kamble", "akola", "Maharastra", "444001", "8806187589", "prashikkamble@gmail.com"),
                new Contact("Ratnadip", "Bharde", "amaravathi", "Maharastra", "444601", "8983253934", "ratnadipbharde@gmail.com"),
                new Contact("Priyanka", "Shinde", "Nasik", "Maharastra", "420003", "9518905320", "priyankashinde@gmail.com"),
                new Contact("Mazhar", "Ali", "Hyderabad", "Telangana", "500008", "8125629427", "syedmazharali742@gmail.com"),
                new Contact("Jaison", "Adidela", "Hyderabad", "Telangana", "500001", "7013770364", "jaisonadidela@gmail.com")
        );
        Assert.assertArrayEquals(expectedContactList.toArray(), observedContactList.toArray());
    }

    @Test
    public void when_SearchForPersonsSortedByStateInUnAvailableAddressBook_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("managers", "state");
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_SearchForPersonsSortedByZipInAddressBook_MustReturnPersonsListInLexicalOrder() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("students", "zip");
        List<Contact> expectedContactList = Arrays.asList(
                new Contact("Priyanka", "Shinde", "Nasik", "Maharastra", "420003", "9518905320", "priyankashinde@gmail.com"),
                new Contact("Prashik", "Kamble", "akola", "Maharastra", "444001", "8806187589", "prashikkamble@gmail.com"),
                new Contact("Ratnadip", "Bharde", "amaravathi", "Maharastra", "444601", "8983253934", "ratnadipbharde@gmail.com"),
                new Contact("Jaison", "Adidela", "Hyderabad", "Telangana", "500001", "7013770364", "jaisonadidela@gmail.com"),
                new Contact("Mazhar", "Ali", "Hyderabad", "Telangana", "500008", "8125629427", "syedmazharali742@gmail.com")
        );
        Assert.assertArrayEquals(expectedContactList.toArray(), observedContactList.toArray());
    }

    @Test
    public void when_SearchForPersonsSortedByZipInUnAvailableAddressBook_MustReturnNull() {
        List<Contact> observedContactList = addressBookDirectory.sortAddressBook("managers", "zip");
        List<Contact> expectedContactList = null;
        Assert.assertEquals(expectedContactList, observedContactList);
    }

    @Test
    public void when_AddingContactWithSameNameInTheBook_MustReturnFalse() {
        boolean result = addressBookDirectory.getAddressBookMap().get("students").addContact(new Contact("Mazhar", "Ali",
                "Nagpur", "Karnataka", "898089", "1234567890", "siyiua@gckag.com"));
        Assert.assertEquals(false, result);
    }

    @Test
    public void when_InsertNullContactInTheBook_MustReturnFalse() {
        Assert.assertEquals(false, addressBookDirectory.getAddressBookMap().get("students").addContact(null));
    }

    @Test
    public void when_AddressBookNameExistInTheDirectory_MustReturnTrue() {
        Assert.assertEquals(true, addressBookDirectory.getAddressBookMap().get("students") != null);
        Assert.assertEquals(false, addressBookDirectory.getAddressBookMap().get("Azhar") != null);
    }

    @Test
    public void when_AddressBookNameIsNull_MustReturnFalse() {
        Assert.assertEquals(false, addressBookDirectory.getAddressBookMap().get(null) != null);
        Assert.assertEquals(false, addressBookDirectory.getAddressBookMap().get("") != null);
    }

    @Test
    public void when_UnAvailableContactEdited_MustReturnFalse() {
        boolean observedResult=false;
        try{
            addressBookDirectory.checkIfNameExistsInTheDirectory("firstName", "lastName");
        }
        catch(CustomException c){
            observedResult=c.getMessage().equals("Invalid Entry")?false:true;
        }
        Assert.assertEquals(false, observedResult);
        Assert.assertEquals(true, addressBookDirectory.checkIfNameExistsInTheDirectory("Mazhar", "Ali") != null);
    }


}
