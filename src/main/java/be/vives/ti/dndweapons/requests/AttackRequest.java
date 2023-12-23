package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.enums.AbilityType;
import jakarta.validation.constraints.NotNull;

public class AttackRequest extends WeaponAttackRequest {
    @NotNull
    private int damageModifier;

    @NotNull
    private AbilityType abilityType;

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
}
