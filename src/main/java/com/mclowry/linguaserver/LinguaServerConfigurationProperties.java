package com.mclowry.linguaserver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("lingua-server")
public class LinguaServerConfigurationProperties {

    private boolean preloadModels;
    
    private boolean prettyPrint;

    
    public boolean isPreloadModels() {
        return preloadModels;
    }
    
    public void setPreloadModels(boolean preloadModels) {
        this.preloadModels = preloadModels;
    }

    public boolean isPrettyPrint() {
        return prettyPrint;
    }

    public void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }
    
}
