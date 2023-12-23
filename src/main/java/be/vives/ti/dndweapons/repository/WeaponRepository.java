package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    List<Weapon> findByNameContainingIgnoreCase(String query);
}