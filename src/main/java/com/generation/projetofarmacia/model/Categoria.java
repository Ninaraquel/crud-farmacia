package com.generation.projetofarmacia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo Nome é Obrigatório!")
    @Size(min = 5, max = 100, message = "O atributo nome deve conter no mínimo 5 e no máximo 100 caracteres!")
    private String nome;

    @NotBlank(message = "O atributo Descricao é Obrigatório!")
    @Size(min = 10, max = 1000, message = "O atributo Descricao deve conter no mínimo 10 e no máximo 1000 caracteres!")
    private String descricao;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
