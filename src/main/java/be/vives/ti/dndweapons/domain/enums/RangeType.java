package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RangeType {
    @JsonProperty("Ranged")
    RANGED,

    @JsonProperty("Melee")
    MELEE,

    @JsonProperty("Thrown")
    THROWN,

    @JsonProperty("Reach")
    REACH;
}

