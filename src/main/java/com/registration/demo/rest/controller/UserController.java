package com.registration.demo.rest.controller;

import com.registration.demo.core.datamodel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final  Logger logger = LoggerFactory.getLogger(UserController.class);

    private static List<User> USERS = new ArrayList<User>(){{
        add(new User(1L, "Vasya Pupkin", "pupkin@gmail.com"));
        add(new User(2L, "Kolya Ushkin", "ushkin@gmail.com"));
    }};

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsers() {
        logger.info("getUsers method invoked");
        return USERS;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUser(@PathVariable("id") Long id) {
        Iterator<User> iterator = USERS.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getId().equals(id)) {
                return currentUser;
            }
        }
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
        public boolean addUser(@RequestBody User user) {
        logger.info("addUser method invoked");
        return USERS.add(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public boolean updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        logger.info("updateUser method invoked");
        for (User currentUser : USERS) {
            if((id.equals(user.getId()))
                    &&(currentUser.getId().equals(user.getId()))) {
                currentUser.setName(user.getName());
                currentUser.setEmail(user.getEmail());
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUser(@PathVariable("id") Long id){
        Iterator<User> iterator = USERS.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getId().equals(id)) {
                iterator.remove();
            }
        }
    }
}
