package be.vives.ti.dndweapons.domain;

import jakarta.persistence.*;

import java.util.List;

public abstract class BaseAttack {
    private String name;

    @ElementCollection
    private List<DamageRoll> damageRolls;

    @Embedded
    private AttackRange range;

    protected BaseAttack() {

    }

    public BaseAttack(String name, List<DamageRoll> damageRolls, AttackRange range) {
        this.name = name;
        this.damageRolls = damageRolls;
        this.range = range;
    }

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
