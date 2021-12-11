package com.duan.component;

import Helper.ShareHelper;
import javax.swing.Icon;

public class Profile extends javax.swing.JPanel {

    public Profile() {
        initComponents();
        setOpaque(false);
//        if(ShareHelper.isLogin()){
//            lblTen.setText(ShareHelper.user.getHoTen());
//            lblVaiTro.setText(String.valueOf(ShareHelper.user.isChucVu() ? "Quản giáo" : "Cán bộ"));
        
    }
    public void name(String name,String vaitro,Icon icon){
        lblTen.setText(name);
        lblVaiTro.setText(vaitro);
        imageAvatar2.setIcon(icon);
    }
    public void name1(String name,int vaitro){
        lblTen.setText(name);
        lblVaiTro.setText(String.valueOf(vaitro));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTen = new javax.swing.JLabel();
        lblVaiTro = new javax.swing.JLabel();
        imageAvatar2 = new com.duan.swing.ImageAvatar();

        lblTen.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTen.setForeground(new java.awt.Color(50, 133, 253));
        lblTen.setText("Thịnh");

        lblVaiTro.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblVaiTro.setForeground(new java.awt.Color(50, 133, 253));
        lblVaiTro.setText("Dashboard Swing");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTen)
                    .addComponent(lblVaiTro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblTen)
                        .addGap(0, 0, 0)
                        .addComponent(lblVaiTro)
                        .addGap(10, 10, 10))
                    .addComponent(imageAvatar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.duan.swing.ImageAvatar imageAvatar2;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblVaiTro;
    // End of variables declaration//GEN-END:variables
}
