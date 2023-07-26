package homework2407;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Factory {
    private final Random random;
    private final List<RobotPart> availableParts;
    private boolean productionActive;

    public Factory() {
        random = new Random();
        availableParts = new ArrayList<>();
        productionActive = true;
    }

    public void producePart() {
        new Thread(() -> {
            while (productionActive) {
                try {
                    Thread.sleep(100);
                    String[] partNames = {"Left Arm", "Right Arm", "Left Leg", "Right Leg", "Body", "Head"};
                    String randomPartName = partNames[random.nextInt(partNames.length)];
                    RobotPart part = new RobotPart(randomPartName);
                    availableParts.add(part);
                    System.out.println("Produced: " + part.getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public synchronized RobotPart getPart() {
        if (availableParts.isEmpty()) {
            return null;
        }
        return availableParts.remove(0);
    }

    public void stopProduction() {
        productionActive = false;
    }
}
