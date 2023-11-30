package be.vives.ti.dndweapons;

import be.vives.ti.dndweapons.domain.*;
import be.vives.ti.dndweapons.domain.enums.*;
import be.vives.ti.dndweapons.repository.AttackRepository;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineRunnerAtStartup implements CommandLineRunner {
    private final WeaponRepository weaponRepository;
    private final AttackRepository attackRepository;

    public CommandLineRunnerAtStartup(WeaponRepository weaponRepository, AttackRepository attackRepository) {
        this.weaponRepository = weaponRepository;
        this.attackRepository = attackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addWeapons();
        addAttacks();
    }

    private void addWeapons() {
        // LONGSWORD
        Cost cost = new Cost(15, CoinType.GP);
        List<DamageRoll> damageRolls = new ArrayList<>();
        DamageRoll damageRoll = new DamageRoll(1, 8, DamageType.SLASHING);
        damageRolls.add(damageRoll);
        WeaponRange range = new WeaponRange(null, null);
        List<WeaponProperty> properties = new ArrayList<>();
        properties.add(WeaponProperty.VERSATILE);

        weaponRepository.save(new Weapon(
                "longsword",
                cost,
                0,
                damageRolls,
                3.0,
                properties,
                RangeType.MELEE,
                ProficiencyType.MARTIAL,
                range
        ));

        // SHORTBOW
        Cost cost2 = new Cost(25, CoinType.GP);
        List<DamageRoll> damageRolls2 = new ArrayList<>();
        DamageRoll damageRoll2 = new DamageRoll(1, 6, DamageType.PIERCING);
        damageRolls2.add(damageRoll2);
        WeaponRange range2 = new WeaponRange(80, 320);
        List<WeaponProperty> properties2 = new ArrayList<>();
        properties2.add(WeaponProperty.AMMUNITION);
        properties2.add(WeaponProperty.TWO_HANDED);

        weaponRepository.save(new Weapon(
                "shortbow",
                cost2,
                0,
                damageRolls2,
                2.0,
                properties2,
                RangeType.RANGED,
                ProficiencyType.SIMPLE,
                range2
        ));
    }

    private void addAttacks() {
        // LONGSWORD
        List<DamageRoll> damageRolls1 = new ArrayList<>();
        damageRolls1.add(new DamageRoll(1, 8, DamageType.SLASHING));
        attackRepository.save(new Attack("Longsword", 0, damageRolls1));

        // FLAMETONGUE LONGSWORD
        List<DamageRoll> damageRolls2 = new ArrayList<>();
        damageRolls2.add(new DamageRoll(1, 8, DamageType.SLASHING));
        damageRolls2.add(new DamageRoll(2, 8, DamageType.FIRE));
        attackRepository.save(new Attack("Flametongue Longsword", 0, damageRolls2));
    }
}
