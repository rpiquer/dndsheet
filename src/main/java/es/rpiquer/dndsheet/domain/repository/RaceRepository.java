package es.rpiquer.dndsheet.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.domain.entity.Race;

@Component
public interface RaceRepository {
    Optional<Race> findById(int id);

    Race save(Race race);

    void delete(Race race);
}
