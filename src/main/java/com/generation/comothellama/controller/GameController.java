package com.generation.comothellama.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.comothellama.model.Game;
import com.generation.comothellama.repository.CategoriaRepository;
import com.generation.comothellama.repository.GameRepository;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/games")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GameController {

    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    // Endpoint para obter todos os jogos
    @GetMapping
    public ResponseEntity<List<Game>> getAll(){
        return ResponseEntity.ok(gameRepository.findAll());
    }
    
    // Endpoint para obter um jogo pelo seu ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable Long id){
        return gameRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    // Endpoint para obter jogos por título
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Game>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(gameRepository.findAllByTituloContainingIgnoreCase(titulo));
    }
    
    // Endpoint para criar um novo jogo
    @PostMapping
    public ResponseEntity<Game> post(@Valid @RequestBody Game game){
        if (categoriaRepository.existsById(game.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(gameRepository.save(game));
            
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
    }
    
    // Endpoint para atualizar um jogo existente
    @PutMapping
    public ResponseEntity<Game> put(@Valid @RequestBody Game game){
        if (gameRepository.existsById(game.getId())){
            
            if (categoriaRepository.existsById(game.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(gameRepository.save(game));
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
            
        }           
            
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        
    }
    
    
    // Endpoint para excluir um jogo pelo seu ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Game> postagem = gameRepository.findById(id);
        
        if(postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        gameRepository.deleteById(id);                
    }

}
