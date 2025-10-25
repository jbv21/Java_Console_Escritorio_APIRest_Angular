/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pe.com.galaxy.training.escritorio.app;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.com.galaxy.training.escritorio.bean.escuela.EscuelaConduccion;
import pe.com.galaxy.training.escritorio.bean.ubicacion.Departamento;
import pe.com.galaxy.training.escritorio.bean.ubicacion.Distrito;
import pe.com.galaxy.training.escritorio.bean.ubicacion.Provincia;
import pe.com.galaxy.training.escritorio.persistence.escuela.EscuelaConduccionBD;
import pe.com.galaxy.training.escritorio.persistence.escuela.EscuelaConduccionBDImpl;
import pe.com.galaxy.training.escritorio.persistence.ubicacion.DepartamentoBD;
import pe.com.galaxy.training.escritorio.persistence.ubicacion.DepartamentoBDImpl;
import pe.com.galaxy.training.escritorio.persistence.ubicacion.DistritoBD;
import pe.com.galaxy.training.escritorio.persistence.ubicacion.DistritoBDImpl;
import pe.com.galaxy.training.escritorio.persistence.ubicacion.ProvinciaBD;
import pe.com.galaxy.training.escritorio.persistence.ubicacion.ProvinciaBDImpl;
import pe.com.galaxy.training.escritorio.persistence.util.PersistenciaException;

/**
 *
 * @author HUAWEI
 */
public class FrmEscuelaConduccion extends javax.swing.JFrame {

    /**
     * Creates new form FrmEscuelaConduccion
     */
    
    EscuelaConduccionBD escuelaConduccionBD = new EscuelaConduccionBDImpl();
    DepartamentoBD departamentoBD = new DepartamentoBDImpl();
    ProvinciaBD provinciaBD = new ProvinciaBDImpl();
    DistritoBD distritoBD = new DistritoBDImpl();
    
    Integer idEscuela = 0;
    
    public FrmEscuelaConduccion() {
        initComponents();
        cbDepartamentoFiltro.addActionListener(e->cargarProvincias(true));
        cbProvinciaFiltro.addActionListener(e->cargarDistritos(true));
        cargarDepartamentos(true);
        
        cbDepartamento.addActionListener(e->cargarProvincias(false));
        cbProvincia.addActionListener(e->cargarDistritos(false));
        cargarDepartamentos(false);
    }
    
    private void cargarDepartamentos(boolean esFiltro) {
         try {
             
            if(esFiltro) {
                cbDepartamentoFiltro.removeAllItems();
                List<Departamento> deps = departamentoBD.listar(new Departamento(null,null));
                for (Departamento dep : deps) {
                    cbDepartamentoFiltro.addItem(dep);
                }
            } else {
                cbDepartamento.removeAllItems();
                List<Departamento> deps = departamentoBD.listar(new Departamento(null,null));
                for (Departamento dep : deps) {
                    cbDepartamento.addItem(dep);
                }
            }
            
         } catch (Exception e) {
         }

    }

    private void cargarProvincias(boolean esFiltro)  {
        try {
            if(esFiltro) {
                cbProvinciaFiltro.removeAllItems();
                cbDistritoFiltro.removeAllItems();
                Departamento dep = (Departamento) cbDepartamentoFiltro.getSelectedItem();
                if (dep != null) {
                    List<Provincia> provs = provinciaBD.listar(new Provincia(null,null,dep));
                    for (Provincia p : provs) {
                        cbProvinciaFiltro.addItem(p);
                    }
                }
            } else {
                cbProvincia.removeAllItems();
                cbDistrito.removeAllItems();
                Departamento dep = (Departamento) cbDepartamento.getSelectedItem();
                if (dep != null) {
                    List<Provincia> provs = provinciaBD.listar(new Provincia(null,null,dep));
                    for (Provincia p : provs) {
                        cbProvincia.addItem(p);
                    }
                }
            }

        } catch (Exception e) {
        }
  
    }

    private void cargarDistritos(boolean esFiltro) {
        try {
            if(esFiltro) {
                cbDistritoFiltro.removeAllItems();
                Departamento dep = (Departamento) cbDepartamentoFiltro.getSelectedItem();
                Provincia prov = (Provincia) cbProvinciaFiltro.getSelectedItem();
                prov.setDepartamento(dep);
                if (dep != null && prov != null) {
                    List<Distrito> dists = distritoBD.listar(new Distrito(null,null,prov));
                    for (Distrito d : dists) {
                        cbDistritoFiltro.addItem(d);
                    }
                }
            } else {
                cbDistrito.removeAllItems();
                Departamento dep = (Departamento) cbDepartamento.getSelectedItem();
                Provincia prov = (Provincia) cbProvincia.getSelectedItem();
                prov.setDepartamento(dep);
                if (dep != null && prov != null) {
                    List<Distrito> dists = distritoBD.listar(new Distrito(null,null,prov));
                    for (Distrito d : dists) {
                        cbDistrito.addItem(d);
                    }
                }                
            }
        } catch (Exception e) {
        }

    }
    
