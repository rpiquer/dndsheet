package es.rpiquer.dndsheet.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import es.rpiquer.dndsheet.domain.entity.Character;
import es.rpiquer.dndsheet.domain.entity.Level;
import es.rpiquer.dndsheet.common.dto.CharacterDTO;
import es.rpiquer.dndsheet.common.dto.LevelDTO;
import es.rpiquer.dndsheet.controller.model.request.CharacterRequest;
import es.rpiquer.dndsheet.controller.model.request.LevelRequest;
import es.rpiquer.dndsheet.controller.model.response.CharacterResponse;
import es.rpiquer.dndsheet.controller.model.response.LevelResponse;
import es.rpiquer.dndsheet.persistence.model.CharacterEntity;
import es.rpiquer.dndsheet.persistence.model.LevelEntity;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    CharacterMapper mapper = Mappers.getMapper(CharacterMapper.class);

    @Named("LevelListEntityToLevelList")
    default List<Level> mapLevelListEntityToLevelList(List<LevelEntity> levelListEntity){
        if (levelListEntity==null){
            return null;
        }
        return levelListEntity.stream()
                .map(LevelMapper.mapper::toLevel)
                .toList();
    }

    @Mapping(target = "levelList", expression = "java(mapLevelListDTOToLevelList(characterDTO.getLevelListDTO()))")
    @Mapping(target = "race", expression = "java(RaceMapper.mapper.toRace(characterDTO.getRaceDTO()))")
    Character toCharacter(CharacterDTO characterDTO);

    @Named("LevelListDTOToLevelList")
    default List<Level> mapLevelListDTOToLevelList(List<LevelDTO> levelListDTO){
        if (levelListDTO==null){
            return null;
        }
        return levelListDTO.stream()
                .map(LevelMapper.mapper::toLevel)
                .toList();
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "raceDTO", expression = "java(RaceMapper.mapper.toRaceDTO(characterRequest.getRaceRequest()))")
    @Mapping(target = "levelListDTO", expression = "java(mapLevelListRequestToLevelListDTO(characterRequest.getLevelListRequest()))")
    CharacterDTO toCharacterDTO(CharacterRequest characterRequest);

    @Named("LevelListRequestToLevelListDTO")
    default List<LevelDTO> mapLevelListRequestToLevelListDTO(List<LevelRequest> levelListRequest){
        return levelListRequest.stream()
                                    .map(LevelMapper.mapper::toLevelDTO)
                                    .toList();
    }


    @Mapping(target = "levelListDTO", expression = "java(mapLevelListEntityToLevelListDTO(characterEntity.getLevelListEntity()))")
    @Mapping(target = "raceDTO", expression = "java(RaceMapper.mapper.toRaceDTO(characterEntity.getRaceEntity()))")
    CharacterDTO toCharacterDTO(CharacterEntity characterEntity);
    
    @Named("LevelListEntityToLevelListDTO")
    default List<LevelDTO> mapLevelListEntityToLevelListDTO(List<LevelEntity> levelListEntity){
        if (levelListEntity==null){
            return null;
        }
        return levelListEntity.stream()
                .map(LevelMapper.mapper::toLevelDTO)
                .toList();
    }

    @Mapping(target = "levelListDTO", expression = "java(mapLevelListToLevelListDTO(character.getLevelList()))")
    @Mapping(target = "raceDTO", expression = "java(RaceMapper.mapper.toRaceDTO(character.getRace()))")
    CharacterDTO toCharacterDTO(Character character);

    @Named("LevelListToLevelListDTO")
    default List<LevelDTO> mapLevelListToLevelListDTO(List<Level> levelList){
        if (levelList==null){
            return null;
        }
        return levelList.stream()
                .map(LevelMapper.mapper::toLevelDTO)
                .toList();
    }

    @Mapping(target = "levelListEntity", expression = "java(mapLevelListDTOToLevelListEntity(characterDTO.getLevelListDTO()))")
    @Mapping(target = "raceEntity", expression = "java(RaceMapper.mapper.toRaceEntity(characterDTO.getRaceDTO()))")
    CharacterEntity toCharacterEntity(CharacterDTO characterDTO);

    @Named("LevelListDTOToLevelListEntity")
    default List<LevelEntity> mapLevelListDTOToLevelListEntity(List<LevelDTO> levelListDTO){
        if (levelListDTO==null){
            return null;
        }
        return levelListDTO.stream()
                .map(LevelMapper.mapper::toLevelEntity)
                .toList();
    }

    @Mapping(target = "link", ignore = true)
    @Mapping(target = "levelListResponse", expression = "java(mapLevelListDTOToLevelListResponse(characterDTO.getLevelListDTO()))")
    @Mapping(target = "raceResponse", expression = "java(RaceMapper.mapper.toRaceResponse(characterDTO.getRaceDTO()))")
    CharacterResponse toCharacterResponse(CharacterDTO characterDTO);

    @Named("LevelListDTOToLevelListResponse")
    default List<LevelResponse> mapLevelListDTOToLevelListResponse(List<LevelDTO> levelListDTO){
        if (levelListDTO==null){
            return null;
        }
        return levelListDTO.stream()
                .map(LevelMapper.mapper::toLevelResponse)
                .toList();
    }

    @Mapping(target = "race", expression = "java(RaceMapper.mapper.toRace(characterDTO.getRaceDTO()))")
    @Mapping(target = "levelList", expression = "java(mapLevelListDTOToLevelList(characterDTO.getLevelListDTO()))")
    Character toCharacterWithRaceAndLevels(CharacterDTO characterDTO);

    @Mapping(target = "raceDTO", expression = "java(RaceMapper.mapper.toRaceDTO(characterEntity.getRaceEntity()))")
    @Mapping(target = "levelListDTO", expression = "java(mapLevelListEntityToLevelListDTO(characterEntity.getLevelListEntity()))")
    CharacterDTO toCharacterDTOWithRaceAndLevels(CharacterEntity characterEntity);

    void updateCharacterFromCharacterDTO(CharacterDTO characterDTO, @MappingTarget Character character);
}
