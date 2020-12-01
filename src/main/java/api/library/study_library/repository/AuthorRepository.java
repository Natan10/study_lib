package api.library.study_library.repository;

import api.library.study_library.model.entity.Author;
import api.library.study_library.model.entity.Book;
import api.library.study_library.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value = "select distinct (b) from Author a ,Book b where b.author.id = :id")
    List<Book> listaLivros(@Param("id") Integer id);

    @Query(value = "select distinct(g) from Author a,Book b,Genre g where b.author.id = :id and b.genre.id = g.id")
    List<Genre> listaGeneros(@Param("id") Integer id);
}