     void listarEscuelaConducir() {

         try {
             String colums[] = {
                "Id",
                "Nombre",
                "RUC",
                "Estado"
            };
             DefaultTableModel dtm = new DefaultTableModel(colums, 0);

            EscuelaConduccion obj = new EscuelaConduccion();
            
            Departamento seleDepartamento = (Departamento) cbDepartamentoFiltro.getSelectedItem();
            obj.setIdDepartamento(seleDepartamento.getId());
            Provincia seleProvincia = (Provincia) cbProvinciaFiltro.getSelectedItem();
            obj.setIdProvincia(seleProvincia.getId());
            Distrito seleDistrito = (Distrito) cbDistritoFiltro.getSelectedItem();
            obj.setIdDistrito(seleDistrito.getId());

            List<EscuelaConduccion> listEscuelaConduccion = escuelaConduccionBD.listar(obj);

            for (EscuelaConduccion escuelaConduccion : listEscuelaConduccion) {
                Object row[] = {
                    escuelaConduccion.getId(),
                    escuelaConduccion.getNombre(),
                    escuelaConduccion.getRuc(),
                    escuelaConduccion.getEstado()
                };
                dtm.addRow(row);
            }

            this.tblEscuelaConducir.setModel(dtm);

         } catch (Exception e) {
         }
            
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEscuelaConducir = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        cbDistritoFiltro = new javax.swing.JComboBox<>();
        cbDepartamentoFiltro = new javax.swing.JComboBox<>();
        cbProvinciaFiltro = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        cbDepartamento = new javax.swing.JComboBox<>();
        cbProvincia = new javax.swing.JComboBox<>();
        cbDistrito = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbEstado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtRuc = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        tblEscuelaConducir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Nombre", "RUC", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblEscuelaConducir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEscuelaConducirMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEscuelaConducir);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbDepartamentoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbProvinciaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDistritoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDistritoFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBuscar)
                        .addComponent(cbDepartamentoFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbProvinciaFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("ID:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("RUC:");

        jLabel4.setText("Departamento:");

        jLabel5.setText("Provincia:");

        jLabel6.setText("Distrito:");

        cbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "SUSPENDIDO", "BAJA" }));
        cbEstado.setSelectedItem(-1);

        jLabel7.setText("Estado: ");
        jLabel7.setToolTipText("");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        txtId.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setText("Dirección:");

        jLabel9.setText("Correo:");

