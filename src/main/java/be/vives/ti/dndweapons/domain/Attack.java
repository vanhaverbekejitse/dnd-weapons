package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.AbilityType;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    private List<DamageRoll> damageRolls;

    @Embedded
    private AttackRange range;

    private int damageModifier;

    @Enumerated(EnumType.STRING)
    private AbilityType abilityType;

    protected Attack() {

    }

    public Attack(String name, int damageModifier, AbilityType abilityType, List<DamageRoll> damageRolls, AttackRange range) {
        this.name = name;
        this.damageRolls = damageRolls;
        this.range = range;
        this.damageModifier = damageModifier;
        this.abilityType = abilityType;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public AbilityType getAbilityType() {
        return abilityType;
    }

    public void setAbilityType(AbilityType abilityType) {
        this.abilityType = abilityType;
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
