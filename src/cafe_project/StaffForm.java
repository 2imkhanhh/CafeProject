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
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.util.CellRangeAddress;


public class StaffForm extends javax.swing.JPanel {
    Map<Integer, String> roleMap = new HashMap<>();
    
    public StaffForm() {
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
            Logger.getLogger(StaffForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        model.setRowCount(0);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT a.userID, a.userName, a.Email, e.workHours, e.wageHours, e.wageMonths " +
                         "FROM account a " +
                         "LEFT JOIN employee e ON a.userID = e.userID " +
                         "WHERE a.roleID = 2";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ID = rs.getString("userID");
                String userName = rs.getString("userName");
                String email = rs.getString("Email");
                int workHours = rs.getInt("workHours");
                int wageHours = rs.getInt("wageHours");
                int wageMonth = rs.getInt("wageMonths");
                String roleName = roleMap.get(2); 

                model.addRow(new Object[]{
                    ID, userName, email, workHours, wageHours, wageMonth
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffForm.class.getName()).log(Level.SEVERE, null, ex);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbContent = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel.png"))); // NOI18N
        btnAdd.setText("Xuất excel");
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
                .addGap(12, 12, 12)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReload)
                .addGap(20, 20, 20))
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
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("TÌm kiếm"));

        cbOption.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "Tên đăng nhập", "Email", "Phân Quyền" }));

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
                .addContainerGap()
                .addComponent(cbOption, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(btnFind)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFind)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbOption)
                        .addComponent(txtFind)))
                .addContainerGap())
        );

        tbContent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Email", "Work hours", "Wage hours", "Wage month"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbContent);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked

    }//GEN-LAST:event_btnAddMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file Excel");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Tệp Excel", "xlsx"));

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            // Tạo workbook Excel
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Dữ liệu nhân viên");

                // Tạo hàng tiêu đề chính 
                Row titleRow = sheet.createRow(0);
                Cell titleCell = titleRow.createCell(0);
                titleCell.setCellValue("Quản lý nhân viên");
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));

                DefaultTableModel model = (DefaultTableModel) tbContent.getModel();

                // Lấy danh sách chỉ số cột hợp lệ (loại bỏ username và email)
                List<Integer> validColumns = new ArrayList<>();
                for (int col = 0; col < model.getColumnCount(); col++) {
                    String columnName = model.getColumnName(col).toLowerCase();
                    if (!columnName.equals("username") && !columnName.equals("email")) {
                        validColumns.add(col);
                    }
                }

                // Tạo hàng tiêu đề cột
                Row headerRow = sheet.createRow(1);
                for (int i = 0; i < validColumns.size(); i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(model.getColumnName(validColumns.get(i)));
                }

                // Thêm dữ liệu từ bảng vào sheet
                for (int row = 0; row < model.getRowCount(); row++) {
                    Row excelRow = sheet.createRow(row + 2);
                    for (int i = 0; i < validColumns.size(); i++) {
                        Cell cell = excelRow.createCell(i);
                        Object value = model.getValueAt(row, validColumns.get(i));
                        if (value != null) {
                            if (value instanceof String) {
                                cell.setCellValue((String) value);
                            } else if (value instanceof Integer) {
                                cell.setCellValue((Integer) value);
                            } else {
                                cell.setCellValue(value.toString());
                            }
                        }
                    }
                }

                // Tự động điều chỉnh kích thước cột
                for (int i = 0; i < validColumns.size(); i++) {
                    sheet.autoSizeColumn(i);
                }

                // Lưu file Excel
                try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                    workbook.write(fileOut);
                    JOptionPane.showMessageDialog(this, "Xuất file Excel thành công!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Lỗi khi lưu file Excel: " + e.getMessage());
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi tạo workbook: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        loadDataToTable();
    }//GEN-LAST:event_btnReloadActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tbContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xoá!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        String userID = model.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Connection conn = null;
            PreparedStatement psEmployee = null;
            PreparedStatement psAccount = null;

            try {
                conn = DatabaseConnection.getConnection();
                conn.setAutoCommit(false); // Bắt đầu transaction

                // Xóa bản ghi từ employee
                String sqlEmployee = "DELETE FROM employee WHERE userID = ?";
                psEmployee = conn.prepareStatement(sqlEmployee);
                psEmployee.setString(1, userID);
                int rowAffectedEmployee = psEmployee.executeUpdate();

                // Xóa bản ghi từ account
                String sqlAccount = "DELETE FROM account WHERE userID = ?";
                psAccount = conn.prepareStatement(sqlAccount);
                psAccount.setString(1, userID);
                int rowAffectedAccount = psAccount.executeUpdate();

                if (rowAffectedEmployee >= 0 && rowAffectedAccount > 0) {
                    JOptionPane.showMessageDialog(this, "Xoá thành công!");
                    loadDataToTable();
                    conn.commit(); // Xác nhận transaction
                } else {
                    conn.rollback(); // Hoàn tác nếu thất bại
                    JOptionPane.showMessageDialog(this, "Xoá thất bại!");
                }
            } catch (SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback(); // Hoàn tác nếu có lỗi
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Lỗi khi hoàn tác: " + ex.getMessage());
                    }
                }
                JOptionPane.showMessageDialog(this, "Lỗi khi xoá: " + e.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StaffForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        int selectedRow = tbContent.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa!");
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        String userID = model.getValueAt(selectedRow, 0).toString();
        String userName = model.getValueAt(selectedRow, 1).toString();
        String email = model.getValueAt(selectedRow, 2).toString();
        String workHours = model.getValueAt(selectedRow, 3).toString();
        String wageHours = model.getValueAt(selectedRow, 4).toString();
        String wageMonths = model.getValueAt(selectedRow, 5).toString();

        String phoneNumber = "";
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT phoneNumber FROM account WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                phoneNumber = rs.getString("phoneNumber") != null ? rs.getString("phoneNumber") : "";
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lấy số điện thoại: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Tạo form EditStaff và truyền thông tin
        EditStaff editStaffForm = new EditStaff(userID, userName, email, phoneNumber, workHours, wageHours, wageMonths);
        editStaffForm.setVisible(true);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String searchColumn = cbOption.getSelectedItem().toString();
        String searchText = txtFind.getText().trim();
        DefaultTableModel model = (DefaultTableModel) tbContent.getModel();
        model.setRowCount(0);

        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(tbContent, "Vui lòng nhập từ khoá tìm kiếm");
        }
        
        if (searchColumn.equals("ID")) {
            if (!searchText.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, searchColumn + " phải là dạng số");
                return;
            }
        }

        String sql = "";
        switch (searchColumn) {
            case "ID":
                sql = "SELECT userID, userName, Email, workHours, wageHours, wageMonths FROM employee WHERE userID LIKE ?";
                break;
            case "Tên đăng nhập":
                sql = "SELECT userID, userName, Email, workHours, wageHours, wageMonths FROM employee WHERE userName LIKE ?";
                break;
            case "Email":
                sql = "SELECT userID, userName, Email, workHours, wageHours, wageMonths FROM employee WHERE Email LIKE ?";
                break;
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
                int workHours = rs.getInt("workHours");
                int wageHours = rs.getInt("wageHours");
                int wageMonths = rs.getInt("wageMonths");

                model.addRow(new Object[]{
                    ID, userName, email, workHours, wageHours, wageMonths
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFindActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnReload;
    private javax.swing.JComboBox<String> cbOption;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbContent;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables
}
