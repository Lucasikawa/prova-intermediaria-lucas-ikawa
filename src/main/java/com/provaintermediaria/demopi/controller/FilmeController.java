package com.provaintermediaria.demopi.controller;

import com.provaintermediaria.demopi.dto.FilmeDTO;
import com.provaintermediaria.demopi.model.Filme;
import com.provaintermediaria.demopi.service.FilmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @GetMapping
    public List<FilmeDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public FilmeDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public FilmeDTO adicionar(@RequestBody Filme filme) {
        return service.adicionar(filme);
    }

    @PutMapping("/{id}")
    public FilmeDTO atualizar(@PathVariable Long id, @RequestBody Filme filme) {
        return service.atualizar(id, filme);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
