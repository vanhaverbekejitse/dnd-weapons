package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.ProficiencyType;
import be.vives.ti.dndweapons.domain.enums.RangeType;
import be.vives.ti.dndweapons.domain.enums.WeaponProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Embedded
    private Cost cost;

    private int damageModifier;

    @ElementCollection
    private List<DamageRoll> damageRolls;

    private double weight;

    @ElementCollection
    private List<WeaponProperty> properties;

    @Enumerated(EnumType.STRING)
    private RangeType rangeType;

    @Enumerated(EnumType.STRING)
    private ProficiencyType proficiencyType;

    @Embedded
    private WeaponRange range;

    protected Weapon() {

    }

    public Weapon(
            String name,
            Cost cost,
            int damageModifier,
            List<DamageRoll> damageRolls,
            double weight,
            List<WeaponProperty> properties,
            RangeType rangeType,
            ProficiencyType proficiencyType,
            WeaponRange range) {
        this.name = name;
        this.cost = cost;
        this.damageModifier = damageModifier;
        this.damageRolls = damageRolls;
        this.weight = weight;
        this.properties = properties;
        this.rangeType = rangeType;
        this.proficiencyType = proficiencyType;
        this.range = range;
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
