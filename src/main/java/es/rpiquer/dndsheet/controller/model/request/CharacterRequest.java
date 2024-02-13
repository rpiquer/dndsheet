package es.rpiquer.dndsheet.controller.model.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class CharacterRequest {
    @Nullable
    private Integer id;
    @Nullable
    private String name;
    @Nullable
    private String alias;
    @Nullable
    private Integer age;
    @Nullable
    private String deity;
    @Nullable
    private Integer str;
    @Nullable
    private Integer dex;
    @Nullable
    private Integer con;
    @Nullable
    private Integer inte;
    @Nullable
    private Integer wis;
    @Nullable
    private Integer cha;

    @Nullable
    @JsonProperty("race")
    private RaceRequest raceRequest;
    
    @Nullable
    @JsonProperty("levels")
    private List<LevelRequest> levelListRequest;
}
