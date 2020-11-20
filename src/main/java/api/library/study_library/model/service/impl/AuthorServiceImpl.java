package api.library.study_library.model.service.impl;

import api.library.study_library.model.entity.Author;
import api.library.study_library.model.repository.AuthorRepository;
import api.library.study_library.model.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository repository;

    // Injeçao de depêndecia pelo construtor
    public AuthorServiceImpl(AuthorRepository repository){
        super();
        this.repository = repository;
    }

    @Override
    @Transactional // Realiza as transações do JPA
    public Author salvarAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    @Transactional
    public Author atualizarAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    @Transactional
    public void deletarAuthor(Author author) {
        Objects.requireNonNull(author);
        repository.delete(author);
    }

    @Override
    public Optional<Author> buscarAuthor(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Author> listarAutores() {
        return repository.findAll();
    }
}
