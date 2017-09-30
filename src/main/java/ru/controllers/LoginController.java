package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.objectss.User;

@Controller
public class LoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed() {

        return new ModelAndView("login-failed", "message", "Login failed");
    }
}
