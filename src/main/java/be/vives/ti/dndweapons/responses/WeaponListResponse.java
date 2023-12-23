package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.enums.Rarity;
import be.vives.ti.dndweapons.domain.enums.WeaponType;

public class WeaponListResponse {
    private Long id;

    private String name;

    private WeaponType weaponType;

    private Rarity rarity;

    public WeaponListResponse(Weapon weapon) {
        id = weapon.getId();
        name = weapon.getName();
        weaponType = weapon.getWeaponType();
        rarity = weapon.getRarity();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
