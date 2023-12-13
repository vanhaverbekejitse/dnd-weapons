package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.enums.WeaponProperty;
import com.fasterxml.jackson.annotation.JsonGetter;

public class WeaponPropertyResponse {
    private WeaponProperty weaponProperty;

    public WeaponPropertyResponse(WeaponProperty weaponProperty) {
        this.weaponProperty = weaponProperty;
    }

    @JsonGetter("name")
    public WeaponProperty getWeaponProperty() {
        return weaponProperty;
    }

    public String getDescription() {
        return weaponProperty.getDescription();
    }
}
