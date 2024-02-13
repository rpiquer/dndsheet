package es.rpiquer.dndsheet.domain.service.impl;

import org.springframework.stereotype.Service;

import es.rpiquer.dndsheet.common.dto.ClassDTO;
import es.rpiquer.dndsheet.common.exception.ResourceNotFoundException;
import es.rpiquer.dndsheet.domain.entity.Class;
import es.rpiquer.dndsheet.domain.repository.ClassRepository;
import es.rpiquer.dndsheet.domain.service.ClassService;
import es.rpiquer.dndsheet.mapper.ClassMapper;

@Service
public class ClassServiceImpl implements ClassService{
    final ClassRepository classRepository;

    public ClassServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public ClassDTO findById(int id) {
        return ClassMapper
                .mapper
                .toClassDTO(this.findClass(id));
    }

    private Class findClass(int id) {
        return classRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Actor not found with id: " + id)
                );

    }
    @Override
    public ClassDTO create(ClassDTO classDTO) {
        return ClassMapper
                .mapper
                .toClassDTO(
                        classRepository
                                .save(
                                        ClassMapper
                                                .mapper
                                                .toClass(classDTO)
                                )
                );
    }

    @Override
    public ClassDTO update(ClassDTO classDTO) {
        Class characterClass = this.findClass(classDTO.getId());
        ClassMapper.mapper.updateClassFromClassDTO(classDTO, characterClass);
        return ClassMapper
                .mapper
                .toClassDTO(
                        classRepository
                                .save(characterClass)
                );
    }

    @Override
    public void delete(int id) {
        Class characterClass = this.findClass(id);
        classRepository.delete(characterClass);
    }
}
