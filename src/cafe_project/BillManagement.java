/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package cafe_project;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import connect.DatabaseConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import java.sql.Date;
import panel.ConnectSQL;
//import org.apache.poi.xwpf.usermodel.*;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;


/**
 *
 * @author ADMIN
 */
public class BillManagement extends javax.swing.JPanel {

    /**
     * Creates new form BillManagement
     */
    Connection con;
    public BillManagement() {
        initComponents();
        dtBill.setEnabled(false);
        txtNameCustomer.setEditable(false);
        txtPoint.setEditable(false);
        txtTotalAmount.setEnabled(false);
        txtReduceMoney.setEnabled(false);
        txtCustomerPay.setEnabled(false);
        txtChange.setEnabled(false);
        dtBill.setDate(new java.util.Date());
        txtOrder_Item_ID.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            load_SP();
            LayTenBan();
        }
        });

         try {
             con = DatabaseConnection.getConnection(); // Phải có hàm này
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
         }
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
        @Override
         public void keyReleased(java.awt.event.KeyEvent e) {
            String sdt = txtPhone.getText().trim();
             if (!sdt.isEmpty() && sdt.length() >= 6) {
            showSearchDialog(sdt);
        }
    }
        });

    }
    private void LayTenBan() {
    try {
        String Order_Id = txtOrder_Item_ID.getText();
        
        // 1. Lấy table_id từ order
        String sql = "SELECT table_id FROM `orders` WHERE order_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, Order_Id);
        ResultSet rs = ps.executeQuery();
        
        int Table_Id = -1;
        if (rs.next()) { // Phải kiểm tra rs.next() mới có dữ liệu
            Table_Id = rs.getInt("table_id");
        } else {
            System.out.println("Không tìm thấy order_id này!");
            return; // không chạy tiếp
        }

        // 2. Lấy TableName và Area từ tablemanagement
        String sql1 = "SELECT TableName, Area FROM tablemanagement WHERE IdTable = ?";
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps1.setInt(1, Table_Id);
        ResultSet rs1 = ps1.executeQuery();

        String Area = "";
        String TableName = "";
        if (rs1.next()) { // Phải kiểm tra rs1.next()
            Area = rs1.getString("Area");
            TableName = rs1.getString("TableName");
        } else {
            System.out.println("Không tìm thấy bàn này!");
            return;
        }

        // 3. Hiển thị lên form
        txtArea.setText(Area);
        txtTableName.setText(TableName);

    } catch (SQLException ex) {
        Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void showSearchDialog(String sdtInput)
    {
        javax.swing.JDialog dialog = new javax.swing.JDialog();
        dialog.setTitle("Tìm kiếm Khách hàng");
        dialog.setSize(400, 300);
        dialog.setModal(true);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new java.awt.BorderLayout());

        String[] columnNames = {"Họ tên", "SĐT", "Điểm"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(columnNames, 0);
        javax.swing.JTable table = new javax.swing.JTable(model);

        // SQL tìm kiếm
        try {
            String sql = "SELECT fullName, phoneNumber, Point FROM account WHERE phoneNumber LIKE ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%" + sdtInput + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("fullName"));
                row.add(rs.getString("phoneNumber"));
                row.add(rs.getString("Point"));
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Sự kiện click dòng
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtNameCustomer.setText(model.getValueAt(row, 0).toString());
                    txtPhone.setText(model.getValueAt(row, 1).toString());
                    txtPoint.setText(model.getValueAt(row, 2).toString());
                    dialog.dispose();
                }
            }
        });

        dialog.add(new javax.swing.JScrollPane(table), java.awt.BorderLayout.CENTER);
        dialog.setVisible(true);
    }
    private int layDiemDoiVoucher(String maVoucher) {
        int diemDoi = 0;
        try {
            con = DatabaseConnection.getConnection();
            String sql = "SELECT DiemDoi FROM vouchermangement WHERE Code = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maVoucher);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                diemDoi = rs.getInt("DiemDoi");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return diemDoi;
    }

    private void load_SP(){
         try {
             String id = txtOrder_Item_ID.getText();
             con = DatabaseConnection.getConnection();
             String sql = "Select id,name,quantity,tongtien from order_item where order_id = ?";
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, id);
             ResultSet rs = ps.executeQuery();
             DefaultTableModel tb = (DefaultTableModel)tblDisplay.getModel();
             tb.setRowCount(0); // Xóa dữ liệu cũ trên bảng

             double tongTien = 0; // Biến để cộng dồn tổng tiền
             while(rs.next()){
                 Vector vt = new Vector();
                 vt.add(rs.getString("id"));
                 vt.add(rs.getString("name"));
                 vt.add(rs.getString("quantity"));
                 vt.add(rs.getString("tongtien"));
                 tb.addRow(vt);
                 double tienHang = rs.getDouble("tongtien");
                   tongTien += tienHang;
             }
             txtTotalAmount.setText(String.valueOf(tongTien));
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
   private double layPhanTramGiamGia(String maVoucher) {
    double phanTram = -1;
    try {
        con = DatabaseConnection.getConnection();
        String sql = "SELECT GiamGia FROM vouchermangement WHERE Code = ? and TrangThai = N'Bật'";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, maVoucher);
        ResultSet rs = ps.executeQuery();

        if(rs.next()) {
            String giamGiaStr = rs.getString("GiamGia"); // ví dụ: "10%"
            if(giamGiaStr != null && !giamGiaStr.isEmpty()){
                giamGiaStr = giamGiaStr.replace("%", ""); // bỏ dấu %
                phanTram = Double.parseDouble(giamGiaStr); // về dạng số 10.0
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return phanTram;
}
   private void apDungGiamGia() {
        String maVoucher = txtCode.getText().trim();
        double phanTramGiam = 0;
        int diemDoiVoucher = 0;
        String sdtKhachHang = txtPhone.getText().trim();
        String TenKhachHang = txtNameCustomer.getText();
        String DiemKhachHang = txtPoint.getText();

        // Kiểm tra thông tin cơ bản
        if (sdtKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại khách hàng.");
            return;
        }
        if (DiemKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập điểm khách hàng.");
            return;
        }   
        if (TenKhachHang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.");
            return;
        }

        // Tổng tiền
        double tongTienHang = 0;
        try {
            tongTienHang = Double.parseDouble(txtTotalAmount.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tổng tiền không hợp lệ!");
            return;
        }

        double tongSauGiam = tongTienHang; // Mặc định = không giảm
        double tienGiam = 0;

        // Nếu có nhập mã voucher thì xử lý giảm giá
        if (!maVoucher.isEmpty()) {
            try {
                phanTramGiam = layPhanTramGiamGia(maVoucher);
                if (phanTramGiam == -1) {
                    JOptionPane.showMessageDialog(this, "Mã này đã hết lượt dùng hoặc không hợp lệ. Vui lòng chọn mã khác.");
                    return;
                }
                diemDoiVoucher = layDiemDoiVoucher(maVoucher);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi lấy giảm giá hoặc điểm đổi!");
                return;
            }

            // Kiểm tra điểm khách đủ không
            int diemKhachHienTai = Integer.parseInt(DiemKhachHang);
            if (diemKhachHienTai >= diemDoiVoucher) {
                diemKhachHienTai -= diemDoiVoucher;
                txtPoint.setText(String.valueOf(diemKhachHienTai));
                //capNhatDiemKhachHang(sdtKhachHang, diemKhachHienTai);
            } else {
                JOptionPane.showMessageDialog(this, "Khách không đủ điểm để đổi voucher này!");
                return;
            }

            // Áp dụng giảm giá
            tienGiam = tongTienHang * (phanTramGiam / 100);
            tongSauGiam = tongTienHang - tienGiam;
            txtReduceMoney.setText(phanTramGiam + "%");
        } else {
            // Nếu không nhập mã: giữ nguyên tổng tiền, không áp dụng gì
            txtReduceMoney.setText("0%");
        }

        // Cập nhật số tiền phải trả
        txtCustomerPay.setText(String.format("%.0f", tongSauGiam));
    }
   private void capNhatDiemKhachHang(String sdtKhachHang, int diemMoi) {
    try {
        con = DatabaseConnection.getConnection();
        String sql = "UPDATE account SET Point = ? WHERE phoneNumber = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, diemMoi);
        ps.setString(2, sdtKhachHang);
        ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCashier = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtArea = new javax.swing.JTextField();
        txtTableName = new javax.swing.JTextField();
        dtBill = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNameCustomer = new javax.swing.JTextField();
        txtPhone = new javax.swing.JTextField();
        txtPoint = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisplay = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTotalAmount = new javax.swing.JTextField();
        txtCode = new javax.swing.JTextField();
        txtReduceMoney = new javax.swing.JTextField();
        txtCustomerPay = new javax.swing.JTextField();
        txtChange = new javax.swing.JTextField();
        btnPrint = new javax.swing.JButton();
        btnApDung = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtKhachTra = new javax.swing.JTextField();
        btnLuu = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtOrder_Item_ID = new javax.swing.JTextField();
        txtIdBill = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhân viên"));

        jLabel1.setText("Tên nhân viên");

        jLabel2.setText("Khu vực");

        jLabel9.setText("Ngày lập");

        jLabel15.setText("Tên bàn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCashier)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(txtTableName, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
                    .addComponent(dtBill, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCashier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel9))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(dtBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));

        jLabel3.setText("Họ tên");

        jLabel4.setText("SĐT");

        jLabel5.setText("Điểm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNameCustomer)
                    .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPoint))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNameCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        tblDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Sản phẩm", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblDisplay);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thanh toán "));

        jLabel6.setText("Tổng tiền hàng");

        jLabel7.setText("Mã giảm giá");

        jLabel8.setText("Giảm giá");

        jLabel10.setText("Khách cần trả");

        jLabel11.setText("Tiền thừa");

        txtTotalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalAmountActionPerformed(evt);
            }
        });

        btnPrint.setText("In");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnApDung.setText("Áp mã");
        btnApDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApDungActionPerformed(evt);
            }
        });

        jLabel14.setText("Khách trả");

        txtKhachTra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachTraKeyReleased(evt);
            }
        });

        btnLuu.setText("Lưu hóa đơn");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnReset.setText("Làm mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLuu)
                        .addGap(19, 19, 19)
                        .addComponent(btnReset))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(42, 42, 42)
                        .addComponent(txtChange))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCustomerPay, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtReduceMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApDung, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTotalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApDung))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtReduceMoney, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCustomerPay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel11))
                    .addComponent(txtChange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPrint)
                    .addComponent(btnLuu)
                    .addComponent(btnReset))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 662, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin đơn hàng"));

        jLabel12.setText("Mã hóa đơn");

        jLabel13.setText("Mã đơn hàng");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdBill, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOrder_Item_ID, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtOrder_Item_ID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalAmountActionPerformed

    private void btnApDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApDungActionPerformed
        btnApDung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apDungGiamGia();
            }
        });

    }//GEN-LAST:event_btnApDungActionPerformed
    private void capNhatTienThua() {
        try {
            double tongSauGiam = Double.parseDouble(txtCustomerPay.getText()); // Tổng tiền sau giảm
            double tienKhachTra = Double.parseDouble(txtKhachTra.getText()); // Tiền khách trả

            double tienThua = tienKhachTra - tongSauGiam;

            txtChange.setText(String.format("%.0f", tienThua)); // Tiền thừa sau khi tính
        } catch (NumberFormatException e) {
            // Nếu ô trống hoặc sai định dạng, cho tiền thừa = 0
            txtChange.setText("0");
        }
    }

    private void txtKhachTraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachTraKeyReleased
        capNhatTienThua(); 
    }//GEN-LAST:event_txtKhachTraKeyReleased
    private boolean kiemTraThongTinTrong() {
        if (txtIdBill.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã hóa đơn.");
            return false;
        }
        if (txtArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập khu vực.");
            return false;
        }
        if (txtNameCustomer.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên khách hàng.");
            return false;
        }
        if (txtPhone.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại.");
            return false;
        }
        if (dtBill.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày lập hóa đơn.");
            return false;
        }
        if (txtCustomerPay.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách cần trả.");
            return false;
        }
        if (txtCashier.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thu ngân.");
            return false;
        }

        // Có thể thêm các điều kiện khác tùy yêu cầu
        return true;
    }
     private boolean kiemTraTrungMaHoaDon(String idBill) {
        try {
            String sql = "SELECT IdBill FROM billmanagement WHERE IdBill = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idBill);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true nếu có dòng -> trùng
        } catch (SQLException ex) {
            Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
            return true; // trả về true để an toàn (nếu lỗi)
        }
    }
     private void ThayDoiTrangThaiBan(String tableName, String Area){
        try {
            String sql = "Update tablemanagement set Status = N'Tắt' where TableName = ? and Area = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tableName);
            ps.setString(2, Area);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
         try {
            if (!kiemTraThongTinTrong()) {
                 return; // Dừng lại nếu còn ô trống
                }  
            String IdBill = txtIdBill.getText().trim();

            // Kiểm tra trùng mã hóa đơn
            if (kiemTraTrungMaHoaDon(IdBill)) {
                JOptionPane.showMessageDialog(this, "Mã hóa đơn đã tồn tại. Vui lòng nhập mã khác.");
                return;
            }
             String Area = txtArea.getText();
             String TableName = txtTableName.getText();
             String fullName = txtNameCustomer.getText();
             String Phone = txtPhone.getText();
             Date Ngl = new Date(dtBill.getDate().getTime());
             String Method = "Tiền mặt";
             double TotalAmout = Double.parseDouble(txtCustomerPay.getText());
             String Cashier = txtCashier.getText();
             String sql = "insert into BillManagement values (?,?,?,?,?,?,?,?)";
             PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, IdBill);
             ps.setString(2, TableName);
             ps.setString(3, fullName);
             ps.setString(4, Phone);
             ps.setDate(5, Ngl);
             ps.setString(6, Method);
             ps.setDouble(7, TotalAmout);
             ps.setString(8, Cashier);
             ps.executeUpdate();
             ThayDoiTrangThaiBan(TableName, Area);
             insertRevenue(Ngl, TotalAmout);
             int diemKhachHienTai = Integer.parseInt(txtPoint.getText())+10;
             capNhatDiemKhachHang(Phone, diemKhachHienTai);
             JOptionPane.showMessageDialog(this,"lưu hóa đơn thành công và khách được tích thêm 10 điểm");
         } catch (SQLException ex) {
             Logger.getLogger(BillManagement.class.getName()).log(Level.SEVERE, null, ex);
         }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void insertRevenue(Date date, double totalAmount) {
       String sql = "INSERT INTO revenues (date, TotalAmount) VALUES (?, ?)";

        try (Connection conn = ConnectSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pstmt.setDate(1, sqlDate);
            pstmt.setDouble(2, totalAmount);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Đã lưu doanh thu vào bảng revenues.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu doanh thu: " + ex.getMessage());
        }
}
    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // Chọn nơi lưu file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu hóa đơn");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        if (!filePath.endsWith(".docx")) {
            filePath += ".docx";
        }

        xuatHoaDonWord(filePath);
}
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtArea.setText("");
        txtTableName.setText("");
        txtOrder_Item_ID.setText("");
        txtIdBill.setText("");
        txtNameCustomer.setText("");
        txtPhone.setText("");
        txtPoint.setText("");
        txtTotalAmount.setText("");
        txtCode.setText("");
        txtReduceMoney.setText("");
        txtCustomerPay.setText("");
        txtKhachTra.setText("");
        txtChange.setText("");
    }//GEN-LAST:event_btnResetActionPerformed
