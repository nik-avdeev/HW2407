package homework2407;

import java.util.ArrayList;
import java.util.List;

public class Country extends Thread {
    private final String name;
    private final Factory factory;
    private final List<Robot> robots;

    public Country(String name, Factory factory) {
        this.name = name;
        this.factory = factory;
        this.robots = new ArrayList<>();
    }

    @Override
    public void run() {
        while (robots.size() < 20) {
            Robot robot = new Robot(this);
            while (!robot.isComplete()) {
                RobotPart part = factory.getPart();
                if (part != null) {
                    robot.addPart(part);
                    System.out.println(name + " got part: " + part.getName());
                }
            }
            robots.add(robot);
            System.out.println(name + " assembled a robot.");
            robot.notifyAssembled();
        }
        System.out.println(name + " has won!");
        factory.stopProduction();
    }
}
