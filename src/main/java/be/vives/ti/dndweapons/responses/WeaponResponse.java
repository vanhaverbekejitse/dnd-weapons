package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.*;
import be.vives.ti.dndweapons.domain.enums.WeaponProperty;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class WeaponResponse extends WeaponListResponse {

    private Cost cost;

    private int damageModifier;

    private List<WeaponAttack> weaponAttacks;

    private double weight;

    private List<WeaponProperty> properties;

    private boolean isMartial;

    public WeaponResponse(Weapon weapon) {
        super(weapon);
        this.cost = weapon.getCost();
        this.damageModifier = weapon.getDamageModifier();
        this.weight = weapon.getWeight();
        this.properties = weapon.getProperties();
        this.isMartial = weapon.isMartial();
        this.weaponAttacks = weapon.getWeaponAttacks();
    }

    public Cost getCost() {
        return cost;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public double getWeight() {
        return weight;
    }

    public List<WeaponProperty> getProperties() {
        return properties;
    }

    @JsonGetter("isMartial")
    public boolean isMartial() {
        return isMartial;
    }

    @JsonGetter("attacks")
    public List<WeaponAttack> getWeaponAttacks() {
        return weaponAttacks;
    }
}
