package com.provaintermediaria.demopi.service;

import com.provaintermediaria.demopi.dto.FilmeDTO;
import com.provaintermediaria.demopi.exception.ResourceNotFoundException;
import com.provaintermediaria.demopi.model.Filme;
import com.provaintermediaria.demopi.repository.FilmeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeRepository filmeRepository;

    @Test
    public void test_listarShouldReturnEmpty() {
        Mockito.when(filmeRepository.findAll()).thenReturn(new ArrayList<>());

        List<FilmeDTO> resp = filmeService.listar();

        Assertions.assertNotNull(resp);
        Assertions.assertTrue(resp.isEmpty());
    }

    @Test
    public void test_listarShouldReturnOne() {
        Filme filme = new Filme("Matrix", "Ficção Científica", 2, "Wachowski");
        filme.setId(1L);

        Mockito.when(filmeRepository.findAll()).thenReturn(List.of(filme));

        List<FilmeDTO> resp = filmeService.listar();

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals("Matrix", resp.get(0).titulo());
    }

    @Test
    public void test_buscarPorIdShouldReturnFilme() {
        Filme filme = new Filme("Interestelar", "Sci-Fi", 3, "Nolan");
        filme.setId(1L);

        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));

        FilmeDTO resp = filmeService.buscarPorId(1L);

        Assertions.assertNotNull(resp);
        Assertions.assertEquals("Interestelar", resp.titulo());
    }

    @Test
    public void test_buscarPorIdShouldThrowException() {
        Mockito.when(filmeRepository.findById(99L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> filmeService.buscarPorId(99L));
    }

    @Test
    public void test_adicionarShouldReturnDTO() {
        Filme filme = new Filme("Avatar", "Aventura", 3, "Cameron");
        filme.setId(1L);
        filme.setDataCadastro(LocalDate.now());

        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(filme);

        FilmeDTO resp = filmeService.adicionar(filme);

        Assertions.assertEquals("Avatar", resp.titulo());
        Assertions.assertEquals("Cameron", resp.diretor());
    }

    @Test
    public void test_gettersAndSettersOfModel() {
        Filme filme = new Filme();
        filme.setId(1L);
        filme.setTitulo("Teste");
        filme.setDescricao("Descricao");
        filme.setDuracao(2);
        filme.setDiretor("Diretor");

        Assertions.assertEquals(1L, filme.getId());
        Assertions.assertEquals("Teste", filme.getTitulo());
        Assertions.assertEquals("Descricao", filme.getDescricao());
        Assertions.assertEquals(2, filme.getDuracao());
        Assertions.assertEquals("Diretor", filme.getDiretor());
    }

}
