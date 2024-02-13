package es.rpiquer.dndsheet.controller;

import es.rpiquer.dndsheet.common.dto.CharacterDTO;
import es.rpiquer.dndsheet.common.dto.LevelDTO;
import es.rpiquer.dndsheet.common.dto.validation.Validation;
import es.rpiquer.dndsheet.controller.http_response.Response;
import es.rpiquer.dndsheet.controller.model.request.CharacterRequest;
import es.rpiquer.dndsheet.controller.model.request.LevelRequest;
import es.rpiquer.dndsheet.controller.model.response.CharacterResponse;
import es.rpiquer.dndsheet.domain.service.CharacterService;
import es.rpiquer.dndsheet.mapper.CharacterMapper;
import es.rpiquer.dndsheet.mapper.LevelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Stream;

@RequestMapping(CharacterController.CHARACTERS)
@RestController
public class CharacterController {

    @Value("${application.url}")
    private String urlBase;

    public static final String CHARACTERS = "/characters";

    @Autowired
    private CharacterService characterService;

    @Value("${page.size}")
    private int PAGE_SIZE;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public Response findAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        pageSize = (pageSize != null)? pageSize : PAGE_SIZE;
        Stream<CharacterDTO> characterStreamDTO = (page != null)? characterService.getAll(page, pageSize) : characterService.getAll();

        Stream<CharacterResponse> characterResponseStream =
                characterStreamDTO.map(
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
        Response response = Response.builder()
                .data(characterResponseStream)
                .totalRecords(totalRecords)
                .build();

        if(page != null) {
            response.paginate(page, pageSize, urlBase);
        }
        return response;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Response find(@PathVariable("id") int id) {
        CharacterResponse characterResponse = CharacterMapper
                .mapper
                .toCharacterResponse(
                        characterService.findById(id)
                );
        this.removeDataFromResponse(characterResponse);
        return Response
                .builder()
                .data(characterResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Response create(@RequestBody CharacterRequest characterRequest) {
        CharacterDTO characterDTO = CharacterMapper
                .mapper
                .toCharacterDTO(characterRequest);
        Validation.validate(characterDTO);
        CharacterResponse characterResponse = CharacterMapper
        .mapper
        .toCharacterResponse(
                characterService
                        .create(characterDTO)
        );
        this.removeDataFromResponse(characterResponse);
        return Response
                .builder()
                .data(characterResponse)
                .build();
        }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Response update(@PathVariable("id") int id, @RequestBody CharacterRequest characterRequest) {
        CharacterDTO characterDTO = CharacterMapper.mapper.toCharacterDTO(characterRequest);
        Validation.validate(characterDTO);
        characterDTO.setId(id);
        CharacterResponse characterResponse = CharacterMapper
                .mapper
                .toCharacterResponse(
                        characterService
                                .update(characterDTO)
                );
        this.removeDataFromResponse(characterResponse);
        return Response
                .builder()
                .data(characterResponse)
                .build();
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        characterService.delete(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/levels")
    public Response addLevel(@PathVariable("id") int id, @RequestBody LevelRequest levelRequest){
        LevelDTO levelDTO = LevelMapper.mapper.toLevelDTO(levelRequest);
        CharacterResponse characterResponse = CharacterMapper
                .mapper
                .toCharacterResponse(
                        characterService
                                .addLevel(id, levelDTO)
                );
        this.removeDataFromResponse(characterResponse);
        return Response
                .builder()
                .data(characterResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/levels/{levelId}")
    public Response updateLevel(
            @PathVariable("id") int id,
            @PathVariable("levelId") int levelId,
            @RequestBody LevelRequest levelRequest){
        LevelDTO levelDTO = LevelMapper.mapper.toLevelDTO(levelRequest);
        levelDTO.setId(levelId);
        CharacterResponse characterResponse = CharacterMapper
                .mapper
                .toCharacterResponse(
                        characterService
                                .updateLevel(id, levelDTO)
                );
        this.removeDataFromResponse(characterResponse);
        return Response
                .builder()
                .data(characterResponse)
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}/characters/{characterId}")
    public Response deleteCharacterMovie(
            @PathVariable("id") int id,
            @PathVariable("levelId") int levelId) {
        CharacterResponse characterResponse = CharacterMapper
                .mapper
                .toCharacterResponse(
                        characterService
                                .deleteLevel(id, levelId)
                );
        this.removeDataFromResponse(characterResponse);
        return Response
                .builder()
                .data(characterResponse)
                .build();
    }

    private void removeDataFromResponse(CharacterResponse characterResponse) {
        characterResponse
                .getRaceResponse()
                .setDescription(null);
        characterResponse
                .getRaceResponse()
                .setMaxAge(null);
        characterResponse
                .getRaceResponse()
                .setLanguage(null);               
        characterResponse
                .getRaceResponse()
                .setSize(null);
        characterResponse
                .getRaceResponse()
                .setSpeed(null);
        characterResponse
                .getRaceResponse()
                .setFeats(null);
        characterResponse
                .getLevelListResponse()
                .stream()
                .forEach(
                        levelResponse -> {
                                levelResponse
                                    .getClassResponse()
                                    .setDescription(null);
                                levelResponse
                                    .getClassResponse()
                                    .setLevelHP(null);
                                levelResponse
                                    .getClassResponse()
                                    .setArmor(null);
                                levelResponse
                                    .getClassResponse()
                                    .setWeapons(null);
                                levelResponse
                                    .getClassResponse()
                                    .setAbilities(null);
                        }
                );
    }
}
