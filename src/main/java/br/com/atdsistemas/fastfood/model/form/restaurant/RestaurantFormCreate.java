package br.com.atdsistemas.fastfood.model.form.restaurant;

import br.com.atdsistemas.fastfood.model.Address;
import br.com.atdsistemas.fastfood.model.Restaurant;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class RestaurantFormCreate {

    private MultipartFile picture;
    @NotNull @NotEmpty
    private String phone;

    private String details;

    @NotNull @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String opening_hours;

    private boolean visible;

    private boolean is_open;

    @NotNull @NotEmpty
    private String street;

    private String number;

    @NotNull @NotEmpty
    private String neighborhood;

    @NotNull @NotEmpty
    private String city;
    private String zipcode;

    @NotNull @NotEmpty
    private String reference_point;


    @AssertTrue(message = "File must be provided")
    public boolean isFileProvided() {
        return (picture != null) && ( ! picture.isEmpty());
    }

    public Restaurant getRestaurant(String picture){
        Restaurant restaurant = new Restaurant();
        restaurant.setDetails(this.getDetails());
        restaurant.setIs_open(this.is_open);
        restaurant.setName(this.getName());
        restaurant.setOpening_hours(this.opening_hours);
        restaurant.setPhone(this.getPhone());
        restaurant.setPicture(picture);
        restaurant.setVisible(this.visible);
        Address address = new Address();
        address.setCity(this.getCity());
        address.setReference_point(this.getReference_point());
        address.setNeighborhood(this.getNeighborhood());
        address.setNumber(this.getNumber());
        address.setStreet(this.getStreet());
        address.setZipcode(this.getZipcode());
        restaurant.setAddress(address);
        return restaurant;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getReference_point() {
        return reference_point;
    }

    public void setReference_point(String reference_point) {
        this.reference_point = reference_point;
    }
}
