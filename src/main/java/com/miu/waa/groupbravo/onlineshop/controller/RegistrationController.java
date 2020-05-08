package com.miu.waa.groupbravo.onlineshop.controller;

import edu.mum.cs.bravowaaproject.model.Role;
import edu.mum.cs.bravowaaproject.model.User;
import edu.mum.cs.bravowaaproject.service.RoleService;
import edu.mum.cs.bravowaaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ModelAttribute("roles")
    public List<Role> getRoles(Model model) {
        List<Role> roles = roleService.findAll();
        List<Role> roles1 = roles.stream()
                                .filter(role -> ("BUYER".equals(role.getRole()) || "SELLER".equals(role.getRole())))
                                .collect(Collectors.toList());
        return roles1;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@ModelAttribute("user") User user) {

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the username provided");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        } else {
            user.getRoles().forEach(System.out::println);
            userService.saveUser(user);
            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            return "registration";

        }

    }
}