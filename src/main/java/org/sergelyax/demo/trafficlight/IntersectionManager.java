package org.sergelyax.demo.trafficlight;

import org.sergelyax.demo.events.Event;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class IntersectionManager {
    private final Map<String, TrafficLight> trafficLights;
    private int vehicleQueueSize;
    private int pedestrianQueueSize;

    public IntersectionManager() {
        trafficLights = new HashMap<>();
    }

    public void addTrafficLight(TrafficLight trafficLight) {
        trafficLights.put(trafficLight.getId(), trafficLight);
    }

    public void sendEvent(Event event, String targetId) {
        TrafficLight target = trafficLights.get(targetId);
        if (target != null) {
            target.addEvent(event);
        }
    }

    public TrafficLightState getTrafficLightState(String id) {
        TrafficLight trafficLight = trafficLights.get(id);
        if (trafficLight != null) {
            return trafficLight.getCurrentState();
        }
        return null;
    }

    public Map<String, TrafficLight> getAllTrafficLights() {
        return trafficLights;
    }

    public void updateVehicleQueueSize(int size) {
        this.vehicleQueueSize = size;
    }

    public void updatePedestrianQueueSize(int size) {
        this.pedestrianQueueSize = size;
    }

    public int getVehicleQueueSize() {
        return vehicleQueueSize;
    }

    public int getPedestrianQueueSize() {
        return pedestrianQueueSize;
    }
}
