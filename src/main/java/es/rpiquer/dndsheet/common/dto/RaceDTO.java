package es.rpiquer.dndsheet.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class RaceDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer maxAge;
    private String language;
    private String size;
    private Integer speed;
    private String feats;
    @Override
    public String toString() {
        return "RaceDTO [id=" + id + ", name=" + name + ", description=" + description + ", maxAge=" + maxAge
                + ", language=" + language + ", size=" + size + ", speed=" + speed + ", feats=" + feats + "]";
    }
}
