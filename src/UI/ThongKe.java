/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.BenhNhanDAO;
import DuAnDAO.DichVuCTDAO;
import DuAnDAO.HoaDonDAO;
import DuAnDAO.ThongKeBenhNhanDAO;
import DuAnDAO.ThongKeDichVuDAO;
import DuAnDAO.ThongKeDoanhThuDAO;
import Entity.BenhNhan;
import Entity.DichVuCT;
import Entity.HoaDon;
import Entity.ThongKeBenhNhan;
import Entity.ThongKeDichVu;
import Entity.ThongKeDoanhThu;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class ThongKe extends javax.swing.JPanel {

    ThongKeDoanhThuDAO dtdao = new ThongKeDoanhThuDAO();
    HoaDonDAO hd = new HoaDonDAO();
    ThongKeDichVuDAO dvdao = new ThongKeDichVuDAO();
    DichVuCTDAO dvct = new DichVuCTDAO();
    ThongKeBenhNhanDAO bndao = new ThongKeBenhNhanDAO();
    BenhNhanDAO bn = new BenhNhanDAO();

    /**
     * Creates new form ThongKe
     */
    public ThongKe() {
        initComponents();
        fillComboBoxBN();
        fillComboBoxDT();
        fillComboBoxDV();
        chartbenhnhan(pnlBN);
        chartdichvu(pnlDV);
        chartdoanhthu(pnlDoanhThu);
    }

    void fillComboBoxDT() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDT.getModel();
        model.removeAllElements();
        List<HoaDon> list = hd.selectAll();
        for (HoaDon kh : list) {
            int nam = kh.getNgayThanhToan().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboDT.setSelectedIndex(0);
    }

    void fillComboBoxDV() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboDV.getModel();
        model.removeAllElements();
        List<DichVuCT> list = dvct.selectAll();
        for (DichVuCT kh : list) {
            int nam = kh.getNgayDK().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboDV.setSelectedIndex(0);
    }

    void fillComboBoxBN() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboBN.getModel();
        model.removeAllElements();
        List<BenhNhan> list = bn.selectAll();
        for (BenhNhan kh : list) {
            int nam = kh.getNgayVT().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboBN.setSelectedIndex(0);
    }

    public void chartdoanhthu(JPanel jpnItem) {
        int nam = Integer.parseInt(cboDT.getSelectedItem().toString());
        ArrayList<ThongKeDoanhThu> nc = dtdao.getAllList(nam);
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (nc != null) {
            for (ThongKeDoanhThu sv : nc) {
                dataset.addValue(sv.getSum(), "Năm", sv.getPhong());

            }
        }
        String fontName = "BertholdScript I";
        JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ thống kê doanh thu".toUpperCase(), "So sánh doanh thu", "Thu nhập", dataset, PlotOrientation.VERTICAL, true, true, true);
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        //Đổi màu background không chứa biểu đồ
        theme.setTitlePaint(Color.decode("#4572a7"));
        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint(Color.white);
        theme.setChartBackgroundPaint(Color.white);
        theme.setGridBandPaint(Color.red);
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.setAxisLabelPaint(Color.decode("#482247"));
        theme.apply(barChart);
        CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
        ((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
        BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        //Đổi màu cột 1 
        r.setSeriesPaint(0, new Color(49, 132, 252));
        TextTitle chartinfo = new TextTitle("Chú thích", new Font(fontName, Font.BOLD, 18), TextTitle.DEFAULT_TEXT_PAINT, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, RectangleInsets.ZERO_INSETS);
        barChart.addSubtitle(chartinfo);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    public void chartdichvu(JPanel jpnItem) {
        int nam = Integer.parseInt(cboDV.getSelectedItem().toString());
        ArrayList<ThongKeDichVu> nc = dvdao.getAllList(nam);
        final DefaultPieDataset dataset = new DefaultPieDataset();
        if (nc != null) {
            for (ThongKeDichVu sv : nc) {
                dataset.setValue(sv.getTenDV(), sv.getSoluongBN());

            }
        }
        String fontName = "BertholdScript I";
        JFreeChart barChart = ChartFactory.createPieChart3D("Biểu đồ thống kê dịch vụ", dataset, true, true, true);
        PiePlot3D p = (PiePlot3D) barChart.getPlot();
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();

        theme.setTitlePaint(Color.decode("#4572a7"));
        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint(Color.white);
//        theme.setChartBackgroundPaint(Color.white);
//        theme.setGridBandPaint(Color.red);
//        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
//        theme.setBarPainter(new StandardBarPainter());
//        theme.setAxisLabelPaint(Color.decode("#482247"));
        theme.apply(barChart);
//        TextTitle chartinfo = new TextTitle("Chú thích", new Font(fontName, Font.BOLD, 18), TextTitle.DEFAULT_TEXT_PAINT, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, RectangleInsets.ZERO_INSETS);
//        barChart.addSubtitle(chartinfo); 
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 337));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    public void chartbenhnhan(JPanel jpnItem) {
        int nam = Integer.parseInt(cboBN.getSelectedItem().toString());
        ArrayList<ThongKeBenhNhan> nc = bndao.getAllList(nam);
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (nc != null) {
            for (ThongKeBenhNhan sv : nc) {
                dataset.addValue(sv.getSoluongBN(), "Phòng", sv.getTenDV());

            }
        }
        String fontName = "BertholdScript I";
        JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ thống kê dịch vụ bệnh nhân".toUpperCase(), "So sánh dịch vụ", "Thu nhập", dataset, PlotOrientation.VERTICAL, true, true, true);
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        //Đổi màu background không chứa biểu đồ
        theme.setTitlePaint(Color.decode("#4572a7"));
        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint(Color.white);
        theme.setChartBackgroundPaint(Color.white);
        theme.setGridBandPaint(Color.red);
        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
        theme.setBarPainter(new StandardBarPainter());
        theme.setAxisLabelPaint(Color.decode("#482247"));
        theme.apply(barChart);
        TextTitle chartinfo = new TextTitle("Chú thích", new Font(fontName, Font.BOLD, 18), TextTitle.DEFAULT_TEXT_PAINT, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, RectangleInsets.ZERO_INSETS);
        barChart.addSubtitle(chartinfo);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 337));
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        pnlDV = new javax.swing.JPanel();
        pnlBN = new javax.swing.JPanel();
        pnlDoanhThu = new javax.swing.JPanel();
        cboDT = new javax.swing.JComboBox<>();
        cboDV = new javax.swing.JComboBox<>();
        cboBN = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());

        jPanel3.setMaximumSize(new java.awt.Dimension(1640, 850));
        jPanel3.setMinimumSize(new java.awt.Dimension(1640, 850));
        jPanel3.setPreferredSize(new java.awt.Dimension(1640, 850));

        pnlDV.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlDVLayout = new javax.swing.GroupLayout(pnlDV);
        pnlDV.setLayout(pnlDVLayout);
        pnlDVLayout.setHorizontalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlDVLayout.setVerticalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        pnlBN.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlBNLayout = new javax.swing.GroupLayout(pnlBN);
        pnlBN.setLayout(pnlBNLayout);
        pnlBNLayout.setHorizontalGroup(
            pnlBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBNLayout.setVerticalGroup(
            pnlBNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        pnlDoanhThu.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 801, Short.MAX_VALUE)
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        cboDT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDTItemStateChanged(evt);
            }
        });

        cboDV.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDVItemStateChanged(evt);
            }
        });

        cboBN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboBNItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlDoanhThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cboDT, javax.swing.GroupLayout.Alignment.LEADING, 0, 801, Short.MAX_VALUE)
                        .addComponent(pnlDV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboDV, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboBN, 0, 797, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        add(jPanel3, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void cboDVItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDVItemStateChanged
        // TODO add your handling code here:
        chartdichvu(pnlDV);
    }//GEN-LAST:event_cboDVItemStateChanged

    private void cboBNItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboBNItemStateChanged
        // TODO add your handling code here:
        chartbenhnhan(pnlBN);
    }//GEN-LAST:event_cboBNItemStateChanged

    private void cboDTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDTItemStateChanged
        // TODO add your handling code here:
        chartdoanhthu(pnlDoanhThu);
    }//GEN-LAST:event_cboDTItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboBN;
    private javax.swing.JComboBox<String> cboDT;
    private javax.swing.JComboBox<String> cboDV;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel pnlBN;
    private javax.swing.JPanel pnlDV;
    private javax.swing.JPanel pnlDoanhThu;
    // End of variables declaration//GEN-END:variables

   
    
}
