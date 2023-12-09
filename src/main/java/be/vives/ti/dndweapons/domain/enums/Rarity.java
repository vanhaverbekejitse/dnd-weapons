package be.vives.ti.dndweapons.domain.enums;

public enum Rarity {
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    VERY_RARE("Very rare"),
    LEGENDARY("Legendary");

    private final String name;

    Rarity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
