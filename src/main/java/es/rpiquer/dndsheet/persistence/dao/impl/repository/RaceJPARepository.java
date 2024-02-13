package es.rpiquer.dndsheet.persistence.dao.impl.repository;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.persistence.model.RaceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Component
public interface RaceJPARepository extends JpaRepository<RaceEntity, Integer> {
    
}
