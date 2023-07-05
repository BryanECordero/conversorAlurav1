package com.alura.conversionapp;

public class Temperatura {
    private static String[] valores = { "°C", "K", "°F" };

    public static String[] getValores() {
        return valores;
    }

    public static double convertir(Double amount, String fromUnit, String toUnit) {
        double convertedAmount = 0.0;
        double gradosC = 0.0;
        // Converitr a la unidad base C
        switch (fromUnit) {
            case "°C":
                gradosC = amount;
                break;
            case "K":
                gradosC = amount - 273.15;
                break;
            case "°F":
                gradosC = (amount - 32) / 1.8;
                break;
            default:
                // System.out.println("Unidad de masa no reconocida: " + fromUnit);
                return convertedAmount;
        }

        // Convertir los grado centigrados a la unidad de destino
        switch (toUnit) {
            case "°C":
                convertedAmount = gradosC;
                break;
            case "K":
                convertedAmount = gradosC + 273.15;
                break;
            case "°F":
                convertedAmount = (gradosC * 1.8) + 32;
                break;
            default:
                // System.out.println("Unidad de masa no reconocida: " + toUnit);
                return convertedAmount;
        }

        return convertedAmount;
    }
}
