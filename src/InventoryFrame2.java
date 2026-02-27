import javax.swing.*;
import java.awt.*;

public class InventoryFrame2 extends JFrame {

    JTextArea displayArea;
    String[] products;
    double[] prices;
    int[] stock;

    public InventoryFrame2(String[] products, double[] prices, int[] stock) {
        this.products = products;
        this.prices = prices;
        this.stock = stock;

        setTitle("Inventory Frame");
        setSize(400, 300);
        setLocationRelativeTo(null); // Center screen
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); // Hide, don't close app

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(new JScrollPane(displayArea));

        refreshInventory(); // Load initial data
    }

    // Called by StoreFrame whenever a sale happens
    public void refreshInventory() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-10s %-10s\n", "PRODUCT", "PRICE", "STOCK"));
        sb.append("--------------------------------------\n");

        for (int i = 0; i < products.length; i++) {
            sb.append(String.format("%-15s %-10.2f %-10d\n",
                    products[i],
                    prices[i],
                    stock[i] // Reads directly from the shared array
            ));
        }
        displayArea.setText(sb.toString());
    }
}