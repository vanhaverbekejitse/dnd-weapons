package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.*;
import be.vives.ti.dndweapons.domain.enums.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class WeaponRepositoryTest {
    @Autowired
    private WeaponRepository weaponRepository;

    @Test
    public void simpleCrud() {
        // Create
        List<WeaponProperty> properties = new ArrayList<>();
        properties.add(WeaponProperty.VERSATILE);
        Weapon longsword = weaponRepository.save(new Weapon(
                "Longsword",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                properties,
                WeaponType.MELEE_WEAPON,
                true
        ));
        assertThat(longsword.getId()).isNotNull();
        assertThat(weaponRepository.findAll().size()).isEqualTo(1);

        assertThat(longsword.getName()).isEqualTo("Longsword");
        assertThat(longsword.getCost().getAmount()).isEqualTo(15);
        assertThat(longsword.getCost().getCoinType()).isEqualTo(CoinType.GP);
        assertThat(longsword.getRarity()).isEqualTo(Rarity.COMMON);
        assertThat(longsword.getDamageModifier()).isEqualTo(0);
        assertThat(longsword.getWeight()).isEqualTo(3.0);
        assertThat(longsword.getProperties().size()).isEqualTo(1);
        assertThat(longsword.getProperties().get(0)).isEqualTo(WeaponProperty.VERSATILE);
        assertThat(longsword.getWeaponType()).isEqualTo(WeaponType.MELEE_WEAPON);
        assertThat(longsword.isMartial()).isTrue();

        // Update
        longsword.setName("Flametongue longsword");
        longsword.setCost(new Cost(500, CoinType.SP));
        longsword.setRarity(Rarity.UNCOMMON);
        longsword.setDamageModifier(1);
        longsword.setWeight(4.0);
        properties.add(WeaponProperty.SPECIAL);
        longsword.setProperties(properties);
        longsword.setWeaponType(WeaponType.RANGED_WEAPON);
        longsword.setMartial(false);

        assertThat(longsword.getName()).isEqualTo("Flametongue longsword");
        assertThat(longsword.getCost().getAmount()).isEqualTo(500);
        assertThat(longsword.getCost().getCoinType()).isEqualTo(CoinType.SP);
        assertThat(longsword.getRarity()).isEqualTo(Rarity.UNCOMMON);
        assertThat(longsword.getDamageModifier()).isEqualTo(1);
        assertThat(longsword.getWeight()).isEqualTo(4.0);
        assertThat(longsword.getProperties().size()).isEqualTo(2);
        assertThat(longsword.getProperties().get(0)).isEqualTo(WeaponProperty.VERSATILE);
        assertThat(longsword.getProperties().get(1)).isEqualTo(WeaponProperty.SPECIAL);
        assertThat(longsword.getWeaponType()).isEqualTo(WeaponType.RANGED_WEAPON);
        assertThat(longsword.isMartial()).isFalse();

        // Read
        List<WeaponProperty> properties2 = new ArrayList<>();
        properties2.add(WeaponProperty.AMMUNITION);
        properties2.add(WeaponProperty.LOADING);
        Weapon shortbow = weaponRepository.save(new Weapon(
                "Shortbow",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                properties2,
                WeaponType.RANGED_WEAPON,
                false
        ));
        assertThat(shortbow.getId()).isNotNull();
        assertThat(weaponRepository.findAll().size()).isEqualTo(2);

        Weapon weaponWithId = weaponRepository.findById(shortbow.getId()).get();
        assertThat(weaponWithId.getId()).isEqualTo(shortbow.getId());
        assertThat(weaponWithId.getName()).isEqualTo(shortbow.getName());

        // Delete
        weaponRepository.delete(weaponWithId);
        assertThat(weaponRepository.findAll().size()).isEqualTo(1);
        assertThat(weaponRepository.findById(shortbow.getId()).isEmpty()).isTrue();
    }

    @Test
    void findByNameContainingIgnoreCase() {
        List<WeaponProperty> properties = new ArrayList<>();
        properties.add(WeaponProperty.VERSATILE);
        weaponRepository.save(new Weapon(
                "Longsword",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                properties,
                WeaponType.MELEE_WEAPON,
                true
        ));

        List<WeaponProperty> properties2 = new ArrayList<>();
        properties2.add(WeaponProperty.AMMUNITION);
        properties2.add(WeaponProperty.LOADING);
        weaponRepository.save(new Weapon(
                "Shortbow",
                new Cost(15, CoinType.GP),
                Rarity.COMMON,
                0,
                3.0,
                properties2,
                WeaponType.RANGED_WEAPON,
                false
        ));

        List<Weapon> containsLong =
                weaponRepository.findByNameContainingIgnoreCase("Long", PageRequest.of(0, 10)).stream().toList();
        assertThat(containsLong.size()).isEqualTo(1);
        assertThat(containsLong.get(0).getName()).isEqualTo("Longsword");

        List<Weapon> containsOr =
                weaponRepository.findByNameContainingIgnoreCase("Or", PageRequest.of(0, 10)).stream().toList();
        assertThat(containsOr.size()).isEqualTo(2);

        List<Weapon> containsQ =
                weaponRepository.findByNameContainingIgnoreCase("Q", PageRequest.of(0, 10)).stream().toList();
        assertThat(containsQ.size()).isEqualTo(0);
    }
}
