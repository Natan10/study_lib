package api.library.study_library.repository;

import api.library.study_library.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "select b from Book b where b.genre.id = :id")
    List<Book> findByGenrer(@Param("id") Integer id);
}
