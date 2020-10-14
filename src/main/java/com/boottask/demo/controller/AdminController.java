package com.boottask.demo.controller;

import com.boottask.demo.model.User;
import com.boottask.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public ModelAndView Test(){
        User user = userService.getById(1);
        List<User> users = userService.getAll();
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("user", user);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addGet(){
        ModelAndView modelAndView = new ModelAndView("main");
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.addObject("submit_url", "/admin/add");
        modelAndView.addObject("submit_text", "Add");
        return modelAndView;
    }

    @GetMapping(value = "/update")
    public ModelAndView updateGet(@ModelAttribute("id") Long id){
        ModelAndView modelAndView = new ModelAndView("main");
        User user = userService.getById(id);
        modelAndView.addObject("user", user);
        modelAndView.addObject("submit_url", "/admin/update");
        modelAndView.addObject("submit_text", "Update");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addPost(@ModelAttribute("obj") @Validated User user, BindingResult result){
        userService.add(user);
        return new ModelAndView("redirect:/admin/");
    }

    @PostMapping(value = "/update")
    public ModelAndView updatePost(@ModelAttribute("obj") @Validated User user, BindingResult result){
        User old_user = (User) userService.loadUserByUsername(user.getUsername());
        user.setId(old_user.getId());
        userService.update(user);
        return new ModelAndView("redirect:/admin/");
    }

    @GetMapping(value = "/delete")
    public ModelAndView deleteGet(@ModelAttribute("id") Long id){
        User user = userService.getById(id);
        userService.delete(user);
        return new ModelAndView("redirect:/admin/");
    }

    @PostMapping(value = "/delete")
    public ModelAndView deletePost(@ModelAttribute("obj") @Validated User user, BindingResult result){
        User user1 = (User) userService.loadUserByUsername(user.getUsername());
        userService.delete(user1);
        return new ModelAndView("redirect:/admin/");
    }

    @GetMapping("/form")
    public String userForm(ModelMap model){
        return "main";
    }
}
