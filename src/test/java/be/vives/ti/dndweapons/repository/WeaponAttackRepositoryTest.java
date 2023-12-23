package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.AttackRange;
import be.vives.ti.dndweapons.domain.DamageRoll;
import be.vives.ti.dndweapons.domain.WeaponAttack;
import be.vives.ti.dndweapons.domain.enums.DamageType;
import be.vives.ti.dndweapons.domain.enums.RangeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class WeaponAttackRepositoryTest {
    @Autowired
    private WeaponAttackRepository weaponAttackRepository;

    @Test
    public void simpleCrud() {
        // Create
        List<DamageRoll> damageRolls1 = new ArrayList<>();
        AttackRange range1 = new AttackRange(RangeType.MELEE, null, null);
        damageRolls1.add(new DamageRoll(1, 8, DamageType.SLASHING));
        WeaponAttack longsword = weaponAttackRepository.save(
                new WeaponAttack("Longsword", damageRolls1, range1));
        assertThat(longsword.getId()).isNotNull();
        assertThat(weaponAttackRepository.findAll().size()).isEqualTo(1);

        assertThat(longsword.getName()).isEqualTo("Longsword");

        List<DamageRoll> testDamageRolls = longsword.getDamageRolls();
        assertThat(testDamageRolls.size()).isEqualTo(1);
        assertThat(testDamageRolls.get(0).getAmount()).isEqualTo(1);
        assertThat(testDamageRolls.get(0).getDieType()).isEqualTo(8);
        assertThat(testDamageRolls.get(0).getDamageType()).isEqualTo(DamageType.SLASHING);

        AttackRange testRange = longsword.getRange();
        assertThat(testRange.getRangeType()).isEqualTo(RangeType.MELEE);
        assertThat(testRange.getNormalRange()).isNull();
        assertThat(testRange.getLongRange()).isNull();

        // Update
        longsword.setName("Longsword (two-handed)");
        List<DamageRoll>  updateDamageRolls = new ArrayList<>();
        updateDamageRolls.add(new DamageRoll(2, 6, DamageType.PIERCING));
        longsword.setDamageRolls(updateDamageRolls);
        longsword.setRange(new AttackRange(RangeType.THROWN, 20, 40));
        longsword = weaponAttackRepository.save(longsword);

        assertThat(longsword.getName()).isEqualTo("Longsword (two-handed)");

        List<DamageRoll> testUpdateDamageRolls = longsword.getDamageRolls();
        assertThat(testUpdateDamageRolls.size()).isEqualTo(1);
        assertThat(testUpdateDamageRolls.get(0).getAmount()).isEqualTo(2);
        assertThat(testUpdateDamageRolls.get(0).getDieType()).isEqualTo(6);
        assertThat(testUpdateDamageRolls.get(0).getDamageType()).isEqualTo(DamageType.PIERCING);

        AttackRange testUpdateRange = longsword.getRange();
        assertThat(testUpdateRange.getRangeType()).isEqualTo(RangeType.THROWN);
        assertThat(testUpdateRange.getNormalRange()).isEqualTo(20);
        assertThat(testUpdateRange.getLongRange()).isEqualTo(40);

        // Read
        List<DamageRoll> damageRolls2 = new ArrayList<>();
        damageRolls2.add(new DamageRoll(1, 6, DamageType.PIERCING));
        AttackRange range2 = new AttackRange(RangeType.RANGED, 80, 320);
        WeaponAttack shortbow = weaponAttackRepository.save(
                new WeaponAttack("Shortbow", damageRolls2, range2));
        assertThat(shortbow.getId()).isNotNull();
        assertThat(weaponAttackRepository.findAll().size()).isEqualTo(2);

        WeaponAttack attackWithId = weaponAttackRepository.findById(shortbow.getId()).get();
        assertThat(attackWithId.getId()).isEqualTo(shortbow.getId());
        assertThat(attackWithId.getName()).isEqualTo(shortbow.getName());

        // Delete
        weaponAttackRepository.delete(attackWithId);
        assertThat(weaponAttackRepository.findAll().size()).isEqualTo(1);
        assertThat(weaponAttackRepository.findById(shortbow.getId()).isEmpty()).isTrue();
    }
}
