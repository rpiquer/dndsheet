package es.rpiquer.dndsheet.domain.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import es.rpiquer.dndsheet.common.dto.CharacterDTO;
import es.rpiquer.dndsheet.common.dto.LevelDTO;
import es.rpiquer.dndsheet.common.dto.RaceDTO;
import es.rpiquer.dndsheet.common.exception.BadRequestException;
import es.rpiquer.dndsheet.common.exception.ResourceNotFoundException;
import es.rpiquer.dndsheet.domain.entity.Character;
import es.rpiquer.dndsheet.domain.entity.Level;
import es.rpiquer.dndsheet.domain.entity.Race;
import es.rpiquer.dndsheet.domain.entity.Class;
import es.rpiquer.dndsheet.domain.repository.CharacterRepository;
import es.rpiquer.dndsheet.domain.repository.ClassRepository;
import es.rpiquer.dndsheet.domain.repository.RaceRepository;
import es.rpiquer.dndsheet.domain.service.CharacterService;
import es.rpiquer.dndsheet.mapper.CharacterMapper;
import es.rpiquer.dndsheet.mapper.LevelMapper;
import jakarta.validation.ValidationException;

@Service
public class CharacterServiceImpl implements CharacterService{

    private CharacterRepository characterRepository;
    private RaceRepository raceRepository;
    private ClassRepository classRepository;
    
    public CharacterServiceImpl(CharacterRepository characterRepository, RaceRepository raceRepository, ClassRepository classRepository) {
        this.characterRepository = characterRepository;
        this.raceRepository = raceRepository;
        this.classRepository = classRepository;
    }

    @Override
    public Stream<CharacterDTO> getAll(Integer page, Integer pageSize) {
        return characterRepository
                .getAll(page, pageSize)
                .map(CharacterMapper.mapper::toCharacterDTO);
    }

    @Override
    public Stream<CharacterDTO> getAll() {
        return characterRepository.getAll(null, null)
                .map(CharacterMapper.mapper::toCharacterDTO);
    }

    @Override
    public CharacterDTO findById(int id) {
        return CharacterMapper
                .mapper
                .toCharacterDTO(
                        this.findCharacter(id)
                );
    }

    private Character findCharacter(int id) {
        return characterRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Character not found with id: " + id)
                );
    }

    @Override
    public long getTotalNumberOfRecords() {
        return characterRepository.getTotalNumberOfRecords();
    }

    @Override
    public CharacterDTO create(CharacterDTO characterDTO) {
        Character character = CharacterMapper.mapper.toCharacter(characterDTO);
        this.addRaceDTO(character, characterDTO.getRaceDTO());

        if (character.getAge() > character.getRace().getMaxAge()+10) {
            throw new ValidationException("La edad no puede sobrepasar tanto la edad máxima de la raza.");
        }

        List<LevelDTO> levelListDTO = characterDTO.getLevelListDTO();
        List<Level> levelList = levelListDTO.stream().map(LevelMapper.mapper::toLevel).toList();
        levelList.forEach(
                level -> {
                    level
                            .setCharacterClass(
                                    classRepository
                                    .findById(
                                            level.getCharacterClass().getId())
                                    .orElseThrow(
                                            () -> new ResourceNotFoundException("Class not found with id: " + level.getCharacterClass().getId())
                                    )
                    );
                }
        );
        character.setLevelList(levelList);

        return CharacterMapper
                .mapper
                .toCharacterDTO(
                        characterRepository
                                .save(character)
                );  
    }

    private void addRaceDTO(Character character, RaceDTO raceDTO) {
        if(raceDTO == null || raceDTO.getId() == null) {
            throw new BadRequestException("Race cannot be null");
        }
        Race race = raceRepository
                .findById(raceDTO.getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Race not found with id: " + raceDTO.getId())
                );
        character.setRace(race);
    }

    @Override
    public CharacterDTO update(CharacterDTO characterDTO) {
        if(characterDTO.getId() == null) {
            throw new BadRequestException("Character cannot be null");
        }
        Character character = this.findCharacter(characterDTO.getId());
        this.addRaceDTO(character, characterDTO.getRaceDTO());

        CharacterMapper.mapper.updateCharacterFromCharacterDTO(characterDTO, character);
        return CharacterMapper
                .mapper
                .toCharacterDTO(
                        characterRepository
                                .save(character)
                );
    }

    @Override
    public Stream<CharacterDTO> findByRaceId(int raceId) {
        return characterRepository
                .findByRaceId(raceId)
                .map(CharacterMapper.mapper::toCharacterDTO);
    }

    @Override
    public void delete(int id) {
        Character character = this.findCharacter(id);
        characterRepository.delete(character);
    }

    @Override
    public CharacterDTO addLevel(int id, LevelDTO levelDTO) {
        Character character = this.findCharacter(id);
        Level level = this.buildLevel(levelDTO);
        character.addLevel(level);
        return CharacterMapper
                .mapper
                .toCharacterDTO(
                        characterRepository.save(character)
                );
    }

    @Override
    public CharacterDTO updateLevel(int id, LevelDTO levelDTO) {
        Character character = this.findCharacter(id);
        Level level = this.buildLevel(levelDTO);
        character.updateLevel(level);
        return CharacterMapper
                .mapper
                .toCharacterDTO(
                        characterRepository.save(character)
                );
    }

    private Level buildLevel(LevelDTO levelDTO) {
        Level level = LevelMapper
                .mapper
                .toLevel(levelDTO);
        Class characterClass = classRepository.findById(
                        level
                                .getCharacterClass()
                                .getId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Class not found with id: " + level.getCharacterClass().getId())
                );
        level.setCharacterClass(characterClass);
        return level;
    }

    @Override
    public CharacterDTO deleteLevel(int characterId, int levelId) {
        Character character = this.findCharacter(characterId);
        character.deleteLevel(levelId);
        return CharacterMapper
                .mapper
                .toCharacterDTO(
                        characterRepository.save(character)
                );
    }
    
}
