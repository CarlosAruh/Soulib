package com.soulcode.soullib.controllers;
// ao acessar local host 8080/home devemos exibir a pag index

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//configura como controlador web
@Controller
public class HomeController {
    @GetMapping({ "/", "/home", "/h" })
    public String paginaHome() {
        return "index"; // indica qual view
    }

    @GetMapping("/contato")
    public String paginaContato() {
        return "contato"; // indica qual view
    }
}
