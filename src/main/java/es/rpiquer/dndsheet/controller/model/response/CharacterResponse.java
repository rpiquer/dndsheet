package es.rpiquer.dndsheet.controller.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.rpiquer.dndsheet.common.ApplicationProperties;
import es.rpiquer.dndsheet.controller.CharacterController;
import lombok.Data;

@Data
public class CharacterResponse {
    String link;
    @JsonIgnore
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alias;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer str;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer dex;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer con;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer inte;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer wis;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer cha;

    @JsonProperty("race")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RaceResponse raceResponse;
    
    @JsonProperty("levels")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LevelResponse> levelListResponse;
    
    
    public void setId(int id) {
        this.id = id;
        this.link = String.join("/",ApplicationProperties.getUrl() + CharacterController.CHARACTERS, Integer.toString(this.id)) ;
    }
}
