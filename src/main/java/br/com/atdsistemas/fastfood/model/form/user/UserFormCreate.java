package br.com.atdsistemas.fastfood.model.form.user;

import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.User;
import br.com.atdsistemas.fastfood.model.UserType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public class UserFormCreate {

    private String name;
    private String email;
    private String password;
    private long restaurant_id;
    private String type;


    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getUser() {
        User user = new User();
        user.setName(this.getName());
        user.setPassword(this.password);
        user.setEmail(this.getEmail());
        user.setType(UserType.valueOf(this.getType()));
        return user;
    }
}
