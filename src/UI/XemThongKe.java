/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import DuAnDAO.BenhNhanDAO;
import DuAnDAO.DichVuCTDAO;
import DuAnDAO.HoaDonDAO;
import DuAnDAO.HoatDongCTDAO;
import DuAnDAO.ThongKeBenhNhanDAO;
import DuAnDAO.ThongKeDichVuDAO;
import DuAnDAO.ThongKeDoanhThuDAO;
import DuAnDAO.ThongKeHoatDongDAO;
import Entity.BenhNhan;
import Entity.DichVuCT;
import Entity.HoaDon;
import Entity.HoatDongCT;
import Entity.ThongKeBenhNhan;
import Entity.ThongKeDichVu;
import Entity.ThongKeDoanhThu;
import Entity.ThongKeHoatDong;
import Helper.DateHelper;
import Helper.DialogHelper;
import Helper.FormatHepler;
import Helper.ShareHelper;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
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
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.VerticalAlignment;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Admin
 */
public class XemThongKe extends javax.swing.JDialog {

    /**
     * Creates new form XemThongKe
     */
    public XemThongKe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        getContentPane().setBackground(new Color(250, 250, 250));
        pnlBenhNhan.addLegend("Phòng", new Color(135, 189, 245));

    }
    DefaultTableModel model;
    ThongKeDoanhThuDAO dtdao = new ThongKeDoanhThuDAO();
    HoaDonDAO hd = new HoaDonDAO();
    ThongKeDichVuDAO dvdao = new ThongKeDichVuDAO();
    DichVuCTDAO dvct = new DichVuCTDAO();
    ThongKeBenhNhanDAO bndao = new ThongKeBenhNhanDAO();
    BenhNhanDAO bn = new BenhNhanDAO();
    ThongKeHoatDongDAO hddao = new ThongKeHoatDongDAO();
    HoatDongCTDAO hdct = new HoatDongCTDAO();

    void fillComboBoxDV() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<DichVuCT> list = dvct.selectAll();
        for (DichVuCT kh : list) {
            int nam = kh.getNgayDK().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboNam.setSelectedIndex(0);
    }
    void fillComboBoxHD() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<HoatDongCT> list = hdct.selectAll();
        for (HoatDongCT kh : list) {
            int nam = kh.getNgayThucHien().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboNam.setSelectedIndex(0);
    }

    void fillDataBN() {
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<ThongKeBenhNhan> list1 = bndao.getAllList(nam);
        for (ThongKeBenhNhan ns : list1) {
            pnlBenhNhan.addData(ns);
        }
    }

    void fillDataDT() {
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<ThongKeDoanhThu> list1 = dtdao.getAllList(nam);
        for (ThongKeDoanhThu ns : list1) {
            FormatHepler.formatMoney(ns.getSum());
            ns.getPhong();
        }
        pnlDoanhThu.setModel(list1);
    }

    void loadtableBN() {
        model = new DefaultTableModel();
        model.addColumn("Tên dịch vụ");
        model.addColumn("Số lượng bệnh nhân");
        jTable1.setModel(model);
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> list = bndao.getBenhNhan(nam);
        ThongKeBenhNhan benh = new ThongKeBenhNhan();
        for (Object[] row : list) {
            model.addRow(row);
            benh.getTenDV();
            benh.getSoluongBN();
        }
    }

    void loadtableDT() {
        model = new DefaultTableModel();
        model.addColumn("Phòng");
        model.addColumn("Số lượng bệnh nhân");
        model.addColumn("Tổng");
        model.addColumn("Nhỏ nhất");
        model.addColumn("Lớn nhất");
        jTable1.setModel(model);
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> list = dtdao.getDoanhThu(nam);
        ThongKeDoanhThu doanhv = new ThongKeDoanhThu();
        for (Object[] row : list) {
            model.addRow(row);
            doanhv.getPhong();
            doanhv.getSoluongbn();
            FormatHepler.formatMoney(doanhv.getSum());
            FormatHepler.formatMoney(doanhv.getMin());
            FormatHepler.formatMoney(doanhv.getMax());
        }
    }    
    void loadtableHD() {
        model = new DefaultTableModel();
        model.addColumn("Hoạt động");
        model.addColumn("Số lượng bệnh nhân");
        jTable1.setModel(model);
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> list = hddao.getHD(nam);
        ThongKeHoatDong doanhv = new ThongKeHoatDong();
        for (Object[] row : list) {
            model.addRow(row);
            doanhv.getTenhd();
            doanhv.getSoluong();
            
        }
        
    }

    void thongkehd() {
        pnlBieuDo.removeAll();
        pnlBieuDo.add(pnlHoatDong);
        charthoatdong(pnlHoatDong);
        pnlBieuDo.repaint();
        pnlBieuDo.revalidate();
        
    }

    void thongkebn() {
        pnlBieuDo.removeAll();
        pnlBieuDo.add(pnlBenhNhan);
        pnlBenhNhan.clear();
        fillDataBN();
        pnlBenhNhan.start();
        pnlBieuDo.repaint();
        pnlBieuDo.revalidate();
        
    }

    void thongkedv() {
        pnlBieuDo.removeAll();
        pnlBieuDo.add(pnlDV);
        chartdichvu(pnlDV);
        pnlBieuDo.repaint();
        pnlBieuDo.revalidate();
       
    }

    void thongkedt() {
        pnlBieuDo.removeAll();
        pnlBieuDo.add(pnlDoanhThu);
        fillDataDT();
        pnlBieuDo.repaint();
        pnlBieuDo.revalidate();
      
    }

    void loadtableDV() {
        model = new DefaultTableModel();
        model.addColumn("Tên dịch vụ");
        model.addColumn("Số lượng bệnh nhân");
        jTable1.setModel(model);
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        List<Object[]> list = dvdao.getDichVu(nam);
        ThongKeDichVu dichvu = new ThongKeDichVu();
        for (Object[] row : list) {
            model.addRow(row);
            dichvu.getTenDV();
            dichvu.getSoluongBN();
        }

    }

    void fillComboBoxDT() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<HoaDon> list = hd.selectAll();
        for (HoaDon kh : list) {
            int nam = kh.getNgayThanhToan().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboNam.setSelectedIndex(0);
    }

    void fillComboBoxBN() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<BenhNhan> list = bn.selectAll();
        for (BenhNhan kh : list) {
            int nam = kh.getNgayVT().getYear() + 1900;
            if (model.getIndexOf(nam) < 0) {
                model.addElement(nam);
            }
        }
        cboNam.setSelectedIndex(0);
        loadtableBN();
    }

