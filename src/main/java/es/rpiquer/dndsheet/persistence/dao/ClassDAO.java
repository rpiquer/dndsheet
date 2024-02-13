package es.rpiquer.dndsheet.persistence.dao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.common.dto.ClassDTO;

@Component
public interface ClassDAO{
    Optional<ClassDTO> find(int id);

    ClassDTO save(ClassDTO classDTO);

    void delete(ClassDTO classDTO);
}
