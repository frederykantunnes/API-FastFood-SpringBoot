package br.com.atdsistemas.fastfood.resource;

import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDTO;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDetailsDTO;
import br.com.atdsistemas.fastfood.model.form.FoodServiceForm;
import br.com.atdsistemas.fastfood.service.FoodServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("food_services")
public class FoodServiceResource {

    @Autowired
    FoodServiceService foodServiceService;

    @GetMapping
    public ResponseEntity<Page<FoodServiceDTO>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        return ResponseEntity.ok().body(foodServiceService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodServiceDetailsDTO> detailsService(@PathVariable Long id, @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable){
        FoodServiceDetailsDTO foodServiceDetails = foodServiceService.findFoodServiceDetails(id, pageable);
        if(foodServiceDetails!=null){
            return ResponseEntity.ok().body(foodServiceDetails);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> getAll(UriComponentsBuilder builder,@ModelAttribute @Valid FoodServiceForm foodServiceForm){
//        FoodService foodService = foodServiceService.create(foodServiceForm);
//        URI uri = builder.path("/food_services/{id}").buildAndExpand(foodService.getId()).toUri();
        URI uri = builder.path("/food_services/{id}").buildAndExpand(1).toUri();
        return ResponseEntity.created(uri).body(foodServiceForm.getPicture().getOriginalFilename() + foodServiceForm.getName());
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok().body(?);
//    }
//
//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok().body(?);
//    }

}
