package br.com.atdsistemas.fastfood.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String picture;
    private String details;
    private Double price;
    private String preparation_time;
    private boolean is_featured;
    private boolean is_combo;
    private boolean available;
    @ManyToOne
    private FoodCategory foodCategory;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Additional> additionals;
    private LocalDateTime created_at = LocalDateTime.now();

    public Food() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Objects.equals(id, food.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }

    public List<Additional> getAdditionals() {
        return additionals;
    }

    public void setAdditionals(List<Additional> additionals) {
        this.additionals = additionals;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
