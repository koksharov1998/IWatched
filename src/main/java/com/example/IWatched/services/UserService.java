package com.example.IWatched.services;

import com.example.IWatched.db.User;
import com.example.IWatched.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements BdService<User>{
    private UserRepository userRepository;

    public List<User> findAll() {
        return new ArrayList<User>((Collection<? extends User>) userRepository.findAll());
    }

    public User findById(int id){
        return userRepository.findById(id).get();
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Autowired
    public void DataInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
