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
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FilmeServiceTest {

    @InjectMocks
    private FilmeService filmeService;

    @Mock
    private FilmeRepository filmeRepository;

    @Test
    public void test_listarShouldReturnOne() {
        Filme filme = new Filme("Matrix", "Sci-fi", 2, "Wachowski");
        filme.setId(1L);

        Mockito.when(filmeRepository.findAll()).thenReturn(List.of(filme));

        List<FilmeDTO> resp = filmeService.listar();

        Assertions.assertEquals(1, resp.size());
        Assertions.assertEquals("Matrix", resp.get(0).titulo());
    }

    @Test
    public void test_buscarPorIdShouldReturn() {
        Filme filme = new Filme("Avatar", "Aventura", 3, "Cameron");
        filme.setId(1L);

        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(filme));

        FilmeDTO resp = filmeService.buscarPorId(1L);

        Assertions.assertEquals("Avatar", resp.titulo());
    }

    @Test
    public void test_buscarPorIdShouldThrow() {
        Mockito.when(filmeRepository.findById(99L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> filmeService.buscarPorId(99L));
    }

    @Test
    public void test_adicionarShouldReturnDTO() {
        Filme filme = new Filme("Interestelar", "Sci-fi", 3, "Nolan");
        filme.setId(1L);
        filme.setDataCadastro(LocalDate.now());

        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(filme);

        FilmeDTO resp = filmeService.adicionar(filme);

        Assertions.assertEquals("Interestelar", resp.titulo());
    }

    @Test
    public void test_atualizarShouldModifyData() {
        Filme antigo = new Filme("Old", "Teste", 1, "Diretor");
        antigo.setId(1L);

        Filme novo = new Filme("Novo", "Desc nova", 2, "Outro");

        Mockito.when(filmeRepository.findById(1L)).thenReturn(Optional.of(antigo));
        Mockito.when(filmeRepository.save(Mockito.any(Filme.class))).thenReturn(novo);

        FilmeDTO resp = filmeService.atualizar(1L, novo);

        Assertions.assertEquals("Novo", resp.titulo());
    }

    @Test
    public void test_deletarShouldThrowIfNotExists() {
        Mockito.when(filmeRepository.existsById(99L)).thenReturn(false);

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> filmeService.deletar(99L));
    }

    @Test
    public void test_deletarShouldPass() {
        Mockito.when(filmeRepository.existsById(1L)).thenReturn(true);

        filmeService.deletar(1L);

        Mockito.verify(filmeRepository, Mockito.times(1)).deleteById(1L);
    }
}
