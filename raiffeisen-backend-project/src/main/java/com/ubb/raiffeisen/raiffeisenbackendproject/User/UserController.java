package com.ubb.raiffeisen.raiffeisenbackendproject.User;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/***
 * The UserController class is a REST controller responsible for handling HTTP requests related to user data.
 * It provides endpoints for retrieving, creating, and deleting user information.
 */
@RestController
public class UserController {

    /**
     * The repository for user-related database operations.
     */
    private UserJpaRepository userJpaRepository;

    /**
     * Constructor for UserController.
     * @param userJpaRepository The UserJpaRepository dependency used for user-related operations.
     */
    public UserController(UserJpaRepository userJpaRepository) {
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
    private Optional<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userJpaRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("User with id: " + id + " does not exist!");
        return user;
    }

    /***
     * Creates a new user.
     * @param user The user object to be created.
     */
    @PostMapping(path = "/user")
    private void createUser(@RequestBody User user){
        userJpaRepository.save(user);
    }

    /***
     * Deletes a user by their unique identifier.
     * @param id The unique identifier of the user to be deleted.
     */
    @DeleteMapping(path = "/user/{id}")
    private void deleteUser(@PathVariable Long id){
        userJpaRepository.deleteById(id);
    }

    // TODO -> Update User

}
