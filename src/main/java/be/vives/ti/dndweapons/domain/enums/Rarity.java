package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Rarity {
    @JsonProperty("Common")
    COMMON,

    @JsonProperty("Uncommon")
    UNCOMMON,

    @JsonProperty("Rare")
    RARE,

    @JsonProperty("Very rare")
    VERY_RARE,

    @JsonProperty("Legendary")
    LEGENDARY;
}
