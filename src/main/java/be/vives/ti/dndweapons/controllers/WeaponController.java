package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.WeaponAttack;
import be.vives.ti.dndweapons.exceptions.ResourceNotFoundException;
import be.vives.ti.dndweapons.repository.WeaponAttackRepository;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import be.vives.ti.dndweapons.requests.WeaponAttackRequest;
import be.vives.ti.dndweapons.requests.WeaponRequest;
import be.vives.ti.dndweapons.responses.WeaponListResponse;
import be.vives.ti.dndweapons.responses.WeaponResponse;
import be.vives.ti.dndweapons.responses.WeaponWithPropertiesResponse;
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

    @GetMapping
    public List<WeaponListResponse> findAllWeapons(){
        return weaponRepository.findAll().stream().map(WeaponListResponse::new).toList();
    }

    @GetMapping("/search")
    public List<WeaponListResponse> findByNameContainingIgnoreCase(@RequestParam("query") String query) {
        return weaponRepository.findByNameContainingIgnoreCase(query).stream().map(WeaponListResponse::new).toList();
    }

    @GetMapping("/{weaponId}")
    public WeaponResponse retrieveWeaponById(@PathVariable(name = "weaponId") Long weaponId) {
        return new WeaponResponse(
                weaponRepository.findById(weaponId).orElseThrow(() -> new ResourceNotFoundException(weaponId.toString(), "weapon"))
        );
    }

    @GetMapping("/{weaponId}/properties")
    public WeaponWithPropertiesResponse retrieveWeaponByIdWithProperties(@PathVariable(name = "weaponId") Long weaponId) {
        return new WeaponWithPropertiesResponse(weaponRepository.findById(weaponId).orElseThrow(() -> new ResourceNotFoundException(weaponId.toString(), "weapon")));
    }

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
