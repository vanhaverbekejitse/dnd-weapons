package be.vives.ti.dndweapons.domain.enums;

public enum DamageType {
    SLASHING("Slashing"),
    PIERCING("Piercing"),
    BLUDGEONING("Bludgeoning"),
    POISON("Poison"),
    ACID("Acid"),
    FIRE("Fire"),
    COLD("Cold"),
    RADIANT("Radiant"),
    NECROTIC("Necrotic"),
    LIGHTNING("Lightning"),
    THUNDER("Thunder"),
    FORCE("Force"),
    PSYCHIC("Psychic");

    private final String name;

    DamageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
