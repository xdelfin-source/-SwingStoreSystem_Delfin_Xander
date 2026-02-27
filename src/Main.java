import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {


            String[] products = {"Rice", "Sugar", "Coffee", "Milk"};
            double[] prices = {50.00, 45.00, 30.00, 85.00};
            int[] stock = {20, 20, 20, 20};

            // Transaction Log Data (Fixed size: 100)
            String[] logProducts = new String[100];
            int[] logQtys = new int[100];
            double[] logTotals = new double[100];

            // Counter (Array of size 1 for shared reference)
            int[] transactionCount = {0};


            InventoryFrame2 invFrame = new InventoryFrame2(products, prices, stock);
            LogsFrame3 logsFrame = new LogsFrame3(logProducts, logQtys, logTotals, transactionCount);

            new StoreFrame1(products, prices, stock, logProducts, logQtys, logTotals, transactionCount, invFrame, logsFrame);
        });
    }
}