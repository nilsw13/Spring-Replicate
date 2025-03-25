package com.nilsw13.spring_replicate.ResponseType.Hardware;

import java.util.List;
import java.util.Map;

public class Hardware {
    private String name;
    private String sku;

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    @Override
    public String toString() {
        return "Hardware{" +
                "name='" + name + '\'' +
                ", sku='" + sku + '\'' +
                '}';
    }
}
