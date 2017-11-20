
package controlador;
import modelo.CategoriaDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import vista.JFCategoria;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class controladorCRUD implements ActionListener{
     
    JFCategoria xchan = new JFCategoria();
    CategoriaDAO Cate = new CategoriaDAO();

    public controladorCRUD(JFCategoria xchan, CategoriaDAO Cate) {
        this.xchan = xchan;
        this.Cate = Cate;
        this.xchan.btnIngresar.addActionListener(this);
        this.xchan.btnenlistar.addActionListener(this);
        this.xchan.btnmodificar.addActionListener(this);
        this.xchan.btneliminar.addActionListener(this);
        this.xchan.btnok.addActionListener(this);
        this.xchan.btnsalir.addActionListener(this);
        
        
    }
    public void inicializarCRUD(){
        
    }
    public void LlenarTabla(JTable tableC){
        DefaultTableModel modeloT = new DefaultTableModel();
        tableC.setModel(modeloT);
        modeloT.addColumn("nombreProducto");
        modeloT.addColumn("codigoProducto");
        modeloT.addColumn("categoriaProducto");
        modeloT.addColumn("descripcionProducto");

        
        Object [] columna = new Object [4];
        
        int numRegistros = Cate.ListPersona().size();
        
        for(int i = 0; i < numRegistros ; i++){
            columna[0] = Cate.ListPersona().get(i).getNombreProducto();
            columna[1] = Cate.ListPersona().get(i).getCodigoProducto();
            columna[2] = Cate.ListPersona().get(i).getCategoriaProducto();
            columna[3] = Cate.ListPersona().get(i).getDescripcionProducto();
            
            modeloT.addRow(columna);
        }
    }
    public void LimpiarElementos(){
        xchan.txtproducto.setText("");
        xchan.txtcodigo.setText("");
        xchan.txtcategoria.setText("");
        xchan.txtdescripcion.setText("");
        xchan.txtcodigo.requestFocus();
    }                       
    @Override
    public void actionPerformed(ActionEvent e) {
            
        if(e.getSource() == xchan.btnIngresar){
            String nombreProducto = xchan.txtproducto.getText();
            int codigoProducto = Integer.parseInt(xchan.txtcodigo.getText());
            String categoriaProducto = xchan.txtcategoria.getText();
            String descripcionProducto = xchan.txtdescripcion.getText();
            
            String rptaRegistro = Cate.insertDatos(nombreProducto, codigoProducto, categoriaProducto, descripcionProducto);
            
            if(rptaRegistro != null){
                JOptionPane.showMessageDialog(null, rptaRegistro);
            }else{
                JOptionPane.showMessageDialog(null, "registro Erroneo");
            }   
            LlenarTabla(xchan.TableC);
        }  
        if(e.getSource() == xchan.btnenlistar){
            LlenarTabla(xchan.TableC);
        }
        if(e.getSource() == xchan.btnmodificar){
            int filaModificar = xchan.TableC.getSelectedRow();
            int numFS = xchan.TableC.getSelectedRowCount();
            
            if(filaModificar >= 0 && numFS == 1){
                xchan.txtproducto.setText(String.valueOf(xchan.TableC.getValueAt(filaModificar, 0)));
                xchan.txtcodigo.setText(String.valueOf(xchan.TableC.getValueAt(filaModificar,1)));
                xchan.txtcategoria.setText(String.valueOf(xchan.TableC.getValueAt(filaModificar, 2)));
                xchan.txtdescripcion.setText(String.valueOf(xchan.TableC.getValueAt(filaModificar, 3)));
                xchan.txtcodigo.setEditable(false);
                xchan.btnenlistar.setEnabled(false);
                xchan.btnmodificar.setEnabled(false);
                xchan.btneliminar.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(null, "Debes seleccionar al menos 1 fila.");
            } 
        }
        if(e.getSource() == xchan.btnok){
            String nombreProducto = xchan.txtproducto.getText();
            int codigoProducto = Integer.parseInt(xchan.txtcodigo.getText());
            String categoriaProducto = xchan.txtcategoria.getText();
            String descripcionProducto = xchan.txtdescripcion.getText();
            
            int rptaEdit = Cate.editarCategoria(nombreProducto, codigoProducto , categoriaProducto, descripcionProducto);
                if(rptaEdit>0){
                    JOptionPane.showMessageDialog(null, "Edicion Exitosa.");
                    xchan.btnIngresar.setEnabled(true);
                    xchan.btnmodificar.setEnabled(true);
                    xchan.btneliminar.setEnabled(true);
                    xchan.btnok.setEnabled(false);
                    
                }else{
                    JOptionPane.showMessageDialog(null, "No se pudo realizar la edicion.");
                }
            
                LimpiarElementos(); 
                xchan.btnIngresar.setEnabled(true);
                xchan.btnmodificar.setEnabled(true);
                xchan.btneliminar.setEnabled(true);
                xchan.btnok.setEnabled(true);
                LlenarTabla(xchan.TableC);
            
        }
        if(e.getSource() == xchan.btneliminar){
            int filaInicio = xchan.TableC.getSelectedRow();
            int numFS = xchan.TableC.getSelectedRowCount();
            ArrayList<String> listaCodigo = new ArrayList();
            String codigoProducto = "";
            if(filaInicio>=0){
                for (int i = 0; i < numFS; i++) {
                    codigoProducto = String.valueOf(xchan.TableC.getValueAt(i+filaInicio, 0));
                }
                for (int i = 0; i < numFS; i++) {
                    int rptaUsuario = JOptionPane.showConfirmDialog(null, "Quiere eliminar el registro con " + codigoProducto + "?");
                    if(rptaUsuario==0){
                       // Cate.eliminarCategoria(codigoProducto);
                    }
                }
                LlenarTabla(xchan.TableC);
            }else{
                JOptionPane.showMessageDialog(null,"Seleccione al menos una fila a eliminar.");
            }
        }
    }
    
    
    
    
    
    
    
    
    
    }
