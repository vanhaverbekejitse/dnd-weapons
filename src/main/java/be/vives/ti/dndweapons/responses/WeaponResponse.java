package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Cost;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.enums.ProficiencyType;
import be.vives.ti.dndweapons.domain.WeaponRange;
import be.vives.ti.dndweapons.domain.enums.RangeType;
import be.vives.ti.dndweapons.domain.enums.WeaponProperty;

import java.util.List;

public class WeaponResponse {
    private Long id;

    private String name;

    private Cost cost;

    private List<DamageRoll> damageRolls;

    private double weight;

    private List<WeaponProperty> properties;

    private RangeType rangeType;

    private ProficiencyType proficiencyType;

    private WeaponRange range;

    public WeaponResponse(Weapon weapon) {
        this.id = weapon.getId();
        this.name = weapon.getName();
        this.cost = weapon.getCost();
        this.damageRolls = weapon.getDamageRolls();
        this.weight = weapon.getWeight();
        this.properties = weapon.getProperties();
        this.rangeType = weapon.getRangeType();
        this.proficiencyType = weapon.getProficiencyType();
        this.range = weapon.getRange();
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

    public Cost getCost() {
        return cost;
    }

    public void setCost(Cost cost) {
        this.cost = cost;
    }

    public List<DamageRoll> getDamageRolls() {
        return damageRolls;
    }

    public void setDamageRolls(List<DamageRoll> damageRolls) {
        this.damageRolls = damageRolls;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<WeaponProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<WeaponProperty> properties) {
        this.properties = properties;
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
    }

    public ProficiencyType getProficiencyType() {
        return proficiencyType;
    }

    public void setProficiencyType(ProficiencyType proficiencyType) {
        this.proficiencyType = proficiencyType;
    }

    public WeaponRange getRange() {
        return range;
    }

    public void setRange(WeaponRange range) {
        this.range = range;
    }
}
