package org.sergelyax.demo.events;

import lombok.Getter;
import org.sergelyax.demo.events.Event;

@Getter
public class PedestrianQueueEvent extends Event {
    private final int pedestrianCount;

    public PedestrianQueueEvent(int pedestrianCount) {
        super(Type.UPDATE_QUEUE_SIZE, pedestrianCount);
        this.pedestrianCount = pedestrianCount;
    }
}
