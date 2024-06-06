package org.sergelyax.demo.events;

import lombok.Getter;

import java.util.concurrent.ConcurrentLinkedQueue;

@Getter
public class EventQueue {
    private final ConcurrentLinkedQueue<Event> queue = new ConcurrentLinkedQueue<>();

    public void addEvent(Event event) {
        queue.add(event);
    }

    public Event pollEvent() {
        return queue.poll();
    }
}
