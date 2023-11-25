package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.repository.WeaponRepository;
import be.vives.ti.dndweapons.responses.WeaponResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weapons")
@CrossOrigin("*")
public class WeaponController {
    private final WeaponRepository weaponRepository;

    public WeaponController(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @GetMapping
    public Page<WeaponResponse> findAllLectors(Pageable pageable){
        return weaponRepository.findAll(pageable).map(WeaponResponse::new);
    }
}
