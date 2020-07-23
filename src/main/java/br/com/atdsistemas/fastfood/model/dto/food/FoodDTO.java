package br.com.atdsistemas.fastfood.model.dto.food;

import br.com.atdsistemas.fastfood.model.Additional;
import br.com.atdsistemas.fastfood.model.Food;
import br.com.atdsistemas.fastfood.model.dto.additional.AdditionalDTO;

import java.util.List;
import java.util.stream.Collectors;

public class FoodDTO {

    private long id;
    private String name;
    private String picture;
    private String preparation_time;
    private boolean available;
    private Double price;
    private String details;
    private List<AdditionalDTO> additionalList;

    public FoodDTO(Food food) {
        this.id = food.getId();
        this.name = food.getName();
        this.picture = food.getPicture();
        this.available = food.isAvailable();
        this.preparation_time = food.getPreparation_time();
        this.price = food.getPrice();
        this.additionalList = food.getAdditionals().stream().map(AdditionalDTO::new).collect(Collectors.toList());
        details = food.getDetails();
    }

    public String getDetails() {
        return details;
    }

    public List<AdditionalDTO> getAdditionalList() {
        return additionalList;
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

    public String getPreparation_time() {
        return preparation_time;
    }

    public boolean isAvailable() {
        return available;
    }

    public Double getPrice() {
        return price;
    }
}
