package viewpanel;

import dinhtuananmodel.Category;
import dinhtuananmodel.CategoryDAO;
import dinhtuananmodel.MenuItem;
import dinhtuananmodel.MenuItemDAO;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author admin
 */
public class MenuItemPanel extends javax.swing.JPanel {
    private String role;
    private MenuItemDAO menuItemDAO = new MenuItemDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    /**
     * Creates new form MenuItemPanel
     */
    public MenuItemPanel(String role) {
        this.role=role;
        initComponents();
        DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[] {"ID","Name","Price","CategoryName","Image"}
        ) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 4) return ImageIcon.class;
                return super.getColumnClass(column);
            }
        };
        tableMenuItem.setModel(model);
        tableMenuItem.setRowHeight(100);
        loadCategories();
        loadData();
    }

    private void applyRolePermissions() {
        boolean isAdmin = "admin".equalsIgnoreCase(role);
        btnAdd.setVisible(isAdmin);
        btnUpdate.setVisible(isAdmin);
        btnDelete.setVisible(isAdmin);
    }
    
    private void loadCategories() {
        List<Category> cats = categoryDAO.getAllCategories();
        DefaultComboBoxModel<Category> cbModel = new DefaultComboBoxModel<>();
        for (Category c : cats) cbModel.addElement(c);
        cbCategory.setModel(cbModel);
    }
    
    private void loadData() {
    DefaultTableModel model = (DefaultTableModel) tableMenuItem.getModel();
    model.setRowCount(0);
    List<MenuItem> items = menuItemDAO.getAllMenuItems();
    for (MenuItem m : items) {
        // tìm tên category
        String catName = "";
        for (int i = 0; i < cbCategory.getItemCount(); i++) {
            Category c = cbCategory.getItemAt(i);
            if (c.getId() == m.getCategoryId()) {
                catName = c.getName();
                break;
            }
        }
        
        ImageIcon icon = loadIconFromString(m.getImageUrl());
        
        model.addRow(new Object[]{
            m.getId(),
            m.getName(),
            m.getPrice(),
            catName,
            icon
        });
    }
}
    
    private ImageIcon loadIconFromString(String imageUrlOrDataUri) {
    if (imageUrlOrDataUri == null || imageUrlOrDataUri.trim().isEmpty()) {
        return null;
    }
    try {
        if (imageUrlOrDataUri.startsWith("data:")) {
            // --- Trường hợp Data URI (Base64) ---
            // Ví dụ: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgA..."
            // 1) Tách phần Base64 sau dấu phẩy:
            int commaIndex = imageUrlOrDataUri.indexOf(',');
            if (commaIndex < 0) {
                // Không phải định dạng "data:<MIME>;base64,<BASE64DATA>"
                return null;
            }
            String metadata = imageUrlOrDataUri.substring(5, commaIndex);
            String base64data = imageUrlOrDataUri.substring(commaIndex + 1);

            // (Tuỳ bạn có muốn kiểm tra metadata xem có chứa "base64" không)
            // Giải mã Base64:
            byte[] imageBytes = Base64.getDecoder().decode(base64data);
            // Đọc mảng byte thành BufferedImage:
            java.awt.image.BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(imageBytes));
            if (bufImg == null) {
                return null;
            }
            // Scale xuống 100x100 (hoặc tuỳ ý):
            Image scaled = bufImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);

        } else {
            // --- Trường hợp URL HTTP/HTTPS bình thường ---
            URL url = new URL(imageUrlOrDataUri);
            ImageIcon rawIcon = new ImageIcon(url);
            Image scaled = rawIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        }
    } catch (MalformedURLException ex) {
        System.err.println("MalformedURLException: URL không hợp lệ -> " + imageUrlOrDataUri);
        ex.printStackTrace();
        return null;
    } catch (Exception ex) {
        System.err.println("Lỗi khi load hình từ chuỗi: " + imageUrlOrDataUri);
        ex.printStackTrace();
        return null;
    }
}
    
    private boolean isValidImageString(String str) {
        if (str.startsWith("data:")) {
            int commaIndex = str.indexOf(',');
            if (commaIndex < 0) return false;
            String base64data = str.substring(commaIndex + 1);
            try {
                byte[] decoded = Base64.getDecoder().decode(base64data);
                return decoded.length > 0;
            } catch (IllegalArgumentException ex) {
                return false;
            }
        } else {
            try {
                URL url = new URL(str);
                String protocol = url.getProtocol().toLowerCase();
                return protocol.equals("http") || protocol.equals("https");
            } catch (MalformedURLException ex) {
                return false;
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMenuItem = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbCategory = new javax.swing.JComboBox<>();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabelmage = new javax.swing.JLabel();
        jTextImage = new javax.swing.JTextField();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        cbSearch = new javax.swing.JComboBox<>();

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tableMenuItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Price", "CategoryName", "Image"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMenuItemMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMenuItem);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ID:");

        jLabel2.setText("Tên Món:");

        jLabel3.setText("Giá Món:");

        jLabel4.setText("Danh Mục:");

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabelmage.setText("Link Ảnh:");

        btnSearch.setText("Tìm kiếm");
        btnSearch.setToolTipText("");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        cbSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name", "CategoryName", "Price", " " }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextImage, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAdd)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelmage)
                                .addGap(396, 396, 396))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelmage)
                    .addComponent(jTextImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(cbSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMenuItemMouseClicked
        int row = tableMenuItem.getSelectedRow();
        if (row < 0) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tableMenuItem.getModel();

        // 1. Lấy ID (cột 0), Name (cột 1), Price (cột 2), CategoryName (cột 3), ImageIcon (cột 4)
        Object idObj    = model.getValueAt(row, 0);
        Object nameObj  = model.getValueAt(row, 1);
        Object priceObj = model.getValueAt(row, 2);
        Object catNameObj = model.getValueAt(row, 3);
        Object iconObj  = model.getValueAt(row, 4); // là ImageIcon, nếu cần bạn có thể bỏ qua

        // 2. Set vào form
        txtId.setText(String.valueOf(idObj));
        txtName.setText(String.valueOf(nameObj));
        txtPrice.setText(String.valueOf(priceObj));

        // 3. Lấy link ảnh từ DAO (nếu bạn muốn show lại đường link gốc)
        //    Cách đơn giản: khi loadData(), bạn có thể lưu imageURL vào một hidden column hoặc 1 Map<ID->imageURL>.
        //    Ở đây giả sử mảng model có thêm một hidden column chứa imageURL gốc (hoặc bạn gọi DAO để lấy lại đối tượng MenuItem theo id).
        //    Ví dụ đơn giản: gọi lại DAO để fetch MenuItem theo id (chỉ nên làm khi số bản ghi nhỏ).
        long selectedId = Long.parseLong(txtId.getText());
        MenuItem clickedItem = menuItemDAO.getMenuItemById(selectedId); // Bạn cần bổ sung phương thức này trong DAO
        if (clickedItem != null) {
            jTextImage.setText(clickedItem.getImageUrl());
        } else {
            jTextImage.setText("");
        }

        // 4. Chọn ComboBox tương ứng categoryName
        String selectedCatName = String.valueOf(catNameObj);
        for (int i = 0; i < cbCategory.getItemCount(); i++) {
            Category c = cbCategory.getItemAt(i);
            if (c.getName().equals(selectedCatName)) {
                cbCategory.setSelectedIndex(i);
                break;
            }
        }

    }//GEN-LAST:event_tableMenuItemMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String name = txtName.getText().trim();
        String priceText = txtPrice.getText().trim();
        String ImageURL=jTextImage.getText().trim();

        if (name.isEmpty()||priceText.isEmpty()||ImageURL.isEmpty()) {
            JOptionPane.showMessageDialog(this, "không được để trống các trường");
            return;
        }
        if (name.length() > 255) {
            JOptionPane.showMessageDialog(this, "Tên món không được vượt quá " + 255 + " ký tự.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(priceText);
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải >= 0");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ");
            return;
        }

        if (!isValidImageString(ImageURL)) {
            JOptionPane.showMessageDialog(this, "Link Ảnh không hợp lệ.\n" +
                "Nếu là Data URI thì phải bắt đầu bằng \"data:image/...;base64,<dữ liệu>\".\n" +
                "Nếu là URL HTTP/HTTPS thì phải đúng định dạng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Category selectedCategory = (Category) cbCategory.getSelectedItem();
        if (selectedCategory == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn danh mục");
            return;
        }

        boolean success = menuItemDAO.insertMenuItem(name, price, selectedCategory.getId(),ImageURL );
        if (success) {
            loadData();
            JOptionPane.showMessageDialog(this, "Thêm món thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Thêm món thất bại");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // 1. Kiểm tra ID có hợp lệ hay không
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để sửa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long id;
        try {
            id = Long.parseLong(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Lấy các thông tin từ form
        String name = txtName.getText().trim();
        String priceText = txtPrice.getText().trim();
        String imageURL = jTextImage.getText().trim();
        Category selectedCategory = (Category) cbCategory.getSelectedItem();

        // 3. Validate dữ liệu
        if (name.isEmpty() || priceText.isEmpty() || selectedCategory == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống các trường Name, Price và Category.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (name.length() > 255) {
            JOptionPane.showMessageDialog(this, "Tên món không được vượt quá " + 255 + " ký tự.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        BigDecimal price;
        try {
            price = new BigDecimal(priceText);
            if (price.compareTo(BigDecimal.ZERO) < 0) {
                JOptionPane.showMessageDialog(this, "Giá phải là số >= 0.", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Giá không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!isValidImageString(imageURL)) {
            JOptionPane.showMessageDialog(this, "Link Ảnh không hợp lệ.\n" +
                "Nếu là Data URI thì phải bắt đầu bằng \"data:image/...;base64,<dữ liệu>\".\n" +
                "Nếu là URL HTTP/HTTPS thì phải đúng định dạng.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // 4. Gọi DAO để update
        boolean success = menuItemDAO.updateMenuItem(id, name, price, selectedCategory.getId(), imageURL);
        if (success) {
            loadData();  // reload lại bảng
            JOptionPane.showMessageDialog(this, "Cập nhật món thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            // Có thể xóa trắng form nếu muốn:
            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");
            jTextImage.setText("");
            cbCategory.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "Cập nhật món thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String idText = txtId.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để xóa.", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        long id;
        try {
            id = Long.parseLong(idText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Hỏi xác nhận trước khi xóa
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Bạn có chắc muốn xóa món này không?",
            "Xác nhận xóa",
            JOptionPane.YES_NO_OPTION
        );
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        boolean success = menuItemDAO.deleteMenuItem(id);
        if (success) {
            loadData();  // reload lại bảng
            JOptionPane.showMessageDialog(this, "Xóa món thành công.", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            // Xóa trắng form
            txtId.setText("");
            txtName.setText("");
            txtPrice.setText("");
            jTextImage.setText("");
            cbCategory.setSelectedIndex(-1);
        } else {
            JOptionPane.showMessageDialog(this, "Xóa món thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        String criteria = (String) cbSearch.getSelectedItem();
        String keyword  = txtSearch.getText().trim();

        // Nếu không nhập gì, load toàn bộ dữ liệu
        if (keyword.isEmpty()) {
            loadData();
            return;
        }

        List<MenuItem> foundItems;
        try {
            switch (criteria) {
                case "Name":
                // Tìm theo tên món (gần đúng, không phân biệt hoa thường)
                foundItems = menuItemDAO.searchMenuItemsByName(keyword);
                break;

                case "CategoryName":
                // Tìm theo tên danh mục (gần đúng, không phân biệt hoa thường)
                foundItems = menuItemDAO.searchMenuItemsByCategoryName(keyword);
                break;

                case "Price":
                // Tìm theo giá chính xác
                BigDecimal priceValue;
                try {
                    priceValue = new BigDecimal(keyword);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Giá phải là một số hợp lệ.",
                        "Lỗi định dạng",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                foundItems = menuItemDAO.searchMenuItemsByPrice(priceValue);
                break;

                default:
                // Trường hợp không khớp (mặc định load toàn bộ)
                foundItems = menuItemDAO.getAllMenuItems();
                break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                this,
                "Đã có lỗi xảy ra khi tìm kiếm.",
                "Lỗi",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // 2. Cập nhật lại tableMenuItem với kết quả tìm được
        DefaultTableModel model = (DefaultTableModel) tableMenuItem.getModel();
        model.setRowCount(0); // Xóa hết hàng cũ

        for (MenuItem m : foundItems) {
            // a. Lấy tên category tương ứng từ cbCategory
            String catName = "";
            for (int i = 0; i < cbCategory.getItemCount(); i++) {
                Category c = cbCategory.getItemAt(i);
                if (c.getId() == m.getCategoryId()) {
                    catName = c.getName();
                    break;
                }
            }

            // b. Tạo ImageIcon từ chuỗi imageUrl (có thể là URL hoặc Data URI)
            ImageIcon icon = loadIconFromString(m.getImageUrl());

            // c. Thêm dòng mới vào model
            model.addRow(new Object[]{
                m.getId(),
                m.getName(),
                m.getPrice(),
                catName,
                icon
            });
        }

        // 3. Xóa trắng các trường nhập liệu nếu cần
        txtId.setText("");
        txtName.setText("");
        txtPrice.setText("");
        jTextImage.setText("");

    }//GEN-LAST:event_btnSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<Category> cbCategory;
    private javax.swing.JComboBox<String> cbSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelmage;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextImage;
    private javax.swing.JTable tableMenuItem;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
