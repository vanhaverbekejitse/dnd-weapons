package be.vives.ti.dndweapons;

import be.vives.ti.dndweapons.domain.*;
import be.vives.ti.dndweapons.domain.enums.*;
import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.repository.WeaponAttackRepository;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineRunnerAtStartup implements CommandLineRunner {
    private final WeaponRepository weaponRepository;
    private final WeaponAttackRepository weaponAttackRepository;
    private final AttackRepository attackRepository;

    public CommandLineRunnerAtStartup(
            WeaponRepository weaponRepository, WeaponAttackRepository weaponAttackRepository, AttackRepository attackRepository) {
        this.weaponRepository = weaponRepository;
        this.weaponAttackRepository = weaponAttackRepository;
        this.attackRepository = attackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addWeapons();
        addAttacks();
    }

    private void addWeapons() {
        addSimpleMeleeWeapons();
        addSimpleRangedWeapons();
        addMartialMeleeWeapons();
        addMartialRangedWeapons();
    }

    private void addSimpleMeleeWeapons() {
        addClub();
        addDagger();
        addGreatclub();
        addHandaxe();
        addJavelin();
        addLightHammer();
        addMace();
        addQuarterstaff();
        addSickle();
        addSpear();
    }

    private void addClub() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Club",
                new Cost(1, CoinType.SP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.LIGHT),
                WeaponType.MACE,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Club",
                List.of(new DamageRoll(1, 4, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addDagger() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Dagger",
                new Cost(2, CoinType.GP),
                Rarity.COMMON,
                0,
                1.0,
                List.of(WeaponProperty.LIGHT, WeaponProperty.FINESSE, WeaponProperty.THROWN),
                WeaponType.KNIFE,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Dagger",
                List.of(new DamageRoll(1, 4, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Dagger (thrown)",
                List.of(new DamageRoll(1, 4, DamageType.PIERCING)),
                new AttackRange(RangeType.THROWN, 20, 60)
        ));

        weapon.addAttack(attack);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }

    private void addGreatclub() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Greatclub",
                new Cost(2, CoinType.SP),
                Rarity.COMMON,
                0,
                10.0,
                List.of(WeaponProperty.TWO_HANDED),
                WeaponType.MACE,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Greatclub",
                List.of(new DamageRoll(1, 8, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addHandaxe() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Handaxe",
                new Cost(5, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.LIGHT, WeaponProperty.THROWN),
                WeaponType.AXE,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Handaxe",
                List.of(new DamageRoll(1, 6, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Handaxe (thrown)",
                List.of(new DamageRoll(1, 6, DamageType.SLASHING)),
                new AttackRange(RangeType.THROWN, 20, 60)
        ));

        weapon.addAttack(attack);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }


    private void addJavelin() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Javelin",
                new Cost(5, CoinType.SP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.THROWN),
                WeaponType.SPEAR,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Javelin",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Javelin (thrown)",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.THROWN, 30, 120)
        ));

        weapon.addAttack(attack);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }


    private void addLightHammer() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Light hammer",
                new Cost(2, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.LIGHT, WeaponProperty.THROWN),
                WeaponType.HAMMER,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Light hammer",
                List.of(new DamageRoll(1, 4, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Light hammer (thrown)",
                List.of(new DamageRoll(1, 4, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.THROWN, 20, 60)
        ));

        weapon.addAttack(attack);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }

    private void addMace() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Mace",
                new Cost(5, CoinType.GP),
                Rarity.COMMON,
                0,
                4.0,
                List.of(),
                WeaponType.MACE,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Mace",
                List.of(new DamageRoll(1, 6, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addQuarterstaff() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Quarterstaff",
                new Cost(2, CoinType.SP),
                Rarity.COMMON,
                0,
                4.0,
                List.of(WeaponProperty.VERSATILE),
                WeaponType.STAFF,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Quarterstaff",
                List.of(new DamageRoll(1, 6, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Quarterstaff (two-handed)",
                List.of(new DamageRoll(1, 8, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }

    private void addSickle() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Sickle",
                new Cost(1, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.LIGHT),
                WeaponType.KNIFE,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Sickle",
                List.of(new DamageRoll(1, 4, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addSpear() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Spear",
                new Cost(1, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                List.of(WeaponProperty.VERSATILE, WeaponProperty.THROWN),
                WeaponType.SPEAR,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Spear",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Spear (thrown)",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.THROWN, 20, 60)
        ));

        WeaponAttack attack3 = weaponAttackRepository.save(new WeaponAttack(
                "Spear (two-handed)",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack);
        weapon.addAttack(attack2);
        weapon.addAttack(attack3);
        weaponRepository.save(weapon);
    }

    private void addSimpleRangedWeapons() {
        addCrossbowLight();
        addDart();
        addShortbow();
        addSling();
    }

    private void addCrossbowLight() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Crossbow, light",
                new Cost(25, CoinType.GP),
                Rarity.COMMON,
                0,
                5.0,
                List.of(WeaponProperty.AMMUNITION, WeaponProperty.LOADING, WeaponProperty.TWO_HANDED),
                WeaponType.CROSSBOW,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Crossbow, light",
                List.of(new DamageRoll(1, 8, DamageType.PIERCING)),
                new AttackRange(RangeType.RANGED, 80, 320)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addDart() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Dart",
                new Cost(5, CoinType.CP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.FINESSE, WeaponProperty.THROWN),
                WeaponType.DART,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Dart",
                List.of(new DamageRoll(1, 4, DamageType.PIERCING)),
                new AttackRange(RangeType.THROWN, 20, 60)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addSling() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Sling",
                new Cost(1, CoinType.SP),
                Rarity.COMMON,
                0,
                0.0,
                List.of(WeaponProperty.AMMUNITION),
                WeaponType.RANGED_WEAPON,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Sling",
                List.of(new DamageRoll(1, 4, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.THROWN, 30, 120)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addShortbow() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Shortbow",
                new Cost(25, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.AMMUNITION, WeaponProperty.TWO_HANDED),
                WeaponType.BOW,
                false
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Shortbow",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.RANGED, 80, 320)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addMartialMeleeWeapons() {
        addBattleaxe();
        addFlail();
        addGlaive();
        addGreataxe();
        addGreatsword();
        addHalberd();
        addLance();
        addLongsword();
        addMaul();
        addMorningstar();
        addPike();
        addRapier();
        addScimitar();
        addShortsword();
        addTrident();
        addWarPick();
        addWarhammer();
        addWhip();
    }

    private void addBattleaxe() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Battleaxe",
                new Cost(10, CoinType.GP),
                Rarity.COMMON,
                0,
                4.0,
                List.of(WeaponProperty.VERSATILE),
                WeaponType.AXE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Battleaxe",
                List.of(new DamageRoll(1, 8, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Battleaxe (two-handed)",
                List.of(new DamageRoll(1, 10, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }

    private void addFlail() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Flail",
                new Cost(10, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.VERSATILE),
                WeaponType.MACE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Flail",
                List.of(new DamageRoll(1, 8, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addGlaive() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Glaive",
                new Cost(20, CoinType.GP),
                Rarity.COMMON,
                0,
                6.0,
                List.of(WeaponProperty.HEAVY, WeaponProperty.REACH, WeaponProperty.TWO_HANDED),
                WeaponType.GLAIVE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Glaive",
                List.of(new DamageRoll(1, 10, DamageType.SLASHING)),
                new AttackRange(RangeType.REACH, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addGreataxe() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Greataxe",
                new Cost(30, CoinType.GP),
                Rarity.COMMON,
                0,
                7.0,
                List.of(WeaponProperty.HEAVY, WeaponProperty.TWO_HANDED),
                WeaponType.AXE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Greataxe",
                List.of(new DamageRoll(1, 12, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addGreatsword() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Greatsword",
                new Cost(50, CoinType.GP),
                Rarity.COMMON,
                0,
                6.0,
                List.of(WeaponProperty.HEAVY, WeaponProperty.TWO_HANDED),
                WeaponType.SWORD,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Greatsword",
                List.of(new DamageRoll(2, 6, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addHalberd() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Halberd",
                new Cost(20, CoinType.GP),
                Rarity.COMMON,
                0,
                6.0,
                List.of(WeaponProperty.HEAVY, WeaponProperty.REACH, WeaponProperty.TWO_HANDED),
                WeaponType.AXE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Halberd",
                List.of(new DamageRoll(1, 10, DamageType.SLASHING)),
                new AttackRange(RangeType.REACH, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addLance() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Lance",
                new Cost(10, CoinType.GP),
                Rarity.COMMON,
                0,
                6.0,
                List.of(WeaponProperty.REACH),
                WeaponType.SPEAR,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Lance",
                List.of(new DamageRoll(1, 12, DamageType.PIERCING)),
                new AttackRange(RangeType.REACH, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addLongsword() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Longsword",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                List.of(WeaponProperty.VERSATILE),
                WeaponType.SWORD,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Longsword",
                List.of(new DamageRoll(1, 8, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Longsword (two-handed)",
                List.of(new DamageRoll(1, 10, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }

    private void addMaul() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Maul",
                new Cost(10, CoinType.GP),
                Rarity.COMMON,
                0,
                10.0,
                List.of(WeaponProperty.HEAVY, WeaponProperty.TWO_HANDED),
                WeaponType.HAMMER,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Maul",
                List.of(new DamageRoll(2, 6, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addMorningstar() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Morningstar",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                4.0,
                List.of(),
                WeaponType.MACE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Morningstar",
                List.of(new DamageRoll(1, 8, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addPike() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Pike",
                new Cost(5, CoinType.GP),
                Rarity.COMMON,
                0,
                18.0,
                List.of(WeaponProperty.HEAVY, WeaponProperty.REACH, WeaponProperty.TWO_HANDED),
                WeaponType.SPEAR,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Pike",
                List.of(new DamageRoll(1, 10, DamageType.PIERCING)),
                new AttackRange(RangeType.REACH, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addRapier() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Rapier",
                new Cost(25, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.FINESSE),
                WeaponType.SWORD,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Rapier",
                List.of(new DamageRoll(1, 8, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addScimitar() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Scimitar",
                new Cost(25, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                List.of(WeaponProperty.FINESSE, WeaponProperty.LIGHT),
                WeaponType.SWORD,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Scimitar",
                List.of(new DamageRoll(1, 6, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addShortsword() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Scimitar",
                new Cost(10, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.FINESSE, WeaponProperty.LIGHT),
                WeaponType.SWORD,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Scimitar",
                List.of(new DamageRoll(1, 6, DamageType.SLASHING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addTrident() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Trident",
                new Cost(5, CoinType.GP),
                Rarity.COMMON,
                0,
                4.0,
                List.of(WeaponProperty.THROWN, WeaponProperty.VERSATILE),
                WeaponType.TRIDENT,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Trident",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Trident (thrown)",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.THROWN, 20, 60)
        ));

        WeaponAttack attack3 = weaponAttackRepository.save(new WeaponAttack(
                "Trident (two-handed)",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weapon.addAttack(attack2);
        weapon.addAttack(attack3);
        weaponRepository.save(weapon);
    }

    private void addWarPick() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "War pick",
                new Cost(5, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(),
                WeaponType.PICKAXE,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "War pick",
                List.of(new DamageRoll(1, 8, DamageType.PIERCING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addWarhammer() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Warhammer",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                2.0,
                List.of(WeaponProperty.VERSATILE),
                WeaponType.HAMMER,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Warhammer",
                List.of(new DamageRoll(1, 8, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        WeaponAttack attack2 = weaponAttackRepository.save(new WeaponAttack(
                "Warhammer (two-handed)",
                List.of(new DamageRoll(1, 10, DamageType.BLUDGEONING)),
                new AttackRange(RangeType.MELEE, null, null)
        ));

        weapon.addAttack(attack1);
        weapon.addAttack(attack2);
        weaponRepository.save(weapon);
    }

    private void addWhip() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Whip",
                new Cost(2, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                List.of(WeaponProperty.FINESSE, WeaponProperty.REACH),
                WeaponType.WHIP,
                true
        ));

        WeaponAttack attack1 = weaponAttackRepository.save(new WeaponAttack(
                "Whip",
                List.of(new DamageRoll(1, 4, DamageType.SLASHING)),
                new AttackRange(RangeType.REACH, null, null)
        ));

        weapon.addAttack(attack1);
        weaponRepository.save(weapon);
    }

    private void addMartialRangedWeapons() {
        addBlowgun();
        addCrossbowHand();
        addCrossbowHeavy();
        addLongbow();
    }

    private void addBlowgun() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Blowgun",
                new Cost(10, CoinType.GP),
                Rarity.COMMON,
                0,
                1.0,
                List.of(WeaponProperty.AMMUNITION, WeaponProperty.LOADING),
                WeaponType.BLOWGUN,
                true
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Blowgun",
                List.of(new DamageRoll(1, 1, DamageType.PIERCING)),
                new AttackRange(RangeType.RANGED, 25, 100)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addCrossbowHand() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Crossbow, hand",
                new Cost(75, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                List.of(WeaponProperty.AMMUNITION, WeaponProperty.LIGHT, WeaponProperty.LOADING),
                WeaponType.CROSSBOW,
                true
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Crossbow, hand",
                List.of(new DamageRoll(1, 6, DamageType.PIERCING)),
                new AttackRange(RangeType.RANGED, 30, 120)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addCrossbowHeavy() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Crossbow, heavy",
                new Cost(50, CoinType.GP),
                Rarity.COMMON,
                0,
                18.0,
                List.of(WeaponProperty.AMMUNITION, WeaponProperty.HEAVY, WeaponProperty.LOADING, WeaponProperty.TWO_HANDED),
                WeaponType.CROSSBOW,
                true
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Crossbow, heavy",
                List.of(new DamageRoll(1, 10, DamageType.PIERCING)),
                new AttackRange(RangeType.RANGED, 100, 400)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addLongbow() {
        Weapon weapon = weaponRepository.save(new Weapon(
                "Longbow",
                new Cost(50, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                List.of(WeaponProperty.AMMUNITION, WeaponProperty.LIGHT, WeaponProperty.TWO_HANDED),
                WeaponType.CROSSBOW,
                true
        ));

        WeaponAttack attack = weaponAttackRepository.save(new WeaponAttack(
                "Longbow",
                List.of(new DamageRoll(1, 8, DamageType.PIERCING)),
                new AttackRange(RangeType.RANGED, 150, 600)
        ));

        weapon.addAttack(attack);
        weaponRepository.save(weapon);
    }

    private void addAttacks() {
        // LONGSWORD
        List<DamageRoll> damageRolls1 = new ArrayList<>();
        AttackRange range1 = new AttackRange(RangeType.MELEE, null, null);
        damageRolls1.add(new DamageRoll(1, 8, DamageType.SLASHING));
        attackRepository.save(new Attack("Longsword", 0, AbilityType.STRENGTH , damageRolls1, range1));

        // FLAMETONGUE LONGSWORD
        List<DamageRoll> damageRolls2 = new ArrayList<>();
        damageRolls2.add(new DamageRoll(1, 8, DamageType.SLASHING));
        damageRolls2.add(new DamageRoll(2, 8, DamageType.FIRE));
        AttackRange range2 = new AttackRange(RangeType.MELEE, null, null);
        attackRepository.save(new Attack("Flametongue Longsword", 0, AbilityType.DEXTERITY, damageRolls2, range2));

        List<DamageRoll> damageRolls4 = new ArrayList<>();
        damageRolls4.add(new DamageRoll(1, 6, DamageType.PIERCING));
        AttackRange range4 = new AttackRange(RangeType.RANGED, 80, 320);
        attackRepository.save(new Attack("Shortbow", 0, AbilityType.DEXTERITY , damageRolls4, range4));

        List<DamageRoll> damageRolls5 = new ArrayList<>();
        damageRolls5.add(new DamageRoll(1, 10, DamageType.SLASHING));
        AttackRange range5 = new AttackRange(RangeType.MELEE, null, null);
        attackRepository.save(new Attack("Longsword (two-handed)", 0, AbilityType.STRENGTH , damageRolls5, range5));
    }
}
