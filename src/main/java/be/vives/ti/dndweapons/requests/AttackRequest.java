package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.Range;
import be.vives.ti.dndweapons.domain.enums.AbilityModifierType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AttackRequest {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    private int damageModifier;

    @NotNull
    private AbilityModifierType abilityModifierType;

    @NotNull
    @NotEmpty
    private List<DamageRoll> damageRolls;

    @NotNull
    private Range range;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
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

    public AbilityModifierType getAbilityModifierType() {
        return abilityModifierType;
    }

    public void setAbilityModifierType(AbilityModifierType abilityModifierType) {
        this.abilityModifierType = abilityModifierType;
    }
}
