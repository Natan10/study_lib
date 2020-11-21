package api.library.study_library.resource;

import api.library.study_library.model.entity.Genre;
import api.library.study_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/genre")
public class GenreResource {

    @Autowired
    private GenreService service;

    public GenreResource(GenreService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Genre genre){
        try{
            Genre genreSave = new Genre();
            genreSave.setNome(genre.getNome());
            Genre save = service.salvarGenero(genreSave);
            return new ResponseEntity(save, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,@RequestBody Genre dto){
        Optional<Genre> genre = service.buscarGenero(id);
        if(!genre.isPresent()){
            return new ResponseEntity("Gênero não encontrado!",HttpStatus.NOT_FOUND);
        }
        genre.get().setNome(dto.getNome());
        service.atualizarGenero(genre.get());
        return new ResponseEntity(genre,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        Optional<Genre> genre = service.buscarGenero(id);
        if(!genre.isPresent()){
            return new ResponseEntity("Gênero não encontrado!",HttpStatus.NOT_FOUND);
        }
        service.deletarGenero(genre.get());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity buscarGenero(@PathVariable Integer id){
        Optional<Genre> genre = service.buscarGenero(id);
        if(!genre.isPresent()){
            return new ResponseEntity("Gênero não encontrado!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(genre,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity all(){
        return new ResponseEntity(service.listarGeneros(),HttpStatus.OK);
    }
}
