package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.Weapon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {
    Page<Weapon> findByNameContainingIgnoreCase(String query, Pageable pageable);
}