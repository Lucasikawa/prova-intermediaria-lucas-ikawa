package com.provaintermediaria.demopi.controller;

import com.provaintermediaria.demopi.dto.ClienteDTO;
import com.provaintermediaria.demopi.model.Cliente;
import com.provaintermediaria.demopi.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(clienteController)
                .build();
    }

    @Test
    public void test_listarShouldReturnOneCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Lucas", "lucas@email.com");

        Mockito.when(clienteService.listar())
                .thenReturn(List.of(clienteDTO));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/clientes")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome")
                        .value("Lucas"));
    }

    @Test
    public void test_buscarPorIdShouldReturnCliente() throws Exception {
        ClienteDTO clienteDTO = new ClienteDTO(1L, "Lucas", "lucas@email.com");

        Mockito.when(clienteService.buscarPorId(1L))
                .thenReturn(clienteDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome")
                        .value("Lucas"));
    }

    @Test
    public void test_adicionarShouldReturnCliente() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Lucas");
        cliente.setEmail("lucas@email.com");

        ClienteDTO clienteDTO = new ClienteDTO(1L, "Lucas", "lucas@email.com");

        Mockito.when(clienteService.adicionar(Mockito.any(Cliente.class)))
                .thenReturn(clienteDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType("application/json")
                        .content("{\"nome\":\"Lucas\",\"email\":\"lucas@email.com\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Lucas"));
    }

}
