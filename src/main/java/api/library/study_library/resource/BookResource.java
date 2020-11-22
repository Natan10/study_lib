package api.library.study_library.resource;


import api.library.study_library.dto.BookDto;
import api.library.study_library.model.entity.Author;
import api.library.study_library.model.entity.Book;
import api.library.study_library.model.entity.Genre;
import api.library.study_library.service.AuthorService;
import api.library.study_library.service.BookService;
import api.library.study_library.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookResource {
    @Autowired
    private BookService service;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    public BookResource(BookService service) {
        this.service = service;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody BookDto dto){
        try {
            Book aux = criarBookDto(dto);
            Book book = service.salvarLivro(aux);
            return new ResponseEntity(book,HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody BookDto dto){
         return service.buscarLivro(id)
                 .map(entity->{
                     try{
                         Book book = criarBookDto(dto);
                         book.setId(entity.getId());
                         service.atualizarBook(book);
                         return new ResponseEntity(book,HttpStatus.OK);
                     }catch (Exception e) {
                         return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
                     }
                 }).orElseGet(()->new ResponseEntity("Livro não encontrado na base",HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        return service.buscarLivro(id).map(entity -> {
            service.deletarBook(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }).orElseGet(()-> new ResponseEntity("Livro não encontrado na base!",HttpStatus.BAD_REQUEST));
    }

    @GetMapping("{id}")
    public ResponseEntity buscarLivro(@PathVariable("id") Integer id){
        return service.buscarLivro(id)
                .map(book -> new ResponseEntity(book,HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity("Livro não encontrado na base!",HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity all(){
        return new ResponseEntity(service.listarLivros(), HttpStatus.OK);
    }

    private Book criarBookDto(BookDto dto) throws Exception {
        Book book = new Book();
        book.setNome(dto.getNome());
        book.setDataPublicacao(dto.getDataPublicacao());
        Author author = authorService.buscarAuthor(dto.getAuthor())
                .orElseThrow(()-> new Exception("Autor não encontrado!"));
        Genre genre = genreService.buscarGenero(dto.getGenre())
                .orElseThrow(()-> new Exception("Genero não encontrado!"));
        book.setAuthor(author);
        book.setGenre(genre);

        return book;
    }
}
