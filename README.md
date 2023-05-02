
# Lingua Service

A thin HTTP service wrapper around the Lingua library ([https://github.com/pemistahl/lingua]).

## Gimme now

Run the image published to Docker Hub:
```
> docker run -it --rm -p 8080:8080 matthewlowry/lingua-service
```

Then hit it with HTTP requests:
```
> curl localhost:8080/api/v1/predict?text=The%20quick%20brown%20fox
```


## OK, what actually is it?

The Lingua library provides language prediction for text, supporting over 70 languages. 
It uses a combination of glyph usage rule sets and n-gram distribution statistics to provide predictions.
It is typically faster and more accurate compared to other open source libraries.
Notably it tends to give better results on short fragments of text compared to other open source
libraries like Apache Tika and OpenNLP.

* Run as a self-contained JAR (built with Spring Boot) or as a Docker container (Slim Debain Bullseye + OpenJDK 17 base image).
* Simple JSON HTTP API: GET or POST requests with one or more pieces of text; get back predicted language(s).
* Primary use case: run an autoscaling cluster behind a load-balancer.

### Examples

Get most confident prediction for a single segment of text:
```
GET /api/v1/predict?text=The%20quick%20brown%20fox
```
```
{ "name": "ENGLISH", "iso639_3": "ENG", "iso639_1": "EN" }
```

Get ranked predictions with confidence score for multiple segments of text:
```
POST /api/v1/predict/confidence?limit=3
[
  "El rápido zorro marrón saltó sobre el perro perezoso.", 
  "Fljóti brúni refurinn stökk yfir lata hundinn"
]
```
```
[
  [
    { "confidence": 1, "name": "SPANISH", "iso639_3": "SPA", "iso639_1": "ES" },
    { "confidence": 0.8354881949853769, "name": "PORTUGUESE", "iso639_3": "POR", "iso639_1": "PT" },
    { "confidence": 0.8238285693519217, "name": "BASQUE", "iso639_3": "EUS", "iso639_1": "EU" }
  ],
  [
    { "confidence": 1, "name": "ICELANDIC", "iso639_3": "ISL", "iso639_1": "IS" },
    { "confidence": 0.6397300617864857, "name": "HUNGARIAN", "iso639_3": "HUN", "iso639_1": "HU" },
    { "confidence": 0.6246984403030562, "name": "LATIN", "iso639_3": "LAT", "iso639_1": "LA" }
  ]
]
```


## Building

Requires:
 * Java 17+
 * Maven
 * Docker

Then:
```
> mvn clean package
```

This will build the self-contained Spring Boot based JAR (under `target`) and a Docker image
(generally built with your local Docker daemon but you can point the plugin respects `DOCKER_HOST`
environment see [plugin](https://github.com/fabric8io/docker-maven-plugin) for details).

Note Podman may struggle with the build. Try manually deleting intermediate layers that aren't
being cleaned up.


## Running

Run the self-contained JAR:
```
> java -jar target/lingua-service-1.2.2-1.jar
```

Run the Docker image:
```
> docker run -it --rm -p 8080:8080 matthewlowry/lingua-service:1.2.2-1
```


