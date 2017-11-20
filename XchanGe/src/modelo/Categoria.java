
package modelo;


public class Categoria {
    String nombreProducto;
    int codigoProducto;
    String categoriaProducto;
    String descripcionProducto;

    public Categoria(String nombreProducto, int codigoProducto, String categoriaProducto, String descripcionProducto) {
        this.nombreProducto = "";
        this.codigoProducto = codigoProducto;
        this.categoriaProducto = "";
        this.descripcionProducto = "";
    }

    Categoria() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }










}
