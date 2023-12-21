package be.vives.ti.dndweapons.responses;

import be.vives.ti.dndweapons.domain.TestDomain;

public class TestResponse {
    private Long id;

    private String name;

    public TestResponse(TestDomain testDomain) {
        id = testDomain.getId();
        name = testDomain.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
