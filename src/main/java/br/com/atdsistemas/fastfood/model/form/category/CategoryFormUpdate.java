package br.com.atdsistemas.fastfood.model.form.category;

import br.com.atdsistemas.fastfood.model.FoodCategory;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryFormUpdate implements Serializable {

    @NotNull @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
