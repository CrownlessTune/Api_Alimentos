package com.alimentos.inventory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Ruta para la página de login
    @GetMapping("/login")
    public String login() {
        return "login";  // Devuelve la vista login.html
    }
}
