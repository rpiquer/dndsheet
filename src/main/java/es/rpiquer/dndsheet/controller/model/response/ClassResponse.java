package es.rpiquer.dndsheet.controller.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import es.rpiquer.dndsheet.common.ApplicationProperties;
import es.rpiquer.dndsheet.controller.ClassController;
import lombok.Data;

@Data
public class ClassResponse {
    private String link;

    @JsonIgnore
    Integer id;

    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer levelHP;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String armor;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String weapons;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String abilities;

    public void setId(int id) {
        this.id = id;
        this.link = String.join("/", ApplicationProperties.getUrl() + ClassController.CLASSES, Integer.toString(this.id)) ;
    }
}
