package com.nilsw13.spring_replicate.ResponseType.Hardware;

import java.util.List;
import java.util.Map;

/**
 * Represents available hardware options in the Replicate API.
 *
 * This class encapsulates information about the various hardware resources
 * that can be selected when creating predictions or deployments. Different
 * hardware options offer varying levels of computational power, memory,
 * and specialized accelerators (like GPUs), which affect both the speed
 * of model execution and the associated costs.
 *
 * Hardware options in Replicate are typically identified by their SKU
 * (Stock Keeping Unit) and have a human-readable name for easier selection.
 * The available hardware types can vary based on the model requirements
 * and Replicate's current offerings.
 *
 * Common hardware options include:
 * - CPU instances for lightweight models
 * - Various GPU types (T4, A100, etc.) for computationally intensive models
 * - Specialized hardware for specific model architectures
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public class Hardware {
    private String name;
    private String sku;


    public Hardware(String name, String sku) {
        this.name = name;
        this.sku = sku;
    }

    public Hardware() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

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
