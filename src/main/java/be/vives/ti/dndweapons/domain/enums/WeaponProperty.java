package be.vives.ti.dndweapons.domain.enums;

public enum WeaponProperty {
    AMMUNITION("Ammunition", "You can use a weapon that has the ammunition property to make a ranged attack only if you have ammunition to fire from the weapon. Each time you attack with the weapon, you expend one piece of ammunition. Drawing the ammunition from a quiver, case, or other container is part of the attack (you need a free hand to load a one-handed weapon). At the end of the battle, you can recover half your expended ammunition by taking a minute to search the battlefield. If you use a weapon that has the ammunition property to make a melee attack, you treat the weapon as an improvised weapon. A sling must be loaded to deal any damage when used in this way."),
    FINESSE("Finesse", "When making an attack with a finesse weapon, you use your choice of your Strength or Dexterity modifier for the attack and damage rolls. You must use the same modifier for both rolls."),
    HEAVY("Heavy", "Small creatures have disadvantage on attack rolls with heavy weapons. A heavy weapon’s size and bulk make it too large for a Small creature to use effectively."),
    LIGHT("Light", "A light weapon is small and easy to handle, making it ideal for use when fighting with two weapons."),
    LOADING("Loading", "Because of the time required to load this weapon, you can fire only one piece of ammunition from it when you use an action, bonus action, or reaction to fire it, regardless of the number of attacks you can normally make."),
    REACH("Reach", "This weapon adds 5 feet to your reach when you attack with it, as well as when determining your reach for opportunity attacks with it."),
    SPECIAL("Special", "A weapon with the special property has unusual rules governing its use."),
    THROWN("Thrown", "If a weapon has the thrown property, you can throw the weapon to make a ranged attack. If the weapon is a melee weapon, you use the same ability modifier for that attack roll and damage roll that you would use for a melee attack with the weapon. For example, if you throw a handaxe, you use your Strength, but if you throw a dagger, you can use either your Strength or your Dexterity, since the dagger has the finesse property."),
    TWO_HANDED("Two handed", "This weapon requires two hands when you attack with it."),
    VERSATILE("Versatile", "This weapon can be used with one or two hands.");

    private final String name;
    private final String description;

    WeaponProperty(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
