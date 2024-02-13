package es.rpiquer.dndsheet.domain.service.impl;

import org.springframework.stereotype.Service;

import es.rpiquer.dndsheet.common.dto.RaceDTO;
import es.rpiquer.dndsheet.common.exception.ResourceNotFoundException;
import es.rpiquer.dndsheet.domain.entity.Race;
import es.rpiquer.dndsheet.domain.repository.RaceRepository;
import es.rpiquer.dndsheet.domain.service.RaceService;
import es.rpiquer.dndsheet.mapper.RaceMapper;

@Service
public class RaceServiceImpl implements RaceService{
    final RaceRepository raceRepository;

    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public RaceDTO findById(int id) {
        return RaceMapper
                .mapper
                .toRaceDTO(this.findRace(id));
    }

    private Race findRace(int id) {
        return raceRepository
                                .findById(id)
                                .orElseThrow(
                                        () -> new ResourceNotFoundException("Director not found with id: " + id)
                                );

    }
    @Override
    public RaceDTO create(RaceDTO raceDTO) {
        return RaceMapper
                .mapper
                .toRaceDTO(
                        raceRepository
                                .save(
                                        RaceMapper
                                                .mapper
                                                .toRace(raceDTO)
                                )
                );
    }

    @Override
    public RaceDTO update(RaceDTO raceDTO) {
        Race race = this.findRace(raceDTO.getId());
        RaceMapper.mapper.updateRaceFromRaceDTO(raceDTO, race);
        return RaceMapper
                .mapper
                .toRaceDTO(
                        raceRepository
                                .save(race)
                );
    }

    @Override
    public void delete(int id) {
        Race race = this.findRace(id);
        raceRepository.delete(race);
    }
}
