package com.generation.comothellama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.comothellama.model.Categoria;

// Repositório Spring Data JPA para a entidade Categoria
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    // Método para encontrar todas as categorias por gênero, ignorando maiúsculas e minúsculas
    public List<Categoria> findAllByGeneroContainingIgnoreCase(@Param("genero") String genero);
    
}

