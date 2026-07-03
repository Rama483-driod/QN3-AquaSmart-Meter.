public class Main {

    public static void main(String[] args) {

        SmartMeter meter = new SmartMeter("M001", 200);

        System.out.println("Initial: " + meter.getCreditBalance());

        meter.recordConsumption(3);

        System.out.println("After use: " + meter.getCreditBalance());

        meter.recordConsumption(10);

        System.out.println("Valve Open: " + meter.isValveOpen());

        meter.loadToken(1000);

        System.out.println("After recharge: " + meter.getCreditBalance());
        System.out.println("Valve Open: " + meter.isValveOpen());
    }
} 