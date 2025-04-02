package com.nilsw13.spring_boot.replicate.unitaire;

import com.nilsw13.spring_boot.replicate.ResponseType.webhook.SigningSecretDefaultWebhook;
import com.nilsw13.spring_boot.replicate.ResponseType.webhook.WebhookEvent;
import com.nilsw13.spring_boot.replicate.api.ReplicateRestClient;
import com.nilsw13.spring_boot.replicate.impl.WebhookServiceImpl;
import com.nilsw13.spring_boot.replicate.service.WebhookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("unit-test")
public class WebhookServiceImplTest {

    @Mock
    private ReplicateRestClient mockRestClient;
    private  WebhookService webhookService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        webhookService = new WebhookServiceImpl(mockRestClient);
    }



    @Test
    public void testGetValue(){
        String value = WebhookEvent.COMPLETED.getValue();
        assertThat(value).isEqualTo("completed");

    }

    @Test
    public void testToString() {
        String value = WebhookEvent.LOGS.toString();
        assertThat(value).isEqualTo("logs");
    }

    @Test
    void constructorTest() {
        SigningSecretDefaultWebhook stringConstructor = new SigningSecretDefaultWebhook("test key");
        assertThat(stringConstructor).isNotNull();
        assertThat(stringConstructor.getKey()).isNotNull().isEqualTo("test key");

    }


    @Test
    void getSecretTest() {
        SigningSecretDefaultWebhook value = new SigningSecretDefaultWebhook();
        value.setKey("test secret key");


        assertThat(value.getKey()).isEqualTo("test secret key");
    }


}
