package es.rpiquer.dndsheet.domain.service;

import org.springframework.stereotype.Service;

import es.rpiquer.dndsheet.common.dto.ClassDTO;

@Service
public interface ClassService {
    ClassDTO findById(int id);

    ClassDTO create(ClassDTO classDTO);
    ClassDTO update(ClassDTO classDTO);

    void delete(int id);

    
}
