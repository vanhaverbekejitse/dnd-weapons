package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.WeaponAttack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeaponAttackRepository extends JpaRepository<WeaponAttack, Long> {
    Optional<WeaponAttack> findByName(String name);
}
