package org.sergelyax.demo.events;

import lombok.Getter;

@Getter
public class Event {
    public enum Type {
        CHANGE_STATE,
        UPDATE_QUEUE_SIZE,
        TIMER_EVENT,
        CHECK_STATE
    }

    private final Type type;
    private final Object data;

    public Event(Type type, Object data) {
        this.type = type;
        this.data = data;
    }

}
