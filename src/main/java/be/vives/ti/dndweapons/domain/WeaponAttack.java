package be.vives.ti.dndweapons.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class WeaponAttack extends BaseAttack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected WeaponAttack() {

    }

    public WeaponAttack(String name, List<DamageRoll> damageRolls, AttackRange range) {
        super(name, damageRolls, range);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
