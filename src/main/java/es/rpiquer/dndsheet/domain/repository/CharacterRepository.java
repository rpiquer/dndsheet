package es.rpiquer.dndsheet.domain.repository;

import java.util.Optional;
import java.util.stream.Stream;
import es.rpiquer.dndsheet.domain.entity.Character;
import org.springframework.stereotype.Component;

@Component
public interface CharacterRepository {
    public Stream<Character> getAll(Integer page, Integer pageSize);
    public Optional<Character> findById(int id);
    public long getTotalNumberOfRecords();
    public Character save(Character character);
    public Stream<Character> findByRaceId(int raceId);
    public void delete(Character character);
}
