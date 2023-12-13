package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.enums.WeaponProperty;
import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public class WeaponWithPropertiesResponse extends WeaponListResponse {
    private List<WeaponProperty> properties;

    public WeaponWithPropertiesResponse(Weapon weapon) {
        super(weapon);
        this.properties = weapon.getProperties();
    }

    @JsonGetter("properties")
    public List<WeaponPropertyResponse> getWeaponPropertyResponses() {
        return properties.stream().map(WeaponPropertyResponse::new).toList();
    }
}
