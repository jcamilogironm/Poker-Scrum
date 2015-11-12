/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.model.ControllerPsUsuarios;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PsUsuarios;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Juan Camilo Giron
 */
public class RegistroJInternalFrame extends javax.swing.JInternalFrame implements InterfaceCRUD {

    ControllerPsUsuarios controllerPsUsuarios;
    PsUsuarios psUsuarios;
    LoginJInternalFrame loginJInternalFrame;

    /**
     * Creates new form RegistroJInternalFrame
     */
    public RegistroJInternalFrame() {
        initComponents();
        controllerPsUsuarios = new ControllerPsUsuarios();
    }

    public void getViewLogin() {
        loginJInternalFrame = new LoginJInternalFrame();
        MDIApplication.desktopPane.add(loginJInternalFrame);
        loginJInternalFrame.setVisible(true);
        this.dispose();

        try {
            loginJInternalFrame.setMaximum(true);
            loginJInternalFrame.setLocation(0, 0);

        } catch (PropertyVetoException ex) {
            Logger.getLogger(MDIApplication.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void limpiarCampos(){
    
        nombresTextField.setText("");
        apellidosTextField.setText("");
        usuarioTextField.setText("");
        passwordField.setText("");
        empresaTextField.setText("");
      
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nombresTextField = new javax.swing.JTextField();
        apellidosTextField = new javax.swing.JTextField();
        usuarioTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        rolComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        empresaTextField = new javax.swing.JTextField();
        salirButton = new javax.swing.JButton();

        setTitle("Registro");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nombres:");

        jLabel2.setText("Apellidos:");

        jLabel3.setText("Usuario:");

        jLabel4.setText("Password:");

        jLabel5.setText("Rol:");

        rolComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Scrum Master", "Desarrollador", "Product Owner" }));

        jLabel6.setText("Empresa:");

        salirButton.setText("Back");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usuarioTextField)
                    .addComponent(passwordField)
                    .addComponent(nombresTextField)
                    .addComponent(apellidosTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rolComboBox, 0, 368, Short.MAX_VALUE)
                    .addComponent(empresaTextField)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(salirButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nombresTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(apellidosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(usuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(rolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(empresaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(salirButton)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        getViewLogin();

    }//GEN-LAST:event_salirButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidosTextField;
    private javax.swing.JTextField empresaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombresTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox rolComboBox;
    private javax.swing.JButton salirButton;
    private javax.swing.JTextField usuarioTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public Object save() {

        String[] argsUsuario = new String[8];
        argsUsuario[0] = nombresTextField.getText();
        argsUsuario[1] = apellidosTextField.getText();
        argsUsuario[2] = usuarioTextField.getText();
        argsUsuario[3] = DigestUtils.sha1Hex(passwordField.getText());
        argsUsuario[4] = empresaTextField.getText();
        argsUsuario[5] = rolComboBox.getSelectedItem().toString();

        psUsuarios = controllerPsUsuarios.crearUsuarios(argsUsuario);
        limpiarCampos();
        return psUsuarios;

    }

    @Override
    public Object edit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object select() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
