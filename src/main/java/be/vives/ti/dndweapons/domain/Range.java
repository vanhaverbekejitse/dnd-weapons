package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.RangeType;
import jakarta.persistence.Embeddable;

@Embeddable
public class Range {
    private RangeType rangeType;

    private Integer normalRange;

    private Integer longRange;

    protected Range() {

    }

    public Range(RangeType rangeType, Integer normalRange, Integer longRange) {
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
