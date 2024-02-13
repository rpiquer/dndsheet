package es.rpiquer.dndsheet.persistence.repositoryImpl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import es.rpiquer.dndsheet.domain.entity.Character;
import es.rpiquer.dndsheet.domain.repository.CharacterRepository;
import es.rpiquer.dndsheet.mapper.CharacterMapper;
import es.rpiquer.dndsheet.persistence.dao.CharacterDAO;
import jakarta.transaction.Transactional;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository{
    @Qualifier("CharacterDAOJpaImpl")
    private final CharacterDAO characterDAO;

    public CharacterRepositoryImpl(CharacterDAO characterDAO) {
        this.characterDAO = characterDAO;
    }
    @Override
    public Stream<Character> getAll(Integer page, Integer pageSize) {
        return characterDAO
                .getAll(page, pageSize)
                .map(characterDTO -> CharacterMapper.mapper.toCharacter(characterDTO));
    }

    @Override
    public Optional<Character> findById(int id) {
        return Optional.ofNullable(
                CharacterMapper
                        .mapper
                        .toCharacterWithRaceAndLevels(
                                characterDAO
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public long getTotalNumberOfRecords() {
        return characterDAO.count();
    }

    @Override
    @Transactional
    public Character save(Character character) {
        return CharacterMapper
                .mapper
                .toCharacterWithRaceAndLevels(
                        characterDAO
                                .save(
                                        CharacterMapper
                                                .mapper
                                                .toCharacterDTO(character)
                                )
                );
    }

    @Override
    public Stream<Character> findByRaceId(int raceId) {
        return characterDAO
                .findByRaceId(raceId)
                .map(characterDTO -> CharacterMapper.mapper.toCharacter(characterDTO));
    }

    @Override
    @Transactional
    public void delete(Character character) {
        characterDAO.delete(
                CharacterMapper
                        .mapper
                        .toCharacterDTO(character)
        );
    }

}

