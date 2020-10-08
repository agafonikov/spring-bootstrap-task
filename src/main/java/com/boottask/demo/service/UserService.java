package com.boottask.demo.service;

import com.boottask.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    public void add(User user);
    public void delete(User user);
    public void update(User user);

    public List<User> getAll();
    public User getById(long id);
}
