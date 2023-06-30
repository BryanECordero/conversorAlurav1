import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class UnitConverterApp extends JFrame {
    // Componentes de la interfaz
    private JTextField inputField;
    private JLabel seleccionLabel, resultLabel;
    private JComboBox<String> unitComboBox;
    private JPanel panel = new JPanel();
    private JComboBox<String> unidadesBox;

    public UnitConverterApp() {
        // Configuración de la ventana
        setTitle("Unit Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new FlowLayout());

        // Creación de los componentes
        inputField = new JTextField(10);
        unitComboBox = new JComboBox<>(new String[]{"Monedas", "Distancia", "Masa", "Temperatura"});
        JButton convertButton = new JButton("Convert");
        seleccionLabel = new JLabel("¿Qué desea convertir?: ");
        resultLabel = new JLabel("Result: ");

        // Agregar componentes a la ventana
        add(seleccionLabel);
        add(unitComboBox);

        // Dentro del constructor de la clase UnitConverterApp
        unitComboBox.addItemListener((ItemListener) new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedUnit = (String) unitComboBox.getSelectedItem();
                    showHideComponents(selectedUnit);
                }
            }
        });

        showHideComponents("Monedas");

        // Manejador de eventos para el botón de conversión
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
    }

    private void showHideComponents(String selectedUnit) {
            // Mostrar u ocultar componentes según la unidad seleccionada
            if (selectedUnit.equals("Monedas")) {
                unidadesBox = new JComboBox<>(monedas.getMonedas());
                panel.add(unidadesBox);
            } else if (selectedUnit.equals("Distancia")) {
                unidadesBox = new JComboBox<>(distancia.getUnidades());
                panel.add(unidadesBox);
            } else if (selectedUnit.equals("Masa")) {
                
            } else if (selectedUnit.equals("Temperatura")) {
                
            }
            panel.setVisible(true);
    }

    private void convert() {
        String inputValue = inputField.getText();
        String selectedUnit = (String) unitComboBox.getSelectedItem();

        // Realiza la lógica de conversión según la unidad seleccionada
        if (selectedUnit.equals("Distance")) {
            // Lógica de conversión de distancia
            // ...
            // double result = ...;

            // Mostrar el resultado en el JLabel
            // resultLabel.setText("Result: " + result);
        } else if (selectedUnit.equals("Weight")) {
            // Lógica de conversión de peso
            // ...
            // double result = ...;

            // Mostrar el resultado en el JLabel
            // resultLabel.setText("Result: " + result);
        } else if (selectedUnit.equals("Currency")) {
            // Lógica de conversión de moneda
            // ...
            // double result = ...;

            // Mostrar el resultado en el JLabel
            // resultLabel.setText("Result: " + result);
        } else if (selectedUnit.equals("Temperature")) {
            // Lógica de conversión de temperatura
            // ...
            // double result = ...;

            // Mostrar el resultado en el JLabel
            // resultLabel.setText("Result: " + result);
        }
    }
}
