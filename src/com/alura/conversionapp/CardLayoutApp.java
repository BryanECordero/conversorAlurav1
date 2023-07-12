package com.alura.conversionapp;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.*;
import javax.swing.text.AbstractDocument;

public class CardLayoutApp implements ItemListener {
    JPanel cards;
    String opciones[] = { "Monedas", "Longitud", "Masa", "Temperatura" };

    public void addComponentToPane(Container pane) {
        JPanel seleccionPanel = new JPanel();
        JComboBox<String> seleccionBox = new JComboBox<>(opciones);
        seleccionBox.setEditable(false);
        seleccionBox.addItemListener(this);
        JLabel seleccionLabel = new JLabel("¿Qué desea convertir?: ");

        seleccionPanel.add(seleccionLabel);
        seleccionPanel.add(seleccionBox);
        JPanel card;

        cards = new JPanel(new CardLayout());

        for (int i = 0; i < opciones.length; i++) {
            card = new JPanel();
            AddConversionCard(card, opciones[i]);
            cards.add(card, opciones[i]);
        }

        pane.add(seleccionPanel, BorderLayout.PAGE_START);
        pane.add(cards, BorderLayout.CENTER);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) e.getItem());
    }

    public static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("Conversor de unidades");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        CardLayoutApp demo = new CardLayoutApp();
        demo.addComponentToPane(frame.getContentPane());
        // Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 300);
        frame.setResizable(false);
        ImageIcon img = new ImageIcon("src\\com\\alura\\conversionapp\\icons\\convertir.png");
        frame.setIconImage(img.getImage());
    }

    private void AddConversionCard(JPanel panel, String tipo) {
        panel.setLayout(new GridBagLayout());
        String[] unidades = getLista(tipo);
        JLabel convertirLabel = new JLabel("Convertir: ");
        JTextField inputField = new JTextField(10);
        ((AbstractDocument) inputField.getDocument()).setDocumentFilter(new NumberOnlyFilter());
        JLabel deLabel = new JLabel("De: ");
        JComboBox<String> unidades1Box = new JComboBox<>(unidades);
        JLabel aLabel = new JLabel("A: ");
        JComboBox<String> unidades2Box = new JComboBox<>(unidades);
        JButton btnConvertir = new JButton("Convertir");
        JLabel resultadoLabel = new JLabel(" ");
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 60, 0, 10);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        panel.add(convertirLabel, c);
        c.insets = new Insets(5, 60, 0, 10);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(inputField, c);
        c.insets = new Insets(20, 5, 0, 5);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(deLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5, 5, 0, 5);
        panel.add(unidades1Box, c);
        c.insets = new Insets(20, 10, 0, 60);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(aLabel, c);
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 0, 60);
        panel.add(unidades2Box, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(10, 30, 0, 30);
        panel.add(resultadoLabel, c);
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(30, 30, 30, 30);
        panel.add(btnConvertir, c);
        if (tipo == "Monedas") {
            Monedas.actulizarTasas();
            JLabel dateLabel = new JLabel("Última fecha de actualización de tasas: " + Monedas.getFechaActualizacion());
            c.gridx = 0;
            c.gridy = 4;
            c.insets = new Insets(0, 0, 0, 0);
            c.anchor = GridBagConstraints.LINE_START;
            panel.add(dateLabel, c);
        }

        btnConvertir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double valor = Double.parseDouble(inputField.getText());
                    double resultado = Calculadora.convertirUnidades(tipo, "convertir", valor,
                            (String) unidades1Box.getSelectedItem(),
                            (String) unidades2Box.getSelectedItem());
                    resultadoLabel.setText(valor + " " + (String) unidades1Box.getSelectedItem()
                            + " son " + resultado + " " + (String) unidades2Box.getSelectedItem());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número valido");
                    inputField.setText("");
                }
            }
        });
    }

    private String[] getLista(String tipo) {
        try {
            // Obtén la referencia de la clase utilizando el nombre proporcionado
            Class<?> clazz = Class.forName("com.alura.conversionapp." + tipo);

            // Crea una instancia de la clase
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // Obtén el método utilizando el nombre proporcionado
            Method method = clazz.getMethod("getValores");

            // Invoca al método en la instancia creada
            String[] lista = (String[]) method.invoke(instance);
            return lista;
        } catch (ClassNotFoundException e) {
            System.out.println("Clase no encontrada: " + tipo);
        } catch (NoSuchMethodException e) {
            System.out.println("Método no encontrado: " + "getValores");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Error al crear la instancia o invocar el método");
        }
        return null;
    }
}
