package be.vives.ti.dndweapons.domain.enums;

public enum AbilityModifierType {
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    FINESSE("Finesse"),
    SPELLCASTING("Spellcasting");

    private final String name;

    AbilityModifierType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
