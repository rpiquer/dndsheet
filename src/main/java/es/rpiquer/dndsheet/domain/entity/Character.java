package es.rpiquer.dndsheet.domain.entity;

import java.util.List;
import java.util.Objects;

import es.rpiquer.dndsheet.common.exception.ResourceNotFoundException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Character {
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
    private Race race;
    private List<Level> levelList;
    
    public Character(int id, String name, String alias, int age, String deity, int str, int dex, int con, int inte,
            int wis, int cha, Race race, List<Level> levelList) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.age = age;
        this.deity = deity;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.inte = inte;
        this.wis = wis;
        this.cha = cha;
        this.race = race;
        this.levelList = levelList;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDeity() {
        return deity;
    }
    public void setDeity(String deity) {
        this.deity = deity;
    }
    public int getStr() {
        return str;
    }
    public void setStr(int str) {
        this.str = str;
    }
    public int getDex() {
        return dex;
    }
    public void setDex(int dex) {
        this.dex = dex;
    }
    public int getCon() {
        return con;
    }
    public void setCon(int con) {
        this.con = con;
    }
    public int getInte() {
        return inte;
    }
    public void setInte(int inte) {
        this.inte = inte;
    }
    public int getWis() {
        return wis;
    }
    public void setWis(int wis) {
        this.wis = wis;
    }
    public int getCha() {
        return cha;
    }
    public void setCha(int cha) {
        this.cha = cha;
    }
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }
    public List<Level> getLevelList() {
        return levelList;
    }
    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }
    @Override
    public String toString() {
        return "Character [id=" + id + ", name=" + name + ", alias=" + alias + ", age=" + age + ", deity=" + deity
                + ", str=" + str + ", dex=" + dex + ", con=" + con + ", inte=" + inte + ", wis=" + wis + ", cha=" + cha
                + ", race=" + race + ", levelList=" + levelList + "]";
    }

    public void addLevel(Level level) {
        /*if(level.getCharacterClass().get() != null && characterMovie.getActor().getBirthYear() > this.year) {
            throw new ValidationException("El año de nacimiento del actor no puede ser mayor que el de la película.");
        }*/
        levelList.add(level);
    }

    public void updateLevel(Level updatedLevel) {
        /*if(updatedCharacterMovie.getActor().getBirthYear() != null && updatedCharacterMovie.getActor().getBirthYear() > this.year) {
            throw new ValidationException("El año de nacimiento del actor no puede ser mayor que el de la película.");
        }*/

        levelList.stream()
                .filter(level -> Objects.equals(level.getId(), updatedLevel.getId()))
                .findFirst()
                .ifPresentOrElse(level -> {
                    level.setCharacterClass(updatedLevel.getCharacterClass());
                    level.setLevel(updatedLevel.getLevel());
                }, () -> { throw new ResourceNotFoundException("Nivel no encontrado con id: " + updatedLevel.getId());}
                );
    }

    public void deleteLevel(int levelId) {
        /*if(this.characterMovieList.size() == 1) {
            throw new ValidationException("Una película tiene que tener, como mínimo, un personaje");
        }*/
        boolean removed = this.levelList.removeIf(level -> level.getId() == levelId);
        if(!removed) {
            throw new ResourceNotFoundException("Nivel no encontrado con id: " + levelId);
        }
    }

    
    
}
