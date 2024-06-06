package org.sergelyax.demo.controller;

import org.sergelyax.demo.dto.TrafficLightDTO;
import org.sergelyax.demo.events.Event;
import org.sergelyax.demo.trafficlight.TrafficLight;
import org.sergelyax.demo.trafficlight.IntersectionManager;
import org.sergelyax.demo.trafficlight.TrafficLightState;
import org.sergelyax.demo.trafficlight.VehicleTrafficLight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trafficlights")
public class TrafficLightController {

    @Autowired
    private IntersectionManager intersectionManager;

    @GetMapping
    public List<TrafficLightDTO> getAllTrafficLights() {
        return intersectionManager.getAllTrafficLights().values().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TrafficLightDTO createTrafficLight(@RequestBody TrafficLightDTO trafficLightDTO) {
        TrafficLight trafficLight = convertToEntity(trafficLightDTO);
        intersectionManager.addTrafficLight(trafficLight);
        return trafficLightDTO;
    }

    @PutMapping("/{id}")
    public TrafficLightDTO updateTrafficLight(@PathVariable String id, @RequestBody TrafficLightDTO trafficLightDTO) {
        TrafficLight trafficLight = intersectionManager.getAllTrafficLights().get(id);
        if (trafficLight != null) {
            trafficLight.handleEvent(new Event(Event.Type.CHANGE_STATE, TrafficLightState.valueOf(trafficLightDTO.getState())));
        }
        return trafficLightDTO;
    }

    @DeleteMapping("/{id}")
    public void deleteTrafficLight(@PathVariable String id) {
        intersectionManager.getAllTrafficLights().remove(id);
    }

    @PutMapping("/vehicleQueue")
    public void updateVehicleQueueSize(@RequestParam int size) {
        intersectionManager.updateVehicleQueueSize(size);
    }

    @PutMapping("/pedestrianQueue")
    public void updatePedestrianQueueSize(@RequestParam int size) {
        intersectionManager.updatePedestrianQueueSize(size);
    }

    private TrafficLightDTO convertToDTO(TrafficLight trafficLight) {
        return new TrafficLightDTO(trafficLight.getId(), trafficLight.getCurrentState().name());
    }

    private TrafficLight convertToEntity(TrafficLightDTO trafficLightDTO) {
        return new VehicleTrafficLight(trafficLightDTO.getId(), intersectionManager); // Замените на нужный тип TrafficLight
    }
}
