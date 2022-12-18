package test.resources;

import com.company.AddressBook;
import com.company.AddressBookDirectory;
import com.company.Contact;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONTestData {
    private static AddressBookDirectory addressBookDirectory;

    private JSONTestData() {
    }


    public static AddressBookDirectory getAddressBookDirectory() {
        if (addressBookDirectory == null) {
            addressBookDirectory = new AddressBookDirectory();
            loadData();
        }
        return addressBookDirectory;
    }

    private static void loadData() {
        Map<String, AddressBook> addressBookMap = new HashMap<>();
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("D:\\Maven WorkSpace\\AddressBook\\src\\test\\resources\\addresBookDirectory.json")) {
            Object obj = jsonParser.parse(reader);
            Map<String, List<Object>> map = (Map<String, List<Object>>) obj;
            map.values().forEach(k -> {
                JSONArray k1 = (JSONArray) k;
                for (int i = 0; i < k1.size(); i++) {
                    Map<String, List<Object>> stringListMap = (Map<String, List<Object>>) k1.get(i);
                    stringListMap.entrySet().forEach(k9 -> {
                        JSONArray k11 = (JSONArray) k9.getValue();
                        AddressBook addressBook = new AddressBook();
                        addressBookDirectory.addAddressBook(k9.getKey(), addressBook);
                        addressBookMap.put(k9.getKey(), addressBook);
                        for (int j = 0; j < k11.size(); j++) {
                            JSONObject jsonObject = (JSONObject) k11.get(j);
                            Contact contact = new Contact(jsonObject.get("firstName").toString(), jsonObject.get("lastName").toString(),
                                    jsonObject.get("city").toString(), jsonObject.get("state").toString()
                                    , jsonObject.get("zip").toString(), jsonObject.get("phoneNumber").toString(), jsonObject.get("email").toString());
                            addressBook.addContact(contact);
                        }
                    });
                }
            });

        } catch (IOException | ParseException f) {
        }
        addressBookDirectory.addStateWiseContacts();
        addressBookDirectory.addCityWiseContacts();
    }
}
