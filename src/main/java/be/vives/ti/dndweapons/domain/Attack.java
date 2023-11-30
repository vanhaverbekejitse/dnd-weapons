package be.vives.ti.dndweapons.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Attack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int damageModifier;

    @ElementCollection
    private List<DamageRoll> damageRolls;

    protected Attack() {

    }

    public Attack(String name, int damageModifier, List<DamageRoll> damageRolls) {
        this.name = name;
        this.damageModifier = damageModifier;
        this.damageRolls = damageRolls;
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

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public List<DamageRoll> getDamageRolls() {
        return damageRolls;
    }

    public void setDamageRolls(List<DamageRoll> damageRolls) {
        this.damageRolls = damageRolls;
    }
}
