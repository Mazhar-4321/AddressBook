package test;

import com.company.AddressBookDirectory;
import com.company.Contact;
import org.junit.Assert;
import org.junit.Test;
import test.resources.JSONTestData;

public class AddressBookTestEdgeCase {
    private static AddressBookDirectory addressBookDirectory = JSONTestData.getAddressBookDirectory();

    @Test
    public void when_UnAvailableContactDeleted_MustReturnFalse() {
        Assert.assertEquals(false, addressBookDirectory.deleteContactFromAddressBook(new Contact("a", "a", "a", "a", "a", "a", "a")));
        Assert.assertEquals(true, addressBookDirectory.deleteContactFromAddressBook(new Contact("Mazhar", "Ali", "a", "a", "a", "a", "a")));
    }
}
