package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WeaponType {
    @JsonProperty("Melee weapon")
    MELEE_WEAPON,

    @JsonProperty("Ranged weapon")
    RANGED_WEAPON,

    @JsonProperty("Axe")
    AXE,

    @JsonProperty("Blowgun")
    BLOWGUN,

    @JsonProperty("Bow")
    BOW,

    @JsonProperty("Crossbow")
    CROSSBOW,

    @JsonProperty("Dart")
    DART,

    @JsonProperty("Glaive")
    GLAIVE,

    @JsonProperty("Hammer")
    HAMMER,

    @JsonProperty("Knife")
    KNIFE,

    @JsonProperty("Mace")
    MACE,

    @JsonProperty("Pickaxe")
    PICKAXE,

    @JsonProperty("Spear")
    SPEAR,

    @JsonProperty("Staff")
    STAFF,

    @JsonProperty("Sword")
    SWORD,

    @JsonProperty("Trident")
    TRIDENT,

    @JsonProperty("Whip")
    WHIP
}
