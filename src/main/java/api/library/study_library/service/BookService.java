package api.library.study_library.service;

import api.library.study_library.model.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book salvarLivro(Book book);
    Book atualizarBook(Book book);
    void deletarBook(Book book);
    Optional<Book> buscarLivro(Integer id);
    List<Book> listarLivros();
    List<Book> buscarPorGenero(Integer id);
}
