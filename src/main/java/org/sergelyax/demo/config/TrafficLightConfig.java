package org.sergelyax.demo.config;

import org.sergelyax.demo.trafficlight.IntersectionManager;
import org.sergelyax.demo.trafficlight.PedestrianTrafficLight;
import org.sergelyax.demo.trafficlight.TrafficLight;
import org.sergelyax.demo.trafficlight.VehicleTrafficLight;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrafficLightConfig {

    @Bean
    public IntersectionManager intersectionManager() {
        return new IntersectionManager();
    }

    @Bean
    public TrafficLight vehicleLight1(IntersectionManager intersectionManager) {
        return new VehicleTrafficLight("V1", intersectionManager);
    }

    @Bean
    public TrafficLight vehicleLight2(IntersectionManager intersectionManager) {
        return new VehicleTrafficLight("V2", intersectionManager);
    }

    @Bean
    public TrafficLight pedestrianLight1(IntersectionManager intersectionManager) {
        return new PedestrianTrafficLight("P1", intersectionManager);
    }

    @Bean
    public TrafficLight pedestrianLight2(IntersectionManager intersectionManager) {
        return new PedestrianTrafficLight("P2", intersectionManager);
    }
}
