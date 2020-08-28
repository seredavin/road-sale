package ru.avokzal63.roadsale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.avokzal63.roadsale.domain.Role;
import ru.avokzal63.roadsale.domain.User;
import ru.avokzal63.roadsale.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("{user}")
    public String userSave(@RequestParam String username,
                           @RequestParam Map<String, String> form,
                           @PathVariable User user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public String addUser(@RequestParam("password2") String passwordConfirm,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        model.addAttribute("users", userService.findAll());
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Заполните пароль!");
        }
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Пароли не совпадают!");
        }
        if (isConfirmEmpty || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("thisUsername", user.getUsername());
            return "userList";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("usernameError", "Такой пользователь уже существует!");
            model.addAttribute("thisUsername", user.getUsername());
            return "userList";
        }
        return "redirect:/user";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("fullName", user.getFullName());
        return "profile";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PostMapping("profile")
    public String updateProfile(@AuthenticationPrincipal User user,
                                @RequestParam String password) {
        userService.updateProfile(user, password);
        return "redirect:/user/profile";
    }

}
