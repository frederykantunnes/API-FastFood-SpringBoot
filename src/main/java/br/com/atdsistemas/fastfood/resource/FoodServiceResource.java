package br.com.atdsistemas.fastfood.resource;

import br.com.atdsistemas.fastfood.model.FoodService;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDTO;
import br.com.atdsistemas.fastfood.model.dto.food_service.FoodServiceDetailsDTO;
import br.com.atdsistemas.fastfood.model.form.FoodServiceForm;
import br.com.atdsistemas.fastfood.service.FoodServiceService;
import br.com.atdsistemas.fastfood.util.Directories;
import br.com.atdsistemas.fastfood.util.FilesUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("food_services")
public class FoodServiceResource {

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

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
    public ResponseEntity<FoodServiceDTO> create(UriComponentsBuilder builder, @ModelAttribute @Valid FoodServiceForm foodServiceForm) throws IOException, URISyntaxException {
        String upload = FilesUpload.upload(foodServiceForm.getPicture(), Directories.FOODSSERVICES.toString());
        if (upload!=""){
            FoodService foodService = foodServiceService.create(foodServiceForm.getFoodService(upload));
            URI uri = builder.path("food_services/{id}").buildAndExpand(foodService.getId()).toUri();
            return ResponseEntity.created(uri).body(new FoodServiceDTO(foodService));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        foodServiceService.delete(id);
        return ResponseEntity.notFound().build();
    }



}
