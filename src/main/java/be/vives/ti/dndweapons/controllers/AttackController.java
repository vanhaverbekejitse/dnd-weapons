package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.exceptions.ResourceNotFoundException;
import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.requests.AttackRequest;
import be.vives.ti.dndweapons.responses.AttackResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/attacks")
@CrossOrigin("*")
public class AttackController {
    private final AttackRepository attackRepository;

    public AttackController(AttackRepository attackRepository) {
        this.attackRepository = attackRepository;
    }

    @Operation(summary = "Get all attacks", description = "Returns a list with all attacks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of attacks",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AttackResponse.class))) })
    })
    @GetMapping
    public List<AttackResponse> findAllAttacks(){
        return attackRepository.findAll().stream().map(AttackResponse::new).toList();
    }

    @Operation(summary = "Find attack by id", description = "Returns one attack by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the attack",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttackResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Attack not found",
                    content = @Content)
    })
    @GetMapping("/{attackId}")
    public AttackResponse retrieveAttackById(@PathVariable(name = "attackId") Long attackId) {
        return new AttackResponse(
                attackRepository.findById(attackId).orElseThrow(() -> new ResourceNotFoundException(attackId.toString(), "attack"))
        );
    }

    @Operation(summary = "Add new attack", description = "Add new attack")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Attack created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createAttack(@RequestBody @Valid AttackRequest attackRequest) {
        Attack newAttack = new Attack(
                attackRequest.getName(),
                attackRequest.getDamageModifier(),
                attackRequest.getAbilityType(),
                attackRequest.getDamageRolls(),
                attackRequest.getRange()
        );

        Attack attack = attackRepository.save(newAttack);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(attack.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing attack", description = "Update existing attack by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Attack updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AttackResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Attack not found",
                    content = @Content)
    })
    @PutMapping("/{attackId}")
    public AttackResponse putAttack(@PathVariable(name = "attackId") Long attackId,
                                    @RequestBody @Valid AttackRequest attackRequest) {
        Attack attack = attackRepository.findById(attackId).orElseThrow(
                () -> new ResourceNotFoundException(attackId.toString(), "attack")
        );

        attack.setName(attackRequest.getName());
        attack.setDamageModifier(attackRequest.getDamageModifier());
        attack.setAbilityType(attackRequest.getAbilityType());
        attack.setDamageRolls(attackRequest.getDamageRolls());
        attack.setRange(attackRequest.getRange());

        return new AttackResponse(attackRepository.save(attack));
    }

    @Operation(summary = "Delete attack", description = "Delete attack by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Attack deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Attack not found",
                    content = @Content)
    })
    @DeleteMapping("/{attackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttack(@PathVariable(name = "attackId") Long attackId) {
        attackRepository.findById(attackId).orElseThrow(() -> new ResourceNotFoundException(attackId.toString(), "attack"));

        try {
            attackRepository.deleteById(attackId);
        } catch (EmptyResultDataAccessException e) {
            // als het niet bestaat dan hoefde het niet verwijderd te worden
        }
    }
}
