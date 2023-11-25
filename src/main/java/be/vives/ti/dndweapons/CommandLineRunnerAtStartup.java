package be.vives.ti.dndweapons;

import be.vives.ti.dndweapons.domain.Cost;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.WeaponRange;
import be.vives.ti.dndweapons.domain.enums.*;
import be.vives.ti.dndweapons.repository.WeaponRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineRunnerAtStartup implements CommandLineRunner {
    private final WeaponRepository weaponRepository;

    public CommandLineRunnerAtStartup(WeaponRepository weaponRepository) {
        this.weaponRepository = weaponRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Cost cost = new Cost(15, CoinType.GP);
        List<DamageRoll> damageRolls = new ArrayList<>();
        DamageRoll damageRoll = new DamageRoll(1, 8, DamageType.SLASHING);
        damageRolls.add(damageRoll);
        WeaponRange range = new WeaponRange(null, null);
        List<WeaponProperty> properties = new ArrayList<>();
        properties.add(WeaponProperty.VERSATILE);

        weaponRepository.save(new Weapon(
                "longsword",
                cost, damageRolls,
                3.0,
                properties,
                RangeType.MELEE,
                ProficiencyType.MARTIAL,
                range
        ));
    }
}
