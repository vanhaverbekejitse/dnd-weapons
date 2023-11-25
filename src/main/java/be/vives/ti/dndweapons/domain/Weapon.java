package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.ProficiencyType;
import be.vives.ti.dndweapons.domain.enums.Range;
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

    @ElementCollection
    private List<DamageRoll> damageRolls;

    private int weight;

    @ElementCollection
    private List<WeaponProperty> properties;

    @Enumerated(EnumType.STRING)
    private RangeType rangeType;

    @Enumerated(EnumType.STRING)
    private ProficiencyType proficiencyType;

    @Embedded
    private Range range;

    protected Weapon() {

    }

    public Weapon(
            Long id,
            String name,
            Cost cost,
            List<DamageRoll> damageRolls,
            int weight,
            List<WeaponProperty> properties,
            RangeType rangeType,
            ProficiencyType proficiencyType,
            Range range) {
        this.id = id;
        this.name = name;
        this.cost = cost;
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

    public List<DamageRoll> getDamageRolls() {
        return damageRolls;
    }

    public void setDamageRolls(List<DamageRoll> damageRolls) {
        this.damageRolls = damageRolls;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
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

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }
}
