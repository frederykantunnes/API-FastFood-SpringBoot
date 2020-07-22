package br.com.atdsistemas.fastfood.model.dto.food_category;

import br.com.atdsistemas.fastfood.model.FoodCategory;

public class FoodCategoryDTO {

    private long id;
    private String name;
    private String picture;

    public FoodCategoryDTO(FoodCategory foodCategory) {
        this.id = foodCategory.getId();
        this.name = foodCategory.getName();
        this.picture = foodCategory.getPicture();
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
}
