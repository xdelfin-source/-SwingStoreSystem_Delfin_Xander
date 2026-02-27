import javax.swing.*;
import java.awt.*;

public class StoreFrame1 extends JFrame {

    // Shared Data Arrays
    String[] products;
    double[] prices;
    int[] stock;
    String[] logProducts;
    int[] logQtys;
    double[] logTotals;
    int[] transactionCount;

    // References to other Frames
    InventoryFrame2 inventoryFrame;
    LogsFrame3 logsFrame;

    // UI Components
    JComboBox<String> productBox;
    JTextField qtyField;
    JLabel priceLabel;

    public StoreFrame1(String[] prod, double[] prc, int[] stk,
                      String[] lProd, int[] lQty, double[] lTot, int[] tCount,
                      InventoryFrame2 invFrame, LogsFrame3 lgFrame) {

        // Save all references
        this.products = prod;
        this.prices = prc;
        this.stock = stk;
        this.logProducts = lProd;
        this.logQtys = lQty;
        this.logTotals = lTot;
        this.transactionCount = tCount;
        this.inventoryFrame = invFrame;
        this.logsFrame = lgFrame;

        // Window Setup
        setTitle("Store Transaction Frame");
        setSize(400, 350);
        setLayout(new GridLayout(6, 2, 10, 10));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Closing this closes the app
        setLocationRelativeTo(null);

        // UI Components
        add(new JLabel("Select Product:"));
        productBox = new JComboBox<>(products);
        add(productBox);

        add(new JLabel("Price:"));
        priceLabel = new JLabel(String.valueOf(prices[0]));
        add(priceLabel);

        add(new JLabel("Enter Quantity:"));
        qtyField = new JTextField();
        add(qtyField);

        JButton btnCompute = new JButton("Complete Transaction");
        add(btnCompute);

        // Navigation Buttons
        JButton btnLogs = new JButton("View Transaction Logs");
        add(btnLogs);

        JButton btnInventory = new JButton("View Inventory");
        add(btnInventory);


        // Update Price Label on selection
        productBox.addActionListener(e -> {
            int idx = productBox.getSelectedIndex();
            priceLabel.setText(String.valueOf(prices[idx]));
        });

        // Open other frames
        btnLogs.addActionListener(e -> logsFrame.setVisible(true));
        btnInventory.addActionListener(e -> inventoryFrame.setVisible(true));

        // Compute Logic
        btnCompute.addActionListener(e -> processTransaction());

        setVisible(true);
    }

    private void processTransaction() {
        try {
            int index = productBox.getSelectedIndex();
            String input = qtyField.getText();

            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a quantity.");
                return;
            }

            int qty = Integer.parseInt(input);

            // Constraint: Prevent negative quantities
            if (qty <= 0) {
                JOptionPane.showMessageDialog(this, "Quantity must be positive.");
                return;
            }

            // Constraint: Prevent transactions exceeding stock
            if (qty > stock[index]) {
                JOptionPane.showMessageDialog(this, "Insufficient Stock! Only " + stock[index] + " left.");
                return;
            }


            // 1. Compute
            double total = qty * prices[index];

            // 2. Update Inventory Array
            stock[index] = stock[index] - qty;

            // 3. Save to Logs Arrays
            int currentLogIdx = transactionCount[0];
            if (currentLogIdx < logProducts.length) {
                logProducts[currentLogIdx] = products[index];
                logQtys[currentLogIdx] = qty;
                logTotals[currentLogIdx] = total;
                transactionCount[0]++; // Increment shared counter

                inventoryFrame.refreshInventory();
                logsFrame.refreshLogs();

                JOptionPane.showMessageDialog(this, "Transaction Successful!\nTotal: " + total);
                qtyField.setText(""); // Clear input
            } else {
                JOptionPane.showMessageDialog(this, "Log storage full!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.");
        }
    }
}