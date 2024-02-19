package com.ubb.raiffeisen.raiffeisenbackendproject.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();
    private Long counter = 1L;

     {
         userList.add(new User(counter++, "John", "Doe", "john.doe@example.com", "password123", LocalDate.of(1998, 5, 15), "123 Main St"));
         userList.add(new User(counter++, "Alice", "Smith", "alice.smith@example.com", "password456", LocalDate.of(1999, 8, 25), "456 Elm St"));
         userList.add(new User(counter++, "Bob", "Johnson", "bob.johnson@example.com", "password789", LocalDate.of(2000, 2, 10), "789 Oak St"));
         userList.add(new User(counter++, "Emily", "Brown", "emily.brown@example.com", "passwordabc", LocalDate.of(1997, 11, 30), "101 Pine St"));
         userList.add(new User(counter++, "Michael", "Davis", "michael.davis@example.com", "passwordxyz", LocalDate.of(1996, 7, 8), "202 Cedar St"));
     }

     public List<User> getListOfUsers(){
         return userList;
     }

}
