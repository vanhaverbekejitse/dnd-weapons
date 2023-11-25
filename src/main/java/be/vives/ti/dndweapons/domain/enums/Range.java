package be.vives.ti.dndweapons.domain.enums;

import jakarta.persistence.Embeddable;

@Embeddable
public class Range {
    private int normalRange;

    private int longRange;
}
