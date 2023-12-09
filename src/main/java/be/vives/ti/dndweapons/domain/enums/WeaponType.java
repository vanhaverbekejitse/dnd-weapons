package be.vives.ti.dndweapons.domain.enums;

public enum WeaponType {
    MELEE("Melee"),
    RANGED("Ranged");

    private final String name;

    WeaponType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
