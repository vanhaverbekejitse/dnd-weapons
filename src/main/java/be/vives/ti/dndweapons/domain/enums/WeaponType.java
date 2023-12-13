package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WeaponType {
    @JsonProperty("Melee weapon")
    MELEE_WEAPON,

    @JsonProperty("Ranged weapon")
    RANGED_WEAPON
}
