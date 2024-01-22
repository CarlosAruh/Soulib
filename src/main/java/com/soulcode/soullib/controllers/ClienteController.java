package com.soulcode.soullib.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @GetMapping("/clientes")
    public String paginaClientes() {
        return "clientes"; // indica qual view
    }
}
