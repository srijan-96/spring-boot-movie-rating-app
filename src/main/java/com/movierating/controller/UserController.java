package com.movierating.controller;

import com.movierating.model.Movie;
import com.movierating.model.User;
import com.movierating.service.MovieService;
import com.movierating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/users")
    private List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    private User getUser(@PathVariable("id") int id)
    {
        return userService.getUserById(id);
    }
    @DeleteMapping("/user/{id}")
    private void deleteUser(@PathVariable("id") int id)
    {
        userService.delete(id);
    }
    @PostMapping("/user")
    private ResponseEntity<String> saveUser(@RequestBody @Valid User user)
    {
        userService.saveOrUpdate(user);
        return new ResponseEntity<>("User added with id :" + user.getId(), HttpStatus.OK);
    }
}
