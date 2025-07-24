package cafe_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


public class AccountForm extends javax.swing.JPanel {
    Map<Integer, String> roleMap = new HashMap<>();
    
    public AccountForm() {
        loadRoleMap();
        initComponents();
        loadDataToTable();
        
    }
    
    private void loadRoleMap() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT roleID, roleName FROM role";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int roleID = rs.getInt("roleID");
                String roleName = rs.getString("roleName");
                roleMap.put(roleID, roleName);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách role: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDataToTable(){
        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        model.setRowCount(0);
        
        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "SELECT userID, userName, Email, roleID FROM account";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String ID = rs.getString("userID");
                String userName = rs.getString("userName");
                String email = rs.getString("Email");
                int roleID = rs.getInt("roleID");
                String roleName = roleMap.get(roleID);
                
                model.addRow(new Object[]{
                    ID, userName, email, roleName
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnReload = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        cbOption = new javax.swing.JComboBox<>();
        txtFind = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbContent = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-user.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.setBorderPainted(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnReload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reload.png"))); // NOI18N
        btnReload.setText("Tải lại");
        btnReload.setBorderPainted(false);
        btnReload.setContentAreaFilled(false);
        btnReload.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReload.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-user.png"))); // NOI18N
        btnDelete.setText("Xoá");
        btnDelete.setBorderPainted(false);
        btnDelete.setContentAreaFilled(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-user.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setBorderPainted(false);
        btnEdit.setContentAreaFilled(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReload)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEdit)
                    .addComponent(btnAdd)
                    .addComponent(btnReload)
                    .addComponent(btnDelete))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        cbOption.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tên đăng nhập", " " }));

        txtFind.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnFind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        btnFind.setBorder(null);
        btnFind.setBorderPainted(false);
        btnFind.setContentAreaFilled(false);
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cbOption, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFind)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFind)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFind)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbOption))
                .addContainerGap())
        );

        tbContent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbContent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên đăng nhập", "Email", "Phân quyền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbContent.setShowGrid(true);
        jScrollPane2.setViewportView(tbContent);
        if (tbContent.getColumnModel().getColumnCount() > 0) {
            tbContent.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbContent.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        loadDataToTable();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int selectedRow = tbContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        String id = model.getValueAt(selectedRow, 0).toString();
        String userName = model.getValueAt(selectedRow, 1).toString();
        String email = model.getValueAt(selectedRow, 2).toString();
        String phoneNumber = model.getValueAt(selectedRow, 3).toString();
        String roleName = model.getValueAt(selectedRow, 4).toString();
        
        EditAccount editAccountForm = new EditAccount(id, userName, email, phoneNumber, roleName, roleMap);
        editAccountForm.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String searchColumn = cbOption.getSelectedItem().toString();
        String searchText = txtFind.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        model.setRowCount(0);

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(tbContent, "Vui lòng nhập từ khoá tìm kiếm");
        }
        
//        if (searchColumn.equals("ID") || searchColumn.equals("Số điện thoại")) {
//            if (!searchText.matches("\\d+")) {
//                JOptionPane.showMessageDialog(this, searchColumn + " phải là dạng số");
//                return;
//            }
//        }
        
        String sql = "";
        switch (searchColumn) {
//            case "ID":
//                sql = "SELECT userID, userName, Email, phoneNumber, roleID FROM account WHERE userID LIKE ?";
//                break;
            case "Tên đăng nhập":
                sql = "SELECT userID, userName, Email, phoneNumber, roleID FROM account WHERE userName LIKE ?";
                break;
//            case "Email":
//                sql = "SELECT userID, userName, Email, phoneNumber, roleID FROM account WHERE Email LIKE ?";
//                break;
//            case "Số điện thoại":
//                sql = "SELECT userID, userName, Email, phoneNumber, roleID FROM account WHERE phoneNumber LIKE ?";
//                break;
//            case "Phân Quyền":
//                sql = "SELECT userID, userName, Email, phoneNumber, roleID FROM account WHERE roleID IN (SELECT roleID FROM role WHERE roleName LIKE ?)";
//                break;
            default:
                loadDataToTable();
                return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchText + "%"); // Tìm kiếm không phân biệt chính xác
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ID = rs.getString("userID");
                String userName = rs.getString("userName");
                String email = rs.getString("Email");
                String phoneNumber = rs.getString("phoneNumber");
                int roleID = rs.getInt("roleID");
                String roleName = roleMap.get(roleID);

                model.addRow(new Object[]{
                    ID, userName, email, phoneNumber, roleName
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tbContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xoá!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        String userID = model.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá ? ", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "DELETE FROM account WHERE userID = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, userID);
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Xoá thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xoá: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddAccount addAccountForm = new AddAccount();
        addAccountForm.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnReload;
    private javax.swing.JComboBox<String> cbOption;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbContent;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables
}
