package es.rpiquer.dndsheet.domain.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Level {
    private int id;
    private int level;
    private Class characterClass;
    public Level(int id, int level, Class characterClass) {
        this.id = id;
        this.level = level;
        this.characterClass = characterClass;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public Class getCharacterClass() {
        return characterClass;
    }
    public void setCharacterClass(Class characterClass) {
        this.characterClass = characterClass;
    }
    @Override
    public String toString() {
        return "Level [id=" + id + ", level=" + level + ", characterClass=" + characterClass + "]";
    }

}
