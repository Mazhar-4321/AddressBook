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
        boolean observedResult = false;
        try {
            addressBookDirectory.deleteContactFromAddressBook(new Contact("a", "a", "a", "a", "a", "a", "a"));
        } catch (CustomException c) {
            observedResult = c.getMessage().equals("No AddressBook Associated With This Contact") ? false : true;
        }
        Assert.assertEquals(false, observedResult);
        Assert.assertEquals(true, addressBookDirectory.deleteContactFromAddressBook(new Contact("Mazhar", "Ali", "a", "a", "a", "a", "a")));
    }

}
