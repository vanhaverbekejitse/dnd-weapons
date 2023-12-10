package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.enums.AbilityModifierType;
import be.vives.ti.dndweapons.validation.RangeContraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AttackRequest {
    @NotBlank
    @NotEmpty
    private String name;

    private int damageModifier;

    @NotNull
    private AbilityModifierType abilityModifierType;

    @Valid
    @NotEmpty
    private List<DamageRoll> damageRolls;

    @Valid
    @RangeContraint
    private AttackRange range;

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

    public AttackRange getRange() {
        return range;
    }

    public void setRange(AttackRange range) {
        this.range = range;
    }

    public AbilityModifierType getAbilityModifierType() {
        return abilityModifierType;
    }

    public void setAbilityModifierType(AbilityModifierType abilityModifierType) {
        this.abilityModifierType = abilityModifierType;
    }
}
