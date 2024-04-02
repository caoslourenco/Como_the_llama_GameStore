package com.generation.comothellama.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.comothellama.model.Game;

// Repositório Spring Data JPA para a entidade Game
public interface GameRepository extends JpaRepository<Game, Long> {
    
    // Método para encontrar todos os jogos por título, ignorando maiúsculas e minúsculas
    public List<Game> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
    
}

