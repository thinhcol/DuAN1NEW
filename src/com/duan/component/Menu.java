package com.duan.component;

import Helper.ShareHelper;
import com.duan.event.EventMenu;
import com.duan.event.EventMenuCallBack;
import com.duan.event.EventMenuSelected;
import com.duan.model.Model_Menu;
import com.duan.shadow.ShadowBorder;
import com.duan.swing.PanelShadow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Menu extends PanelShadow {

    private int selectedIndex = -1;
    private double menuTarget;
    private double menuLastTarget;
    private double currentLocation;
    private BufferedImage selectedImage;
    private Animator animator;
    private EventMenuCallBack callBack;
    private EventMenu event;

    public Menu() {

        initComponents();
        init();

    }

    private void init() {
        setRadius(20);
        initData();
        listMenu.addEventSelectedMenu(new EventMenuSelected() {
            @Override
            public void menuSelected(int index, EventMenuCallBack callBack) {
                if (!animator.isRunning()) {
                    if (index != selectedIndex) {
                        Menu.this.callBack = callBack;
                        selectedIndex = index;
                        menuTarget = selectedIndex * 50 + listMenu.getY();
                        animator.start();
                    }
                }
            }
        });
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                currentLocation = (menuTarget - menuLastTarget) * fraction;
                currentLocation += menuLastTarget;
                repaint();
            }

            @Override
            public void end() {
                menuLastTarget = menuTarget;
                callBack.call(selectedIndex);
                if (event != null) {
                    event.menuIndexChange(selectedIndex);
                }
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(1);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
    }

    public void setSelectedIndex(int index) {
        selectedIndex = index;
        menuTarget = selectedIndex * 50 + listMenu.getY();
        menuLastTarget = menuTarget;
        currentLocation = menuLastTarget;
        listMenu.selectedIndex(index);
        repaint();
    }

    public void nicknam(String ten, String vaitro, Icon icon) {
        profile1.name(ten, vaitro, icon);
    }

    private void initData() {
        listMenu.addItem(new Model_Menu("1", "Trang chủ", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("2", "Bệnh nhân", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("3", "Bệnh án", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("4", "Phòng", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("5", "Nhân viên", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("6", "Nghề", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("7", "Giới thiệu", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("8", "Hóa đơn", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("9", "Dịch vụ", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("10", "Hoạt động", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("11", "Thân nhân", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("12", "Đăng xuất", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("13", "Đổi mật khẩu", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("14", "Thoát", Model_Menu.MenuType.MENU));
        listMenu.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
    }

    private void createImage() {
        int width = getWidth() - 30;
        selectedImage = ShadowBorder.getInstance().createShadowOut(width, 50, 8, 8, new Color(242, 246, 253));
    }

    @Override
    public void setBounds(int i, int i1, int i2, int i3) {
        super.setBounds(i, i1, i2, i3);
        createImage();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (selectedIndex >= 0) {
            grphcs.drawImage(selectedImage, 15, (int) currentLocation, null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profile1 = new com.duan.component.Profile();
        listMenu = new com.duan.swing.ListMenu<>();

        setBackground(new java.awt.Color(255, 255, 255));

        listMenu.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(profile1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
            .addComponent(listMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(profile1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addEvent(EventMenu event) {
        this.event = event;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.swing.ListMenu<String> listMenu;
    private com.duan.component.Profile profile1;
    // End of variables declaration//GEN-END:variables
}
