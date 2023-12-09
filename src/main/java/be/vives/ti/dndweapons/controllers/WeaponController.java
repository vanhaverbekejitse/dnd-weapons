package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.exceptions.ResourceNotFoundException;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import be.vives.ti.dndweapons.responses.WeaponListResponse;
import be.vives.ti.dndweapons.responses.WeaponResponse;
import be.vives.ti.dndweapons.responses.WeaponWithPropertiesResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weapons")
@CrossOrigin("*")
public class WeaponController {
    private final WeaponRepository weaponRepository;

    public WeaponController(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @GetMapping
    public Page<WeaponListResponse> findAllWeapons(Pageable pageable){
        return weaponRepository.findAll(pageable).map(WeaponListResponse::new);
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
}
