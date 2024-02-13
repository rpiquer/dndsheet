package es.rpiquer.dndsheet.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LevelDTO {
    private Integer id;
    private Integer level;
    private ClassDTO classDTO;
    @Override
    public String toString() {
        return "LevelDTO [level=" + level + ", classDTO=" + classDTO + "]";
    }
}
