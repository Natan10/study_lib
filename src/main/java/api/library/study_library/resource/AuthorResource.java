package api.library.study_library.resource;

import api.library.study_library.model.entity.Author;
import api.library.study_library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/autores")
public class AuthorResource {
    @Autowired
    private AuthorService service;

    public AuthorResource(AuthorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Author author){
        try {
            Author authorSave = new Author();
            authorSave.setNome(author.getNome());
            authorSave.setEmail(author.getEmail());

            Author save = service.salvarAuthor(authorSave);
            return new ResponseEntity(save, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Author dto){
        return service.buscarAuthor(id).map(entity -> {
            try{
                Author author = new Author();
                author.setNome(dto.getNome());
                author.setEmail(dto.getEmail());
                author.setId(entity.getId());

                service.atualizarAuthor(author);
                return ResponseEntity.ok(author);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }).orElseGet(() -> new ResponseEntity("Autor não encontrado na base!",HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        return service.buscarAuthor(id).map(entity ->{
            service.deletarAuthor(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(()-> new ResponseEntity("Autor não encontrado na base!",HttpStatus.BAD_REQUEST));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarAutor(@PathVariable("id") Integer id){
        Optional<Author> author = service.buscarAuthor(id);
        if(!author.isPresent()){
            return new ResponseEntity("Autor não encontrado!",HttpStatus.NOT_FOUND);
        }
        //return ResponseEntity.ok(author);
        return new ResponseEntity(author,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity all(){
        return ResponseEntity.ok(service.listarAutores());
    }
}
