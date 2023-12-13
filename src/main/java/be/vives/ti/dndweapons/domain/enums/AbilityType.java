package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AbilityType {
    @JsonProperty("Strength")
    STRENGTH,

    @JsonProperty("Dexterity")
    DEXTERITY,

    @JsonProperty("Finesse")
    FINESSE,

    @JsonProperty("Spell casting")
    SPELL_CASTING;
}

