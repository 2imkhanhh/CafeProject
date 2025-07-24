package cafe_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import connect.DatabaseConnection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends javax.swing.JFrame {

  
    public Login() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnSignUp = new javax.swing.JButton();
        btnForgotPass = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 38)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Login");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, 120, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel5.setText("Email");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 110, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel6.setText("Password");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, 241, -1));

        btnSignUp.setBackground(new java.awt.Color(102, 102, 0));
        btnSignUp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSignUp.setForeground(new java.awt.Color(255, 255, 255));
        btnSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sign up.png"))); // NOI18N
        btnSignUp.setText("Sign up");
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        getContentPane().add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 100, -1));

        btnForgotPass.setBackground(new java.awt.Color(102, 102, 0));
        btnForgotPass.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnForgotPass.setForeground(new java.awt.Color(255, 255, 255));
        btnForgotPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forgot password.png"))); // NOI18N
        btnForgotPass.setText("Forgot password?");
        btnForgotPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForgotPassActionPerformed(evt);
            }
        });
        getContentPane().add(btnForgotPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, -1, -1));

        btnLogin.setBackground(new java.awt.Color(102, 102, 0));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/login.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, -1, -1));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 270, 240, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/first page background.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
        SignUp signUpForm = new SignUp();
        signUpForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnSignUpActionPerformed
    
    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email và mật khẩu đầy đủ!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "SELECT roleID FROM account WHERE email = ? AND password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
                
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int roleID = rs.getInt("roleID");
                DashBoard dashboard = new DashBoard(roleID); // Truyền roleID
                dashboard.setVisible(true);
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Email hoặc mật khẩu không đúng!", "Lỗi đăng nhập", JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void btnForgotPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForgotPassActionPerformed
        ForgotPassword forgotPasswordForm = new ForgotPassword();
        forgotPasswordForm.setVisible(true);
        //this.setVisible(false);
    }//GEN-LAST:event_btnForgotPassActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnForgotPass;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}
