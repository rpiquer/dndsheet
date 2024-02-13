package es.rpiquer.dndsheet.domain.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Class {
    private int id;
    private String name;
    private String description;
    private int levelHP;
    private String armor;
    private String weapons;
    private String abilities;
    
    public Class(int id, String name, String description, int levelHP, String armor, String weapons, String abilities) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.levelHP = levelHP;
        this.armor = armor;
        this.weapons = weapons;
        this.abilities = abilities;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getLevelHP() {
        return levelHP;
    }
    public void setLevelHP(int levelHP) {
        this.levelHP = levelHP;
    }
    public String getArmor() {
        return armor;
    }
    public void setArmor(String armor) {
        this.armor = armor;
    }
    public String getWeapons() {
        return weapons;
    }
    public void setWeapons(String weapons) {
        this.weapons = weapons;
    }
    public String getAbilities() {
        return abilities;
    }
    public void setAbilities(String abilities) {
        this.abilities = abilities;
    }
    @Override
    public String toString() {
        return "Class [id=" + id + ", name=" + name + ", description=" + description + ", levelHP=" + levelHP
                + ", armor=" + armor + ", weapons=" + weapons + ", abilities=" + abilities + "]";
    }
    
    
}
