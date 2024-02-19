package com.ubb.raiffeisen.raiffeisenbackendproject.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @PostMapping(path = "/user")
    private void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @PostMapping(path = "/user/{id}")
    private void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

}
