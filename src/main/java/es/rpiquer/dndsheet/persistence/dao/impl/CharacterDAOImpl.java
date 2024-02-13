package es.rpiquer.dndsheet.persistence.dao.impl;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.common.dto.CharacterDTO;
import es.rpiquer.dndsheet.mapper.CharacterMapper;
import es.rpiquer.dndsheet.persistence.dao.CharacterDAO;
import es.rpiquer.dndsheet.persistence.dao.impl.repository.CharacterJPARepository;

@Component
public class CharacterDAOImpl implements CharacterDAO {
    final CharacterJPARepository characterJPARepository;

    public CharacterDAOImpl(CharacterJPARepository characterJPARepository) {
        this.characterJPARepository = characterJPARepository;
    }

    @Override
    public Stream<CharacterDTO> getAll(Integer page, Integer pageSize) {
        if(page != null && page > 0) {
            Pageable pageable = PageRequest.of(page - 1, pageSize);
            return characterJPARepository
                    .findAll(pageable)
                    .stream()
                    .map(CharacterMapper.mapper::toCharacterDTO);
        }
        return characterJPARepository
                .findAll()
                .stream()
                .map(CharacterMapper.mapper::toCharacterDTO);
    }

    @Override
    public Optional<CharacterDTO> find(int id) {
        return Optional.ofNullable(
                CharacterMapper
                        .mapper
                        .toCharacterDTOWithRaceAndLevels(
                                characterJPARepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public long count() {
        return characterJPARepository.count();
    }

    @Override
    public CharacterDTO save(CharacterDTO characterDTO) {
        return CharacterMapper
                .mapper
                .toCharacterDTOWithRaceAndLevels(
                        characterJPARepository
                                .save(
                                        CharacterMapper
                                                .mapper
                                                .toCharacterEntity(characterDTO)
                                )
                );
    }

    @Override
    public void delete(CharacterDTO characterDTO) {
        characterJPARepository
                .delete(
                        CharacterMapper
                                .mapper
                                .toCharacterEntity(characterDTO)
                );
    }

    @Override
    public Stream<CharacterDTO> findByRaceId(int raceId) {
        return characterJPARepository
                .findByRaceEntityId(raceId)
                .stream()
                .map(CharacterMapper.mapper::toCharacterDTO);
    }
}
