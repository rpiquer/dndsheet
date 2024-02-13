package es.rpiquer.dndsheet.controller.model.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ClassRequest {
    @Nullable
    private Integer id;
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private Integer levelHP;
    @Nullable
    private String armor;
    @Nullable
    private String weapons;
    @Nullable
    private String abilities;
}
