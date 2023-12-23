package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.Rarity;
import be.vives.ti.dndweapons.domain.enums.WeaponProperty;
import be.vives.ti.dndweapons.domain.enums.WeaponType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Rarity rarity;

    @Embedded
    private Cost cost;

    private int damageModifier;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "weaponAttackId")
    private List<WeaponAttack> weaponAttacks = new ArrayList<>();

    private double weight;

    @ElementCollection
    private List<WeaponProperty> properties;

    @Enumerated(EnumType.STRING)
    private WeaponType weaponType;

    private boolean isMartial;

    protected Weapon() {

    }

    public Weapon(
            String name,
            Cost cost,
            Rarity rarity,
            int damageModifier,
            double weight,
            List<WeaponProperty> properties,
            WeaponType weaponType,
            boolean isMartial) {
        this.name = name;
        this.cost = cost;
        this.rarity = rarity;
        this.damageModifier = damageModifier;
        this.weight = weight;
        this.properties = properties;
        this.weaponType = weaponType;
        this.isMartial = isMartial;
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

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
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

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public boolean isMartial() {
        return isMartial;
    }

    public void setMartial(boolean martial) {
        isMartial = martial;
    }

    public List<WeaponAttack> getWeaponAttacks() {
        return weaponAttacks;
    }

    public void setWeaponAttacks(List<WeaponAttack> weaponAttacks) {
        this.weaponAttacks = weaponAttacks;
    }

    public void addAttack(WeaponAttack attack) {
        this.getWeaponAttacks().add(attack);
    }
}
