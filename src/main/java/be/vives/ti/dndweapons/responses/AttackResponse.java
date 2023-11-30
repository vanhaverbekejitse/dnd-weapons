package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.domain.DamageRoll;

import java.util.List;

public class AttackResponse {
    private Long id;

    private String name;

    private int damageModifier;

    private List<DamageRoll> damageRolls;

    public AttackResponse(Attack attack) {
        this.id = attack.getId();
        this.name = attack.getName();
        this.damageModifier = attack.getDamageModifier();
        this.damageRolls = attack.getDamageRolls();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
