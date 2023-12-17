package be.vives.ti.dndweapons.requests;

import be.vives.ti.dndweapons.domain.Cost;
import be.vives.ti.dndweapons.domain.WeaponAttack;
import be.vives.ti.dndweapons.domain.enums.Rarity;
import be.vives.ti.dndweapons.domain.enums.WeaponProperty;
import be.vives.ti.dndweapons.domain.enums.WeaponType;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class WeaponRequest {
    @NotBlank
    private String name;

    @NotNull
    private Rarity rarity;

    @NotNull
    @Valid
    private Cost cost;

    @NotNull
    private int damageModifier;

    @NotNull
    @Min(0)
    private double weight;

    @NotNull
    private List<WeaponProperty> properties;

    @NotNull
    private WeaponType weaponType;

    @NotNull
    private boolean isMartial;

    public String getName() {
        return name;
    }

    public Rarity getRarity() {
        return rarity;
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

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public boolean isMartial() {
        return isMartial;
    }
}
