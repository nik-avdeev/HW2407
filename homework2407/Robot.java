package homework2407;

import java.util.ArrayList;
import java.util.List;

public class Robot {
    private final List<RobotPart> parts;
    private final Object lock;

    public Robot(Object lock) {
        parts = new ArrayList<>();
        this.lock = lock;
    }

    public void addPart(RobotPart part) {
        parts.add(part);
    }

    public boolean isComplete() {
        return parts.size() == 6;
    }

    public void waitForParts() {
        synchronized (lock) {
            try {
                while (!isComplete()) {
                    lock.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifyAssembled() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
