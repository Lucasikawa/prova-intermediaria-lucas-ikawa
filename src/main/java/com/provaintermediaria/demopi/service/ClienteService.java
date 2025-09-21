package com.provaintermediaria.demopi.service;

import com.provaintermediaria.demopi.dto.ClienteDTO;
import com.provaintermediaria.demopi.exception.ResourceNotFoundException;
import com.provaintermediaria.demopi.model.Cliente;
import com.provaintermediaria.demopi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteDTO> listar() {
        return repository.findAll().stream()
                .map(c -> new ClienteDTO(c.getId(), c.getNome(), c.getEmail()))
                .toList();
    }

    public ClienteDTO adicionar(Cliente cliente) {
        Cliente salvo = repository.save(cliente);
        return new ClienteDTO(salvo.getId(), salvo.getNome(), salvo.getEmail());
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id " + id));
        return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
    }
}
