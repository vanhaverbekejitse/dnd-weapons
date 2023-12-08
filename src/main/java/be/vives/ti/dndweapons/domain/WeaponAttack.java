package be.vives.ti.dndweapons.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WeaponAttack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection
    private List<DamageRoll> damageRolls;

    @Embedded
    private Range range;

    protected WeaponAttack() {

    }

    public WeaponAttack(String name, List<DamageRoll> damageRolls, Range range) {
        this.name = name;
        this.damageRolls = damageRolls;
        this.range = range;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DamageRoll> getDamageRolls() {
        return damageRolls;
    }

    public void setDamageRolls(List<DamageRoll> damageRolls) {
        this.damageRolls = damageRolls;
    }

    public Range getRange() {
        return range;
    }

    public void setRange(Range range) {
        this.range = range;
    }
}
