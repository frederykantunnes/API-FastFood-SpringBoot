package br.com.atdsistemas.fastfood.model.dto.restaurant;

import br.com.atdsistemas.fastfood.model.Address;
import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.Restaurant;

import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

public class RestaurantDTO {

    private Long id;
    private String name;
    private String picture;
    private String phone;
    private String details;
    private String opening_hours;
    private boolean is_open;
    private String address;
    private String reference_point;

    public RestaurantDTO(Restaurant restaurant){
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.picture = restaurant.getPicture();
        this.phone = restaurant.getPhone();
        this.details = restaurant.getDetails();
        this.opening_hours = restaurant.getOpening_hours();
        this.is_open = restaurant.isIs_open();
        Address address = restaurant.getAddress();
        if (address!=null){
            this.address = address.getStreet() + ", "+ address.getNumber() + ", "+address.getNeighborhood() + " - "+address.getCity() + ", "+address.getZipcode();
            this.reference_point = address.getReference_point();
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getPhone() {
        return phone;
    }

    public String getDetails() {
        return details;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public String getAddress() {
        return address;
    }

    public String getReference_point() {
        return reference_point;
    }
}
