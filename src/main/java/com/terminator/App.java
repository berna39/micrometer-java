package com.terminator;

import java.util.Optional;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SimpleMeterRegistry simpleMeterRegistry = new SimpleMeterRegistry(); 
        Metrics.globalRegistry.add(simpleMeterRegistry);

       turnAround(10);

       Optional<Counter> counterOptional = Optional.ofNullable(Metrics.globalRegistry
      .find("com.terminator.turns").counter());

      if(counterOptional.isPresent())
        System.out.println(counterOptional.get().count());
       
    }

    private static void turnAround(int rounds){
        for(int i = 0; i < rounds; i ++){
            Metrics.counter("com.terminator.turns").increment(1.0); // this will increment by one everytime loop exexutes
            System.out.println("I'm turning around");
        }
    }
}
