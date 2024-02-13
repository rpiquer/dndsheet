package es.rpiquer.dndsheet.domain.service;

import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import es.rpiquer.dndsheet.common.dto.CharacterDTO;
import es.rpiquer.dndsheet.common.dto.LevelDTO;

@Service
public interface CharacterService {
    public Stream<CharacterDTO> getAll(Integer page, Integer pageSize);
    public Stream<CharacterDTO> getAll();
    public long getTotalNumberOfRecords();
    public CharacterDTO findById(int id);
    public CharacterDTO create(CharacterDTO characterDTO);
    public CharacterDTO update(CharacterDTO characterDTO);
    public Stream<CharacterDTO> findByRaceId(int raceId);
    public void delete(int id);
    public CharacterDTO addLevel(int id, LevelDTO levelDTO);
    public CharacterDTO updateLevel(int id, LevelDTO levelDTO);
    public CharacterDTO deleteLevel(int characterId, int levelId);
}
