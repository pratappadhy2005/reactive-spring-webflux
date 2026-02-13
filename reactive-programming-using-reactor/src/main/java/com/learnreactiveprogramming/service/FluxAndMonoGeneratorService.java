package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
