package be.vives.ti.dndweapons.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class WeaponRange {
    private Integer normalRange;

    private Integer longRange;

    protected WeaponRange() {

    }

    public WeaponRange(Integer normalRange, Integer longRange) {
        this.normalRange = normalRange;
        this.longRange = longRange;
    }

    public Integer getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(Integer normalRange) {
        this.normalRange = normalRange;
    }

    public Integer getLongRange() {
        return longRange;
    }

    public void setLongRange(Integer longRange) {
        this.longRange = longRange;
    }
}
