package org.sergelyax.demo;

import org.junit.jupiter.api.Test;
import org.sergelyax.demo.trafficlight.IntersectionManager;
import org.sergelyax.demo.trafficlight.TrafficLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = TrafficSystem.class)
public class TrafficSystemTests {

    @Autowired
    private ApplicationContext ctx;

    @Test
    void contextLoads() {
        assertNotNull(ctx.getBean(IntersectionManager.class), "IntersectionManager bean should be loaded");
        assertNotNull(ctx.getBean("vehicleLight1", TrafficLight.class), "VehicleTrafficLight1 bean should be loaded");
        assertNotNull(ctx.getBean("vehicleLight2", TrafficLight.class), "VehicleTrafficLight2 bean should be loaded");
        assertNotNull(ctx.getBean("pedestrianLight1", TrafficLight.class), "PedestrianTrafficLight1 bean should be loaded");
        assertNotNull(ctx.getBean("pedestrianLight2", TrafficLight.class), "PedestrianTrafficLight2 bean should be loaded");
    }
}
