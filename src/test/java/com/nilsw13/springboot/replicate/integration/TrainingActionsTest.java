package com.nilsw13.springboot.replicate.integration;

import com.nilsw13.springboot.replicate.responsetype.training.Training;
import com.nilsw13.springboot.replicate.responsetype.training.TrainingList;
import com.nilsw13.springboot.replicate.responsetype.webhook.WebhookEvent;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("integration-test")
 class TrainingActionsTest extends BaseReplicateTest {

    @Test
    void getTrainingTest() {
        String trainingId = "ctwtgfw1vhrma0cmy6avxzwdjg";
        Training training = replicate.trainings().get(trainingId);
        System.out.println(training);
        assertThat(training.getUrls()).isNotNull();

    }

    @Test
    void getTrainingsList() {
        TrainingList trainingList = replicate.trainings().list();
        assertThat(trainingList.getResults()).isNotNull();
        assertThat(trainingList.getNext()).isNotNull();
        assertThat(trainingList.getPrevious()).isNull();
    }

    @Test
    void createTrainingTestWithWaitingWithoutWebhook() throws InterruptedException {
        List<WebhookEvent> eventList = new ArrayList<>();
        eventList.add(WebhookEvent.START);
        eventList.add(WebhookEvent.COMPLETED);

        Training training = replicate.trainings().create("ostris","flux-dev-lora-trainer", "b6af14222e6bd9be257cbc1ea4afda3cd0503e1133083b9d1de0364d8568e6ef")
                .destination("nilsw13", "a_b_c")
                .input("input_images", "https://github.com/nilsw13/test_image_replicate/releases/download/testreplicate/testreplicate.zip")
                .execute(true, 60);



        Thread.sleep(6000);
        assertThat(training.getId()).isNotNull();
        assertThat(training.getModel()).isEqualTo("ostris/flux-dev-lora-trainer");
        System.out.println(training.getStartedAt());


        Training training2 = replicate.trainings().cancel(training.getId());
        assertThat(training2.getStatus()).isEqualTo("canceled");


    }

    @Test
    void createTrainingTestWithoutWaiting() throws InterruptedException {

        List<WebhookEvent> eventList = new ArrayList<>();
        eventList.add(WebhookEvent.START);
        eventList.add(WebhookEvent.COMPLETED);

        Training training = replicate.trainings().create("ostris","flux-dev-lora-trainer", "b6af14222e6bd9be257cbc1ea4afda3cd0503e1133083b9d1de0364d8568e6ef")
                .destination("nilsw13", "a_b_c")
                .input("input_images", "https://github.com/nilsw13/test_image_replicate/releases/download/testreplicate/testreplicate.zip")
                .webhook("https://monwebhooktraining.com/test")
                .webhookEventFilter(eventList)
                .execute(false);

        assertThat(training.getId()).isNotNull();
        System.out.println(training);


        Thread.sleep(6000);
        assertThat(training.getModel()).isEqualTo("ostris/flux-dev-lora-trainer");
        System.out.println(training.getStartedAt());


        Training training2 = replicate.trainings().cancel(training.getId());
        assertThat(training2.getStatus()).isEqualTo("canceled");

    }


}
