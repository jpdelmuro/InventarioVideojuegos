import java.util.ArrayList;
public class Inventario {
    private static Inventario instance;
    private ArrayList<Videojuego> videojuegos;

    //creamos el inventario como un arraylist
    private Inventario() {
        videojuegos = new ArrayList<>();
    }
    
    //singleton para la creacion del inventario y asegurar que solo haya 1
    public static Inventario getInstance() {
        if (instance == null) {
            instance = new Inventario();
        }
        return instance;
    }
    
    // agregamos el videojuego con una verificacion en la que revise si ya existe en la lista
    public void agregarVideojuego(Videojuego juego) {
        for (int i = 0; i < videojuegos.size(); i++) {
            Videojuego v = videojuegos.get(i);
            if (v.getTitulo().equalsIgnoreCase(juego.getTitulo()) && v.getConsola().equalsIgnoreCase(juego.getConsola())) {
                v.setStock(v.getStock() + juego.getStock());
                System.out.println("El stock del videojuego se ha actualizado.");
                return;
            }
        }
        // si no lo existe lo arregla
        videojuegos.add(juego);
        System.out.println("El videojuego se ha agregado al inventario.");
    }


    
    public ArrayList<Videojuego> getVideojuegos() {
        return videojuegos;
    }



    public Videojuego buscarVideojuego(String titulo, String consola) {
        for (Videojuego juego : videojuegos) {
            if (juego.getTitulo().equalsIgnoreCase(titulo) && juego.getClass().getSimpleName().equalsIgnoreCase(consola)) {
                return juego;
            }
        }
        return null;
    }

    public void mostrarInventario() {
        for (Videojuego juego : videojuegos) {
            System.out.println(juego);
        }
    }
}
