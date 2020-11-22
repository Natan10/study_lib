package api.library.study_library.resource;

import api.library.study_library.dto.AuthorDto;
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
    public ResponseEntity save(@RequestBody AuthorDto dto){
        try {
            Author aux = criarAuthorDto(dto);
            Author author = service.salvarAuthor(aux);

            return new ResponseEntity(author, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody AuthorDto dto){
        return service.buscarAuthor(id).map(entity -> {
            try{
                Author author = criarAuthorDto(dto);
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
         return service.buscarAuthor(id)
                 .map(author -> new ResponseEntity(author,HttpStatus.OK))
                 .orElseGet(() -> new ResponseEntity("Autor não encontrado!",HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity all(){
        return ResponseEntity.ok(service.listarAutores());
    }


    private Author criarAuthorDto(AuthorDto dto){
        Author author = new Author();
        author.setNome(dto.getNome());
        author.setEmail(dto.getEmail());

        return author;
    }
}
