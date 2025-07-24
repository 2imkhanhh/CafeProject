package cafe_project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import panel.History_orders;
import panel.order_item;
import panel.orders;
import panel.revenues;
import viewpanel.CategoryPanel;
import viewpanel.IngredientTransactionPanel;
import viewpanel.MenuItemPanel;


public class DashBoard extends javax.swing.JFrame {
    private int roleID;

    
    public DashBoard(int roleID) {
        this.roleID = roleID;
        initComponents();
        configurePermissions(); // Cấu hình quyền
        addHoverEffect();
    }
    public void setMainPanelContent(JPanel panel) {
        mainPanel.removeAll();          // Xóa nội dung cũ
        mainPanel.add(panel);           // Thêm panel mới
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private void configurePermissions() {
        switch (roleID) {
            case 1: 
                plAccount.setEnabled(false);
                plStaff.setEnabled(false);
                plTable.setEnabled(false);
                break;
            case 2: 
                plAccount.setEnabled(false);
                plStaff.setEnabled(false);
                plTable.setEnabled(false);
                break;
            case 3: 
                plAccount.setEnabled(true);
                plStaff.setEnabled(true);
                plTable.setEnabled(true);
                break;
            default:
                plAccount.setEnabled(false);
                plStaff.setEnabled(false);
                plTable.setEnabled(false);
                break;
        }

         //Đảm bảo người dùng không thể tương tác với JPanel bị vô hiệu
        setPanelInteraction(plAccount);
        setPanelInteraction(plStaff);
        setPanelInteraction(plTable);
    }
    
    private void setPanelInteraction(javax.swing.JPanel panel) {
        // Vô hiệu hóa JPanel và các thành phần con bên trong
//      panel.setEnabled(panel.isEnabled());
//        panel.setFocusable(false); 
        for (java.awt.Component comp : panel.getComponents()) {
            comp.setEnabled(panel.isEnabled());
        }   
    }
    
    private void addHoverEffect() {
        
        plAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plAccount.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plAccount.setBackground(new Color(102, 102, 102));
            }
        });

       
        plStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plStaff.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plStaff.setBackground(new Color(102, 102, 102));
            }
        });

        
        plTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plTable.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plTable.setBackground(new Color(102, 102, 102));
            }
        });
        
        plCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plCategory.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plCategory.setBackground(new Color(102, 102, 102));
            }
        });
        
        plMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plMenu.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plMenu.setBackground(new Color(102, 102, 102));
            }
        });
        
        plWareHouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plWareHouse.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plWareHouse.setBackground(new Color(102, 102, 102));
            }
        });
        
        plVoucherManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plVoucherManagement.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plVoucherManagement.setBackground(new Color(102, 102, 102));
            }
        });
        
        plBillManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plBillManagement.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plBillManagement.setBackground(new Color(102, 102, 102));
            }
        });
        
        plOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plOrder.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plOrder.setBackground(new Color(102, 102, 102));
            }
        });
        
        plOrderItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plOrderItem.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plOrderItem.setBackground(new Color(102, 102, 102));
            }
        });
        
        plHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plHistory.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plHistory.setBackground(new Color(102, 102, 102));
            }
        });
        
        plRevenue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plRevenue.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plRevenue.setBackground(new Color(102, 102, 102));
            }
        });
        
        plLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plLogout.setBackground(new Color(152, 152, 152));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plLogout.setBackground(new Color(102, 102, 102));
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        plAccount = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        plStaff = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        plCategory = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        plMenu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        plWareHouse = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        plTable = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        plVoucherManagement = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        plBillManagement = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        plOrder = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        plHistory = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        plRevenue = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        plOrderItem = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        plLogout = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 153, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Welcome to Cafe Management System");

        plAccount.setBackground(new java.awt.Color(102, 102, 102));
        plAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plAccountMouseClicked(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        jLabel2.setText("QUẢN LÝ TÀI KHOẢN");

        javax.swing.GroupLayout plAccountLayout = new javax.swing.GroupLayout(plAccount);
        plAccount.setLayout(plAccountLayout);
        plAccountLayout.setHorizontalGroup(
            plAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plAccountLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plAccountLayout.setVerticalGroup(
            plAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plAccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plStaff.setBackground(new java.awt.Color(102, 102, 102));
        plStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plStaffMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staff.png"))); // NOI18N
        jLabel3.setText("QUẢN LÝ NHÂN VIÊN");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout plStaffLayout = new javax.swing.GroupLayout(plStaff);
        plStaff.setLayout(plStaffLayout);
        plStaffLayout.setHorizontalGroup(
            plStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plStaffLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plStaffLayout.setVerticalGroup(
            plStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plStaffLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        plCategory.setBackground(new java.awt.Color(102, 102, 102));
        plCategory.setPreferredSize(new java.awt.Dimension(207, 32));
        plCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plCategoryMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/category.png"))); // NOI18N
        jLabel5.setText("QUẢN LÝ DANH MỤC");

        javax.swing.GroupLayout plCategoryLayout = new javax.swing.GroupLayout(plCategory);
        plCategory.setLayout(plCategoryLayout);
        plCategoryLayout.setHorizontalGroup(
            plCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plCategoryLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plCategoryLayout.setVerticalGroup(
            plCategoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plCategoryLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        plMenu.setBackground(new java.awt.Color(102, 102, 102));
        plMenu.setPreferredSize(new java.awt.Dimension(207, 32));
        plMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plMenuMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/food.png"))); // NOI18N
        jLabel6.setText("QUẢN LÝ MÓN ĂN");

        javax.swing.GroupLayout plMenuLayout = new javax.swing.GroupLayout(plMenu);
        plMenu.setLayout(plMenuLayout);
        plMenuLayout.setHorizontalGroup(
            plMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plMenuLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plMenuLayout.setVerticalGroup(
            plMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        plWareHouse.setBackground(new java.awt.Color(102, 102, 102));
        plWareHouse.setPreferredSize(new java.awt.Dimension(207, 32));
        plWareHouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plWareHouseMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ware-house.png"))); // NOI18N
        jLabel7.setText("QUẢN LÝ KHO");

        javax.swing.GroupLayout plWareHouseLayout = new javax.swing.GroupLayout(plWareHouse);
        plWareHouse.setLayout(plWareHouseLayout);
        plWareHouseLayout.setHorizontalGroup(
            plWareHouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plWareHouseLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plWareHouseLayout.setVerticalGroup(
            plWareHouseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plWareHouseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(14, 14, 14))
        );

        plTable.setBackground(new java.awt.Color(102, 102, 102));
        plTable.setPreferredSize(new java.awt.Dimension(207, 32));
        plTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plTableMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table.png"))); // NOI18N
        jLabel8.setText("QUẢN LÝ BÀN ");

        javax.swing.GroupLayout plTableLayout = new javax.swing.GroupLayout(plTable);
        plTable.setLayout(plTableLayout);
        plTableLayout.setHorizontalGroup(
            plTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plTableLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plTableLayout.setVerticalGroup(
            plTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plTableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plVoucherManagement.setBackground(new java.awt.Color(102, 102, 102));
        plVoucherManagement.setPreferredSize(new java.awt.Dimension(207, 32));
        plVoucherManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plVoucherManagementMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/voucher.png"))); // NOI18N
        jLabel9.setText("QUẢN LÝ VOUCHER");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout plVoucherManagementLayout = new javax.swing.GroupLayout(plVoucherManagement);
        plVoucherManagement.setLayout(plVoucherManagementLayout);
        plVoucherManagementLayout.setHorizontalGroup(
            plVoucherManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plVoucherManagementLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plVoucherManagementLayout.setVerticalGroup(
            plVoucherManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plVoucherManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plBillManagement.setBackground(new java.awt.Color(102, 102, 102));
        plBillManagement.setPreferredSize(new java.awt.Dimension(207, 32));
        plBillManagement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plBillManagementMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/transaction.png"))); // NOI18N
        jLabel10.setText("HÓA ĐƠN");

        javax.swing.GroupLayout plBillManagementLayout = new javax.swing.GroupLayout(plBillManagement);
        plBillManagement.setLayout(plBillManagementLayout);
        plBillManagementLayout.setHorizontalGroup(
            plBillManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plBillManagementLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plBillManagementLayout.setVerticalGroup(
            plBillManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plBillManagementLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plOrder.setBackground(new java.awt.Color(102, 102, 102));
        plOrder.setPreferredSize(new java.awt.Dimension(207, 32));
        plOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plOrderMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/order.png"))); // NOI18N
        jLabel11.setText("ĐƠN HÀNG");

        javax.swing.GroupLayout plOrderLayout = new javax.swing.GroupLayout(plOrder);
        plOrder.setLayout(plOrderLayout);
        plOrderLayout.setHorizontalGroup(
            plOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plOrderLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plOrderLayout.setVerticalGroup(
            plOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plOrderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plHistory.setBackground(new java.awt.Color(102, 102, 102));
        plHistory.setPreferredSize(new java.awt.Dimension(207, 32));
        plHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plHistoryMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clock.png"))); // NOI18N
        jLabel12.setText("LỊCH SỬ");

        javax.swing.GroupLayout plHistoryLayout = new javax.swing.GroupLayout(plHistory);
        plHistory.setLayout(plHistoryLayout);
        plHistoryLayout.setHorizontalGroup(
            plHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plHistoryLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plHistoryLayout.setVerticalGroup(
            plHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        plRevenue.setBackground(new java.awt.Color(102, 102, 102));
        plRevenue.setPreferredSize(new java.awt.Dimension(207, 32));
        plRevenue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plRevenueMouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/revenue.png"))); // NOI18N
        jLabel13.setText("DOANH THU");

        javax.swing.GroupLayout plRevenueLayout = new javax.swing.GroupLayout(plRevenue);
        plRevenue.setLayout(plRevenueLayout);
        plRevenueLayout.setHorizontalGroup(
            plRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plRevenueLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plRevenueLayout.setVerticalGroup(
            plRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plRevenueLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        plOrderItem.setBackground(new java.awt.Color(102, 102, 102));
        plOrderItem.setPreferredSize(new java.awt.Dimension(207, 32));
        plOrderItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plOrderItemMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shopping-list.png"))); // NOI18N
        jLabel14.setText("CHI TIẾT ĐƠN HÀNG");

        javax.swing.GroupLayout plOrderItemLayout = new javax.swing.GroupLayout(plOrderItem);
        plOrderItem.setLayout(plOrderItemLayout);
        plOrderItemLayout.setHorizontalGroup(
            plOrderItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plOrderItemLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plOrderItemLayout.setVerticalGroup(
            plOrderItemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, plOrderItemLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        plLogout.setBackground(new java.awt.Color(102, 102, 102));
        plLogout.setPreferredSize(new java.awt.Dimension(207, 32));
        plLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plLogoutMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Cambria", 1, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel15.setText("ĐĂNG XUẤT");

        javax.swing.GroupLayout plLogoutLayout = new javax.swing.GroupLayout(plLogout);
        plLogout.setLayout(plLogoutLayout);
        plLogoutLayout.setHorizontalGroup(
            plLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLogoutLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        plLogoutLayout.setVerticalGroup(
            plLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plLogoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(plStaff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(plCategory, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(plWareHouse, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plTable, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plVoucherManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plBillManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plHistory, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plOrderItem, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
            .addComponent(plLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plWareHouse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plVoucherManagement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plBillManagement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plOrderItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plHistory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(plRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1004, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void plAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plAccountMouseClicked
        if (!plAccount.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền sử dụng chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        mainPanel.removeAll();
        mainPanel.add(new AccountForm());
        mainPanel.revalidate();
        //mainPanel.repaint();
        
    }//GEN-LAST:event_plAccountMouseClicked

    private void plCategoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plCategoryMouseClicked
        String role = "";
        if(roleID == 3){
            role = "admin";
        }
        else if(roleID == 2){
            role = "staff";
        }
        else{
            role = "user";
        }
        mainPanel.removeAll();
        mainPanel.add(new CategoryPanel(role));
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plCategoryMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        
    }//GEN-LAST:event_jLabel3MouseClicked

    private void plWareHouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plWareHouseMouseClicked
        String role = "";
        if(roleID == 3){
            role = "admin";
        }
        else if(roleID == 2){
            role = "staff";
        }
        else{
            role = "user";
        }
        mainPanel.removeAll();
        mainPanel.add(new IngredientTransactionPanel(role));
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plWareHouseMouseClicked

    private void plMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plMenuMouseClicked
        String role = "";
        if(roleID == 3){
            role = "admin";
        }
        else if(roleID == 2){
            role = "staff";
        }
        else{
            role = "user";
        }
        mainPanel.removeAll();
        mainPanel.add(new MenuItemPanel(role));
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plMenuMouseClicked

    private void plStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plStaffMouseClicked
        if (!plStaff.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền sử dụng chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        mainPanel.removeAll();
        mainPanel.add(new StaffForm());
        mainPanel.revalidate();
    }//GEN-LAST:event_plStaffMouseClicked

    private void plTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plTableMouseClicked
        if (!plAccount.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền sử dụng chức năng này!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        mainPanel.removeAll();
        mainPanel.add(new TableManagement1());
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plTableMouseClicked

    private void plVoucherManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plVoucherManagementMouseClicked
        mainPanel.removeAll();
        mainPanel.add(new VoucherManagement1());
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plVoucherManagementMouseClicked

    private void plBillManagementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plBillManagementMouseClicked
        mainPanel.removeAll();
        mainPanel.add(new BillManagement());
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plBillManagementMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void plOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plOrderMouseClicked
        orders newOrdersPanel = new orders(this); // Truyền DashBoard vào
    mainPanel.removeAll();
    mainPanel.add(newOrdersPanel);
    mainPanel.revalidate();
    mainPanel.repaint();
    }//GEN-LAST:event_plOrderMouseClicked

    private void plHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plHistoryMouseClicked
        mainPanel.removeAll();
        mainPanel.add(new History_orders());    
        mainPanel.revalidate();
        mainPanel.repaint();
    }//GEN-LAST:event_plHistoryMouseClicked

    private void plRevenueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plRevenueMouseClicked
        mainPanel.removeAll();
        mainPanel.add(new revenues()); // nếu order_item extends JPanel
        mainPanel.revalidate();
        mainPanel.repaint();// TODO add your handling code here:
    }//GEN-LAST:event_plRevenueMouseClicked

    private void plOrderItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plOrderItemMouseClicked
        mainPanel.removeAll();
        mainPanel.add(new order_item()); // nếu order_item extends JPanel
        mainPanel.revalidate();
        mainPanel.repaint();  
    }//GEN-LAST:event_plOrderItemMouseClicked

    private void plLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plLogoutMouseClicked
        Login loginForm = new Login();
        loginForm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_plLogoutMouseClicked

    
    public static void main(String args[]) {
     
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashBoard(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel plAccount;
    private javax.swing.JPanel plBillManagement;
    private javax.swing.JPanel plCategory;
    private javax.swing.JPanel plHistory;
    private javax.swing.JPanel plLogout;
    private javax.swing.JPanel plMenu;
    private javax.swing.JPanel plOrder;
    private javax.swing.JPanel plOrderItem;
    private javax.swing.JPanel plRevenue;
    private javax.swing.JPanel plStaff;
    private javax.swing.JPanel plTable;
    private javax.swing.JPanel plVoucherManagement;
    private javax.swing.JPanel plWareHouse;
    // End of variables declaration//GEN-END:variables
}
