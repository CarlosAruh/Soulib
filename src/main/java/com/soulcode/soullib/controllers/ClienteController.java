package com.soulcode.soullib.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.soulcode.soullib.models.Cliente;
import com.soulcode.soullib.repositories.ClienteRepository;

// Os mapeamentos dentro deste controller
// serão utilizados pelo Spring
@Controller
public class ClienteController {
    // Classes como @Controller e @Repository
    // o spring gera uma instância automaticamente.
    // Injeção de Dependências
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public ModelAndView paginaClientes() {
        List<Cliente> clientes = clienteRepository.findAll(); // SELECT * FROM clientes;
        // Usamos ModelAndView quando precisamos fornecer dados
        // para o HTML
        ModelAndView mv = new ModelAndView("clientes"); // Indica qual o template/view
        // Nome da variável do template e o valor dessa variável
        mv.addObject("listaClientes", clientes);
        return mv; // Objeto configurado com a view e os dados que ela vai usar
    }

    // parametro da rota: /clientes/5000, o valor da id =5000
    @GetMapping("/clientes/{id}")
    public ModelAndView paginaDetalheCliente(@PathVariable Integer id) {
        // @Pathvariable = extrai da rota o valor correspondente
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        // O cliente existe ou não

        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();
            ModelAndView mv = new ModelAndView("cliente-detalhe");
            mv.addObject("cliente", cliente);
            return mv;
        } else {
            ModelAndView mvErro = new ModelAndView("erro");
            mvErro.addObject("msg", "O cliente não foi encontrado");
            return mvErro;
        }

    }

}

// Template Engine (thymeleaf) = recurso para gerar as páginas
// HTML dinamicamente usando o back-end.