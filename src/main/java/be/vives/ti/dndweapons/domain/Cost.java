package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.CoinType;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Cost {
    private int amount;

    @Enumerated(EnumType.STRING)
    private CoinType coinType;

    public Cost(int amount, CoinType coinType) {
        this.amount = amount;
        this.coinType = coinType;
    }

    protected Cost() {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCoinType(CoinType coinType) {
        this.coinType = coinType;
    }

    @JsonGetter("coinType")
    public String getCoinTypeName() {
        return coinType.getName();
    }
}
