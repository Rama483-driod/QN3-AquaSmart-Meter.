public class SmartMeterTest {

    static int passed = 0;
    static int failed = 0;

    static void assertTrue(String testName, boolean condition) {
        if (condition) {
            System.out.println("  [PASS] " + testName);
            passed++;
        } else {
            System.out.println("  [FAIL] " + testName);
            failed++;
        }
    }

    static void testLoadTokenReopensValve() {
        System.out.println("\n-- Test 1: loadToken re-opens closed valve --");
        SmartMeter meter = new SmartMeter("M-TEST-01", 100);

        meter.recordConsumption(3);   // costs 150 > 100 → valve closes

        assertTrue("Valve should be closed after credit exhausted", !meter.isValveOpen());

        meter.loadToken(500);

        assertTrue("Valve should be open after token loaded", meter.isValveOpen());
        assertTrue("Balance should be 500", meter.getCreditBalance() == 500.0);
    }

    static void testOverConsumptionClosesValve() {
        System.out.println("\n-- Test 2: excess consumption closes valve --");
        SmartMeter meter = new SmartMeter("M-TEST-02", 200);  // UGX 200 credit

        boolean dispensed = meter.recordConsumption(5);

        assertTrue("Water should have been dispensed",         dispensed);
        assertTrue("Valve should be closed (credit gone)",     !meter.isValveOpen());
        assertTrue("Balance should be 0",                      meter.getCreditBalance() == 0.0);

        boolean blocked = meter.recordConsumption(1);
        assertTrue("Further request should be blocked",        !blocked);
    }

    public static void main(String[] args) {
        System.out.println("===== SmartMeter Unit Tests =====");
        testLoadTokenReopensValve();
        testOverConsumptionClosesValve();
        System.out.println("\n=================================");
        System.out.println("Results: " + passed + " passed, " + failed + " failed.");
    }
}
