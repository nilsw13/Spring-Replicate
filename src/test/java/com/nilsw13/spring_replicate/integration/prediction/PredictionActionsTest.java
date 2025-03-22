package com.nilsw13.spring_replicate.integration.prediction;

import com.nilsw13.spring_replicate.integration.BaseReplicateTest;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionResponse;
import com.nilsw13.spring_replicate.ResponseType.Prediction.PredictionsListResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PredictionActionsTest extends BaseReplicateTest {

    @Test
    void testGetPredictionList() {
        PredictionsListResponse predictionsListResponse = replicate.prediction().list();

        assertThat(predictionsListResponse).isNotNull();
        assertThat(predictionsListResponse.getResults()).isNotEmpty();
    }

    @Test
    void testGetPredictionById(){
        String id = "ecbevw573srm80cnhaesdmp0rc";
        PredictionResponse predictionResponse = replicate.prediction().get(id);

        assertThat(predictionResponse).isNotNull();
        assertThat(predictionResponse.getInput()).isNotEmpty();

        System.out.println(predictionResponse.getInput());
    }

//    @Test
//    void testCreatePredictionAndCancelIt() throws InterruptedException {
//        String modelVersion = "c48fa8ec65b13143cb552ab98ea17984eab9d70e9fe99479117de40a2a7f9ed0";
//
//        PredictionResponse response = replicate.prediction()
//                .create(modelVersion)
//                .input("prompt", "flat color 2d animation of a portrait of woman with curly brown hair and light brown eyes, dynamic scene in the desert.")
//                .webhook("https://testwebhook.com/sdxl")
//                .execute(true, 30);
//
//
//
//        assertThat(response).isNotNull();
//        assertThat(response.getId()).isNotNull();
//
//        System.out.println(response.getUrls());
//
//
//
//    }
}
