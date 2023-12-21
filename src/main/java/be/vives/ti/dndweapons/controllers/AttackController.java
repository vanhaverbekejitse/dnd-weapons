package be.vives.ti.dndweapons.controllers;

import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.domain.TestDomain;
import be.vives.ti.dndweapons.exceptions.ResourceNotFoundException;
import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.repository.TestRepository;
import be.vives.ti.dndweapons.requests.AttackRequest;
import be.vives.ti.dndweapons.responses.AttackResponse;
import be.vives.ti.dndweapons.responses.TestResponse;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/attacks")
@CrossOrigin("*")
public class AttackController {
    private final AttackRepository attackRepository;

    public AttackController(AttackRepository attackRepository, TestRepository testRepository) {
        this.attackRepository = attackRepository;
        this.testRepository = testRepository;
    }

    @GetMapping
    public Page<AttackResponse> findAllAttacks(Pageable pageable){
        Page<AttackResponse> response = attackRepository.findAll(pageable).map(AttackResponse::new);
        return response;
    }

    // VERWIJDEREN
    @GetMapping("/test")
    public List<AttackResponse> findAllAttacksTest(){
        List<AttackResponse> response = attackRepository.findAll().stream().map(AttackResponse::new).toList();
        return response;
    }
    private final TestRepository testRepository;
    // VERWIJDEREN
    @GetMapping("/pageTest")
    public Page<TestResponse> findAllTests(Pageable pageable){
        //Page<TestResponse> response = testRepository.findAll(pageable).map(TestResponse::new);
        Page<TestDomain> domains = testRepository.findAll(pageable);
        List<TestResponse> list = new ArrayList<>();
        for (TestDomain test: domains) {
            list.add(new TestResponse(test));
        }
        Page page = new PageImpl(list);
        return page;
    }

    @GetMapping("/{attackId}")
    public AttackResponse retrieveAttackById(@PathVariable(name = "attackId") Long attackId) {
        return new AttackResponse(
                attackRepository.findById(attackId).orElseThrow(() -> new ResourceNotFoundException(attackId.toString(), "attack"))
        );
    }

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
