
package modelo;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class CategoriaDAO {
    
  
    Conexion conexion;

    public CategoriaDAO() {
        conexion = new Conexion();
        Connection accesoDB = conexion.getConexion();
    }
    public String insertDatos (String nombreProducto, int codigoProducto, String categoriaProducto, String descripcionProducto){    
    String rptaRegistro = null;
         try{
        Connection accesoDB = conexion.getConexion(); 
        CallableStatement cs = accesoDB.prepareCall("{call sp_insertarDatos(?,?,?,?)}");
        cs.setString(1, nombreProducto);
        cs.setInt(2, codigoProducto);
        cs.setString(3, categoriaProducto);
        cs.setString(4, descripcionProducto);
       
        
        int numFAfectadas = cs.executeUpdate();
        if(numFAfectadas > 0){
            rptaRegistro = "Registro Exitoso";
        }
        
        }catch (SQLException e){
            
        }
        return rptaRegistro;
    }
    public ArrayList<Categoria>ListPersona(){
        
        ArrayList listaCategoria = new ArrayList();
        Categoria categoria;
        try {
           Connection acceDB = conexion.getConexion();
           PreparedStatement ps = acceDB.prepareStatement("select * from Categoria");
           ResultSet rs = ps.executeQuery();
           while(rs.next()){
               categoria = new Categoria();
               categoria.setNombreProducto(rs.getString(1));
               categoria.setCodigoProducto(rs.getInt(2));
               categoria.setCategoriaProducto(rs.getString(3));
               categoria.setDescripcionProducto(rs.getString(4));               
               listaCategoria.add(categoria);
           }            
                        
        } catch (Exception e) {
        }
        return listaCategoria;
    }
    public int editarCategoria(String nombreProducto, int codigoProducto, String categoriaProducto, String descripcionProducto){
        int numFA = 0;        
        try{
        Connection accesoDB = conexion.getConexion();
        CallableStatement cs = accesoDB.prepareCall("{call sp_editarCategoria(?,?,?,?)}");
        cs.setString(1, nombreProducto);
        cs.setInt(2, codigoProducto);
        cs.setString(3, categoriaProducto);
        cs.setString(4, descripcionProducto);

        
        numFA = cs.executeUpdate();
            
        }catch (Exception e){
        }
        return numFA;
        }
     public int eliminarCategoria (int codigoProducto){
        int numFA = 0;
        try{
        Connection accesoDB = conexion.getConexion();
        CallableStatement cs = accesoDB.prepareCall("{call sp_eliminarProducto(?)}");
        cs.setInt(2, codigoProducto);
        
        numFA = cs.executeUpdate();
            
        }catch (Exception e){
        }
        return numFA;
    }
    
    
}
