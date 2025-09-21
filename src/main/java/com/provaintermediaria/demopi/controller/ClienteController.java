package com.provaintermediaria.demopi.controller;

import com.provaintermediaria.demopi.dto.ClienteDTO;
import com.provaintermediaria.demopi.model.Cliente;
import com.provaintermediaria.demopi.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public ClienteDTO adicionar(@RequestBody Cliente cliente) {
        return service.adicionar(cliente);
    }
}
