package homework2407;

public class Main {
    public static void main(String[] args) {
        Factory factory = new Factory();
        factory.producePart();
        Country country1 = new Country("Country1", factory);
        Country country2 = new Country("Country2", factory);

        country1.start();
        country2.start();

        try {
            country1.join();
            country2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Simulation completed.");
    }
}
