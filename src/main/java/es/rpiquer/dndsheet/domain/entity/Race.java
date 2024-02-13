package es.rpiquer.dndsheet.domain.entity;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Race {
    private int id;
    private String name;
    private String description;
    private int maxAge;
    private String language;
    private String size;
    private int speed;
    private String feats;

    public Race(int id, String name, String description, int maxAge, String language, String size, int speed,
            String feats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxAge = maxAge;
        this.language = language;
        this.size = size;
        this.speed = speed;
        this.feats = feats;
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

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getFeats() {
        return feats;
    }

    public void setFeats(String feats) {
        this.feats = feats;
    }

    @Override
    public String toString() {
        return "Race [id=" + id + ", name=" + name + ", description=" + description + ", maxAge=" + maxAge
                + ", language=" + language + ", size=" + size + ", speed=" + speed + ", feats=" + feats + "]";
    }

}
