package es.rpiquer.dndsheet.domain.service;

import org.springframework.stereotype.Service;

import es.rpiquer.dndsheet.common.dto.RaceDTO;

@Service
public interface RaceService {
    RaceDTO findById(int id);

    RaceDTO create(RaceDTO raceDTO);
    RaceDTO update(RaceDTO raceDTO);

    void delete(int id);
}
