package com.ubb.raiffeisen.raiffeisenbackendproject.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/***
 * The UserController class is a REST controller responsible for handling HTTP requests related to user data.
 * It provides endpoints for retrieving, creating, and deleting user information.
 */
@RestController
public class UserController {

    private UserService userService;

    private UserJpaRepository userJpaRepository;


    /***
     * Constructor for UserController.
     * @param userService The UserService dependency used for user-related operations.
     */
    public UserController(UserService userService, UserJpaRepository userJpaRepository) {
        this.userService = userService;
        this.userJpaRepository = userJpaRepository;
    }

    /***
     * Retrieves a list of all users.
     * @return List<User> A list of all users.
     */
    @GetMapping(path = "/users")
    private List<User> getUsers(){
        return userJpaRepository.findAll();
    }

    /***
     * Retrieves a user by their unique identifier.
     * @param id The unique identifier of the user.
     * @return User The user object corresponding to the provided ID.
     * @throws UserNotFoundException Thrown if the user with the provided ID does not exist.
     */
    @GetMapping(path = "/user/{id}")
    private User getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if(user == null) throw new UserNotFoundException("User with id: " + id + " does not exist!");
        return user;
    }

    /***
     * Creates a new user.
     * @param user The user object to be created.
     */
    @PostMapping(path = "/user")
    private void createUser(@RequestBody User user){
        userService.saveUser(user);
    }

    /***
     * Deletes a user by their unique identifier.
     * @param id The unique identifier of the user to be deleted.
     */
    @PostMapping(path = "/user/{id}")
    private void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }
}
