package com.provaintermediaria.demopi.service;

import com.provaintermediaria.demopi.dto.FilmeDTO;
import com.provaintermediaria.demopi.exception.ResourceNotFoundException;
import com.provaintermediaria.demopi.model.Filme;
import com.provaintermediaria.demopi.repository.FilmeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    private final FilmeRepository repository;

    public FilmeService(FilmeRepository repository) {
        this.repository = repository;
    }

    public List<FilmeDTO> listar() {
        return repository.findAll().stream()
                .map(f -> new FilmeDTO(f.getId(), f.getTitulo(), f.getDescricao(),
                        f.getDuracao(), f.getDiretor(), f.getDataCadastro()))
                .toList();
    }

    public FilmeDTO adicionar(Filme filme) {
        Filme salvo = repository.save(filme);
        return new FilmeDTO(salvo.getId(), salvo.getTitulo(), salvo.getDescricao(),
                salvo.getDuracao(), salvo.getDiretor(), salvo.getDataCadastro());
    }

    public FilmeDTO buscarPorId(Long id) {
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id " + id));
        return new FilmeDTO(filme.getId(), filme.getTitulo(), filme.getDescricao(),
                filme.getDuracao(), filme.getDiretor(), filme.getDataCadastro());
    }

    public FilmeDTO atualizar(Long id, Filme novosDados) {
        Filme filme = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado com id " + id));

        filme.setTitulo(novosDados.getTitulo());
        filme.setDescricao(novosDados.getDescricao());
        filme.setDuracao(novosDados.getDuracao());
        filme.setDiretor(novosDados.getDiretor());

        Filme atualizado = repository.save(filme);
        return new FilmeDTO(atualizado.getId(), atualizado.getTitulo(), atualizado.getDescricao(),
                atualizado.getDuracao(), atualizado.getDiretor(), atualizado.getDataCadastro());
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Filme não encontrado com id " + id);
        }
        repository.deleteById(id);
    }
}
