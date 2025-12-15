package InterfazGrafica;

import javax.swing.*;

import JUEGO.JuegoUno;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaNombreJugador extends JFrame {
    private JTextField txtNombre;
    private JLabel lblTitulo;
    private int totalJugadores;
    private int contadorActual = 1;
    private ArrayList<String> listaNombres; // Aquí guardamos los nombres

    public VentanaNombreJugador(int total) {
        this.totalJugadores = total;
        this.listaNombres = new ArrayList<>();
        
        setTitle("Registro de Jugadores");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 250);
        getContentPane().setLayout(null);

        lblTitulo = new JLabel("Nombre del Jugador 1:");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setBounds(50, 30, 300, 30);
        getContentPane().add(lblTitulo);

        txtNombre = new JTextField();
        txtNombre.setBounds(50, 80, 280, 35);
        getContentPane().add(txtNombre);

        JButton btnAceptar = new JButton("ACEPTAR");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa un nombre.");
                    return;
                }

                // Guardar nombre
                listaNombres.add(nombre);
                
                if (contadorActual < totalJugadores) {
                    // Reutilizamos la misma ventana: limpiamos y aumentamos contador
                    contadorActual++;
                    lblTitulo.setText("Nombre del Jugador " + contadorActual + ":");
                    txtNombre.setText("");
                    txtNombre.requestFocus();
                } else {
                    // Ya tenemos todos los nombres
                    JOptionPane.showMessageDialog(null, "¡Todos los jugadores registrados!");
                    iniciarJuego(); 
                }
            }
        });
        btnAceptar.setBounds(140, 140, 100, 35);
        getContentPane().add(btnAceptar);
    }

    private void iniciarJuego() {
        JuegoUno partida = new JuegoUno();
        for (String n : listaNombres) {
            partida.agregarJugador(n);
        }
        
        partida.iniciarPartida(); // Aquí se reparten las cartas en la lógica
        
        // Abrimos la ventana profesional
        VentanaJuego gui = new VentanaJuego(partida);
        gui.setVisible(true);
        
        this.dispose();
    }
}