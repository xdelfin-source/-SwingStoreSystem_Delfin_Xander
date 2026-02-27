import javax.swing.*;
import java.awt.*;

public class LogsFrame3 extends JFrame {

    JTextArea displayArea;
    String[] logProducts;
    int[] logQtys;
    double[] logTotals;
    int[] transactionCount;

    public LogsFrame3(String[] lProd, int[] lQty, double[] lTot, int[] tCount) {
        this.logProducts = lProd;
        this.logQtys = lQty;
        this.logTotals = lTot;
        this.transactionCount = tCount;

        setTitle("Transaction History");
        setSize(400, 350); // Made it slightly taller to fit the total
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(displayArea));

        refreshLogs();
    }

    public void refreshLogs() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-15s %-5s %-10s\n", "ID", "PRODUCT", "QTY", "TOTAL"));
        sb.append("------------------------------------------\n");

        int count = transactionCount[0];
        double grandTotal = 0.0; // Variable to store the sum of all sales

        for (int i = 0; i < count; i++) {
            // 1. Add current transaction to the Grand Total
            grandTotal = grandTotal + logTotals[i];

            // 2. Print the row
            sb.append(String.format("%-5d %-15s %-5d %-10.2f\n",
                    (i + 1),
                    logProducts[i],
                    logQtys[i],
                    logTotals[i]
            ));
        }

        sb.append("------------------------------------------\n");
        sb.append(String.format("%-28s %-10.2f\n", "GRAND TOTAL:", grandTotal));

        displayArea.setText(sb.toString());
    }
}
