package br.com.atdsistemas.fastfood.model.form.food;

import br.com.atdsistemas.fastfood.model.Food;
import org.springframework.web.multipart.MultipartFile;

public class FoodFormUpdate {
    private String name;
    private String details;
    private Double price;
    private String preparation_time;
    private boolean is_featured;
    private boolean is_combo;
    private boolean available;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(String preparation_time) {
        this.preparation_time = preparation_time;
    }

    public boolean isIs_featured() {
        return is_featured;
    }

    public void setIs_featured(boolean is_featured) {
        this.is_featured = is_featured;
    }

    public boolean isIs_combo() {
        return is_combo;
    }

    public void setIs_combo(boolean is_combo) {
        this.is_combo = is_combo;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}