public void xuatHoaDonWord(String filePath) {
    try {
        XWPFDocument document = new XWPFDocument();

            // Tiêu đề hóa đơn
    XWPFParagraph title = document.createParagraph();
    title.setAlignment(ParagraphAlignment.CENTER);
    XWPFRun runTitle = title.createRun();
    runTitle.setText("HÓA ĐƠN BÁN HÀNG");
    runTitle.setBold(true);
    runTitle.setFontSize(20);
    runTitle.setFontFamily("Times New Roman");

    // Dòng cách
    document.createParagraph();

    // Thông tin khách hàng
    XWPFParagraph info = document.createParagraph();
    info.setAlignment(ParagraphAlignment.LEFT);
    XWPFRun runInfo = info.createRun();
    runInfo.setFontFamily("Times New Roman");
    runInfo.setFontSize(12);
    runInfo.setText("Mã hóa đơn: " + txtIdBill.getText());
    runInfo.addBreak();
    runInfo.setText("Khách hàng: " + txtNameCustomer.getText());
    runInfo.addBreak();
    runInfo.setText("Số điện thoại: " + txtPhone.getText());
    runInfo.addBreak();
    runInfo.setText("Ngày lập: " + dtBill.getDate().toString());
    runInfo.addBreak();
    runInfo.setText("Thu ngân: " + txtCashier.getText());
    runInfo.addBreak();
    runInfo.setText("Bàn: " + txtArea.getText());

    // Dòng cách
    document.createParagraph();

    // Tạo bảng dữ liệu sản phẩm
    XWPFTable table = document.createTable();

    // Thiết lập độ rộng cột (có thể tinh chỉnh thêm)
    table.setWidth("100%");

    // Header
    XWPFTableRow header = table.getRow(0);
    header.getCell(0).setText("Mã SP");
    header.addNewTableCell().setText("Tên SP");
    header.addNewTableCell().setText("Số lượng");
    header.addNewTableCell().setText("Thành tiền");

    // Dữ liệu từ bảng
    DefaultTableModel model = (DefaultTableModel) tblDisplay.getModel();
    for (int i = 0; i < model.getRowCount(); i++) {
        XWPFTableRow row = table.createRow();
        row.getCell(0).setText(model.getValueAt(i, 0).toString());
        row.getCell(1).setText(model.getValueAt(i, 1).toString());
        row.getCell(2).setText(model.getValueAt(i, 2).toString());
        row.getCell(3).setText(model.getValueAt(i, 3).toString());
    }

    // Dòng cách
    document.createParagraph();

    // Tổng tiền
    XWPFParagraph payment = document.createParagraph();
    payment.setAlignment(ParagraphAlignment.RIGHT);
    XWPFRun runPay = payment.createRun();
    runPay.setFontFamily("Times New Roman");
    runPay.setFontSize(12);
    runPay.setBold(true);
    runPay.setText("Tổng tiền: " + txtTotalAmount.getText());
    runPay.addBreak();
    runPay.setText("Giảm giá: " + txtReduceMoney.getText());
    runPay.addBreak();
    runPay.setText("Khách cần trả: " + txtCustomerPay.getText());
    runPay.addBreak();
    runPay.setText("Khách trả: " + txtKhachTra.getText());
    runPay.addBreak();
    runPay.setText("Tiền thừa: " + txtChange.getText());

    // Dòng cách
    document.createParagraph();

    // Ký tên
    XWPFParagraph sign = document.createParagraph();
    sign.setAlignment(ParagraphAlignment.RIGHT);
    XWPFRun runSign = sign.createRun();
    runSign.setFontFamily("Times New Roman");
    runSign.setFontSize(12);
    runSign.setItalic(true);
    runSign.addBreak();
    runSign.addBreak();
    runSign.setText("Người lập hóa đơn");
    runSign.addBreak();
    runSign.setText("(Ký và ghi rõ họ tên)");

    // Ghi file
    FileOutputStream out = new FileOutputStream(filePath);
    document.write(out);
    out.close();
    document.close();

    JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công!");

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Lỗi xuất file Word: " + e.getMessage());
}
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApDung;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private com.toedter.calendar.JDateChooser dtBill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDisplay;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtCashier;
    private javax.swing.JTextField txtChange;
    private javax.swing.JTextField txtCode;
    private javax.swing.JTextField txtCustomerPay;
    private javax.swing.JTextField txtIdBill;
    private javax.swing.JTextField txtKhachTra;
    private javax.swing.JTextField txtNameCustomer;
    private javax.swing.JTextField txtOrder_Item_ID;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPoint;
    private javax.swing.JTextField txtReduceMoney;
    private javax.swing.JTextField txtTableName;
    private javax.swing.JTextField txtTotalAmount;
    // End of variables declaration//GEN-END:variables
}
