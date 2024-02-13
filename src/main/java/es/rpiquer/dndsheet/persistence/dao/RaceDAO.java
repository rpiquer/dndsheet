package es.rpiquer.dndsheet.persistence.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.common.dto.RaceDTO;

@Component
public interface RaceDAO{
    Optional<RaceDTO> find(int id);

    RaceDTO save(RaceDTO raceDTO);

    void delete(RaceDTO raceDTO);
    
}
