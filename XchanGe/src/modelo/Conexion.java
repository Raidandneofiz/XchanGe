
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    
    public Conexion() {
   }

      public Connection getConexion(){
        Connection con = null;
           try {
           Class.forName("com.mysql.jdbc.Driver").newInstance();
           con = DriverManager.getConnection("jdbc:mysql://10.10.100.124:3306/ventas","ib","123456");
           System.out.println("Se ha establecido la conexion");
              } catch (Exception e) {
           System.out.println(" La conexion ha fallado");
           }
           
        return con;
    }
}
