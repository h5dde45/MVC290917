package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.objectss.User;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Locale;

@Controller
//@SessionAttributes("user")
public class LoginController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(@ModelAttribute("user") User user, HttpSession session, Locale locale) {
        System.out.println(locale.getDisplayLanguage());
        System.out.println(messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));
        user.setName("userNameValue");
        return "login";
    }

    @RequestMapping(value = "/checkUser", method = RequestMethod.POST)
    public ModelAndView checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Locale locale) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("locale",
                messageSource.getMessage("locale", new String[]{locale.getDisplayName(locale)}, locale));

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
        } else modelAndView.setViewName("main");

        return modelAndView;
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed() {

        return new ModelAndView("login-failed", "message", "Login failed");
    }

    @RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") boolean admin) {
        User user = new User();
        user.setName(name);
        user.setAdmin(admin);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        System.out.println(user.getName());
        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

}
