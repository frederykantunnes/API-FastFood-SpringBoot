package br.com.atdsistemas.fastfood.model.dto.food_service;


import br.com.atdsistemas.fastfood.model.FoodService;

public class FoodServiceDTO {

    private Long id;
    private String name;
    private String picture;

    public FoodServiceDTO(FoodService foodService) {
        this.id = foodService.getId();
        this.name = foodService.getName();
        this.picture = foodService.getPicture();
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public Long getId() {
        return id;
    }

}
