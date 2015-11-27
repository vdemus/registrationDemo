package com.registration.demo.core.service;

import com.registration.demo.core.datamodel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static List<User> USERS = new ArrayList<User>(){{
        add(new User(1L, "Vasya Pupkin", "pupkin@gmail.com"));
        add(new User(2L, "Kolya Ushkin", "ushkin@gmail.com"));
    }};

    public List<User> getUsers() {
        logger.info("getUsers method invoked");
        return USERS;
    }

    public User getUser(Long id) {
        Iterator<User> iterator = USERS.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getId().equals(id)) {
                return currentUser;
            }
        }
        return null;
    }

    public boolean addUser(User user) {
        logger.info("addUser method invoked");
        return USERS.add(user);
    }

    public boolean updateUser(Long id, User user) {
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

    public void deleteUser(Long id){
        Iterator<User> iterator = USERS.iterator();
        while (iterator.hasNext()) {
            User currentUser = iterator.next();
            if (currentUser.getId().equals(id)) {
                iterator.remove();
            }
        }
    }
}
