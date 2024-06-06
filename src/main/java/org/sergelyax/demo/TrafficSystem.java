package org.sergelyax.demo;

import org.sergelyax.demo.trafficlight.IntersectionManager;
import org.sergelyax.demo.trafficlight.PedestrianTrafficLight;
import org.sergelyax.demo.trafficlight.TrafficLight;
import org.sergelyax.demo.trafficlight.VehicleTrafficLight;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrafficSystem {

    public static void main(String[] args) {
        SpringApplication.run(TrafficSystem.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            IntersectionManager manager = ctx.getBean(IntersectionManager.class);

            TrafficLight vehicleLight1 = new VehicleTrafficLight("vehicleLight1", manager);
            TrafficLight vehicleLight2 = new VehicleTrafficLight("vehicleLight2", manager);
            TrafficLight pedestrianLight1 = new PedestrianTrafficLight("pedestrianLight1", manager);
            TrafficLight pedestrianLight2 = new PedestrianTrafficLight("pedestrianLight2", manager);

            manager.addTrafficLight(vehicleLight1);
            manager.addTrafficLight(vehicleLight2);
            manager.addTrafficLight(pedestrianLight1);
            manager.addTrafficLight(pedestrianLight2);

            // Start simulation
            new Thread(vehicleLight1).start();
            new Thread(vehicleLight2).start();
            new Thread(pedestrianLight1).start();
            new Thread(pedestrianLight2).start();
        };
    }
}
