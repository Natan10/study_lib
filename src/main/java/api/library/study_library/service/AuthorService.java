package api.library.study_library.service;

import api.library.study_library.model.entity.Author;
import api.library.study_library.model.entity.Book;
import api.library.study_library.model.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Author salvarAuthor(Author author);
    Author atualizarAuthor(Author author);
    void deletarAuthor(Author author);
    Optional<Author> buscarAuthor(Integer id);
    List<Author> listarAutores();
    List<Book> listaLivrosAutor(Integer id);
    List<Genre> listaGenerosAutor(Integer id);
}
