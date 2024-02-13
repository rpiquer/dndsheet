package es.rpiquer.dndsheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import es.rpiquer.dndsheet.common.dto.LevelDTO;
import es.rpiquer.dndsheet.controller.model.request.LevelRequest;
import es.rpiquer.dndsheet.controller.model.response.LevelResponse;
import es.rpiquer.dndsheet.domain.entity.Level;
import es.rpiquer.dndsheet.persistence.model.LevelEntity;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    LevelMapper mapper = Mappers.getMapper(LevelMapper.class);
    
    @Mapping(target = "characterClass", expression = "java(ClassMapper.mapper.toClass(levelEntity.getClassEntity()))")
    Level toLevel(LevelEntity levelEntity);

    @Mapping(target = "characterClass", expression = "java(ClassMapper.mapper.toClass(levelDTO.getClassDTO()))")
    Level toLevel(LevelDTO levelDTO);

    @Mapping(target = "classDTO", expression = "java(ClassMapper.mapper.toClassDTO(levelEntity.getClassEntity()))")
    LevelDTO toLevelDTO(LevelEntity levelEntity);    

    @Mapping(target = "classDTO", expression = "java(ClassMapper.mapper.toClassDTO(level.getCharacterClass()))")
    LevelDTO toLevelDTO(Level level);

    @Mapping(target = "classDTO", expression = "java(ClassMapper.mapper.toClassDTO(levelRequest.getClassRequest()))")
    LevelDTO toLevelDTO(LevelRequest levelRequest);

    @Mapping(target = "classEntity", expression = "java(ClassMapper.mapper.toClassEntity(levelDTO.getClassDTO()))")
    @Mapping(target = "id", ignore = true)
    LevelEntity toLevelEntity(LevelDTO levelDTO);

    @Mapping(target = "classResponse", expression = "java(ClassMapper.mapper.toClassResponse(levelDTO.getClassDTO()))")
    LevelResponse toLevelResponse(LevelDTO levelDTO);
}
