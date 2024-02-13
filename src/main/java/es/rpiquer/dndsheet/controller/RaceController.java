package es.rpiquer.dndsheet.controller;

import java.util.stream.Stream;

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

import es.rpiquer.dndsheet.common.dto.CharacterDTO;
import es.rpiquer.dndsheet.common.dto.RaceDTO;
import es.rpiquer.dndsheet.controller.http_response.Response;
import es.rpiquer.dndsheet.controller.model.request.RaceRequest;
import es.rpiquer.dndsheet.controller.model.response.CharacterResponse;
import es.rpiquer.dndsheet.domain.service.CharacterService;
import es.rpiquer.dndsheet.domain.service.RaceService;
import es.rpiquer.dndsheet.mapper.CharacterMapper;
import es.rpiquer.dndsheet.mapper.RaceMapper;
import es.rpiquer.dndsheet.common.dto.validation.Validation;

@RequestMapping(RaceController.RACES)
@RestController
public class RaceController {
    @Value("${application.url}")
    private String urlBase;

    public static final String RACES = "/races";

    @Value("${page.size}")
    private int PAGE_SIZE;

    final RaceService raceService;
    final CharacterService characterService;

    public RaceController(RaceService raceService, CharacterService characterService) {
        this.raceService = raceService;
        this.characterService = characterService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        return Response.builder().data(RaceMapper.mapper.toRaceResponse(raceService.findById(id))).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody RaceRequest raceRequest){
        RaceDTO raceDTO = RaceMapper.mapper.toRaceDTO(raceRequest);
        Validation.validate(raceDTO);

        return Response
                .builder()
                .data(
                        RaceMapper
                                .mapper
                                .toRaceResponse(
                                        raceService
                                                .create(raceDTO)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody RaceRequest raceRequest) {
        RaceDTO raceDTO = RaceMapper.mapper.toRaceDTO(raceRequest);
        Validation.validate(raceDTO);

        raceDTO.setId(id);
        return Response
                .builder()
                .data(
                        RaceMapper
                                .mapper
                                .toRaceResponse(
                                        raceService
                                                .update(raceDTO)
                                )
                )
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        raceService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/movies")
    public Response findMoviesByDirectorId(@PathVariable("id") int id) {
        Stream<CharacterDTO> characterDTOStream = characterService.findByRaceId(id);
        Stream<CharacterResponse> characterResponseStream =
                characterDTOStream.map(
                        characterDTO -> {
                            characterDTO.setAge(null);
                            characterDTO.setDeity(null);
                            characterDTO.setStr(null);
                            characterDTO.setDex(null);
                            characterDTO.setCon(null);
                            characterDTO.setInte(null);
                            characterDTO.setWis(null);
                            characterDTO.setCha(null);
                            return CharacterMapper.mapper.toCharacterResponse(characterDTO);
                        }
        );
        long totalRecords = characterService.getTotalNumberOfRecords();
        return Response.builder()
                .data(characterResponseStream)
                .totalRecords(totalRecords)
                .build();

    }
}
