package api.library.study_library.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="genre",schema = "lib")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome",nullable = false)
    private String nome;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        return id.equals(genre.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
