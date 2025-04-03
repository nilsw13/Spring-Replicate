package com.nilsw13.springboot.replicate.service;

import com.nilsw13.springboot.replicate.responsetype.hardware.Hardware;
import com.nilsw13.springboot.replicate.exception.ReplicateApiException;

/**
 * Service interface for accessing hardware information from the Replicate API.
 *
 * This service provides methods to retrieve information about the various hardware
 * options available for predictions and deployments in Replicate. Hardware options
 * represent the different computational resources (such as CPU or GPU types) that
 * can be selected when running models or creating deployments.
 *
 * Knowledge of available hardware options is important for optimizing both performance
 * and cost when using Replicate. Different models may perform better on specific
 * hardware types, and pricing varies based on the hardware selected.
 *
 * Usage example:
 *
 * Hardware[] availableHardware = replicate.hardware().list();
 * for (Hardware hardware : availableHardware) {
 *     System.out.println("Option: " + hardware.getName() + " (SKU: " + hardware.getSku() + ")");
 * }
 *
 * @author Nilsw13
 * @since 1.0.0
 */
public interface HardwareService {


    /**
     * Retrieves a list of all available hardware options.
     *
     * This method fetches information about all hardware types that can be used
     * for predictions and deployments in Replicate. The returned array includes
     * details such as the human-readable name and the SKU (unique identifier)
     * for each hardware option.
     *
     * When creating a prediction or deployment, you can specify a hardware option
     * by using its SKU. Selecting the appropriate hardware can significantly
     * impact both performance and cost.
     *
     * @return An array of Hardware objects representing available hardware options
     * @throws ReplicateApiException If an error occurs while fetching hardware information
     */
    Hardware[] list();

}
