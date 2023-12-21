package be.vives.ti.dndweapons.repository;

import be.vives.ti.dndweapons.domain.Attack;
import be.vives.ti.dndweapons.domain.TestDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestDomain, Long> {

}
