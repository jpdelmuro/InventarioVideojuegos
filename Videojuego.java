public abstract class Videojuego {
    protected String titulo;
    protected double precio;
    protected int stock;
    protected String consola;

    public Videojuego(String titulo, double precio, int stock, String consola) {
        this.titulo = titulo;
        this.precio = precio;
        this.stock = stock;
        this.consola = consola;
    }

    public String getTitulo() {
        return titulo; 
    }
    
    public double getPrecio() {
        return precio; 
    }
    
    public int getStock() { 
        return stock;
    }
    public String getConsola() {
        return consola; 
    }
    public void setStock(int stock) {
        this.stock = stock; 
    }

    public boolean vender(int cantidad) {
        if (cantidad > stock) {
            return false;
        }
        stock -= cantidad;
        return true;
    }


    public void comprar(int cantidad) {
        stock += cantidad;
    }

    @Override
    public String toString() {
        return "VideoJuego:{"+
                "\"Titulo\":"+titulo+","+
                "\"Precio\":"+precio+","+
                "\"Stock\":"+stock+","+
                "\"Consola\":"+consola+
                "}";
    }
}
