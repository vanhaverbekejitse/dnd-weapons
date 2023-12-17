package be.vives.ti.dndweapons.domain;

import be.vives.ti.dndweapons.domain.enums.CoinType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class Cost {
    @NotNull
    @Min(0)
    private int amount;

    @NotNull
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

    public CoinType getCoinType() {
        return coinType;
    }
}
