package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AbilityModifierType {
    @JsonProperty("Strength")
    STRENGTH,

    @JsonProperty("Dexterity")
    DEXTERITY,

    @JsonProperty("Finesse")
    FINESSE,

    @JsonProperty("Spellcasting")
    SPELLCASTING;
}

