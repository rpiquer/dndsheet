package es.rpiquer.dndsheet.common.dto;

import java.util.List;

import es.rpiquer.dndsheet.common.dto.validation.ValidAttribute;
import es.rpiquer.dndsheet.common.exception.DTOValidationException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
public class CharacterDTO {
    @Nullable
    private Integer id;
    @Nullable
    private String name;
    @Nullable
    private String alias;
    @Nullable
    @Min(value = 1, message = "La edad debe ser 1 o mayor")
    private Integer age;
    @Nullable
    private String deity;
    @Nullable
    @ValidAttribute
    private Integer str;
    @Nullable
    @ValidAttribute
    private Integer dex;
    @Nullable
    @ValidAttribute
    private Integer con;
    @Nullable
    @ValidAttribute
    private Integer inte;
    @Nullable
    @ValidAttribute
    private Integer wis;
    @Nullable
    @ValidAttribute
    private Integer cha;
    @Nullable
    private RaceDTO raceDTO;
    @Nullable
    private List<LevelDTO> levelListDTO;

    public void setAge(Integer age){
        if(this.age != null && this.raceDTO.getMaxAge() != null && this.age > this.raceDTO.getMaxAge()){
            throw new DTOValidationException("La edad no puede ser mayor que la edad máxima de la raza");
        }
        this.age =  age;
    }

    @Override
    public String toString() {
        return "CharacterDTO [id=" + id + ", name=" + name + ", alias=" + alias + ", age=" + age + ", deity=" + deity
                + ", str=" + str + ", dex=" + dex + ", con=" + con + ", inte=" + inte + ", wis=" + wis + ", cha=" + cha
                + ", raceDTO=" + raceDTO + ", levelListDTO=" + levelListDTO + "]";
    }
}
