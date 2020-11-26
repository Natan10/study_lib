package api.library.study_library.model.entity;

import api.library.study_library.enums.GenreEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="genre",schema = "lib")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome",nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum nome;

    @OneToMany(mappedBy = "genre")
    @JsonIgnore
    private List<Book> books;


}
