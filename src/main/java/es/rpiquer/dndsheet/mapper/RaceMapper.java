package es.rpiquer.dndsheet.mapper;

import org.mapstruct.Mapper;
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

    RaceDTO toRaceDTO(Integer id);

    RaceDTO toRaceDTO(Race Race);

    RaceEntity toRaceEntity(RaceDTO raceDTO);

    RaceResponse toRaceResponse(RaceDTO raceDTO);

    RaceDTO toRaceDTO(RaceRequest raceRequest);

    void updateRaceFromRaceDTO(RaceDTO raceDTO, @MappingTarget Race race);
}
