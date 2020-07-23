package br.com.atdsistemas.fastfood.resource;

import br.com.atdsistemas.fastfood.model.dto.food.FoodDTO;
import br.com.atdsistemas.fastfood.model.dto.food_category.FoodCategoryDTO;
import br.com.atdsistemas.fastfood.model.form.food.FoodFormCreate;
import br.com.atdsistemas.fastfood.model.form.food.FoodFormUpdate;
import br.com.atdsistemas.fastfood.service.FoodServiceFood;
import br.com.atdsistemas.fastfood.util.Directories;
import br.com.atdsistemas.fastfood.util.FilesUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("foods")
public class FoodResource {

    @Autowired
    FoodServiceFood foodService;

    //create
    @PostMapping
    @Transactional
    public ResponseEntity<FoodDTO> create(UriComponentsBuilder builder, @ModelAttribute @Valid FoodFormCreate foodFormCreate) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(foodFormCreate.getPicture(), Directories.FOODS.toString());
        if (upload!=""){
            FoodDTO foodCategoryDTO = foodService.create(foodFormCreate.getFood(upload), foodFormCreate.getCategory());
            URI uri = builder.path("categories/{id}").buildAndExpand(foodCategoryDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(foodCategoryDTO);
        }
        return ResponseEntity.badRequest().build();
    }
    //update
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FoodDTO> update(@RequestBody FoodFormUpdate formUpdate, @PathVariable long id){
        FoodDTO restaurant = foodService.update(formUpdate, id);
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping("/update_image/{id}")
    @Transactional
    public ResponseEntity<FoodDTO> updateImage(@ModelAttribute MultipartFile picture, @PathVariable long id) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(picture, Directories.FOODS.toString());
        if (upload!=""){
            FoodDTO restaurant = foodService.updateImage(upload, id);
            return ResponseEntity.ok().body(restaurant);
        }
        return ResponseEntity.badRequest().build();
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        foodService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
