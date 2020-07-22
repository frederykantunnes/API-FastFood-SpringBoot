package br.com.atdsistemas.fastfood.model.form;

import br.com.atdsistemas.fastfood.model.FoodService;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FoodServiceForm implements Serializable {

    @NotNull @NotEmpty
    private String name;

    private MultipartFile picture;

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

    public FoodService getFoodService(String pictureName) {
        FoodService foodService = new FoodService();
        foodService.setPicture(pictureName);
        foodService.setName(this.getName());
        foodService.setVisible(true);
        return foodService;
    }

    @AssertTrue(message = "File must be provided")
    public boolean isFileProvided() {
        return (picture != null) && ( ! picture.isEmpty());
    }
}
