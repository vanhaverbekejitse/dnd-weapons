package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.responses.AttackResponse;
import be.vives.ti.dndweapons.responses.WeaponListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attacks")
@CrossOrigin("*")
public class AttackController {
    private final AttackRepository attackRepository;

    public AttackController(AttackRepository attackRepository) {
        this.attackRepository = attackRepository;
    }

    @GetMapping
    public Page<AttackResponse> findAllLectors(Pageable pageable){
        return attackRepository.findAll(pageable).map(AttackResponse::new);
    }
}
