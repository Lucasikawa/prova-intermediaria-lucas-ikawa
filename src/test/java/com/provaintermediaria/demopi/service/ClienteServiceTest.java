package com.provaintermediaria.demopi.service;

import com.provaintermediaria.demopi.dto.ClienteDTO;
import com.provaintermediaria.demopi.exception.ResourceNotFoundException;
import com.provaintermediaria.demopi.model.Cliente;
import com.provaintermediaria.demopi.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Test
    public void test_listarShouldReturnListEmpty() {
        Mockito.when(clienteRepository.findAll()).thenReturn(new ArrayList<>());

        List<ClienteDTO> resp = clienteService.listar();

        Assertions.assertNotNull(resp);
        Assertions.assertTrue(resp.isEmpty());
    }

    @Test
    public void test_listarShouldReturnListWithOneElement() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Lucas");
        cliente.setEmail("lucas@email.com");

        Mockito.when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<ClienteDTO> resp = clienteService.listar();

        Assertions.assertNotNull(resp);
        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals("Lucas", resp.get(0).nome());
    }

    @Test
    public void test_buscarPorIdShouldThrowResourceNotFound() {
        Mockito.when(clienteRepository.findById(99L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> clienteService.buscarPorId(99L));
    }

    @Test
    public void test_buscarPorIdShouldReturnCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Lucas");
        cliente.setEmail("lucas@email.com");

        Mockito.when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(cliente));

        ClienteDTO resp = clienteService.buscarPorId(1L);

        Assertions.assertNotNull(resp);
        Assertions.assertEquals("Lucas", resp.nome());
        Assertions.assertEquals("lucas@email.com", resp.email());
    }

    @Test
    public void test_adicionarShouldReturnClienteDTO() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Lucas");
        cliente.setEmail("lucas@email.com");

        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class)))
                .thenReturn(cliente);

        ClienteDTO resp = clienteService.adicionar(cliente);

        Assertions.assertNotNull(resp);
        Assertions.assertEquals("Lucas", resp.nome());
    }

}
