import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InventoryApp extends JFrame {
    private ArrayList<ProductModel> products;
    private ProductTableModel tableModel;
    private JTable table;

    private JTextField codeField, nameField, qtyField, priceField;

    public InventoryApp() {
        setTitle("Inventory History App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        products = new ArrayList<>();
        tableModel = new ProductTableModel(products);
        table = new JTable(tableModel);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Add Product"));

        formPanel.add(new JLabel("Code:"));
        codeField = new JTextField();
        formPanel.add(codeField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Quantity:"));
        qtyField = new JTextField();
        formPanel.add(qtyField);

        formPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        formPanel.add(priceField);

        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");

        formPanel.add(addButton);
        formPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText();
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                ProductModel product = new ProductModel(code, name, qty, price);
                tableModel.addProduct(product);

                codeField.setText("");
                nameField.setText("");
                qtyField.setText("");
                priceField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeProduct(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete");
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new InventoryApp().setVisible(true);
        });
    }
}
