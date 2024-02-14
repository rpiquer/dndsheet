package es.rpiquer.dndsheet.common.dto;

import es.rpiquer.dndsheet.common.dto.validation.ValidLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LevelDTO {
    private Integer id;
    @ValidLevel
    private Integer level;
    private ClassDTO classDTO;
    @Override
    public String toString() {
        return "LevelDTO [level=" + level + ", classDTO=" + classDTO + "]";
    }
}
