package br.com.atdsistemas.fastfood.resource;

import br.com.atdsistemas.fastfood.model.User;
import br.com.atdsistemas.fastfood.model.form.user.UserFormCreate;
import br.com.atdsistemas.fastfood.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("users")
public class UserResource {

    @Autowired
    UserService userService;


    @PostMapping
    @Transactional
    public ResponseEntity<User> create(@RequestBody UserFormCreate userFormCreate){
        User user = userService.create(userFormCreate.getUser(), userFormCreate.getRestaurant_id());
        return ResponseEntity.ok().body(user);
    }

}
