package es.rpiquer.dndsheet.controller.model.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class RaceRequest {
    @Nullable
    private Integer id;
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private Integer maxAge;
    @Nullable
    private String language;
    @Nullable
    private String size;
    @Nullable
    private Integer speed;
    @Nullable
    private String feats;
}
