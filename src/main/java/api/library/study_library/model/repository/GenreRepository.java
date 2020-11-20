package api.library.study_library.model.repository;

import api.library.study_library.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre,Integer> {

}
