package cafe_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import connect.DatabaseConnection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SignUp extends javax.swing.JFrame {

  
    public SignUp() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtFullName = new javax.swing.JTextField();
        txtPhoneNumber = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnLogin = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cbSecurityQuestions = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Sign Up");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 120, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("User Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 150, 104, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Full Name");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 190, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Phone Number");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 240, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Email");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 280, 56, -1));

        txtUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 241, -1));

        txtFullName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtFullName, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 241, -1));

        txtPhoneNumber.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 240, 241, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 241, -1));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 241, -1));

        btnClear.setBackground(new java.awt.Color(102, 102, 0));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clear.png"))); // NOI18N
        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, -1));

        btnExit.setBackground(new java.awt.Color(102, 102, 0));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit small.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 520, 80, -1));

        btnSave.setBackground(new java.awt.Color(102, 102, 0));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, -1, -1));

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
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Password");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 320, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Security Questions");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, -1, -1));

        cbSecurityQuestions.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        cbSecurityQuestions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's your favorite color?", "What's your favorite sport?" }));
        getContentPane().add(cbSecurityQuestions, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 241, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("Answer");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 410, -1, -1));

        txtAnswer.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        getContentPane().add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 241, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/first page background.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String userName = txtUserName.getText().trim();
            String fullName = txtFullName.getText().trim();
            String phoneNumber = txtPhoneNumber.getText().trim();
            String email = txtEmail.getText().trim();
            String password = txtPassword.getText().trim();
            String securityQuestion = cbSecurityQuestions.getSelectedItem().toString();
            String answer = txtAnswer.getText();
            
            if(userName.isEmpty() || fullName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty() || answer.isEmpty()){
                JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
            }
            
            if(!phoneNumber.matches("\\d+") || phoneNumber.length() != 10){
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là dạng số và có 10 chữ số");
                return;
            }
            
            if (!email.toLowerCase().endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(this, "Email phải có dạng @gmail.com!");
                return;
            }
            
            Connection conn = DatabaseConnection.getConnection();
            if(conn != null){
                try {
                    String checkUsernameSql = "SELECT COUNT(*) FROM account WHERE userName = ?";
                    PreparedStatement checkUsernamePs = conn.prepareStatement(checkUsernameSql);
                    checkUsernamePs.setString(1, userName);
                    ResultSet usernameRS = checkUsernamePs.executeQuery();
                    if(usernameRS.next() && usernameRS.getInt(1) > 0){
                        JOptionPane.showMessageDialog(this, "Tên người dùng đã tồn tại");
                        return;
                    }
                    
                    String checkEmailSql = "SELECT COUNT(*) FROM account WHERE Email = ?";
                    PreparedStatement checkEmailPs = conn.prepareStatement(checkEmailSql);
                    checkEmailPs.setString(1, email);
                    ResultSet emailRS = checkEmailPs.executeQuery();
                    if(emailRS.next() && emailRS.getInt(1) > 0){
                        JOptionPane.showMessageDialog(this, "Email đã tồn tại");
                        return;
                    }
                    
                    
                    String checkPhoneSql = "SELECT COUNT(*) FROM account WHERE phoneNumber = ?";
                    PreparedStatement checkPhonePs = conn.prepareStatement(checkPhoneSql);
                    checkPhonePs.setString(1, phoneNumber);
                    ResultSet phoneRS = checkPhonePs.executeQuery();
                    if(phoneRS.next() && phoneRS.getInt(1) > 0){
                        JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
                        return;
                    }
                    
                    String sql = "INSERT INTO account (userName, fullName, phoneNumber, Email, Password, securityQuestions, answer, Point, roleID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    
                    ps.setString(1, userName);
                    ps.setString(2, fullName);
                    ps.setString(3, phoneNumber);
                    ps.setString(4, email);
                    ps.setString(5, password);
                    ps.setString(6, securityQuestion);
                    ps.setString(7, answer);
                    ps.setInt(8, 0);
                    ps.setInt(9, 1);
                    
                    int rowAffected = ps.executeUpdate();
                    if(rowAffected > 0){
                        JOptionPane.showMessageDialog(this, "Đăng ký thành công");
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Đăng ký thất bại");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi" + e.getMessage());
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtUserName.setText("");
        txtFullName.setText("");
        txtPhoneNumber.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        Login loginForm = new Login();
        loginForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbSecurityQuestions;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