//    public void chartdoanhthu(JPanel jpnItem) {
//        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
//        ArrayList<ThongKeDoanhThu> nc = dtdao.getAllList(nam);
//        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (nc != null) {
//            for (ThongKeDoanhThu sv : nc) {
//                dataset.addValue(sv.getSum(), "Năm", sv.getPhong());
//
//            }
//        }
//        String fontName = "BertholdScript I";
//        JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ thống kê doanh thu".toUpperCase(), "So sánh doanh thu", "Thu nhập", dataset, PlotOrientation.VERTICAL, true, true, true);
//        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
//        //Đổi màu background không chứa biểu đồ
//        theme.setTitlePaint(Color.decode("#4572a7"));
//        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
//        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
//        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
//        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
//        theme.setPlotBackgroundPaint(Color.white);
//        theme.setChartBackgroundPaint(Color.white);
//        theme.setGridBandPaint(Color.red);
//        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
//        theme.setBarPainter(new StandardBarPainter());
//        theme.setAxisLabelPaint(Color.decode("#482247"));
//        theme.apply(barChart);
//        CategoryPlot cplot = (CategoryPlot) barChart.getPlot();
//        ((BarRenderer) cplot.getRenderer()).setBarPainter(new StandardBarPainter());
//        BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
//        //Đổi màu cột 1 
//        r.setSeriesPaint(0, new Color(49, 132, 252));
//        TextTitle chartinfo = new TextTitle("Chú thích", new Font(fontName, Font.BOLD, 18), TextTitle.DEFAULT_TEXT_PAINT, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, RectangleInsets.ZERO_INSETS);
//        barChart.addSubtitle(chartinfo);
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 350));
//        jpnItem.removeAll();
//        jpnItem.setLayout(new BorderLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }
    public void chartdichvu(JPanel jpnItem) {
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
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
        theme.apply(barChart);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 337));
        jpnItem.removeAll();
        jpnItem.setLayout(new BorderLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    public void charthoatdong(JPanel jpnItem) {
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        ArrayList<ThongKeHoatDong> nc = hddao.getAllList(nam);
        final DefaultPieDataset dataset = new DefaultPieDataset();
        if (nc != null) {
            for (ThongKeHoatDong sv : nc) {
                dataset.setValue(sv.getTenhd(), sv.getSoluong());

            }
        }
        String fontName = "BertholdScript I";
        JFreeChart barChart = ChartFactory.createPieChart3D("Biểu đồ thống kê hoạt động", dataset, true, true, true);
        PiePlot3D p = (PiePlot3D) barChart.getPlot();
        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
        theme.setTitlePaint(Color.decode("#4572a7"));
        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
        theme.setPlotBackgroundPaint(Color.white);
        theme.apply(barChart);
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 337));
        jpnItem.removeAll();
        jpnItem.setLayout(new BorderLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

//    public void chartbenhnhan(JPanel jpnItem) {
//        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
//        ArrayList<ThongKeBenhNhan> nc = bndao.getAllList(nam);
//        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        if (nc != null) {
//            for (ThongKeBenhNhan sv : nc) {
//                dataset.addValue(sv.getSoluongBN(), "Phòng", sv.getTenDV());
//
//            }
//        }
//        String fontName = "BertholdScript I";
//        JFreeChart barChart = ChartFactory.createBarChart("Biểu đồ thống kê dịch vụ bệnh nhân".toUpperCase(), "So sánh dịch vụ", "Thu nhập", dataset, PlotOrientation.VERTICAL, true, true, true);
//        StandardChartTheme theme = (StandardChartTheme) org.jfree.chart.StandardChartTheme.createJFreeTheme();
//        //Đổi màu background không chứa biểu đồ
//        theme.setTitlePaint(Color.decode("#4572a7"));
//        theme.setExtraLargeFont(new Font(fontName, Font.PLAIN, 16)); //title
//        theme.setLargeFont(new Font(fontName, Font.BOLD, 15)); //axis-title
//        theme.setRegularFont(new Font(fontName, Font.PLAIN, 11));
//        theme.setRangeGridlinePaint(Color.decode("#C0C0C0"));
//        theme.setPlotBackgroundPaint(Color.white);
//        theme.setChartBackgroundPaint(Color.white);
//        theme.setGridBandPaint(Color.red);
//        theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0));
//        theme.setBarPainter(new StandardBarPainter());
//        theme.setAxisLabelPaint(Color.decode("#482247"));
//        theme.apply(barChart);
//        TextTitle chartinfo = new TextTitle("Chú thích", new Font(fontName, Font.BOLD, 18), TextTitle.DEFAULT_TEXT_PAINT, RectangleEdge.BOTTOM, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, RectangleInsets.ZERO_INSETS);
//        barChart.addSubtitle(chartinfo);
//        ChartPanel chartPanel = new ChartPanel(barChart);
//        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 337));
//        jpnItem.removeAll();
//        jpnItem.setLayout(new BorderLayout());
//        jpnItem.add(chartPanel);
//        jpnItem.validate();
//        jpnItem.repaint();
//    }
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
        cboNam = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        cboThongKe = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        kButton1 = new keeptoo.KButton();
        pnlBieuDo = new javax.swing.JPanel();
        pnlDV = new javax.swing.JPanel();
        pnlBenhNhan = new BieuDo.Chart();
        pnlDoanhThu = new BieuDo.BieuDoDT();
        pnlHoatDong = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn năm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(574, 73));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel1.setText("Năm");

        cboNam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cboNam.setFocusable(false);
        cboNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboNamItemStateChanged(evt);
            }
        });
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNam, 0, 503, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn thống kê", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 1, 14))); // NOI18N
        jPanel2.setPreferredSize(new java.awt.Dimension(574, 79));

        cboThongKe.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cboThongKe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thống kê", "Thống kê doanh thu", "Thống kê dịch vụ", "Thống kê bệnh nhân", "Thống kê hoạt động" }));
        cboThongKe.setFocusable(false);
        cboThongKe.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboThongKeItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jLabel2.setText("Thống kê");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboThongKe, 0, 468, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Xuất file excel");

        kButton1.setText("Load");
        kButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButton1ActionPerformed(evt);
            }
        });

        pnlBieuDo.setBackground(new java.awt.Color(255, 255, 255));
        pnlBieuDo.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout pnlDVLayout = new javax.swing.GroupLayout(pnlDV);
        pnlDV.setLayout(pnlDVLayout);
        pnlDVLayout.setHorizontalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1175, Short.MAX_VALUE)
        );
        pnlDVLayout.setVerticalGroup(
            pnlDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        pnlBieuDo.add(pnlDV, "card3");
        pnlBieuDo.add(pnlBenhNhan, "card4");

        pnlDoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        pnlBieuDo.add(pnlDoanhThu, "card4");

        javax.swing.GroupLayout pnlHoatDongLayout = new javax.swing.GroupLayout(pnlHoatDong);
        pnlHoatDong.setLayout(pnlHoatDongLayout);
        pnlHoatDongLayout.setHorizontalGroup(
            pnlHoatDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1175, Short.MAX_VALUE)
        );
        pnlHoatDongLayout.setVerticalGroup(
            pnlHoatDongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        pnlBieuDo.add(pnlHoatDong, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(563, 1044, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(0, 1062, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlBieuDo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addComponent(kButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBieuDo, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cboThongKeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboThongKeItemStateChanged
        // TODO add your handling code here:
        String boloc = cboThongKe.getSelectedItem().toString();
        if (boloc.equals("Thống kê doanh thu")) {
            fillComboBoxDT();
            thongkedt();
            loadtableDT();
        } else if (boloc.equals("Thống kê dịch vụ")) {
            fillComboBoxDV();
            thongkedv();
            loadtableDV();
        } else if (boloc.equals("Thống kê bệnh nhân")) {
            fillComboBoxBN();
            thongkebn();
            loadtableBN();
        } else if (boloc.equals("Thống kê hoạt động")) {
            fillComboBoxHD();
            thongkehd();
            loadtableHD();
        }
    }//GEN-LAST:event_cboThongKeItemStateChanged

    private void cboNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboNamItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cboNamItemStateChanged

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cboNamActionPerformed

    private void kButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButton1ActionPerformed
        // TODO add your handling code here:
        String boloc = cboThongKe.getSelectedItem().toString();
        if (boloc.equals("Thống kê doanh thu")) {
            loadtableDT();
            thongkedt();
        } else if (boloc.equals("Thống kê dịch vụ")) {
            loadtableDV();
            thongkedv();
        } else if (boloc.equals("Thống kê bệnh nhân")) {
            loadtableBN();
            pnlBenhNhan.clear();
            fillDataBN();
            pnlBenhNhan.start();
        }else if (boloc.equals("Thống kê hoạt động")) {
           
            thongkehd();
            loadtableHD();
        }
    }//GEN-LAST:event_kButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(XemThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XemThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XemThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XemThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                XemThongKe dialog = new XemThongKe(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThongKe;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private keeptoo.KButton kButton1;
    private BieuDo.Chart pnlBenhNhan;
    private javax.swing.JPanel pnlBieuDo;
    private javax.swing.JPanel pnlDV;
    private BieuDo.BieuDoDT pnlDoanhThu;
    private javax.swing.JPanel pnlHoatDong;
    // End of variables declaration//GEN-END:variables
}
