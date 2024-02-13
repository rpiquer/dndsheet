package es.rpiquer.dndsheet.persistence.dao.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.persistence.model.ClassEntity;

@Component
public interface ClassJPARepository extends JpaRepository<ClassEntity, Integer> {

    
    
}
