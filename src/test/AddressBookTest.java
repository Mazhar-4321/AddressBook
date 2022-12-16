package test;

import com.company.AddressBook;
import com.company.AddressBookDirectory;
import com.company.Contact;
import com.company.Main;
import org.junit.Assert;
import org.junit.Test;

public class AddressBookTest {
    private Main main = new Main();

    public AddressBookTest() {
        AddressBookDirectory addressBookDirectory = new AddressBookDirectory();
        AddressBook addressBook = new AddressBook();
        addressBook.addContact(new Contact("a", "a", "a", "a", "a", "a", "a"));
        addressBook.addContact(new Contact("b", "b", "b", "b", "b", "b", "b"));
        addressBookDirectory.addAddressBook("Mazhar", addressBook);
        main.addressBookDirectory = addressBookDirectory;
    }

    @Test
    public void when_SameContactExistsInTheBook_MustReturnFalse() {
        Assert.assertEquals(false, main.addContactToAddressBook(main.addressBookDirectory.getAddressBookMap().get("Mazhar"), new Contact("a", "a", "a", "a", "a", "a", "a")));
        Assert.assertEquals(true, main.addContactToAddressBook(main.addressBookDirectory.getAddressBookMap().get("Mazhar"), new Contact("a", "b", "a", "a", "a", "a", "a")));
    }

    @Test
    public void when_InsertNullContactInTheBook_MustReturnFalse() {
        Assert.assertEquals(false, main.addContactToAddressBook(main.addressBookDirectory.getAddressBookMap().get("Mazhar"), null));
    }
    @Test
    public void when_AddressBookNameExistInTheDirectory_MustReturnTrue() {
        Assert.assertEquals(true, main.addressBookDirectory.getAddressBookMap().get("Mazhar")!=null);
        Assert.assertEquals(false, main.addressBookDirectory.getAddressBookMap().get("Azhar")!=null);
    }
    @Test
    public void when_AddressBookNameIsNull_MustReturnFalse() {
        Assert.assertEquals(false, main.addressBookDirectory.getAddressBookMap().get(null)!=null);
        Assert.assertEquals(false, main.addressBookDirectory.getAddressBookMap().get("")!=null);
    }
    @Test
    public void when_UnAvailableContactEdited_MustReturnFalse() {
        Assert.assertEquals(false, main.validateContact("mazhar","ali")!=null);
        Assert.assertEquals(true, main.validateContact("a","a")!=null);
    }
    @Test
    public void when_UnAvailableContactDeleted_MustReturnFalse() {
        Assert.assertEquals(true, main.deleteUserDetailsFromContactDetailsWithReturnValue(new Contact("a", "a", "a", "a", "a", "a", "a")));
        Assert.assertEquals(false, main.deleteUserDetailsFromContactDetailsWithReturnValue(new Contact("abb", "abb", "a", "a", "a", "a", "a")));
    }
    
}
