package api.library.study_library.dto;

import api.library.study_library.enums.GenreEnum;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDto {
    private GenreEnum nome;

}
