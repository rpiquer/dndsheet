package es.rpiquer.dndsheet.persistence.repositoryImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import es.rpiquer.dndsheet.domain.entity.Race;
import es.rpiquer.dndsheet.domain.repository.RaceRepository;
import es.rpiquer.dndsheet.mapper.RaceMapper;
import es.rpiquer.dndsheet.persistence.dao.RaceDAO;

@Repository
public class RaceRepositoryImpl implements RaceRepository{
    @Qualifier("DirectorDaoJpaImpl")
    final RaceDAO raceDAO;

    public RaceRepositoryImpl(RaceDAO raceDAO) {
        this.raceDAO = raceDAO;
    }

    @Override
    public Optional<Race> findById(int id) {
        return Optional.ofNullable(
                RaceMapper
                        .mapper
                        .toRace(
                                raceDAO
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Race save(Race race) {
        return RaceMapper
                .mapper
                .toRace(
                        raceDAO
                                .save(
                                        RaceMapper
                                                .mapper
                                                .toRaceDTO(race)
                                )
                );
    }

    @Override
    public void delete(Race race) {
        raceDAO.delete(
                RaceMapper
                        .mapper
                        .toRaceDTO(race)
        );
    }
}
