package com.mclowry.linguaserver.api.v1;

import com.github.pemistahl.lingua.api.Language;

public class PredictWithConfidenceResponse extends PredictResponse {

    private final double confidence;

    public PredictWithConfidenceResponse(Language language, double confidence) {
        super(language);
        this.confidence = confidence;
    }

    public double getConfidence() {
        return confidence;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
    
}
