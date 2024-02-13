package es.rpiquer.dndsheet.controller.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LevelResponse {
    
    @JsonIgnore
    Integer id;

    private int level;
    
    @JsonProperty("class")
    private ClassResponse classResponse;
}
