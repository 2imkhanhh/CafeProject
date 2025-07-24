/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package viewpanel;

import dinhtuananmodel.IngredientDAO;
import connect.DatabaseConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
//import static org.apache.commons.math3.fitting.leastsquares.LeastSquaresFactory.model;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//import org.apache.poi.xwpf.usermodel.XWPFTable;
//import org.apache.poi.xwpf.usermodel.XWPFTableRow;
/**
 *
 * @author admin
 */
public class IngredientTransactionPanel extends javax.swing.JPanel {
    private final IngredientDAO ingredientDAO = new IngredientDAO();
    private String role;
    // Model cho hai bảng
    private DefaultTableModel ingredientTableModel;
    private DefaultTableModel historyTableModel;

    // Tiêu đề cột bảng Ingredient
    private final String[] INGREDIENT_COLUMNS = {
            "ID", "Name", "Import Price", "Quantity In Stock", "Unit"
    };

    // Tiêu đề cột bảng IngredientHistory
    private final String[] HISTORY_COLUMNS = {
            "ID", "Ingredient ID", "Transaction Type", "Quantity",
            "Price at Transaction", "Stock After Transaction", "Note", "Transaction Date"
    };

    /**
     * Creates new form IngredientTransactionPanel
     */
    public IngredientTransactionPanel(String role) {
        this.role=role;
        initComponents();
        ingredientTableModel = new DefaultTableModel(INGREDIENT_COLUMNS, 0) {
            // Làm cho các ô trong table không thể chỉnh sửa trực tiếp
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableIngredient.setModel(ingredientTableModel);

        historyTableModel = new DefaultTableModel(HISTORY_COLUMNS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableIngredientHistory.setModel(historyTableModel);

        // Load dữ liệu lần đầu
        loadAllIngredients();
        loadAllIngredientHistories();

            
        cbTypeSearch.addActionListener(e -> {
            String selectedTable = (String) cbTypeSearch.getSelectedItem();
            if ("Ingredient".equals(selectedTable)) {
                // Nếu là bảng Ingredient thì chỉ tìm theo Name hoặc Quantity In Stock
                cbFieldSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                    "Name", "Quantity In Stock"
                }));
            } else {
                // Nếu là bảng IngredientHistory thì tìm theo ID hoặc Transaction Date
                cbFieldSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {
                    "ID_Ingredient", "Transaction Date"
                }));
            }
        });

        // Khởi tạo lần đầu: cho cbTypeSearch mặc định là "Ingredient", nên set jComboBox2 tương ứng
        cbTypeSearch.setSelectedIndex(0);
       
    }

    private void applyRolePermissions() {
        boolean isAdmin = "admin".equalsIgnoreCase(role);
        btnDeLete.setVisible(isAdmin);      
    }
    
     private void loadAllIngredients() {
        // Xoá toàn bộ dòng cũ
        ingredientTableModel.setRowCount(0);

        String sql = "SELECT id, name, import_price, quantity_in_stock, unit FROM ingredient";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getLong("id"));
                row.add(rs.getString("name"));
                row.add(rs.getDouble("import_price"));
                row.add(rs.getDouble("quantity_in_stock"));
                row.add(rs.getString("unit"));
                ingredientTableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi load danh sách nguyên liệu: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void loadAllIngredientHistories() {
        // Xoá toàn bộ dòng cũ
        historyTableModel.setRowCount(0);

        String sql = "SELECT id, ingredient_id, transaction_type, quantity, price_at_transaction, stock_after_transaction, note, transaction_date "
                   + "FROM ingredient_history ORDER BY transaction_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getLong("id"));
                row.add(rs.getLong("ingredient_id"));
                row.add(rs.getString("transaction_type"));
                row.add(rs.getDouble("quantity"));

                // price_at_transaction có thể null
                double price = rs.getDouble("price_at_transaction");
                if (rs.wasNull()) {
                    row.add(null);
                } else {
                    row.add(price);
                }

                row.add(rs.getDouble("stock_after_transaction"));

                // note có thể null
                String note = rs.getString("note");
                row.add(note);

                Timestamp ts = rs.getTimestamp("transaction_date");
                if (ts != null) {
                    row.add(ts.toLocalDateTime().format(fmt));
                } else {
                    row.add("");
                }

                historyTableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi load lịch sử nguyên liệu: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IngredientTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void clearInputFields() {
        txtIngredientId.setText("");
        txtQuantity.setText("");
        txtNote.setText("");
        // Nếu muốn xóa luôn name, price, thì uncomment:
        // txtName.setText("");
        // txtPrice.setText("");
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
        jPanel2 = new javax.swing.JPanel();
        lblIngredientId = new javax.swing.JLabel();
        txtIngredientId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtQuantity = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        txtNote = new javax.swing.JTextField();
        btnImport = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtUnit = new javax.swing.JTextField();
        cbTypeSearch = new javax.swing.JComboBox<>();
        cbFieldSearch = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnExportExcel = new javax.swing.JButton();
        btnExportWord = new javax.swing.JButton();
        btnDeLete = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableIngredient = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableIngredientHistory = new javax.swing.JTable();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Quản Lý Kho"));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblIngredientId.setText("id:");

        txtIngredientId.setEditable(false);

        jLabel2.setText("name:");

        jLabel3.setText("quanity:");

        jLabel4.setText("price:");

        jLabel5.setText("Note:");

        txtNote.setToolTipText("");
        txtNote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoteActionPerformed(evt);
            }
        });

        btnImport.setText("Nhập Kho");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnExport.setText("Xuất Kho");
        btnExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportActionPerformed(evt);
            }
        });

        jLabel1.setText("unit:");

        cbTypeSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingredient", "IngredientHistory" }));

        cbFieldSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnExportExcel.setText("xuất execl");
        btnExportExcel.setToolTipText("");
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });

        btnExportWord.setText("xuất word");
        btnExportWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportWordActionPerformed(evt);
            }
        });

        btnDeLete.setText("Xóa Nguyên Liệu Khỏi Kho");
        btnDeLete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeLeteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(cbTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(102, 102, 102)
                                .addComponent(btnExportExcel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExportWord)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDeLete))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(cbFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblIngredientId)
                                        .addGap(26, 26, 26)
                                        .addComponent(txtIngredientId, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(63, 63, 63)
                                        .addComponent(jLabel1))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel5)))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNote)
                                    .addComponent(txtUnit, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addComponent(btnImport)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExport)
                                .addGap(5, 5, 5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIngredientId)
                    .addComponent(txtIngredientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 66, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnImport)
                            .addComponent(btnExportExcel)
                            .addComponent(btnExportWord)
                            .addComponent(btnDeLete)
                            .addComponent(btnExport))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTypeSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng nguyên liệu ở kho"));

        tableIngredient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableIngredient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableIngredientMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableIngredient);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng lịch sử nhập xuất nguyên liệu"));

        tableIngredientHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableIngredientHistory);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 785, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoteActionPerformed

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed

        try {
            String name=txtName.getText().trim();
            BigDecimal price=new BigDecimal(txtPrice.getText().trim());
            Double qty = Double.parseDouble(txtQuantity.getText().trim());
            String unit=txtUnit.getText().trim();
            String note = txtNote.getText().trim();

            // 1. Kiểm tra bắt buộc không để trống
            if (name.isEmpty() || txtPrice.getText().trim().isEmpty() || txtQuantity.getText().trim().isEmpty() || unit.isEmpty()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Vui lòng điền đầy đủ: name, price, quantity và unit.",
                    "Thiếu thông tin",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Gọi DAO để xử lý import
            ingredientDAO.importIngredientByName( name, price, unit, qty, note);

            JOptionPane.showMessageDialog(this, "Nhập kho thành công!");

            // Làm mới cả 2 bảng
            loadAllIngredients();
            loadAllIngredientHistories();

            // Xoá input sau khi thao tác
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập đúng định dạng ID và số lượng!",
                "Lỗi định dạng",
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Lỗi khi nhập kho: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportActionPerformed
        try {
            String name=txtName.getText();
            Double qty = Double.parseDouble(txtQuantity.getText().trim());
            String note = txtNote.getText().trim();

            // Gọi DAO để xử lý export
            ingredientDAO.exportIngredientByName(name, qty, note);

            JOptionPane.showMessageDialog(this, "Xuất kho thành công!");

            // Làm mới cả 2 bảng
            loadAllIngredients();
            loadAllIngredientHistories();

            // Xoá input sau khi thao tác
            clearInputFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng nhập đúng định dạng ID và số lượng!",
                "Lỗi định dạng",
                JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Lỗi khi xuất kho: " + ex.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnExportActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String selectedTable = (String) cbTypeSearch.getSelectedItem();    // "Ingredient" hoặc "IngredientHistory"
        String selectedColumn = (String) cbFieldSearch.getSelectedItem();     // Ví dụ: "Name", "Quantity In Stock", "ID" hoặc "Transaction Date"
        String keyword = txtSearch.getText().trim();

        if (keyword.isEmpty()) {
            // Nếu ô tìm kiếm rỗng, load lại tất cả dữ liệu
            loadAllIngredients();
            loadAllIngredientHistories();
            return;
        }

        if ("Ingredient".equals(selectedTable)) {
            // Tìm kiếm trong bảng Ingredient
            ingredientTableModel.setRowCount(0); // Xóa bảng cũ

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = null;
                PreparedStatement ps = null;

                if ("Name".equals(selectedColumn)) {
                    // Tìm theo tên (LIKE, không phân biệt hoa thường)
                    sql = "SELECT id, name, import_price, quantity_in_stock, unit "
                    + "FROM ingredient "
                    + "WHERE LOWER(name) LIKE LOWER(?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, "%" + keyword + "%");

                } else if ("Quantity In Stock".equals(selectedColumn)) {
                    // Tìm theo quantity_in_stock (bắt buộc phải nhập số)
                    double qtySearch;
                    try {
                        qtySearch = Double.parseDouble(keyword);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            this,
                            "Quantity In Stock phải là số.",
                            "Lỗi định dạng",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    sql = "SELECT id, name, import_price, quantity_in_stock, unit "
                    + "FROM ingredient "
                    + "WHERE quantity_in_stock = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setDouble(1, qtySearch);

                } else {
                    // Trường hợp không hợp lệ (mặc định chỉ có 2 option nên hầu như sẽ không xảy ra)
                    return;
                }

                // Thực thi query và đổ dữ liệu vào tableModel
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getLong("id"));
                        row.add(rs.getString("name"));
                        row.add(rs.getDouble("import_price"));
                        row.add(rs.getDouble("quantity_in_stock"));
                        row.add(rs.getString("unit"));
                        ingredientTableModel.addRow(row);
                    }
                }
                ps.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Lỗi khi tìm trong bảng Ingredient: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(IngredientTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            // Tìm kiếm trong bảng IngredientHistory
            historyTableModel.setRowCount(0); // Xóa bảng cũ

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = null;
                PreparedStatement ps = null;

                if ("ID_Ingredient".equals(selectedColumn)) {
                    // Tìm theo ID (phải nhập số nguyên)
                    long idSearch;
                    try {
                        idSearch = Long.parseLong(keyword);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                            this,
                            "ID phải là số nguyên.",
                            "Lỗi định dạng",
                            JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    sql = "SELECT id, ingredient_id, transaction_type, quantity, price_at_transaction, "
                    + "stock_after_transaction, note, transaction_date "
                    + "FROM ingredient_history "
                    + "WHERE ingredient_id  = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setLong(1, idSearch);

                } else if ("Transaction Date".equals(selectedColumn)) {
                    // Tìm theo Transaction Date (dùng LIKE để tìm gần đúng chuỗi ngày-giờ)
                    // Ví dụ: nhập "2025-06-01" sẽ trả về tất cả bản ghi có transaction_date chứa chuỗi đó
                    sql = "SELECT id, ingredient_id, transaction_type, quantity, price_at_transaction, "
                    + "stock_after_transaction, note, transaction_date "
                    + "FROM ingredient_history "
                    + "WHERE CAST(transaction_date AS CHAR) LIKE ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, "%" + keyword + "%");

                    // LƯU Ý: CAST(transaction_date AS CHAR) là cú pháp chung cho MySQL/MariaDB để convert TIMESTAMP thành chuỗi
                    // Nếu bạn dùng một database khác (PostgreSQL, SQL Server), hãy thay cú pháp tương ứng (VD: TO_CHAR(...) ở PostgreSQL).
                } else {
                    return;
                }

                // Thực thi query và đổ dữ liệu vào historyTableModel
                try (ResultSet rs = ps.executeQuery()) {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getLong("id"));
                        row.add(rs.getLong("ingredient_id"));
                        row.add(rs.getString("transaction_type"));
                        row.add(rs.getDouble("quantity"));

                        double priceAtTrans = rs.getDouble("price_at_transaction");
                        if (rs.wasNull()) {
                            row.add(null);
                        } else {
                            row.add(priceAtTrans);
                        }

                        row.add(rs.getDouble("stock_after_transaction"));
                        row.add(rs.getString("note"));

                        java.sql.Timestamp ts = rs.getTimestamp("transaction_date");
                        if (ts != null) {
                            row.add(ts.toLocalDateTime().format(fmt));
                        } else {
                            row.add("");
                        }

                        historyTableModel.addRow(row);
                    }
                }
                ps.close();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Lỗi khi tìm trong bảng IngredientHistory: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(IngredientTransactionPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed
//        // 1. Mở JFileChooser để chọn nơi lưu file
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Chọn vị trí lưu file Excel");
//        // Thiết lập bộ lọc chỉ .xlsx
//        fileChooser.setSelectedFile(new File("danhsach_nguyenlieu.xlsx"));
//        int userSelection = fileChooser.showSaveDialog(this);
//        if (userSelection != JFileChooser.APPROVE_OPTION) {
//            return; // Người dùng hủy, thoát luôn
//        }
//
//        File fileToSave = fileChooser.getSelectedFile();
//        // Nếu người dùng không nhập đuôi .xlsx, ta tự thêm
//        String path = fileToSave.getAbsolutePath();
//        if (!path.toLowerCase().endsWith(".xlsx")) {
//            path += ".xlsx";
//        }
//
//        // 2. Tạo Workbook và Sheet
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Ingredients");
//
//        // 3. Ghi tiêu đề cột (header) từ INGREDIENT_COLUMNS
//        Row headerRow = sheet.createRow(0);
//        for (int col = 0; col < INGREDIENT_COLUMNS.length; col++) {
//            Cell cell = headerRow.createCell(col);
//            cell.setCellValue(INGREDIENT_COLUMNS[col]);
//        }
//
//        // 4. Ghi dữ liệu từng dòng từ tableIngredient
//        int rowCount = ingredientTableModel.getRowCount();
//        int colCount = ingredientTableModel.getColumnCount();
//        for (int i = 0; i < rowCount; i++) {
//            Row row = sheet.createRow(i + 1);
//            for (int j = 0; j < colCount; j++) {
//                Cell cell = row.createCell(j);
//                Object value = ingredientTableModel.getValueAt(i, j);
//                // Kiểm tra kiểu dữ liệu để ghi cho đúng
//                if (value instanceof Number) {
//                    cell.setCellValue(((Number) value).doubleValue());
//                } else if (value != null) {
//                    cell.setCellValue(value.toString());
//                } else {
//                    cell.setCellValue("");
//                }
//            }
//        }
//
//        // 5. Tự động điều chỉnh kích thước cột cho vừa khít
//        for (int col = 0; col < INGREDIENT_COLUMNS.length; col++) {
//            sheet.autoSizeColumn(col);
//        }
//
//        // 6. Ghi workbook ra file
//        try (FileOutputStream fos = new FileOutputStream(path)) {
//            workbook.write(fos);
//            workbook.close();
//            JOptionPane.showMessageDialog(this,
//                "Xuất Excel thành công:\n" + path,
//                "Thành công",
//                JOptionPane.INFORMATION_MESSAGE);
//        } catch (IOException ex) {
//            JOptionPane.showMessageDialog(this,
//                "Lỗi khi ghi file Excel: " + ex.getMessage(),
//                "Lỗi",
//                JOptionPane.ERROR_MESSAGE);
//            ex.printStackTrace();
//        }
    }//GEN-LAST:event_btnExportExcelActionPerformed

    private void btnExportWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportWordActionPerformed
//        try {
//
//            // 2. Chọn đường dẫn lưu file Word
//            JFileChooser chooser = new JFileChooser();
//            chooser.setDialogTitle("Chọn nơi lưu file Word");
//            chooser.setSelectedFile(new File("IngredientHistory.docx"));
//            if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
//                return; // user hủy
//            }
//            String filePath = chooser.getSelectedFile().getAbsolutePath();
//            if (!filePath.toLowerCase().endsWith(".docx")) {
//                filePath += ".docx";
//            }
//
//            // 3. Tạo document và bảng
//            XWPFDocument document = new XWPFDocument();
//            XWPFTable wordTable = document.createTable(historyTableModel.getRowCount() + 1, historyTableModel.getColumnCount());
//
//            // 4. Ghi header
//            XWPFTableRow headerRow = wordTable.getRow(0);
//            for (int col = 0; col < historyTableModel.getColumnCount(); col++) {
//                headerRow.getCell(col).setText(historyTableModel.getColumnName(col));
//            }
//
//            // 5. Ghi dữ liệu
//            for (int row = 0; row < historyTableModel.getRowCount(); row++) {
//                XWPFTableRow tableRow = wordTable.getRow(row + 1);
//                for (int col = 0; col < historyTableModel.getColumnCount(); col++) {
//                    Object value = historyTableModel.getValueAt(row, col);
//                    tableRow.getCell(col).setText(value != null ? value.toString() : "");
//                }
//            }
//
//            // 6. Ghi file ra đĩa
//            try (FileOutputStream out = new FileOutputStream(filePath)) {
//                document.write(out);
//            }
//            document.close();
//
//            JOptionPane.showMessageDialog(this, "Xuất thành công: " + filePath);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Lỗi khi xuất: " + ex.getMessage(),
//                "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_btnExportWordActionPerformed

    private void btnDeLeteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeLeteActionPerformed
        try {
            Long id = Long.parseLong(txtIngredientId.getText());

            // Hiển thị hộp thoại xác nhận
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Bạn có chắc chắn muốn xóa nguyên liệu có ID = " + id + " không?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (choice == JOptionPane.YES_OPTION) {
                // Nếu người dùng chọn Yes thì mới xóa
                ingredientDAO.deleteIngredientWithHistory(id);
                loadAllIngredients();
                loadAllIngredientHistories();
            }
            // Nếu chọn No thì không làm gì

        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(
                this,
                "ID không hợp lệ!",
                "Lỗi đầu vào",
                JOptionPane.ERROR_MESSAGE
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Xóa thất bại: " + ex.getMessage(),
                "Lỗi SQL",
                JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_btnDeLeteActionPerformed

    private void tableIngredientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableIngredientMouseClicked

        int selectedRow = tableIngredient.getSelectedRow();
        if (selectedRow >= 0) {
            // Lấy giá trị từ model của bảng (theo thứ tự cột đã định nghĩa)
            Object idObj    = ingredientTableModel.getValueAt(selectedRow, 0); // cột ID
            Object nameObj  = ingredientTableModel.getValueAt(selectedRow, 1); // cột Name
            Object priceObj = ingredientTableModel.getValueAt(selectedRow, 2); // cột Import Price
            Object unitObj  = ingredientTableModel.getValueAt(selectedRow, 4); // cột Unit
            Object quanityObj =ingredientTableModel.getValueAt(selectedRow, 3);

            // Đổ giá trị lên các JTextField tương ứng
            txtIngredientId.setText(idObj.toString());
            txtName.setText(nameObj.toString());
            txtPrice.setText(priceObj.toString());
            txtUnit.setText(unitObj.toString());
            txtQuantity.setText(quanityObj.toString());

        }
    }//GEN-LAST:event_tableIngredientMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeLete;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton btnExportWord;
    private javax.swing.JButton btnImport;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cbFieldSearch;
    private javax.swing.JComboBox<String> cbTypeSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIngredientId;
    private javax.swing.JTable tableIngredient;
    private javax.swing.JTable tableIngredientHistory;
    private javax.swing.JTextField txtIngredientId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
