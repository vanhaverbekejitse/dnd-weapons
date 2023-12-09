package be.vives.ti.dndweapons.domain.enums;

public enum CoinType {
    CP("cp"),
    SP("sp"),
    EP("ep"),
    GP("cp"),
    PP("pp");

    private final String name;

    CoinType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
