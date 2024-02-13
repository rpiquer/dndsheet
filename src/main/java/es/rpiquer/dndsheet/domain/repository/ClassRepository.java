package es.rpiquer.dndsheet.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.domain.entity.Class;

@Component
public interface ClassRepository {
    Optional<Class> findById(int id);

    Class save(Class characterClass);

    void delete(Class characterClass);
    
}
