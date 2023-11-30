package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.Attack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttackRepository  extends JpaRepository<Attack, Long> {
}
