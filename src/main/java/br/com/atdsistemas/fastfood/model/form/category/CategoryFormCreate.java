package br.com.atdsistemas.fastfood.model.form.category;

import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.FoodService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryFormCreate implements Serializable {

    @NotNull @NotEmpty
    private String name;
    private MultipartFile picture;
    private long restaurant_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public long getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(long restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public FoodCategory getFoodCategory(String pictureName) {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setPicture(pictureName);
        foodCategory.setName(this.getName());
        return foodCategory;
    }

    @AssertTrue(message = "File must be provided")
    public boolean isFileProvided() {
        return (picture != null) && ( ! picture.isEmpty());
    }
}
