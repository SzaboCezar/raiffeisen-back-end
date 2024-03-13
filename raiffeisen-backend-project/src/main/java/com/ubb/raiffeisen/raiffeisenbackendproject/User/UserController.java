package com.ubb.raiffeisen.raiffeisenbackendproject.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.CreditCard.CreditCard;
import org.springframework.http.ResponseEntity;
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
    private final UserJpaRepository userJpaRepository;
    private final UserService userService;
    /**
     * Constructor for UserController.
     * @param userJpaRepository The UserJpaRepository dependency used for user-related operations.
     */
    public UserController(UserJpaRepository userJpaRepository, UserService userService) {
        this.userJpaRepository = userJpaRepository;
        this.userService = userService;
    }
    /***
     * Retrieves a list of all users.
     * @return List<User> A list of all users.
     */
    @GetMapping(path = "/users")
    private List<User> getUsers(){
        return userService.getListOfUsers();
    }

    /***
     * Retrieves a user by their unique identifier.
     * @param id The unique identifier of the user.
     * @return User The user object corresponding to the provided ID.
     * @throws UserNotFoundException Thrown if the user with the provided ID does not exist.
     */
    @GetMapping(path = "/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findUserById(id);
        if(user.isEmpty()) throw new UserNotFoundException("User with id: " + id + " does not exist!");
        return ResponseEntity.ok(user);
    }


    @GetMapping(path = "/user/{user_id}/credit-card")
    private ResponseEntity<CreditCard> getCreditCardForUser(@PathVariable Long user_id){
        Optional<User> findUser = userService.findUserById(user_id);
        if (findUser.isEmpty()) throw new UserNotFoundException("User with id: " + user_id + " does not exist!");
        return ResponseEntity.ok(findUser.get().getCreditCard());
    }

    @GetMapping(path = "/user-email/{email}")
    private Optional<User> getUserByEmail(@PathVariable String email){
        Optional<User> checkUser = userService.findUserByEmail(email);
        if(checkUser.isEmpty()) throw new UserNotFoundException("User with email: " + email + " does not exist!");
        return checkUser;
    }

    /***
     * Deletes a user by their unique identifier.
     * @param id The unique identifier of the user to be deleted.
     */
    @DeleteMapping(path = "/delete-user/{id}")
    private void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    /**
     * Updates the information of an existing user based on the provided email address.
     *
     * @param email The email address of the user to be updated.
     * @param user The updated user object containing the new information.
     * @return The updated user object.
     * @throws UserNotFoundException If the user with the specified email does not exist.
     */
    @PutMapping(path = "/user-update/{email}")
    private User updateUser(@PathVariable String email, @RequestBody User user){
        Optional<User> checkUser = userService.findUserByEmail(email);
        if (checkUser.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " does not exist!");
        }
        return userService.updateUser(user, checkUser.get());
    }
}