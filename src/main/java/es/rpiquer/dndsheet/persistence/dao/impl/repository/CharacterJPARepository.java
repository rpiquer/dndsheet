package es.rpiquer.dndsheet.persistence.dao.impl.repository;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import es.rpiquer.dndsheet.persistence.model.*;

@Component
public interface CharacterJPARepository extends JpaRepository<CharacterEntity, Integer> {
    List<CharacterEntity> findByRaceEntityId(int raceId);
}
