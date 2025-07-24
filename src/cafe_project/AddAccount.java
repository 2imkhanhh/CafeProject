package cafe_project;

import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import connect.DatabaseConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Statement;


public class AddAccount extends javax.swing.JFrame {
    
    private Map<String, Integer> roleMap;
    
    public AddAccount() {
        roleMap = new HashMap<>();
        initComponents();
        loadRoleData();
    }
    
    private void loadRoleData() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT roleID, roleName FROM role";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            cbRole.removeAllItems();

            // Thêm dữ liệu từ bảng role vào cbRole và roleMap
            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String roleName = rs.getString("roleName");
                cbRole.addItem(roleName);
                roleMap.put(roleName, roleID);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu role: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        txtuserName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();
        txtfullName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtphoneNumber = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtAnswer = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        cbQuestions = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel7.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên đầy đủ");

        jLabel8.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Mật khẩu");

        jLabel9.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Email");

        btnExit.setBackground(new java.awt.Color(102, 102, 0));
        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnExit.setForeground(new java.awt.Color(255, 255, 255));
        btnExit.setText("Thoát");
        btnExit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        txtuserName.setBackground(new java.awt.Color(0, 153, 153));
        txtuserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtuserName.setForeground(new java.awt.Color(255, 255, 255));
        txtuserName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel11.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tên đăng nhập");

        jLabel10.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Phân quyền");

        cbRole.setBackground(new java.awt.Color(0, 153, 153));
        cbRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbRole.setForeground(new java.awt.Color(255, 255, 255));
        cbRole.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cbRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRoleActionPerformed(evt);
            }
        });

        txtfullName.setBackground(new java.awt.Color(0, 153, 153));
        txtfullName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtfullName.setForeground(new java.awt.Color(255, 255, 255));
        txtfullName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtEmail.setBackground(new java.awt.Color(0, 153, 153));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        btnSave.setBackground(new java.awt.Color(102, 102, 0));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");
        btnSave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btnSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Số điện thoại");

        txtphoneNumber.setBackground(new java.awt.Color(0, 153, 153));
        txtphoneNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtphoneNumber.setForeground(new java.awt.Color(255, 255, 255));
        txtphoneNumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel13.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Câu hỏi");

        txtAnswer.setBackground(new java.awt.Color(0, 153, 153));
        txtAnswer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtAnswer.setForeground(new java.awt.Color(255, 255, 255));
        txtAnswer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel14.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Câu trả lời");

        cbQuestions.setBackground(new java.awt.Color(0, 153, 153));
        cbQuestions.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbQuestions.setForeground(new java.awt.Color(255, 255, 255));
        cbQuestions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What's your favorite color?", "What's your favorite sport?" }));
        cbQuestions.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        cbQuestions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbQuestionsActionPerformed(evt);
            }
        });

        txtPassword.setBackground(new java.awt.Color(0, 153, 153));
        txtPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cbQuestions, javax.swing.GroupLayout.Alignment.LEADING, 0, 210, Short.MAX_VALUE)
                        .addComponent(txtphoneNumber, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtuserName, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtfullName, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(cbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(txtPassword))
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(10, 10, 10)
                        .addComponent(txtfullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtuserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel13)
                        .addGap(16, 16, 16))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbQuestions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnExit))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRoleActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String userName = txtuserName.getText().trim();
        String fullName = txtfullName.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String email = txtEmail.getText().trim();
        String phoneNumber = txtphoneNumber.getText().trim();
        String question = (String) cbQuestions.getSelectedItem();
        String answer = txtAnswer.getText().trim();
        String selectedRoleName = (String) cbRole.getSelectedItem();
        int roleID = roleMap.getOrDefault(selectedRoleName, 1); 

        if (userName.isEmpty() || fullName.isEmpty() || password.isEmpty() || email.isEmpty() ||
            phoneNumber.isEmpty() || question == null || answer.isEmpty() || selectedRoleName == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!phoneNumber.matches("\\d+") || phoneNumber.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải là dạng số và có 10 chữ số", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Connection conn = null;
        PreparedStatement pstmtAccount = null;
        PreparedStatement pstmtEmployee = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

            

            String sqlAccount = "INSERT INTO account (userName, fullName, password, email, phoneNumber, securityQuestions, answer, roleID, point) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmtAccount = conn.prepareStatement(sqlAccount, Statement.RETURN_GENERATED_KEYS);
            pstmtAccount.setString(1, userName);
            pstmtAccount.setString(2, fullName);
            pstmtAccount.setString(3, password); 
            pstmtAccount.setString(4, email);
            pstmtAccount.setString(5, phoneNumber);
            pstmtAccount.setString(6, question);
            pstmtAccount.setString(7, answer);
            pstmtAccount.setInt(8, roleID);
            pstmtAccount.setInt(9, 0); 

            int rowsAffected = pstmtAccount.executeUpdate();
            if (rowsAffected > 0) {
                // Lấy userID được sinh ra
                rs = pstmtAccount.getGeneratedKeys();
                int userID = 0;
                if (rs.next()) {
                    userID = rs.getInt(1);
                }

                if (selectedRoleName != null && selectedRoleName.equalsIgnoreCase("staff")) {
                    String sqlEmployee = "INSERT INTO employee (userName, Email, workHours, wageHours, wageMonths, userID) VALUES (?, ?, ?, ?, ?, ?)";
                    pstmtEmployee = conn.prepareStatement(sqlEmployee);
                    pstmtEmployee.setString(1, userName);
                    pstmtEmployee.setString(2, email);
                    pstmtEmployee.setInt(3, 0); 
                    pstmtEmployee.setInt(4, 0); 
                    pstmtEmployee.setInt(5, 0); 
                    pstmtEmployee.setInt(6, userID);
                    pstmtEmployee.executeUpdate();
                }

                conn.commit(); 
                JOptionPane.showMessageDialog(this, "Lưu thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);

                txtuserName.setText("");
                txtfullName.setText("");
                txtPassword.setText("");
                txtEmail.setText("");
                txtphoneNumber.setText("");
                txtAnswer.setText("");
                cbQuestions.setSelectedIndex(0);
                cbRole.setSelectedIndex(0);
            }
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback(); 
            } catch (SQLException ex) {
                Logger.getLogger(AddAccount.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void cbQuestionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbQuestionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbQuestionsActionPerformed
    
    
    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbQuestions;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtfullName;
    private javax.swing.JTextField txtphoneNumber;
    private javax.swing.JTextField txtuserName;
    // End of variables declaration//GEN-END:variables
}
