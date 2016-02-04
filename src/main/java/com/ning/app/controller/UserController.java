package com.ning.app.controller;

import com.ning.app.domain.User;
import com.ning.app.domain.UserRel;
import com.ning.app.domain.UserRelRepository;
import com.ning.app.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evanchai on 2/4/16.
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRelRepository userRelRepository;

    @RequestMapping("/hi")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return name;
    }

    @RequestMapping("/")
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @RequestMapping("/rels")
    public List<UserRel> findAllRels() {
        List<UserRel> rels = userRelRepository.findAll();
        return rels;
    }

    @RequestMapping("/register")
    public User register(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        User user = userRepository.save(new User(username,password));
        return user;
    }

    @RequestMapping("/login")
    public Boolean login(@RequestParam(value="username") String username, @RequestParam(value="password") String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        return null!=user;
    }

    @RequestMapping("/find/{userId}")
    public User find(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        return user;
    }

    @RequestMapping("/{userId}/fans")
    public List<User> fans(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        List<UserRel> rels = userRelRepository.findByToId(userId);
        ArrayList<User> users = new ArrayList<User>();
        for(UserRel rel : rels){
            users.add(find(rel.getFromId()));
        }
        return users;
    }

    @RequestMapping("/{userId}/follows")
    public List<User> follows(@PathVariable long userId) {
        User user = userRepository.findOne(userId);
        List<UserRel> rels = userRelRepository.findByFromId(userId);
        ArrayList<User> users = new ArrayList<User>();
        for(UserRel rel : rels){
            users.add(find(rel.getToId()));
        }
        return users;
    }

    @RequestMapping("/{fromId}/follow/{toId}")
    public Boolean follow(@PathVariable long fromId,@PathVariable long toId) {
        User fromUser = userRepository.findOne(fromId);
        User toUser = userRepository.findOne(toId);

        UserRel rel = userRelRepository.findByFromIdAndToId(fromId,toId);
        if(null==rel){
            userRelRepository.save(new UserRel(fromId,toId));
            return true;
        }
        return false;
    }

    @RequestMapping("/{fromId}/unfollow/{toId}")
    public Boolean unfollow(@PathVariable long fromId,@PathVariable long toId) {
        User fromUser = userRepository.findOne(fromId);
        User toUser = userRepository.findOne(toId);

        UserRel rel = userRelRepository.findByFromIdAndToId(fromId,toId);
        if(null!=rel){
            userRelRepository.delete(rel.getId());
            return true;
        }
        return false;
    }
}
