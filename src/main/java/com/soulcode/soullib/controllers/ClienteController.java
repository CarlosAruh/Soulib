package com.soulcode.soullib.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/clientes/delete") // action, method, name
    public String deleteCliente(@RequestParam Integer id) { // GET-POST-REDIRECT
        // @RequestParam no POST = vai procurar o valor com o nome determinado
        try {
            clienteRepository.deleteById(id);
        } catch (Exception e) {
            // Em caso de algum problema mostra a página de erro
            return "erro";
        }

        // Redireciona o usuário para a lista de clientes
        // após a remoção feita com sucesso
        return "redirect:/clientes";

    }

    // inserção

    @PostMapping("/clientes/create")
    public String createCliente(Cliente cliente) {
        // @RequestBody => monta o objeto de acordo com os dados vindos
        // do formulário de requisição (corpo)
        try {
            clienteRepository.save(cliente);
        } catch (Exception e) {
            return "erro";
        }

        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}/edit")
    public ModelAndView paginaAtualizarCliente(@PathVariable Integer id) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(id);
        if (clienteOpt.isPresent()) { // cliente encontrado?
            Cliente cliente = clienteOpt.get();
            ModelAndView mv = new ModelAndView("cliente-atualizar");
            mv.addObject("cliente", cliente);
            return mv;
        } else {
            ModelAndView mvErro = new ModelAndView("erro");
            mvErro.addObject("msg", "Cliente não encontrado. Impossivel de editar");
            return mvErro;
        }
    }

}

// Template Engine (thymeleaf) = recurso para gerar as páginas
// HTML dinamicamente usando o back-end.