package br.com.atdsistemas.fastfood.resource;

import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.Restaurant;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDTO;
import br.com.atdsistemas.fastfood.model.dto.restaurant.RestaurantDetailsDTO;
import br.com.atdsistemas.fastfood.model.form.FoodServiceForm;
import br.com.atdsistemas.fastfood.model.form.additional.AdditionalForm;
import br.com.atdsistemas.fastfood.model.form.restaurant.RestaurantFormCreate;
import br.com.atdsistemas.fastfood.model.form.restaurant.RestaurantFormEdit;
import br.com.atdsistemas.fastfood.service.AdditionalService;
import br.com.atdsistemas.fastfood.service.RestaurantService;
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
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantResource {

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    AdditionalService additionalService;

    @GetMapping
    public ResponseEntity<Page<RestaurantDTO>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(restaurantService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDetailsDTO> findById(@PathVariable long id){
        RestaurantDetailsDTO restaurantDetailsDTO = restaurantService.findById(id);
        if (restaurantDetailsDTO!=null){
            return ResponseEntity.ok().body(restaurantDetailsDTO);
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping
    @Transactional
    public ResponseEntity<RestaurantDetailsDTO> create(UriComponentsBuilder builder, @ModelAttribute @Valid RestaurantFormCreate restaurantFormCreate) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(restaurantFormCreate.getPicture(), Directories.FOODSCATEGORY.toString());
        if (upload!=""){
            RestaurantDetailsDTO restaurant = restaurantService.create(restaurantFormCreate.getRestaurant(upload));
            URI uri = builder.path("restaurants/{id}").buildAndExpand(restaurant.getId()).toUri();
            return ResponseEntity.created(uri).body(restaurant);
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<RestaurantDetailsDTO> update(@RequestBody @Valid RestaurantFormEdit restaurantFormEdit, @PathVariable long id){
        RestaurantDetailsDTO restaurant = restaurantService.update(restaurantFormEdit, id);
        return ResponseEntity.ok().body(restaurant);
    }

    @PutMapping("/open/{id}/{is_open}")
    @Transactional
    public ResponseEntity<RestaurantDetailsDTO> updateOpenClosed(@PathVariable boolean is_open, @PathVariable long id){
        RestaurantDetailsDTO restaurant = restaurantService.updateOpenClosed(is_open, id);
        return ResponseEntity.ok().body(restaurant);
    }


    @PostMapping("/update_image/{id}")
    @Transactional
    public ResponseEntity<RestaurantDetailsDTO> updateImage(@ModelAttribute MultipartFile picture, @PathVariable long id) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(picture, Directories.FOODSCATEGORY.toString());
        if (upload!=""){
            RestaurantDetailsDTO restaurant = restaurantService.updateImage(upload, id);
            return ResponseEntity.ok().body(restaurant);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/{id}/additionals")
    @Transactional
    public ResponseEntity<RestaurantDetailsDTO> createAdditional(@RequestBody AdditionalForm additionalForm, @PathVariable long id){
        RestaurantDetailsDTO additional = additionalService.createAdditional(additionalForm.getAdditional(), id);
        return ResponseEntity.ok().body(additional);
    }

    @DeleteMapping("/additionals/{id}")
    @Transactional
    public ResponseEntity<Void> deleteAdditional(@PathVariable long id){
        additionalService.deleteAdditional(id);
        return ResponseEntity.noContent().build();
    }
}
