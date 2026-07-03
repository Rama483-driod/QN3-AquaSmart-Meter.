public class SmartMeter {

    private String  meterId;
    private double  creditBalance;
    private boolean valveOpen;

    private static final double COST_PER_LITRE = 50.0;  // UGX 50 per litre

    public SmartMeter(String meterId, double openingCredit) {
        this.meterId       = meterId;
        this.creditBalance = openingCredit;
        this.valveOpen     = true;
    }

    public double loadToken(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Token amount must be positive.");
        creditBalance += amount;
        if (!valveOpen) {
            valveOpen = true;
            System.out.println("  [" + meterId + "] Valve RE-OPENED after token load.");
        }
        System.out.printf("  [%s] Token loaded: UGX %.2f | New balance: UGX %.2f%n",
                meterId, amount, creditBalance);
        return creditBalance;
    }

    public boolean recordConsumption(double litres) {
        if (!valveOpen) {
            System.out.println("  [" + meterId + "] BLOCKED – valve is closed (no credit).");
            return false;
        }
        double cost = litres * COST_PER_LITRE;
        creditBalance -= cost;
        if (creditBalance <= 0) {
            creditBalance = 0;
            valveOpen     = false;
            System.out.printf("  [%s] Credit exhausted. Valve CLOSED.%n", meterId);
        } else {
            System.out.printf("  [%s] %.1f L dispensed | Cost: UGX %.2f | Balance: UGX %.2f%n",
                    meterId, litres, cost, creditBalance);
        }
        return true;   
    }

    // ── Getters ───────────────────────────────────────────────────────────────
    public String  getMeterId()       { return meterId; }
    public double  getCreditBalance() { return creditBalance; }
    public boolean isValveOpen()      { return valveOpen; }
}
