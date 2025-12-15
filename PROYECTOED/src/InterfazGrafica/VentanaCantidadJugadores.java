package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCantidadJugadores extends JFrame {
    private JPanel contentPane;

    public VentanaCantidadJugadores() {
        setTitle("Configuración de Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350); // Un poco más alto para el nuevo botón
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("¿CUÁNTOS JUGADORES?");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(10, 30, 414, 30);
        contentPane.add(lblTitulo);

        // MODIFICACIÓN: El modelo ahora es (valorInicial, mínimo, MÁXIMO, incremento)
        // Cambiamos el 4 por un 12.
        JSpinner spinner = new JSpinner(new SpinnerNumberModel(2, 2, 12, 1));
        spinner.setFont(new Font("Tahoma", Font.PLAIN, 20));
        spinner.setBounds(180, 100, 70, 40);
        contentPane.add(spinner);

        // BOTÓN SIGUIENTE
        JButton btnSiguiente = new JButton("SIGUIENTE");
        btnSiguiente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int cantidad = (int) spinner.getValue();
                // Aquí pasamos la cantidad a la ventana de nombres
                VentanaNombreJugador vNombre = new VentanaNombreJugador(cantidad);
                vNombre.setVisible(true);
                dispose();
            }
        });
        btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnSiguiente.setBounds(150, 170, 130, 40);
        contentPane.add(btnSiguiente);

        // BOTÓN VOLVER (Para regresar al Menú)
        JButton btnVolver = new JButton("VOLVER");
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Menu menuPrincipal = new Menu();
                menuPrincipal.setVisible(true);
                dispose(); // Cerramos esta ventana
            }
        });
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnVolver.setBounds(10, 260, 100, 30);
        contentPane.add(btnVolver);
    }
}
