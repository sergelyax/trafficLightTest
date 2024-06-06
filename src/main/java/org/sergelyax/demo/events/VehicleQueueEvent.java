package org.sergelyax.demo.events;

import lombok.Getter;
import org.sergelyax.demo.events.Event;

@Getter
public class VehicleQueueEvent extends Event {
    private final int vehicleCount;

    public VehicleQueueEvent(int vehicleCount) {
        super(Type.UPDATE_QUEUE_SIZE, vehicleCount);
        this.vehicleCount = vehicleCount;
    }
}