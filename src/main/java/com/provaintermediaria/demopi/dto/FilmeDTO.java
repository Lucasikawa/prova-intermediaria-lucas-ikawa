package com.provaintermediaria.demopi.dto;

import java.time.LocalDate;

public record FilmeDTO(
        Long id,
        String titulo,
        String descricao,
        Integer duracao,
        String diretor,
        LocalDate dataCadastro
) {}
