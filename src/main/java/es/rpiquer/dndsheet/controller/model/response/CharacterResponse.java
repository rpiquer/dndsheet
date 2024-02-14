package es.rpiquer.dndsheet.controller.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import es.rpiquer.dndsheet.common.ApplicationProperties;
import es.rpiquer.dndsheet.controller.CharacterController;
import lombok.Data;

@Data
public class CharacterResponse {
    String link;
    @JsonIgnore
    private Integer id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String alias;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String deity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hp;
    @JsonProperty("strength")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer str;
    @JsonProperty("strengthModifier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer strMod;
    @JsonProperty("dexterity")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer dex;
    @JsonProperty("dexterityModifier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer dexMod;
    @JsonProperty("constitution")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer con;
    @JsonProperty("constitutionModifier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer conMod;
    @JsonProperty("intelligence")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer inte;
    @JsonProperty("intelligenceModifier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer inteMod;
    @JsonProperty("wisdom")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer wis;
    @JsonProperty("wisdomModifier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer wisMod;
    @JsonProperty("charisma")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer cha;
    @JsonProperty("charismaModifier")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer chaMod;
    @JsonProperty("race")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RaceResponse raceResponse;
    
    @JsonProperty("levels")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LevelResponse> levelListResponse;
    
    
    public void setId(int id) {
        this.id = id;
        this.link = String.join("/",ApplicationProperties.getUrl() + CharacterController.CHARACTERS, Integer.toString(this.id)) ;
    }

    public void setStrMod(){
        this.strMod=calculateModifier(str);
    }
    public void setDexMod(){
        this.dexMod=calculateModifier(dex);
    }
    public void setConMod(){
        this.conMod=calculateModifier(con);
    }
    public void setInteMod(){
        this.inteMod=calculateModifier(inte);
    }
    public void setWisMod(){
        this.wisMod=calculateModifier(wis);
    }
    public void setChaMod(){
        this.chaMod=calculateModifier(cha);
    }

    private Integer calculateModifier(Integer attribute){
            Double modifier = Math.floor((attribute-10)/2);
            return modifier.intValue();
        }

    public void setHp(){
        Integer totalHP = 0;
        for (LevelResponse levelResponse : levelListResponse) {
            int classHP = levelResponse.getClassResponse().getLevelHP();
            int levelHP = (classHP+this.conMod)*levelResponse.getLevel();
            totalHP+=levelHP;
        }
        this.hp=totalHP;
    }

    
}
