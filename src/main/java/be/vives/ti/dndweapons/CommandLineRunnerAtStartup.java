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
        addLongsword();
        addCrossbowLight();
        addDart();
        addShortbow();
        addSling();
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
                WeaponType.DART,
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
