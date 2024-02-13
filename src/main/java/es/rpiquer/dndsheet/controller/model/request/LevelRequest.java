package es.rpiquer.dndsheet.controller.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class LevelRequest {
    @Nullable
    Integer id;
    @Nullable
    Integer level;

    @Nullable
    @JsonProperty("class")
    ClassRequest classRequest;
}
