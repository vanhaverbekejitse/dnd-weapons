package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.CoinType;
import be.vives.ti.dndweapons.domain.enums.DamageType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class DamageRoll {
    private int amount;
    private int dietype;
    @Enumerated(EnumType.STRING)
    private DamageType damageType;

    public DamageRoll(int amount, int dietype, DamageType damageType) {
        this.amount = amount;
        this.dietype = dietype;
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

    public int getDietype() {
        return dietype;
    }

    public void setDietype(int dietype) {
        this.dietype = dietype;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }
}
