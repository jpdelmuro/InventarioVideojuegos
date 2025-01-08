import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventarioGUI extends JFrame {
    private Inventario inventario;

    public InventarioGUI() {
        inventario = Inventario.getInstance();
        setTitle("Sistema de Inventario de Videojuegos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Creacion de los botones
        JPanel contenedor = new JPanel();
        JButton btnAgregar = new JButton("Agregar Videojuego");
        JButton btnMostrar = new JButton("Actualizar Inventario");
        JButton btnVender = new JButton("Vender Videojuego");
        contenedor.add(btnAgregar);
        contenedor.add(btnVender);

        //Creamos el area donde aparece el registro de juegos
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);


        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Ingrese el título del videojuego:");
                String precioStr = JOptionPane.showInputDialog("Ingrese el precio:");
                double precio = Double.parseDouble(precioStr);
                String stockStr = JOptionPane.showInputDialog("Ingrese el stock:");
                int stock = Integer.parseInt(stockStr);

 
                String[] opciones = {"Xbox", "PlayStation", "Nintendo"};
                String consola = (String) JOptionPane.showInputDialog(null, "Seleccione la consola:",
                        "Consola", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                Videojuego juego;
                switch (consola) {
                    case "Xbox":
                        juego = new Xbox(titulo, precio, stock);
                        break;
                    case "PlayStation":
                        juego = new PlayStation(titulo, precio, stock);
                        break;
                    case "Nintendo":
                        juego = new Nintendo(titulo, precio, stock);
                        break;
                    default:
                        throw new IllegalStateException("Consola no válida: " + consola);
                }
                inventario.agregarVideojuego(juego);
                
                for (ActionListener al : btnMostrar.getActionListeners()) {
                    al.actionPerformed(e);
                }
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
                for (Videojuego juego : inventario.getVideojuegos()) {
                    textArea.append(juego.toString() + "\n");
                }
            }
        });

        btnVender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Ingrese el título del videojuego a vender:");


                String[] opciones = {"Xbox", "PlayStation", "Nintendo"};
                String consola = (String) JOptionPane.showInputDialog(null, "Seleccione la consola:",
                        "Consola", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

                Videojuego juego = inventario.buscarVideojuego(titulo, consola);
                if (juego != null) {
                    String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad a vender:");
                    int cantidad = Integer.parseInt(cantidadStr);


                    if (cantidad > juego.getStock()) {
                        JOptionPane.showMessageDialog(null, "Error: No hay suficiente stock para realizar la venta.",
                                "Error de Stock", JOptionPane.ERROR_MESSAGE);
                    } else {
                        juego.vender(cantidad);
                        JOptionPane.showMessageDialog(null, "Venta realizada. Stock actual: " + juego.getStock());
                        
                        for (ActionListener al : btnMostrar.getActionListeners()) {
                            al.actionPerformed(e);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Videojuego no encontrado para la consola seleccionada.");
                }
            }
        });
        // Ponemos que los botones esten en la parte de abajo de la pantalla
        add(contenedor, BorderLayout.SOUTH);
        
    }
}
    