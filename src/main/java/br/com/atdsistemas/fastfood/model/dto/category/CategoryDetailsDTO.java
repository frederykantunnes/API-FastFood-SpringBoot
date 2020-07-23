package br.com.atdsistemas.fastfood.model.dto.category;

import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.dto.food.FoodDTO;
import org.springframework.data.domain.Page;

public class CategoryDetailsDTO {
    private long id;
    private String name;
    private String picture;
    private String restaurant_name;
    private String restaurant_picture;
    Page<FoodDTO> foods;

    public CategoryDetailsDTO(FoodCategory foodCategory, Page<FoodDTO> foods) {
        this.foods = foods;
        this.id = foodCategory.getId();
        this.name = foodCategory.getName();
        this.picture = foodCategory.getPicture();
        this.restaurant_name = foodCategory.getRestaurant().getName();
        this.restaurant_picture = foodCategory.getRestaurant().getPicture();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public String getRestaurant_picture() {
        return restaurant_picture;
    }

    public Page<FoodDTO> getFoods() {
        return foods;
    }
}
