package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.Range;
import be.vives.ti.dndweapons.domain.enums.RangeType;

import java.util.List;

public class AttackResponse {
    private Long id;

    private String name;

    private int damageModifier;

    private List<DamageRoll> damageRolls;

    private Range range;

    public AttackResponse(Attack attack) {
        this.id = attack.getId();
        this.name = attack.getName();
        this.damageModifier = attack.getDamageModifier();
        this.damageRolls = attack.getDamageRolls();
        this.range = attack.getRange();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public List<DamageRoll> getDamageRolls() {
        return damageRolls;
    }

    public Range getRange() {
        return range;
    }
}
