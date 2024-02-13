package es.rpiquer.dndsheet.persistence.dao.impl;

import java.util.Optional;

import org.springframework.stereotype.Component;

import es.rpiquer.dndsheet.common.dto.ClassDTO;
import es.rpiquer.dndsheet.mapper.ClassMapper;
import es.rpiquer.dndsheet.persistence.dao.ClassDAO;
import es.rpiquer.dndsheet.persistence.dao.impl.repository.ClassJPARepository;

@Component
public class ClassDAOImpl implements ClassDAO {
    final ClassJPARepository classJPARepository;

    public ClassDAOImpl(ClassJPARepository classJPARepository) {
        this.classJPARepository = classJPARepository;
    }
    @Override
    public Optional<ClassDTO> find(int id) {
        return Optional.ofNullable(
                ClassMapper
                        .mapper
                        .toClassDTO(
                                classJPARepository
                                        .findById(id)
                                        .orElse(null)
                        )
        );
    }

    @Override
    public ClassDTO save(ClassDTO classDTO) {
        return ClassMapper
                .mapper
                .toClassDTO(
                        classJPARepository
                                .save(
                                        ClassMapper
                                                .mapper
                                                .toClassEntity(classDTO)
                                )
                );
    }

    @Override
    public void delete(ClassDTO classDTO) {
        classJPARepository
                .delete(
                        ClassMapper
                                .mapper
                                .toClassEntity(classDTO)
                );
    }

}
