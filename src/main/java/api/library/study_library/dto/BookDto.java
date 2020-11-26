package api.library.study_library.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private Integer id;
    private String nome;
    private Integer author;
    private Integer genre;
    private Date dataPublicacao;

}
