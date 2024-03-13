package com.ubb.raiffeisen.raiffeisenbackendproject.User;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;

    public UserService(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    public List<User> getListOfUsers(){
        return userJpaRepository.findAll();
    }

    public Optional<User> findUserByEmail(String email){
        return userJpaRepository.findByEmail(email);
    }

    public Optional<User> findUserById(Long id){
        return userJpaRepository.findById(id);
    }

    public void deleteUserById(Long id){
        userJpaRepository.deleteById(id);
    }

    public User updateUser(User user, User existingUser){
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setAddress(user.getAddress());
        userJpaRepository.save(existingUser);
        return existingUser;
    }
}
