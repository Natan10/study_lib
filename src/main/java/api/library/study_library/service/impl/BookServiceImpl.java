package api.library.study_library.service.impl;

import api.library.study_library.model.entity.Book;
import api.library.study_library.repository.BookRepository;
import api.library.study_library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }


    @Override
    @Transactional
    public Book salvarLivro(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional
    public Book atualizarBook(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional
    public void deletarBook(Book book) {
        repository.delete(book);
    }

    @Override
    public Optional<Book> buscarLivro(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> listarLivros() {
        return repository.findAll();
    }
}
