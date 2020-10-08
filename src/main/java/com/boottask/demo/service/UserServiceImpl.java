package com.boottask.demo.service;

import com.boottask.demo.dao.UserDao;
import com.boottask.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao dao;

    @Override
    public void add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        dao.add(user);
    }

    @Override
    public void delete(User user) {
        dao.delete(user);
    }

    @Override
    public void update(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        dao.update(user);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getById(long id) {
        return dao.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getByUsername(s);
    }
}
