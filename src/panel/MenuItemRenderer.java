/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panel;


import cafe_project.*;
import panel.*;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import javax.swing.DefaultListCellRenderer;
import java.util.List;

/**
 *
 * @author Nguyen Van Chien
 */
public class MenuItemRenderer extends DefaultListCellRenderer {

    private List<MenuItem> menuItems;  // Danh sách các món ăn

    // Hàm setMenuItems() để thiết lập danh sách món ăn
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
    // Tạo một panel chính để chứa các thành phần con
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    
    // Lấy tên món ăn từ giá trị value (String)
    String itemName = (String) value;

    // Tìm món ăn trong menuItems
    MenuItem item = findMenuItemByName(itemName);

    // Hiển thị hình ảnh
    if (item != null) {
        // Tạo ImageIcon từ URL
         ImageIcon icon = null;
String imageUrlStr = item.getImageUrl();
        try {
    if (imageUrlStr.startsWith("data:image/")) {
        String base64Data = imageUrlStr.substring(imageUrlStr.indexOf(",") + 1);
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Data);
        icon = new ImageIcon(imageBytes);
    } else {
        URL imageUrl = new URL(imageUrlStr);
        icon = new ImageIcon(imageUrl);
    }

    // Resize ảnh
    Image image = icon.getImage();
    Image scaledImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    icon = new ImageIcon(scaledImage);
} catch (Exception e) {
    System.out.println("Error loading image: " + imageUrlStr);
    icon = new ImageIcon("default_image.jpg");
}


        // Hiển thị ảnh trong JLabel
        JLabel imageLabel = new JLabel(icon);
        panel.add(imageLabel, BorderLayout.WEST);

        // Tạo panel con để hiển thị tên và giá món
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel(item.getName()));  // Hiển thị tên món ăn
        textPanel.add(new JLabel("$" + item.getPrice()));  // Hiển thị giá món ăn
        panel.add(textPanel, BorderLayout.CENTER);
    } else {
        panel.add(new JLabel("Item not found"), BorderLayout.CENTER);  // Nếu không tìm thấy món ăn
    }

    // Đổi màu khi mục được chọn
    if (isSelected) {
        panel.setBackground(Color.CYAN);  // Đổi màu nền khi mục được chọn
    } else {
        panel.setBackground(Color.WHITE);  // Màu nền mặc định
    }

    return panel;
}

    // Phương thức tìm món ăn theo tên
    private MenuItem findMenuItemByName(String name) {
        for (MenuItem item : menuItems) {
            if (item.getName().equals(name)) {
                return item;  // Trả về món ăn nếu tìm thấy
            }
        }
        return null;  // Nếu không tìm thấy món ăn, trả về null
    }
}