package InterfazGrafica;

import javax.swing.ImageIcon;
import java.awt.Image; // Necesitamos esta clase para escalar
import JUEGO.Carta;

public class UtilImagenCarta {

	public static ImageIcon obtenerIcono(Carta c) {
	    String colorParaImagen = c.getColor();
	    String tipo = c.getTipo();

	    // BLOQUE DE CORRECCIÓN: 
	    // Si la carta es un comodín, siempre usamos la imagen NEGRA
	    if (tipo.equals("CAMBIO_COLOR") || tipo.equals("MAS4")) {
	        colorParaImagen = "NEGRO"; 
	    }

	    String nombre;
	    if (tipo.equals("NUMERO")) {
	        nombre = colorParaImagen + "_" + c.getValor();
	    } else {
	        nombre = colorParaImagen + "_" + tipo;
	    }

	    String ruta = "/recursos/" + nombre + ".png";
	    
	    // El resto del método de escalado se mantiene igual...
	    java.net.URL imgURL = UtilImagenCarta.class.getResource(ruta);
	    if (imgURL != null) {
	        ImageIcon icon = new ImageIcon(imgURL);
	        Image img = icon.getImage().getScaledInstance(90, 130, Image.SCALE_SMOOTH);
	        return new ImageIcon(img);
	    }
	    return null; 
	}
    
    public static ImageIcon obtenerImagenPersonalizada(String nombreArchivo, int ancho, int alto) {
        String ruta = "/recursos/" + nombreArchivo + ".jpeg";
        java.net.URL imgURL = UtilImagenCarta.class.getResource(ruta);
        
        if (imgURL != null) {
            ImageIcon icon = new ImageIcon(imgURL);
            Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        return null;
    }
}
