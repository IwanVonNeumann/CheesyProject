package look.proxy;

import domain.Address;
import domain.Title;

/**
 * Created by IRuskevich on 08.05.2014
 */
public class AddressViewProxy {

    private Address address;

    public AddressViewProxy(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }


    public String getTitle() {
        return address.getTitle().toString();
    }

    public String getName() {
        return address.getName();
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getCity() {
        return address.getCity();
    }

    public Integer getZipCode() {
        return address.getZipCode();
    }


    public void setTitle(String title) {
        address.setTitle(Title.getConstant(title));
    }

    public void setName(String name) {
        address.setName(name);
    }

    public void setStreet(String street) {
        address.setStreet(street);
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    public void setZipCode(Integer zipCode) {
        address.setZipCode(zipCode);
    }
}