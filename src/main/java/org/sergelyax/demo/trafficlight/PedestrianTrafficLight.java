package org.sergelyax.demo.trafficlight;

import org.sergelyax.demo.events.Event;

public class PedestrianTrafficLight extends TrafficLight {

    public PedestrianTrafficLight(String id, IntersectionManager manager) {
        super(id, manager);
    }

    @Override
    protected TrafficLightState getDefaultState() {
        return TrafficLightState.DONT_WALK;
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
        System.out.println("Pedestrian light " + getId() + " changed to " + newState);
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
        int pedestrianQueueSize = getManager().getPedestrianQueueSize();

        switch (currentState) {
            case WALK:
                delay = Math.max(30, pedestrianQueueSize * 2); // Длительность зеленого сигнала зависит от очереди
                scheduleEvent(new Event(Event.Type.CHANGE_STATE, TrafficLightState.DONT_WALK), delay);
                break;
            case DONT_WALK:
                delay = 10;
                scheduleEvent(new Event(Event.Type.CHANGE_STATE, TrafficLightState.WALK), delay);
                break;
            default:
                break;
        }
    }
}
