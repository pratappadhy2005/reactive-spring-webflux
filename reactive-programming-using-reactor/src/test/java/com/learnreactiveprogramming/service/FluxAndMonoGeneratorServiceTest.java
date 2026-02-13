package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        //then
        StepVerifier.create(namesFlux)
                //.expectNext("alex", "ben", "chloe")
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFlexMap() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("ALEX", "BEN", "CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFluxImmutability() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFluxImmutability();

        //then
        StepVerifier.create(namesFlux)
                //.expectNext("ALEX", "BEN", "CHOLE")
                .expectNext("alex", "ben", "chloe")
                .verifyComplete();
    }

    @Test
    void namesFluxFilter() {
        //given

        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFilter();

        //then
        StepVerifier.create(namesFlux)
                .expectNext("ALEX - filtered", "CHLOE - filtered")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        //given
        int stringLength = 2;
        //when
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap(stringLength);

        //then
        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "B", "E", "N", "C", "H", "L", "O", "E")
                .verifyComplete();
    }
}