package com.mclowry.linguaserver.api.v1;

import com.github.pemistahl.lingua.api.Language;

public class PredictResponse {

    private final Language language;

    public PredictResponse(Language language) {
        this.language = language;
    }

    public String getName() {
        return language.name();
    }

    public String getIso639_1() {
        return language.getIsoCode639_1().name();
    }

    public String getIso639_3() {
        return language.getIsoCode639_3().name();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PredictResponse)) {
            return false;
        }
        PredictResponse other = (PredictResponse) obj;
        return language == other.language;
    }
    
    @Override
    public int hashCode() {
        return language.hashCode();
    }

}
