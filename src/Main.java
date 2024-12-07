import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        List<String> productos = new ArrayList<>();
        List<Double> precios = new ArrayList<>();

        // Añadir productos y precios
        productos.add("Laptop");
        precios.add(1200.0);
        logger.info("Producto añadido: Laptop, Precio: $1200.0");

        productos.add("Telefono");
        precios.add(600.0);
        logger.info("Producto añadido: Teléfono, Precio: $600.0");

        productos.add("Auriculares");
        precios.add(150.0);
        logger.info("Producto añadido: Auriculares, Precio: $150.0");

        // Intentar calcular el total de la compra
        double totalCompra = calcularTotalCompra(productos, precios, new int[] { 1, 2, 1 });

        // El total de la compra debería ser (1200 + 2*600 + 150)*0.85*1.21
        // es decir, 2622.675 (redondeamos al centimo, es decir, 2622.68)
        System.out.println("Total de la compra con impuestos y descuento: $" + totalCompra);
    }

    // Calcula el total de una compra en base a los productos y cantidades
    public static double calcularTotalCompra(List<String> productos, List<Double> precios, int[] cantidades) {
        double subtotal = calcularSubtotal(productos, precios, cantidades);
        double descuento = aplicarDescuento(subtotal); // Aplica descuento
        logger.info("Descuento aplicado: $" + descuento);
        double totalConDescuento = subtotal - descuento;

        // Aplica impuestos al total con descuento
        double totalConImpuestos = calcularImpuestos(totalConDescuento);
        return totalConImpuestos;
    }

    // Calcula el subtotal de la compra
    public static double calcularSubtotal(List<String> productos, List<Double> precios, int[] cantidades) {
        double subtotal = 0;
        for (int i = 0; i < productos.size(); i++) {  // Corregido: i < productos.size()
            subtotal += precios.get(i) * cantidades[i];
        }
        System.out.println("Subtotal: $" + subtotal);
        return subtotal;
    }

    // Aplica un descuento de acuerdo al subtotal
    public static double aplicarDescuento(double subtotal) {
        if (subtotal > 1000) {
            logger.info("Descuento del 15% aplicado");
            return subtotal * 0.15; // Descuento del 15%
        } else if (subtotal > 500) {
            logger.info("Descuento del 10% aplicado");
            return subtotal * 0.10; // Descuento del 10%
        }
        return 0.0; // Sin descuento
    }

    // Calcula los impuestos aplicados al total con descuento
    public static double calcularImpuestos(double total) {
        final double IMPUESTO = 0.21; // Impuesto del 21%
        return total + (total * IMPUESTO);  // Corregido: se suma el impuesto al total
    }
}