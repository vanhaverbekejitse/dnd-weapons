package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.AbilityModifierType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    private List<DamageRoll> damageRolls;

    @Embedded
    private Range range;

    private int damageModifier;

    private AbilityModifierType abilityModifierType;

    protected Attack() {

    }

    public Attack(String name, int damageModifier, AbilityModifierType abilityModifierType, List<DamageRoll> damageRolls, Range range) {
        this.name = name;
        this.damageRolls = damageRolls;
        this.range = range;
        this.damageModifier = damageModifier;
        this.abilityModifierType = abilityModifierType;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public AbilityModifierType getAbilityModifierType() {
        return abilityModifierType;
    }

    public void setAbilityModifierType(AbilityModifierType abilityModifierType) {
        this.abilityModifierType = abilityModifierType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DamageRoll> getDamageRolls() {
        return damageRolls;
    }

    public void setDamageRolls(List<DamageRoll> damageRolls) {
        this.damageRolls = damageRolls;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }
}
