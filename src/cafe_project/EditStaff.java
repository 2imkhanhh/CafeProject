package cafe_project;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.sql.Connection;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import connect.DatabaseConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EditStaff extends javax.swing.JFrame {
    private final DecimalFormat numberFormat = new DecimalFormat("#,###");
    
    public EditStaff() {
        initComponents();
    }

    public EditStaff(String userID, String userName, String email, String phoneNumber, 
                    String workHours, String wageHours, String wageMonths) {
        initComponents();
        
        txtID.setText(userID);
        txtID.setEditable(false); 
        txtworkHours.setText(workHours);
        txtwageHours.setText(numberFormat.format(Integer.parseInt(wageHours)));
        txtuserName.setText(userName);
        txtuserName.setEditable(false);
        txtEmail.setText(email); 
        txtEmail.setEditable(false);
        txtwageMonth.setText(numberFormat.format(Integer.parseInt(wageMonths)));
        txtwageMonth.setEditable(false);
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
        txtworkHours = new javax.swing.JTextField();
        txtwageHours = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtuserName = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnIncrease = new javax.swing.JButton();
        btnDecrease = new javax.swing.JButton();
        btnCalculate = new javax.swing.JButton();
        txtwageMonth = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel7.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Số giờ làm");

        jLabel8.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Lương theo tháng");

        jLabel9.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Lương theo giờ");

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

        txtworkHours.setBackground(new java.awt.Color(0, 153, 153));
        txtworkHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtworkHours.setForeground(new java.awt.Color(255, 255, 255));
        txtworkHours.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtwageHours.setBackground(new java.awt.Color(0, 153, 153));
        txtwageHours.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtwageHours.setForeground(new java.awt.Color(255, 255, 255));
        txtwageHours.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

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
        jLabel12.setText("Tên đăng nhập");

        txtuserName.setBackground(new java.awt.Color(0, 153, 153));
        txtuserName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtuserName.setForeground(new java.awt.Color(255, 255, 255));
        txtuserName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        txtEmail.setBackground(new java.awt.Color(0, 153, 153));
        txtEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

        jLabel14.setFont(new java.awt.Font("Cascadia Mono", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Email");

        btnIncrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/increase.png"))); // NOI18N
        btnIncrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncreaseActionPerformed(evt);
            }
        });

        btnDecrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/decrease.png"))); // NOI18N
        btnDecrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDecreaseActionPerformed(evt);
            }
        });

        btnCalculate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salary.png"))); // NOI18N
        btnCalculate.setBorderPainted(false);
        btnCalculate.setContentAreaFilled(false);
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        txtwageMonth.setBackground(new java.awt.Color(0, 153, 153));
        txtwageMonth.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtwageMonth.setForeground(new java.awt.Color(255, 255, 255));
        txtwageMonth.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));

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
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtuserName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txtwageHours, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                        .addComponent(txtworkHours, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnDecrease)
                                    .addComponent(btnIncrease)))
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(txtwageMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCalculate)))
                .addGap(26, 26, 26))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(8, 8, 8)
                                .addComponent(txtworkHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnIncrease)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDecrease)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtwageHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCalculate)
                            .addComponent(txtwageMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSave)
                            .addComponent(btnExit))
                        .addContainerGap(31, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtuserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))))
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

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            String userID = txtID.getText();
            int workHours = Integer.parseInt(txtworkHours.getText());
            int wageHours = Integer.parseInt(txtwageHours.getText());
            String wageMonthText = txtwageMonth.getText().replace(",", "");
            int wageMonths = Integer.parseInt(wageMonthText);

            Connection conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); 

          
            String sql = "UPDATE employee SET workHours = ?, wageHours = ?, wageMonths = ? WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, workHours);
            ps.setInt(2, wageHours);
            ps.setInt(3, wageMonths);
            ps.setString(4, userID);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                conn.commit(); 
                JOptionPane.showMessageDialog(this, "Lưu thành công!");
                
            } else {
                conn.rollback(); 
                JOptionPane.showMessageDialog(this, "Lưu thất bại!");
            }

            DatabaseConnection.Close(conn);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnIncreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncreaseActionPerformed
        try {
            int workHours = Integer.parseInt(txtworkHours.getText());
            workHours += 4;
            txtworkHours.setText(String.valueOf(workHours));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số giờ làm hợp lệ!");
        }
    }//GEN-LAST:event_btnIncreaseActionPerformed

    private void btnDecreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDecreaseActionPerformed
        try {
            int workHours = Integer.parseInt(txtworkHours.getText());
            workHours = Math.max(0, workHours - 4); 
            txtworkHours.setText(String.valueOf(workHours));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số giờ làm hợp lệ!");
        }
    }//GEN-LAST:event_btnDecreaseActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        try {
            int workHours = Integer.parseInt(txtworkHours.getText().replace(".", ""));
            int wageHours = Integer.parseInt(txtwageHours.getText().replace(".", ""));
            int wageMonth = workHours * wageHours;
            txtwageMonth.setText(numberFormat.format(wageMonth));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số giờ làm và lương theo giờ hợp lệ!");
        }
    }//GEN-LAST:event_btnCalculateActionPerformed

   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditStaff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnDecrease;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnIncrease;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtuserName;
    private javax.swing.JTextField txtwageHours;
    private javax.swing.JTextField txtwageMonth;
    private javax.swing.JTextField txtworkHours;
    // End of variables declaration//GEN-END:variables
}
