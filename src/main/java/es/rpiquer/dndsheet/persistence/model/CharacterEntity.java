package es.rpiquer.dndsheet.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "playercharacter")
@Data
@NoArgsConstructor
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String alias;
    private int age;
    private String deity;
    private int str;
    private int dex;
    private int con;
    private int inte;
    private int wis;
    private int cha;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "race_id")
    private RaceEntity raceEntity;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "playercharacter_id")
    private List<LevelEntity> levelListEntity;

}
