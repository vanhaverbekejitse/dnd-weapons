package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.AbilityType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Attack extends BaseAttack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int damageModifier;

    private AbilityType abilityType;

    protected Attack() {

    }

    public Attack(String name, int damageModifier, AbilityType abilityType, List<DamageRoll> damageRolls, AttackRange range) {
        super(name, damageRolls, range);
        this.damageModifier = damageModifier;
        this.abilityType = abilityType;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
