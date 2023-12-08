package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.AbilityModifierType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attack extends WeaponAttack {
    private int damageModifier;

    private AbilityModifierType abilityModifierType;

    protected Attack() {

    }

    public Attack(String name, int damageModifier, AbilityModifierType abilityModifierType, List<DamageRoll> damageRolls, Range range) {
        super(name, damageRolls, range);
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
}
