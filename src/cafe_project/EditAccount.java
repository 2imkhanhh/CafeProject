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


public class EditAccount extends javax.swing.JFrame {
    private Map<Integer, String> roleMap;
    private String oldRoleName;
    private StaffForm staffForm;
    private AccountForm accountForm;
    
    public EditAccount() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnExit = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtphoneNumber = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbRole = new javax.swing.JComboBox<>();
        txtuserName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel7.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Tên đăng nhập");

        jLabel8.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số điện thoại");

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

        txtID.setBackground(new java.awt.Color(0, 153, 153));
        txtID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtID.setForeground(new java.awt.Color(255, 255, 255));
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel11.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ID");

        txtphoneNumber.setBackground(new java.awt.Color(0, 153, 153));
        txtphoneNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtphoneNumber.setForeground(new java.awt.Color(255, 255, 255));
        txtphoneNumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

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

        txtuserName.setBackground(new java.awt.Color(0, 153, 153));
        txtuserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtuserName.setForeground(new java.awt.Color(255, 255, 255));
        txtuserName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtuserName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(cbRole, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel7)
                .addGap(10, 10, 10)
                .addComponent(txtuserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtphoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnExit))
                .addGap(15, 15, 15))
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
        String id = txtID.getText();
        String userName = txtuserName.getText();
        String email = txtEmail.getText();
        String phoneNumber = txtphoneNumber.getText();
        String roleName = (String) cbRole.getSelectedItem();

        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại chỉ được chứa số!");
            return;
        }

        int roleID = getRoleIDFromRoleName(roleName);
        int oldRoleID = getRoleIDFromRoleName(oldRoleName); 

        Connection conn = null;
        PreparedStatement psAccount = null;
        PreparedStatement psEmployee = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

            // Cập nhật bảng account
            String sqlAccount = "UPDATE account SET userName = ?, Email = ?, phoneNumber = ?, roleID = ? WHERE userID = ?";
            psAccount = conn.prepareStatement(sqlAccount);
            psAccount.setString(1, userName);
            psAccount.setString(2, email);
            psAccount.setString(3, phoneNumber);
            psAccount.setInt(4, roleID);
            psAccount.setString(5, id);
            int rowAffectedAccount = psAccount.executeUpdate();

            if (rowAffectedAccount > 0) {
                if (oldRoleID == 2 && roleID != 2) {
                    String sqlEmployee = "DELETE FROM employee WHERE userID = ?";
                    psEmployee = conn.prepareStatement(sqlEmployee);
                    psEmployee.setString(1, id);
                    int rowAffectedEmployee = psEmployee.executeUpdate();

                    if (rowAffectedEmployee <= 0) {
                        throw new SQLException("Lỗi khi xóa khỏi bảng employee hoặc không có bản ghi để xóa");
                    }
                }
                
                if (oldRoleID != 2 && roleID == 2) {
                    String sqlEmployee = "INSERT INTO employee (userID, userName, Email, workHours, wageHours, wageMonths) VALUES (?, ?, ?, ?, ?, ?)";
                    psEmployee = conn.prepareStatement(sqlEmployee);
                    psEmployee.setString(1, id);
                    psEmployee.setString(2, userName);
                    psEmployee.setString(3, email);
                    psEmployee.setInt(4, 0); 
                    psEmployee.setInt(5, 0); 
                    psEmployee.setInt(6, 0); 
                    int rowAffectedEmployee = psEmployee.executeUpdate();

                    if (rowAffectedEmployee <= 0) {
                        throw new SQLException("Lỗi khi thêm vào bảng employee");
                    }
                }

                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                // Cập nhật các form
                if (staffForm != null) {
                    staffForm.loadDataToTable();
                }
                if (accountForm != null) {
                    accountForm.loadDataToTable();
                }
                conn.commit(); 
                dispose(); 
            } else {
                conn.rollback(); 
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); 
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi hoàn tác: " + ex.getMessage());
                }
            }
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSaveActionPerformed
    
    private int getRoleIDFromRoleName(String roleName) {
        for (Map.Entry<Integer, String> entry : roleMap.entrySet()) {
            if (entry.getValue().equals(roleName)) {
                return entry.getKey();
            }
        }
        return 1; 
    }
    
    // Constructor mới để nhận dữ liệu
    public EditAccount(String id, String userName, String email, String phoneNumber, String roleName, Map<Integer, String> roleMap) {
        this.roleMap = roleMap;
        this.staffForm = staffForm;
        this.oldRoleName = roleName; 
        initComponents();
        txtID.setText(id);
        txtID.setEditable(false);
        txtuserName.setText(userName);
        txtEmail.setText(email);
        txtphoneNumber.setText(String.valueOf(phoneNumber));
        for (String name : roleMap.values()) {
            cbRole.addItem(name);
        }
        cbRole.setSelectedItem(roleName);
    }
    
    public static void main(String args[]) {
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbRole;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtphoneNumber;
    private javax.swing.JTextField txtuserName;
    // End of variables declaration//GEN-END:variables
}
