package be.vives.ti.dndweapons.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CoinType {
    @JsonProperty("cp")
    CP,

    @JsonProperty("sp")
    SP,

    @JsonProperty("ep")
    EP,

    @JsonProperty("gp")
    GP,

    @JsonProperty("pp")
    PP;
}
