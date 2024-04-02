package com.generation.comothellama.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_produto")
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(min = 5, max = 100, message = "O nome do produto deve conter no mínimo 5 e no máximo 100 caracteres")
    private String titulo;
    
    @NotBlank(message = "O atributo descrição é obrigatório!")
    @Size(min = 10, max = 1000, message = "A descrição deve conter no mínimo 10 e no máximo 1000 caracteres")
    private String descricao;

    @NotNull(message = "O atributo preço é obrigatório!")
    @Positive(message = "O preço deve ser positivo!")
    private BigDecimal preco;
    
    // Relacionamento muitos-para-um com a entidade Categoria
    @ManyToOne
    @JsonIgnoreProperties("games")
    private Categoria categoria;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
