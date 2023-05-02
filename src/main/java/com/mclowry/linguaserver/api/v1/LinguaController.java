package com.mclowry.linguaserver.api.v1;

import com.github.pemistahl.lingua.api.LanguageDetector;
import com.github.pemistahl.lingua.api.LanguageDetectorBuilder;
import com.mclowry.linguaserver.LinguaServerConfigurationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@RestController
@RequestMapping(path = "/api/v1")
public class LinguaController {

    @Autowired
    private LinguaServerConfigurationProperties config;

    private LanguageDetector languageDetector;

    @PostConstruct
    public void postConstruct() {
        LanguageDetectorBuilder builder = LanguageDetectorBuilder.fromAllLanguages();
        if (config.isPreloadModels()) {
            builder = builder.withPreloadedLanguageModels();
        }
        languageDetector = builder.build();
    }

    @PreDestroy
    public void preDestroy() {
        languageDetector.unloadLanguageModels();
    }

    @GetMapping("predict")
    public PredictResponse predict(@RequestParam String text) {
        System.out.println(languageDetector.detectLanguageOf(text));
        return new PredictResponse(languageDetector.detectLanguageOf(text));
    }

    @PostMapping("predict")
    public List<PredictResponse> predictMultiple(@RequestBody List<String> texts) {
        return new ArrayList<>(
            texts.stream()
                .map(str -> str != null ? new PredictResponse(languageDetector.detectLanguageOf(str)) : null)
                .collect(Collectors.toList()));
    }

    @GetMapping("predict/confidence")
    public List<PredictWithConfidenceResponse> predictWithConfidence(
        @RequestParam String text,
        @RequestParam(defaultValue="5") int limit
    ) {
        return languageDetector.computeLanguageConfidenceValues(text).entrySet().stream()
            .map(e -> new PredictWithConfidenceResponse(e.getKey(), e.getValue()))
            .limit(limit)
            .collect(Collectors.toList());
    }

    @PostMapping("predict/confidence")
    public List<List<PredictWithConfidenceResponse>> predictMultipleWithConfidence(
        @RequestBody List<String> texts,
        @RequestParam(defaultValue="5") int limit
    ) {
        return texts.stream().map(
            text -> languageDetector.computeLanguageConfidenceValues(text).entrySet().stream()
                .map(e -> new PredictWithConfidenceResponse(e.getKey(), e.getValue()))
                .limit(limit)
                .collect(Collectors.toList()))
            .collect(Collectors.toList());
    }

}
