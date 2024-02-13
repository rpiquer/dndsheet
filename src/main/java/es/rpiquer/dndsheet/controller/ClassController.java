package es.rpiquer.dndsheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.rpiquer.dndsheet.common.dto.ClassDTO;
import es.rpiquer.dndsheet.controller.http_response.Response;
import es.rpiquer.dndsheet.controller.model.request.ClassRequest;
import es.rpiquer.dndsheet.domain.service.ClassService;
import es.rpiquer.dndsheet.mapper.ClassMapper;
import es.rpiquer.dndsheet.common.dto.validation.Validation;

@RequestMapping(ClassController.CLASSES)
@RestController
public class ClassController {
    @Value("${application.url}")
    private String urlBase;

    public static final String CLASSES = "/classes";

    @Autowired
    private ClassService classService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response
                .builder()
                .data(
                        ClassMapper
                                .mapper
                                .toClassResponse(
                                        classService
                                                .findById(id)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody ClassRequest classRequest){
        ClassDTO classDTO = ClassMapper.mapper.toClassDTO(classRequest);
        Validation.validate(classDTO);
        return Response
                .builder()
                .data(
                        ClassMapper
                                .mapper
                                .toClassResponse(
                                        classService.create(classDTO)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody ClassRequest classRequest) {
        ClassDTO classDTO = ClassMapper.mapper.toClassDTO(classRequest);
        classDTO.setId(id);
        Validation.validate(classDTO);
        return Response
                .builder()
                .data(
                        ClassMapper
                                .mapper
                                .toClassResponse(
                                        classService
                                                .update(classDTO)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        classService.delete(id);
    }
}