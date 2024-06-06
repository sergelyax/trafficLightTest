package org.sergelyax.demo.trafficlight;

import lombok.Getter;
import org.sergelyax.demo.events.Event;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public abstract class TrafficLight implements Runnable {

    @Getter
    private final String id;
    @Getter
    protected TrafficLightState currentState;
    protected final BlockingQueue<Event> eventQueue;
    private final ScheduledExecutorService scheduler;
    protected int queueSize;
    @Getter
    private final IntersectionManager manager;

    public TrafficLight(String id, IntersectionManager manager) {
        this.id = id;
        this.currentState = getDefaultState();
        this.eventQueue = new LinkedBlockingQueue<>();
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.queueSize = 0;
        this.manager = manager;
    }

    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Event event = eventQueue.take();
                handleEvent(event);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    protected abstract TrafficLightState getDefaultState();
    public abstract void handleEvent(Event event);

    protected void scheduleEvent(Event event, long delay) {
        scheduler.schedule(() -> addEvent(event), delay, TimeUnit.SECONDS);
    }
}
