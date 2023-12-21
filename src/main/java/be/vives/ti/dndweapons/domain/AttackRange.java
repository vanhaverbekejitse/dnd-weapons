package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.RangeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class AttackRange {
    @NotNull
    private RangeType rangeType;

    @Min(0)
    private Integer normalRange;

    @Min(0)
    private Integer longRange;

    protected AttackRange() {

    }

    public AttackRange(RangeType rangeType, Integer normalRange, Integer longRange) {
        this.rangeType = rangeType;
        this.normalRange = normalRange;
        this.longRange = longRange;
    }

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
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
