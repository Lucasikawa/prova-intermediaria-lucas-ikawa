package com.provaintermediaria.demopi.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private Integer duracao; // em horas
    private String diretor;
    private LocalDate dataCadastro = LocalDate.now();

    public Filme() {}

    public Filme(String titulo, String descricao, Integer duracao, String diretor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.diretor = diretor;
        this.dataCadastro = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }

    public String getDiretor() { return diretor; }
    public void setDiretor(String diretor) { this.diretor = diretor; }

    public LocalDate getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDate dataCadastro) { this.dataCadastro = dataCadastro; }
}
