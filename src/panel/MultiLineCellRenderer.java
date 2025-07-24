/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panel;

import cafe_project.*;
import panel.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
/**
 *
 * @author Nguyen Van Chien
 */
public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {
    public MultiLineCellRenderer() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setEditable(false);
        setMargin(new Insets(5, 5, 5, 5));  // Giãn cách trong ô
    }

@Override
public Component getTableCellRendererComponent(JTable table, Object value,
                                               boolean isSelected, boolean hasFocus,
                                               int row, int column) {
    setFont(table.getFont());
    setText(value != null ? value.toString() : "");
    setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);

    // Tính chiều cao mong muốn dựa trên nội dung
    int preferredHeight = getPreferredSize().height;

    // Lấy chiều cao hiện tại
    int currentHeight = table.getRowHeight(row);

    // Chỉ tăng chiều cao nếu cần, không bao giờ giảm
    if (preferredHeight > currentHeight) {
        table.setRowHeight(row, preferredHeight);
    }

    // Xử lý màu sắc nếu dòng đang được chọn
    if (isSelected) {
        setBackground(table.getSelectionBackground());
        setForeground(table.getSelectionForeground());
    } else {
        setBackground(table.getBackground());
        setForeground(table.getForeground());
    }

    return this;
}



}
