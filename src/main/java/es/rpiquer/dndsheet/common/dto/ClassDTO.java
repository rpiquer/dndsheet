package es.rpiquer.dndsheet.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ClassDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer levelHP;
    private String armor;
    private String weapons;
    private String abilities;
    @Override
    public String toString() {
        return "ClassDTO [id=" + id + ", name=" + name + ", description=" + description + ", levelHP=" + levelHP
                + ", armor=" + armor + ", weapons=" + weapons + ", abilities=" + abilities + "]";
    }
}
