package br.com.atdsistemas.fastfood.resource;

import br.com.atdsistemas.fastfood.model.FoodCategory;
import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.dto.category.CategoryDetailsDTO;
import br.com.atdsistemas.fastfood.model.dto.food_category.FoodCategoryDTO;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDetailsDTO;
import br.com.atdsistemas.fastfood.model.form.FoodServiceForm;
import br.com.atdsistemas.fastfood.model.form.category.CategoryFormCreate;
import br.com.atdsistemas.fastfood.model.form.category.CategoryFormUpdate;
import br.com.atdsistemas.fastfood.service.FoodCategoryService;
import br.com.atdsistemas.fastfood.util.Directories;
import br.com.atdsistemas.fastfood.util.FilesUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("categories")
public class CategoryResource {

    @Autowired
    FoodCategoryService foodCategoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDetailsDTO> findById(@PathVariable long id, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(foodCategoryService.findById(id, pageable));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FoodCategoryDTO> create(UriComponentsBuilder builder, @ModelAttribute @Valid CategoryFormCreate categoryFormCreate) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(categoryFormCreate.getPicture(), Directories.FOODSCATEGORY.toString());
        if (upload!=""){
            FoodCategoryDTO foodCategoryDTO = foodCategoryService.create(categoryFormCreate.getFoodCategory(upload), categoryFormCreate.getRestaurant_id());
            URI uri = builder.path("categories/{id}").buildAndExpand(foodCategoryDTO.getId()).toUri();
            return ResponseEntity.created(uri).body(foodCategoryDTO);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FoodCategoryDTO> updateOpenClosed(@RequestBody CategoryFormUpdate formUpdate, @PathVariable long id){
        FoodCategoryDTO restaurant = foodCategoryService.update(formUpdate.getName(), id);
        return ResponseEntity.ok().body(restaurant);
    }


    @PostMapping("/update_image/{id}")
    @Transactional
    public ResponseEntity<FoodCategoryDTO> updateImage(@ModelAttribute MultipartFile picture, @PathVariable long id) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(picture, Directories.FOODSCATEGORY.toString());
        if (upload!=""){
            FoodCategoryDTO restaurant = foodCategoryService.updateImage(upload, id);
            return ResponseEntity.ok().body(restaurant);
        }
        return ResponseEntity.badRequest().build();
    }

}
