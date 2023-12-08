package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.enums.FightingStyle;
import be.vives.ti.dndweapons.domain.enums.Rarity;

public class WeaponListResponse {
    private Long id;

    private String name;

    private FightingStyle fightingStyle;

    private Rarity rarity;

    public WeaponListResponse(Weapon weapon) {
        id = weapon.getId();
        name = weapon.getName();
        fightingStyle = weapon.getFightingStyle();
        rarity = weapon.getRarity();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public FightingStyle getFightingStyle() {
        return fightingStyle;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
