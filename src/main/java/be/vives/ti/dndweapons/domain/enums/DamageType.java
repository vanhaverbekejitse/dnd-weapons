package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum DamageType {
    @JsonProperty("Slashing")
    SLASHING,

    @JsonProperty("Piercing")
    PIERCING,

    @JsonProperty("Bludgeoning")
    BLUDGEONING,

    @JsonProperty("Poison")
    POISON,

    @JsonProperty("Acid")
    ACID,

    @JsonProperty("Fire")
    FIRE,

    @JsonProperty("Cold")
    COLD,

    @JsonProperty("Radiant")
    RADIANT,

    @JsonProperty("Necrotic")
    NECROTIC,

    @JsonProperty("Lightning")
    LIGHTNING,

    @JsonProperty("Thunder")
    THUNDER,

    @JsonProperty("Force")
    FORCE,

    @JsonProperty("Psychic")
    PSYCHIC;
}
