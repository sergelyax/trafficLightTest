package org.sergelyax.demo.trafficlight;

import org.sergelyax.demo.events.Event;

public class VehicleTrafficLight extends TrafficLight {

    public VehicleTrafficLight(String id, IntersectionManager manager) {
        super(id, manager);
    }

    @Override
    protected TrafficLightState getDefaultState() {
        return TrafficLightState.RED;
    }

    @Override
    public void handleEvent(Event event) {
        switch (event.getType()) {
            case CHANGE_STATE:
                changeState((TrafficLightState) event.getData());
                break;
            case UPDATE_QUEUE_SIZE:
                updateQueueSize((Integer) event.getData());
                break;
            case TIMER_EVENT:
                handleTimerEvent((String) event.getData());
                break;
            default:
                break;
        }
    }

    private void changeState(TrafficLightState newState) {
        this.currentState = newState;
        System.out.println("Traffic light " + getId() + " changed to " + newState);
        scheduleNextStateChange();
    }

    private void updateQueueSize(int newSize) {
        this.queueSize = newSize;
    }

    private void handleTimerEvent(String targetId) {
        Event event = new Event(Event.Type.CHECK_STATE, null);
        getManager().sendEvent(event, targetId);
    }

    private void scheduleNextStateChange() {
        long delay;
        int vehicleQueueSize = getManager().getVehicleQueueSize();

        switch (currentState) {
            case GREEN:
                delay = Math.max(30, vehicleQueueSize * 2); // Длительность зеленого сигнала зависит от очереди
                scheduleEvent(new Event(Event.Type.CHANGE_STATE, TrafficLightState.YELLOW), delay);
                break;
            case YELLOW:
                delay = 5;
                scheduleEvent(new Event(Event.Type.CHANGE_STATE, TrafficLightState.RED), delay);
                break;
            case RED:
                delay = calculateRedLightDuration();
                scheduleEvent(new Event(Event.Type.CHANGE_STATE, TrafficLightState.GREEN), delay);
                break;
            default:
                break;
        }
    }

    private long calculateRedLightDuration() {
        return 30; // Минимум 30 секунд для красного сигнала
    }
}
