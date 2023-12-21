package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.enums.AbilityType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class AttackResponse {
    private Long id;

    private String name;

    private int damageModifier;

    private AbilityType abilityType;

    private List<DamageRoll> damageRolls;

    private AttackRange range;

    public AttackResponse(Attack attack) {
        this.id = attack.getId();
        this.name = attack.getName();
        this.damageModifier = attack.getDamageModifier();
        this.abilityType = attack.getAbilityType();
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

    public AttackRange getRange() {
        return range;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }
}
