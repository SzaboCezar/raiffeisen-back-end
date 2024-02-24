package com.ubb.raiffeisen.raiffeisenbackendproject.User;
import com.ubb.raiffeisen.raiffeisenbackendproject.Auth.AuthenticationRequest;
import com.ubb.raiffeisen.raiffeisenbackendproject.Auth.AuthenticationResponse;
import com.ubb.raiffeisen.raiffeisenbackendproject.Auth.AuthenticationService;
import com.ubb.raiffeisen.raiffeisenbackendproject.Auth.RegisterRequest;
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
@RequestMapping("api/user")
public class UserController {

    /**
     * The repository for user-related database operations.
     */
    private UserJpaRepository userJpaRepository;

    private final AuthenticationService service;


    /**
     * Constructor for UserController.
     * @param userJpaRepository The UserJpaRepository dependency used for user-related operations.
     */
    public UserController(UserJpaRepository userJpaRepository, AuthenticationService service) {
        this.userJpaRepository = userJpaRepository;
        this.service = service;
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

    @GetMapping(path = "/user/{user_id}/credit-card")
    private CreditCard getCreditCardForUser(@PathVariable Long user_id){
        Optional<User> findUser = userJpaRepository.findById(user_id);

        if(findUser.isEmpty()) throw new UserNotFoundException("User with id: " + user_id + " does not exist!");

        return findUser.get().getCreditCard();
    }

    /***
     * Creates a new user.
     * @param user The user object to be created.
     */
    @PostMapping(path = "/user")
    private User createUser(@RequestBody User user){
        return userJpaRepository.save(user);
    }

    @GetMapping(path = "/user-email/{email}")
    private Optional<User> getUserByEmail(@PathVariable String email){
        Optional<User> checkUser = userJpaRepository.findByEmail(email);
        if(checkUser.isEmpty()) throw new UserNotFoundException("User with email: " + email + " does not exist!");
        return checkUser;
    }

    /***
     * Deletes a user by their unique identifier.
     * @param id The unique identifier of the user to be deleted.
     */
    @DeleteMapping(path = "/delete-user/{id}")
    private void deleteUser(@PathVariable Long id){
        userJpaRepository.deleteById(id);
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
        Optional<User> checkUser = userJpaRepository.findByEmail(email);
        if (checkUser.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " does not exist!");
        }else {
            User updatedUser = checkUser.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setDateOfBirth(user.getDateOfBirth());
            updatedUser.setAddress(user.getAddress());
            userJpaRepository.save(updatedUser);
            return updatedUser;
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}