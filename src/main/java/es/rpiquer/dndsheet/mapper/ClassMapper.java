package es.rpiquer.dndsheet.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import es.rpiquer.dndsheet.common.dto.ClassDTO;
import es.rpiquer.dndsheet.controller.model.request.ClassRequest;
import es.rpiquer.dndsheet.controller.model.response.ClassResponse;
import es.rpiquer.dndsheet.domain.entity.Class;
import es.rpiquer.dndsheet.persistence.model.ClassEntity;

@Mapper(componentModel = "spring")
public interface ClassMapper {
    ClassMapper mapper = Mappers.getMapper(ClassMapper.class);

    Class toClass(ClassEntity classEntity);

    Class toClass(ClassDTO classDTO);

    ClassDTO toClassDTO(ClassEntity classEntity);

    ClassDTO toClassDTO(Class characterClass);

    ClassEntity toClassEntity(ClassDTO classDTO);

    @Mapping(target = "link", ignore = true)
    ClassResponse toClassResponse(ClassDTO classDTO);

    ClassDTO toClassDTO(ClassRequest classRequest);

    void updateClassFromClassDTO(ClassDTO classDTO, @MappingTarget Class characterClass);
}
