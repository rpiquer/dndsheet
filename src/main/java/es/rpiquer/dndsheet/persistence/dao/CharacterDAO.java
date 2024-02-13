package es.rpiquer.dndsheet.persistence.dao;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.common.dto.CharacterDTO;

@Component
public interface CharacterDAO  {
    Stream<CharacterDTO> getAll(Integer page, Integer pageSize);

    Optional<CharacterDTO> find(int id);

    long count();

    CharacterDTO save(CharacterDTO characterDTO);

    void delete(CharacterDTO characterDTO);

    Stream<CharacterDTO> findByRaceId(int raceId);
}
