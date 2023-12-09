package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.enums.WeaponProperty;

public class WeaponPropertyResponse {
    private WeaponProperty weaponProperty;

    public WeaponPropertyResponse(WeaponProperty weaponProperty) {
        this.weaponProperty = weaponProperty;
    }

    public WeaponProperty getWeaponProperty() {
        return weaponProperty;
    }

    public String getDescription() {
        return weaponProperty.getDescription();
    }
}
