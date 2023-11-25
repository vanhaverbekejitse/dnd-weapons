package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.DamageType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class DamageRoll {
    private int amount;
    private int dieType;
    @Enumerated(EnumType.STRING)
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

    public void setDieType(int dietype) {
        this.dieType = dietype;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
