package com.provaintermediaria.demopi.controller;

import com.provaintermediaria.demopi.dto.FilmeDTO;
import com.provaintermediaria.demopi.model.Filme;
import com.provaintermediaria.demopi.service.FilmeService;
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

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class FilmeControllerTest {

    @InjectMocks
    private FilmeController filmeController;

    @Mock
    private FilmeService filmeService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController).build();
    }

    @Test
    public void test_listarShouldReturnOneFilme() throws Exception {
        FilmeDTO dto = new FilmeDTO(1L, "Matrix", "Ficção", 2, "Wachowski", LocalDate.now());

        Mockito.when(filmeService.listar()).thenReturn(List.of(dto));

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Matrix"));
    }

    @Test
    public void test_buscarPorIdShouldReturnFilme() throws Exception {
        FilmeDTO dto = new FilmeDTO(1L, "Interestelar", "Sci-Fi", 3, "Nolan", LocalDate.now());

        Mockito.when(filmeService.buscarPorId(1L)).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.get("/filmes/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Interestelar"));
    }

    @Test
    public void test_adicionarShouldReturnFilme() throws Exception {
        Filme filme = new Filme("Avatar", "Aventura", 3, "Cameron");
        FilmeDTO dto = new FilmeDTO(1L, "Avatar", "Aventura", 3, "Cameron", LocalDate.now());

        Mockito.when(filmeService.adicionar(Mockito.any(Filme.class))).thenReturn(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/filmes")
                        .contentType("application/json")
                        .content("{\"titulo\":\"Avatar\",\"descricao\":\"Aventura\",\"duracao\":3,\"diretor\":\"Cameron\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Avatar"));
    }
}
