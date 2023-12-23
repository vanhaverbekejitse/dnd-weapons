package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.WeaponAttack;
import be.vives.ti.dndweapons.exceptions.ResourceNotFoundException;
import be.vives.ti.dndweapons.repository.WeaponAttackRepository;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import be.vives.ti.dndweapons.requests.WeaponAttackRequest;
import be.vives.ti.dndweapons.requests.WeaponRequest;
import be.vives.ti.dndweapons.responses.*;
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
@RequestMapping("/weapons")
@CrossOrigin("*")
public class WeaponController {
    private final WeaponRepository weaponRepository;
    private final WeaponAttackRepository weaponAttackRepository;

    public WeaponController(WeaponRepository weaponRepository, WeaponAttackRepository weaponAttackRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponAttackRepository = weaponAttackRepository;
    }

    @Operation(summary = "Get all weapons", description = "Returns a list with all weapons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of attacks",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = WeaponListResponse.class))) })
    })
    @GetMapping
    public List<WeaponListResponse> findAllWeapons(){
        return weaponRepository.findAll().stream().map(WeaponListResponse::new).toList();
    }

    @Operation(summary = "Get all weapons containing query", description = "Returns a list with all weapons where the name contains the query")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of attacks",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = WeaponListResponse.class))) })
    })
    @GetMapping("/search")
    public List<WeaponListResponse> findByNameContainingIgnoreCase(@RequestParam("query") String query) {
        return weaponRepository.findByNameContainingIgnoreCase(query).stream().map(WeaponListResponse::new).toList();
    }

    @Operation(summary = "Find weapon by id", description = "Returns one weapon by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the weapon",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WeaponResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Weapon not found",
                    content = @Content)
    })
    @GetMapping("/{weaponId}")
    public WeaponResponse retrieveWeaponById(@PathVariable(name = "weaponId") Long weaponId) {
        return new WeaponResponse(
                weaponRepository.findById(weaponId).orElseThrow(() -> new ResourceNotFoundException(weaponId.toString(), "weapon"))
        );
    }

    @Operation(summary = "Find the properties of a weapon", description = "Returns the properties with description of one weapon by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the weapon",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WeaponWithPropertiesResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Weapon not found",
                    content = @Content)
    })
    @GetMapping("/{weaponId}/properties")
    public WeaponWithPropertiesResponse retrieveWeaponByIdWithProperties(@PathVariable(name = "weaponId") Long weaponId) {
        return new WeaponWithPropertiesResponse(weaponRepository.findById(weaponId).orElseThrow(() -> new ResourceNotFoundException(weaponId.toString(), "weapon")));
    }

    @Operation(summary = "Add new weapon", description = "Add new weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Weapon created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createWeapon(@RequestBody @Valid WeaponRequest weaponRequest) {
        Weapon newWeapon = new Weapon(
                weaponRequest.getName(),
                weaponRequest.getCost(),
                weaponRequest.getRarity(),
                weaponRequest.getDamageModifier(),
                weaponRequest.getWeight(),
                weaponRequest.getProperties(),
                weaponRequest.getWeaponType(),
                weaponRequest.isMartial()
        );

        Weapon weapon = weaponRepository.save(newWeapon);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(weapon.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Update existing weapon", description = "Update existing weapon by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weapon updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = WeaponResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Weapon not found",
                    content = @Content)
    })
    @PutMapping("/{weaponId}")
    public WeaponResponse putAttack(@PathVariable(name = "weaponId") Long weaponId,
                                    @RequestBody @Valid WeaponRequest weaponRequest) {
        Weapon weapon = weaponRepository.findById(Long.parseLong(weaponId.toString())).orElseThrow(
                () -> new ResourceNotFoundException(weaponId.toString(), "weapon")
        );

        weapon.setName(weaponRequest.getName());
        weapon.setCost(weaponRequest.getCost());
        weapon.setRarity(weaponRequest.getRarity());
        weapon.setDamageModifier(weaponRequest.getDamageModifier());
        weapon.setWeight(weaponRequest.getWeight());
        weapon.setProperties(weaponRequest.getProperties());
        weapon.setWeaponType(weaponRequest.getWeaponType());
        weapon.setMartial(weaponRequest.isMartial());

        return new WeaponResponse(weaponRepository.save(weapon));
    }

    @Operation(summary = "Delete weapon", description = "Delete weapon and its weapon-attacks by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Weapon deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Weapon not found",
                    content = @Content)
    })
    @DeleteMapping("/{weaponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWeapon(@PathVariable(name = "weaponId") Long weaponId) {
        weaponRepository.findById(weaponId).orElseThrow(() -> new ResourceNotFoundException(weaponId.toString(), "weapon"));

        try {
            weaponRepository.deleteById(weaponId);
        } catch (EmptyResultDataAccessException e) {
            // als het niet bestaat dan hoefde het niet verwijderd te worden
        }
    }

    @Operation(summary = "Add new weapon-attack", description = "Add new weapon-attack to existing weapon")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Weapon-attack created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Weapon not found",
                    content = @Content)
    })
    @PostMapping("/{weaponId}/weapon-attacks")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addAttackToWeapon(@PathVariable(name = "weaponId") Long weaponId, @RequestBody @Valid WeaponAttackRequest weaponAttackRequest) {
        WeaponAttack newWeaponAttack = new WeaponAttack(
                weaponAttackRequest.getName(),
                weaponAttackRequest.getDamageRolls(),
                weaponAttackRequest.getRange()
        );

        WeaponAttack weaponAttack = weaponAttackRepository.save(newWeaponAttack);

        Weapon weapon = weaponRepository.findById(weaponId)
                .orElseThrow(() -> new ResourceNotFoundException(weaponId.toString(), "weapon"));

        weapon.addAttack(weaponAttack);
        weaponRepository.save(weapon);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/weapons/{id}/weapon-attacks")
                .buildAndExpand(weaponAttack.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Delete weapon", description = "Delete weapon-attack by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Weapon attack deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Weapon not found",
                    content = @Content)
    })
    @DeleteMapping("/weapon-attacks/{weaponAttackId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAttackOfWeapon(@PathVariable(name = "weaponAttackId") Long weaponAttackId) {
        try {
            weaponAttackRepository.deleteById(weaponAttackId);
        } catch (EmptyResultDataAccessException e) {
            // als het niet bestaat dan hoefde het niet verwijderd te worden
        }
    }
}