        jLabel10.setText("Teléfono:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(43, 43, 43)
                                .addComponent(btnGuardar)
                                .addGap(47, 47, 47)
                                .addComponent(btnEliminar))
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(91, 91, 91)
                                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(187, 187, 187)
                                        .addComponent(cbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(187, 187, 187)
                                    .addComponent(cbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(116, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)
                                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(187, 187, 187)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(92, 92, 92)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDepartamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbDistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        try {
            if(txtId.getText().isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Registro no Válido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            if (JOptionPane.showConfirmDialog(rootPane, "Confirma la eliminacion del Escuela de Conducción " + txtNombre.getText(), "Alerta", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                EscuelaConduccion obj = new EscuelaConduccion();
                this.idEscuela = Integer.valueOf(txtId.getText());
                obj.setId(this.idEscuela);
                if (escuelaConduccionBD.eliminar(obj)) {
                    System.out.println("Escuela de Conducción eliminado con éxito");
                    JOptionPane.showMessageDialog(rootPane, "Empleado eliminado con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.listarEscuelaConducir();
                } else {
                    System.err.println("Error al eliminar el empleado");
                }
            }    
        } catch (Exception e) {
        }
    // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code listarEscuelaConducirhere:
        this.listarEscuelaConducir();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        txtId.setText("");
        txtNombre.setText("");
        txtRuc.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        cbDepartamento.setSelectedIndex(-1);
        cbProvincia.setSelectedIndex(-1);
        cbDistrito.setSelectedIndex(-1);
        cbEstado.setSelectedIndex(-1);

        this.idEscuela = 0;
        txtNombre.requestFocus();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        this.idEscuela = !txtId.getText().isEmpty() ?  Integer.parseInt(txtId.getText()) : 0;
        if(this.idEscuela > 0){
            // Validar duplicado por RUC
            EscuelaConduccion filtro = new EscuelaConduccion();
            filtro.setRuc(txtRuc.getText());
            List<EscuelaConduccion> existentes = null;
            try {
                existentes = escuelaConduccionBD.listar(filtro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(FrmEscuelaConduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (existentes != null && !existentes.isEmpty()) {
                boolean existe = false;
                for (EscuelaConduccion item : existentes){
                    System.out.println("item.getId()");
                    System.out.println(item.getId());
                    if(item.getId() != idEscuela){
                        existe = true;
                        break;
                    }
                }
                if (existe == true){

                    JOptionPane.showMessageDialog(rootPane, "Ya existe una escuela con ese RUC.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Departamento SeleDepartamento = (Departamento) cbDepartamento.getSelectedItem();
            Provincia SeleProvincia = (Provincia) cbProvincia.getSelectedItem();
            Distrito SeleDistrito = (Distrito) cbDistrito.getSelectedItem();
            EscuelaConduccion updEscuelaConduccion = new EscuelaConduccion(
                    this.idEscuela, 
                    txtRuc.getText(), 
                    txtNombre.getText(), 
                    txtDireccion.getText(), 
                    cbEstado.getSelectedItem().toString(), 
                    txtTelefono.getText(), 
                    txtCorreo.getText(), 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    1);
            updEscuelaConduccion.setIdDepartamento(SeleDepartamento == null? null : SeleDepartamento.getId());
            updEscuelaConduccion.setIdProvincia(SeleProvincia == null? null : SeleProvincia.getId());
            updEscuelaConduccion.setIdDistrito(SeleDistrito == null? null : SeleDistrito.getId());
            try {
                if(escuelaConduccionBD.actualizar(updEscuelaConduccion)){
                    JOptionPane.showMessageDialog(rootPane, "Escuela actualizada con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error al actualizar la escula", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(FrmEscuelaConduccion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Error al actualizar la escula", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {

            System.out.println("idEscuela");
            System.out.println(idEscuela);

            // Validar duplicado por RUC
            EscuelaConduccion filtro = new EscuelaConduccion();
            filtro.setRuc(txtRuc.getText());
            List<EscuelaConduccion> existentes = null;
            try {
                existentes = escuelaConduccionBD.listar(filtro);
            } catch (PersistenciaException ex) {
                Logger.getLogger(FrmEscuelaConduccion.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (existentes != null && !existentes.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Ya existe una escuela con ese RUC.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Departamento SeleDepartamento = (Departamento) cbDepartamento.getSelectedItem();
            Provincia SeleProvincia = (Provincia) cbProvincia.getSelectedItem();
            Distrito SeleDistrito = (Distrito) cbDistrito.getSelectedItem();
            EscuelaConduccion newEscuelaConduccion = new EscuelaConduccion(
                    null,
                    txtRuc.getText(), 
                    txtNombre.getText(), 
                    txtDireccion.getText(), 
                    cbEstado.getSelectedItem().toString(), 
                    txtTelefono.getText(), 
                    txtCorreo.getText(), 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    null, 
                    1);
            newEscuelaConduccion.setIdDepartamento(SeleDepartamento == null? null : SeleDepartamento.getId());
            newEscuelaConduccion.setIdProvincia(SeleProvincia == null? null : SeleProvincia.getId());
            newEscuelaConduccion.setIdDistrito(SeleDistrito == null? null : SeleDistrito.getId());
            try {
                if(escuelaConduccionBD.insertar(newEscuelaConduccion)){
                    JOptionPane.showMessageDialog(rootPane, "Escuela Registrada con éxito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Error al Registrar la escula", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (PersistenciaException ex) {
                Logger.getLogger(FrmEscuelaConduccion.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Error al Registrar la escula", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        this.listarEscuelaConducir();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblEscuelaConducirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEscuelaConducirMouseClicked
        // TODO add your handling code here:
        int row = tblEscuelaConducir.getSelectedRow();
        if (row >= 0){
            String id = tblEscuelaConducir.getValueAt(row, 0).toString();
            EscuelaConduccion escuela = new EscuelaConduccion(); 
            escuela.setId(Integer.valueOf(id));
            try {
                escuela = escuelaConduccionBD.seleccionar(escuela);
            } catch (PersistenciaException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error al Seleccionar escuela la escula", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            txtId.setText( escuela.getId().toString()); 
            txtRuc.setText( escuela.getRuc()); 
            txtNombre.setText(escuela.getNombre());
            txtDireccion.setText(escuela.getDireccion());
            txtTelefono.setText(escuela.getTelefono());
            txtCorreo.setText(escuela.getCorreo());
            
            Departamento departamento = escuela.getDepartamento();
            Provincia provincia = escuela.getProvincia();
            Distrito distrito = escuela.getDistrito();
            
            cbDepartamento.setSelectedItem(departamento);
            
            this.cargarProvincias(false); 
            Provincia pro = new Provincia();
            pro.setId(provincia.getId());
            pro.setNombre(provincia.getNombre());
            cbProvincia.setSelectedItem(pro);
            
            this.cargarDistritos(false);
            Distrito dis = new Distrito();
            dis.setId(distrito.getId());
            dis.setNombre(distrito.getNombre());
            cbDistrito.setSelectedItem(dis);
            
            cbEstado.setSelectedItem(escuela.getEstado());
            
        }
    }//GEN-LAST:event_tblEscuelaConducirMouseClicked

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<Departamento> cbDepartamento;
    private javax.swing.JComboBox<Departamento> cbDepartamentoFiltro;
    private javax.swing.JComboBox<Distrito> cbDistrito;
    private javax.swing.JComboBox<Distrito> cbDistritoFiltro;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JComboBox<Provincia> cbProvincia;
    private javax.swing.JComboBox<Provincia> cbProvinciaFiltro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblEscuelaConducir;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
