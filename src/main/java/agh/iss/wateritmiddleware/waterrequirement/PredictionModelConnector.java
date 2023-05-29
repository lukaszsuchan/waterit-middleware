package agh.iss.wateritmiddleware.waterrequirement;

import agh.iss.wateritmiddleware.waterrequirement.model.PredictionModelRequest;
import agh.iss.wateritmiddleware.waterrequirement.model.PredictionModelResponse;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PredictionModelConnector {

    private final RestTemplate restTemplate;

    @Value("${application.prediction-model.url}")
    private String url;

    public PredictionModelResponse getPrediction(PredictionModelRequest requestData) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var predictionRequestJsonObject = new JSONObject();
        predictionRequestJsonObject.put("soil_type", requestData.soilType().toString());
        predictionRequestJsonObject.put("air_humidity_type", requestData.airHumidityType().toString());
        predictionRequestJsonObject.put("weather_condition", requestData.weatherCondition().toString());
        predictionRequestJsonObject.put("crop_type", requestData.cropType().toString());
        predictionRequestJsonObject.put("temperature", requestData.temperature());
        HttpEntity<String> request = new HttpEntity<>(predictionRequestJsonObject.toString(), headers);

        return restTemplate.postForObject(url, request, PredictionModelResponse.class);
    }
}
