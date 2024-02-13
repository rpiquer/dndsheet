package es.rpiquer.dndsheet.persistence.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.common.dto.RaceDTO;
import es.rpiquer.dndsheet.mapper.RaceMapper;
import es.rpiquer.dndsheet.persistence.dao.RaceDAO;
import es.rpiquer.dndsheet.persistence.dao.impl.repository.RaceJPARepository;

@Component
public class RaceDAOImpl implements RaceDAO {
    final RaceJPARepository raceJPARepository;

    public RaceDAOImpl(RaceJPARepository raceJPARepository) {
        this.raceJPARepository = raceJPARepository;
    }

    @Override
    public Optional<RaceDTO> find(int id) {
        return Optional.ofNullable(
                RaceMapper
                        .mapper
                        .toRaceDTO(
                                raceJPARepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public RaceDTO save(RaceDTO raceDTO) {
        return RaceMapper
                .mapper
                .toRaceDTO(
                        raceJPARepository
                                .save(
                                        RaceMapper
                                                .mapper
                                                .toRaceEntity(raceDTO)
                                )
                );
    }

    @Override
    public void delete(RaceDTO raceDTO) {
        raceJPARepository
                .delete(
                        RaceMapper
                                .mapper
                                .toRaceEntity(raceDTO)
                );
    }
}
