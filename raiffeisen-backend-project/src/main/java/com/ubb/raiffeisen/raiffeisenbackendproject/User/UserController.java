package com.ubb.raiffeisen.raiffeisenbackendproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/users")
    private List<User> getUsers(){
        return userService.getListOfUsers();
    }

    @GetMapping(path = "/user/{id}")
    private User getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user == null) throw new UserNotFoundException("User with id: " + id + " does not exist!");
        
        return user;
    }

}
