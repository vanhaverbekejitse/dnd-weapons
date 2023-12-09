package be.vives.ti.dndweapons.domain.enums;

public enum RangeType {
    RANGED("Ranged"),
    MELEE("Melee"),
    THROWN("Thrown"),
    REACH("Reach");

    private final String name;

    RangeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
