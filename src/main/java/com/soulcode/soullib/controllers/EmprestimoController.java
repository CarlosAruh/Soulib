package com.soulcode.soullib.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.soulcode.soullib.models.Cliente;
import com.soulcode.soullib.models.Emprestimo;
import com.soulcode.soullib.models.Livro;
import com.soulcode.soullib.repositories.ClienteRepository;
import com.soulcode.soullib.repositories.EmprestimoRepository;
import com.soulcode.soullib.repositories.LivroRepository;

@Controller // expor mapeamento
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/emprestimos")
    public ModelAndView paginaEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
        List<Cliente> clientes = clienteRepository.findAll();
        List<Livro> livros = livroRepository.findAll();
        ModelAndView mv = new ModelAndView("emprestimos");
        mv.addObject("listaEmprestimos", emprestimos);
        mv.addObject("listaClientes", clientes);
        mv.addObject("listaLivros", livros);
        return mv;
    }

    // pagina emprestimos.htm
    // Ação de inserir emprestimos

}
