package com.Revature.Study_Buddy_Backend.Controller;

import com.Revature.Study_Buddy_Backend.Model.User;
import com.Revature.Study_Buddy_Backend.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") Long userId) {
        try{
            User user = userService.getUserById(userId);
            if(user != null) {
               return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }catch(Exception ignore){}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addUser(@RequestBody User user) {
        List<User> userList = userService.getAllUser();
        for(User findUser : userList){
            if(findUser.getEmail().equals(user.getEmail())){
                return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
            }
        }
        userService.addUser(user);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            User findUser = userService.getUserById(user.getUserId());
            if(findUser != null){
                return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
            }
        } catch (Exception ignore) {}
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable("userid") Long userId) {
        try {
            userService.deleteByUserId(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/finduser")
    public ResponseEntity<User> findUserByEmailAndPasswd(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.findUserByEmailAndPasswd(user), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
