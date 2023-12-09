package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.enums.WeaponProperty;

public class WeaponPropertyResponse {
    private String name;

    private String description;

    public WeaponPropertyResponse(WeaponProperty weaponProperty) {
        this.name = weaponProperty.getName();
        this.description = weaponProperty.getDescription();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
