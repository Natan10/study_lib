package api.library.study_library.resource;

import api.library.study_library.dto.GenreDto;
import api.library.study_library.model.entity.Genre;
import api.library.study_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity save(@RequestBody GenreDto dto){
        try{
            Genre aux = criarGenreDto(dto);

            Genre genre = service.salvarGenero(aux);
            return new ResponseEntity(genre, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,@RequestBody GenreDto dto){
          return service.buscarGenero(id).map(entity -> {
              try{
                  Genre genre = criarGenreDto(dto);
                  genre.setId(entity.getId());
                  service.atualizarGenero(genre);
                  return new ResponseEntity(genre,HttpStatus.OK);
              }catch (Exception e) {
                  return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
              }
          }).orElseGet(()->new ResponseEntity("Gênero não encontrado!",HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        return service.buscarGenero(id).map(entity -> {
            service.deletarGenero(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(()-> new ResponseEntity("Gênero não encontrado!",HttpStatus.NOT_FOUND));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarGenero(@PathVariable Integer id){
        return service.buscarGenero(id)
                .map(genre -> new ResponseEntity(genre,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity("Gênero não encontrado!",HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity all(@PageableDefault(sort = "id",page = 0,size = 2,
            direction = Sort.Direction.ASC) Pageable page){
        return new ResponseEntity(service.listarGeneros(page),HttpStatus.OK);
    }

    private Genre criarGenreDto(GenreDto dto){
        Genre genre = Genre.builder().nome(dto.getNome()).build();
        return genre;
    }
}
