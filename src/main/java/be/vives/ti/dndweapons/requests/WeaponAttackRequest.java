package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.validation.RangeContraint;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class WeaponAttackRequest {
    @NotBlank
    private String name;

    @NotNull
    @NotEmpty
    @Valid
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
}
