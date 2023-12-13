package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.enums.AbilityType;
import be.vives.ti.dndweapons.validation.RangeContraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class AttackRequest {
    @NotBlank
    private String name;

    private int damageModifier;

    @NotNull
    private AbilityType abilityType;

    @NotNull
    @Valid
    @NotEmpty
    private List<DamageRoll> damageRolls;

    @NotNull
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

    public AbilityType getAbilityModifierType() {
        return abilityType;
    }

    public void setAbilityModifierType(AbilityType abilityType) {
        this.abilityType = abilityType;
    }
}
