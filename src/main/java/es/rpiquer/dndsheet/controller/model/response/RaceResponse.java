package es.rpiquer.dndsheet.controller.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import es.rpiquer.dndsheet.common.ApplicationProperties;
import es.rpiquer.dndsheet.controller.RaceController;
import lombok.Data;

@Data
public class RaceResponse {
    
    String link;

    @JsonIgnore
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer maxAge;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String language;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String size;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer speed;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String feats;

    public void setId(int id) {
        this.id = id;
        this.link = String.join("/", ApplicationProperties.getUrl() + RaceController.RACES, Integer.toString(this.id)) ;
    }
}
