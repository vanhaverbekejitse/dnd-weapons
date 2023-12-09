package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WeaponType {
    @JsonProperty("Melee")
    MELEE,

    @JsonProperty("Ranged")
    RANGED;
}

