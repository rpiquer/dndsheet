package es.rpiquer.dndsheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import es.rpiquer.dndsheet.common.dto.RaceDTO;
import es.rpiquer.dndsheet.controller.model.request.RaceRequest;
import es.rpiquer.dndsheet.controller.model.response.RaceResponse;
import es.rpiquer.dndsheet.domain.entity.Race;
import es.rpiquer.dndsheet.persistence.model.RaceEntity;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    RaceMapper mapper = Mappers.getMapper(RaceMapper.class);
    
    Race toRace(RaceEntity raceEntity);

    Race toRace(RaceDTO raceDTO);

    RaceDTO toRaceDTO(RaceEntity raceEntity);

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "feats", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "maxAge", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "size", ignore = true)
    @Mapping(target = "speed", ignore = true)
    RaceDTO toRaceDTO(Integer id);

    RaceDTO toRaceDTO(Race Race);

    RaceEntity toRaceEntity(RaceDTO raceDTO);

    @Mapping(target = "link", ignore = true)
    RaceResponse toRaceResponse(RaceDTO raceDTO);

    @Mapping(target = "id", ignore = true)
    RaceDTO toRaceDTO(RaceRequest raceRequest);

    void updateRaceFromRaceDTO(RaceDTO raceDTO, @MappingTarget Race race);
}
