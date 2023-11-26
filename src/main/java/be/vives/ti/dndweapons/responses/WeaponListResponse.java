package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.Weapon;
import be.vives.ti.dndweapons.domain.enums.RangeType;

public class WeaponListResponse {
    private Long id;

    private String name;

    private RangeType rangeType;

    public WeaponListResponse(Weapon weapon) {
        id = weapon.getId();
        name = weapon.getName();
        rangeType = weapon.getRangeType();
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

    public RangeType getRangeType() {
        return rangeType;
    }

    public void setRangeType(RangeType rangeType) {
        this.rangeType = rangeType;
    }
}
