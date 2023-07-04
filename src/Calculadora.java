
public class Calculadora {

    public static double convertir(String tipo, String valor,Object selectedItem, Object selectedItem2) {
        try{
            Double cantidad = Double.parseDouble(valor);
            return cantidad;
        }catch(NumberFormatException e){
            return 0; 
        }
    }

}
