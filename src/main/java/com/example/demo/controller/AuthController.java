package com.example.demo.controller;

import com.example.demo.service.UserServiceImpl;
import com.example.demo.model.Users;
import com.example.demo.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserValidator userValidator;
    @GetMapping("sign_up")
    public String signUp(Model model) {
        model.addAttribute("users", new Users());
        return "auth/sign_up";
    }
    @PostMapping("/sign_up")
    public String signUp(
            @ModelAttribute @Valid Users users,
            BindingResult result,
            Model model
    ) {
        userValidator.validate(users, result);
        if (result.hasErrors()) {
            return "/auth/sign_up";
        }
        userService.add(users);
        model.addAttribute("successMessage","Вы успешно зарегистрировались!");
        return "auth/sign_up";
    }
    @RequestMapping("/login")
    public String login(@RequestParam(name = "error", required = false) Boolean error,
                        Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", true);
        }
        return "auth/login";
    }
}
