package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.DamageRoll;
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
    @NotEmpty
    private List<DamageRoll> damageRolls;

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
}
