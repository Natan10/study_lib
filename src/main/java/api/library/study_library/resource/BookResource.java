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
            Book book = new Book();
            book.setNome(dto.getNome());
            book.setDataPublicacao(dto.getDataPublicacao());
            Author author = authorService.buscarAuthor(dto.getAuthor())
                    .orElseThrow(()-> new Exception("Autor não encontrado!"));
            Genre genre = genreService.buscarGenero(dto.getGenre())
                    .orElseThrow(()-> new Exception("Genero não encontrado!"));
            book.setAuthor(author);
            book.setGenre(genre);
            Book save = service.salvarLivro(book);
            return new ResponseEntity(save,HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody BookDto dto){
        Optional<Book> book = service.buscarLivro(id);
        if(!book.isPresent()){
            return new ResponseEntity("Livro não encontrado na base!",HttpStatus.NOT_FOUND);
        }
        try {
            book.get().setNome(dto.getNome());
            book.get().setDataPublicacao(dto.getDataPublicacao());

            Author author = authorService.buscarAuthor(dto.getAuthor())
                    .orElseThrow(() -> new Exception("Autor não encontrado!"));
            Genre genre = genreService.buscarGenero(dto.getGenre())
                    .orElseThrow(()-> new Exception("Genero não encontrado!"));

            book.get().setAuthor(author);
            book.get().setGenre(genre);
            service.atualizarBook(book.get());
            return new ResponseEntity(book,HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id){
        Optional<Book> book = service.buscarLivro(id);
        if(!book.isPresent()){
            return new ResponseEntity("Livro não encontrado na base!",HttpStatus.NOT_FOUND);
        }
        service.deletarBook(book.get());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("{id}")
    public ResponseEntity buscarLivro(@PathVariable("id") Integer id){
        Optional<Book> book = service.buscarLivro(id);
        if(!book.isPresent()){
            return new ResponseEntity("Livro não encontrado na base!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(book,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity all(){
        return new ResponseEntity(service.listarLivros(), HttpStatus.OK);
    }
}
