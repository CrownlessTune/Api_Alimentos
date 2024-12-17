package com.alimentos.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";  // Devuelve la vista de login
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";  // Puedes personalizar la vista de logout si lo necesitas
    }
}