package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .log();
    }

    public Mono<String> nameMono(){
        return Mono.just("alex").log();
    }

    public Flux<String> namesFluxMap(){
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> namesFluxFlatMap(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(name -> name.length() > stringLength)
                .flatMap(this::splitString)
                .log();
    }

    public Flux<String> splitString(String name){
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitString_withdelay(String name){
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray).delayElements(java.time.Duration.ofMillis(delay));
    }

    public Flux<String> namesFluxImmutability() {
        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> namesFluxFilter(){
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .filter(name -> name.length() > 3)
                .map((s) -> s.toUpperCase().concat(" - filtered"))
                .log();
    }

    public Flux<String> namesFlexFlatMapAsync(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(name -> name.length() > stringLength)
                .flatMap(this::splitString_withdelay)
                .log();
    }

    public Flux<String> namesFluxConcatMap(int stringLength){
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(name -> name.length() > stringLength)
                .concatMap(this::splitString_withdelay)
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> System.out.println("Name is : " + name));

        System.out.println("********************");

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> System.out.println("Name is : " + name));

        System.out.println("********************");

        fluxAndMonoGeneratorService.namesFluxMap()
                .subscribe(name -> System.out.println("Name in Uppercase is : " + name));

        System.out.println("********************");
        fluxAndMonoGeneratorService.namesFluxImmutability()
                .subscribe(name -> System.out.println("Name is : " + name));

        System.out.println("********************");
        fluxAndMonoGeneratorService.namesFluxFilter()
                .subscribe(name -> System.out.println("Name in Uppercase is : " + name));
    }


}
