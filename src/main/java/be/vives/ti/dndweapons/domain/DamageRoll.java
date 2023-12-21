package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.DamageType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class DamageRoll {
    @Min(1)
    private int amount;
    @Min(1)
    private int dieType;
    @NotNull
    private DamageType damageType;

    public DamageRoll(int amount, int dieType, DamageType damageType) {
        this.amount = amount;
        this.dieType = dieType;
        this.damageType = damageType;
    }

    protected DamageRoll() {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDieType() {
        return dieType;
    }

    public void setDieType(int dieType) {
        this.dieType = dieType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
