package com.generation.projetofarmacia.controller;

import com.generation.projetofarmacia.model.Categoria;
import com.generation.projetofarmacia.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public ResponseEntity<Categoria> CadastrarCategoria(@Valid @RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
    }
    @GetMapping
    public ResponseEntity <List<Categoria>> ListarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
     @GetMapping("/{id}")
    public ResponseEntity <Categoria> BuscarCategoriaPorId(@PathVariable Long id) {
        return categoriaRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("buscar/{nome}")
    public ResponseEntity<List<Categoria>> BuscarCategoriaPorNome(@PathVariable String nome) {
        return ResponseEntity.ok(categoriaRepository.findByNomeContainingIgnoreCase(nome));

    }

    @PutMapping
    public ResponseEntity<Categoria> AtualizarCategoria(@Valid @RequestBody Categoria categoria) {
        return categoriaRepository.findById(categoria.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.save(categoria)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCategoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        // Se o Optional estiver vazio (categoria não encontrada), retorna 404
        if (categoria.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria não encontrada.");
        }

        // Se a categoria for encontrada, deleta a categoria e retorna 200 OK
        categoriaRepository.delete(categoria.get());
        return ResponseEntity.ok("Deletado com sucesso!");

    }
}
