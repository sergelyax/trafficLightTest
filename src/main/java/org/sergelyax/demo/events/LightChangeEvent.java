package org.sergelyax.demo.events;

import lombok.Getter;

@Getter
public class LightChangeEvent extends Event {
    private final String newState;

    public LightChangeEvent(String newState) {
        super(Type.CHANGE_STATE, newState);
        this.newState = newState;
    }
}

