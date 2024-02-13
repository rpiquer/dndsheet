package es.rpiquer.dndsheet.persistence.repositoryImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import es.rpiquer.dndsheet.domain.repository.ClassRepository;
import es.rpiquer.dndsheet.mapper.ClassMapper;
import es.rpiquer.dndsheet.persistence.dao.ClassDAO;
import es.rpiquer.dndsheet.domain.entity.Class;

@Repository
public class ClassRepositoryImpl implements ClassRepository{
    @Qualifier("ActorDaoJpaImpl")
    final ClassDAO classDAO;

    public ClassRepositoryImpl(ClassDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public Optional<Class> findById(int id) {
        return Optional.ofNullable(
                ClassMapper
                        .mapper
                        .toClass(
                                classDAO
                                        .find(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public Class save(Class characterClass) {
        return ClassMapper
                .mapper
                .toClass(
                        classDAO
                                .save(
                                        ClassMapper
                                                .mapper
                                                .toClassDTO(characterClass)
                                )
                );
    }

    @Override
    public void delete(Class characterClass) {
        classDAO.delete(
                ClassMapper
                        .mapper
                        .toClassDTO(characterClass)
        );
    }
}
