public class Masa {
    private static String[] valores = { "kg", "lb", "g", "oz", "Ton metrica", "Ton corta" };

    public static String[] getValores() {
        return valores;
    }

    public static double convertir(Double amount, String fromUnit, String toUnit) {
        double convertedAmount = 0.0;
        // Convertir la cantidad a gramos como unidad base
        double grams = 0.0;
        switch (fromUnit.toLowerCase()) {
            case "kg":
                grams = amount * 1000;
                break;
            case "g":
                grams = amount;
                break;
            case "lb":
                grams = amount * 453.59237;
                break;
            case "ton metrica":
                grams = amount * Math.pow(10, 6);
                break;
            case "ton corta":
                grams = amount * 907185;
                break;
            case "oz":
                grams = amount * 28.3495;
                break;
            default:
                // System.out.println("Unidad de masa no reconocida: " + fromUnit);
                return convertedAmount;
        }

        // Convertir los gramos a la unidad de destino
        switch (toUnit.toLowerCase()) {
            case "kg":
                convertedAmount = grams / 1000;
                break;
            case "g":
                convertedAmount = grams;
                break;
            case "lb":
                convertedAmount = grams / 453.59237;
                break;
            case "ton metrica":
                convertedAmount = grams / Math.pow(10, 6);
                break;
            case "ton corta":
                convertedAmount = grams / 907185;
                break;
            case "oz":
                convertedAmount = grams / 28.3495;
                break;
            default:
                // System.out.println("Unidad de masa no reconocida: " + toUnit);
                return convertedAmount;
        }

        return convertedAmount;
    }
}
