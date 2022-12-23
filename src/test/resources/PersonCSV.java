package test.resources;

import com.company.Contact;

import java.util.Objects;

public class PersonCSV {
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;
    private String category;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonCSV(){

}

    public PersonCSV(String firstName, String lastName, String city, String state, String zip, String phoneNumber, String email, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.category = category;
    }

    public PersonCSV(Contact contact, String category) {
        super();
        this.firstName = contact.getFirstName();
        this.lastName=contact.getLastName();
       this. city=contact.getCity();
        this.state=contact.getState();
       this. zip=contact.getZip();
        this.phoneNumber=contact.getPhoneNumber();
        this.email=contact.getEmail();
        this.category = category;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonCSV personCSV = (PersonCSV) o;
        return Objects.equals(firstName, personCSV.firstName) && Objects.equals(lastName, personCSV.lastName) && Objects.equals(city, personCSV.city) && Objects.equals(state, personCSV.state) && Objects.equals(zip, personCSV.zip) && Objects.equals(phoneNumber, personCSV.phoneNumber) && Objects.equals(email, personCSV.email) && Objects.equals(category, personCSV.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, city, state, zip, phoneNumber, email, category);
    }

    @Override
    public String toString() {
        return firstName + ","+lastName+","+city+","+state+","+zip+","+phoneNumber+","+email+"," + category;
    }
}
