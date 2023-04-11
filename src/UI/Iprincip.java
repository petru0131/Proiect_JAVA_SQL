/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UI;

import bdproiect.BenefLoader;
import bdproiect.ValutaDisp;
import bdproiect.Beneficiar;
import bdproiect.ComLoader;
import bdproiect.Comenzi;
import bdproiect.ConnectionJDBC;
import bdproiect.LocLoader;
import bdproiect.Locatii;
import bdproiect.ModelPieChart;
import bdproiect.PieChart;
import bdproiect.ValutaDispLoad;
import com.mysql.cj.result.Row;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
/*import org.apache.poi.ss.usermodel.Row;*/
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;


/**
 *
 * @author Petru
 */
public class Iprincip extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Iprincip() throws SQLException, ClassNotFoundException {
        initComponents();
         pieChart1.setChartType(PieChart.PeiChartType.DONUT_CHART);
        pieChart1.clearData();
        showData();
    }

    public void loadTabelV(){
        DefaultTableModel model = (DefaultTableModel) tabelV.getModel();
        model.setRowCount(0);
        List<ValutaDisp> listaVal = new ValutaDispLoad().load();
        Object rowData[] = new Object[8];
         for (int i = 0; i < listaVal.size(); i++) {
         rowData[0]=listaVal.get(i).getCodV();
         rowData[1]=listaVal.get(i).getDenumire();
         rowData[2]=listaVal.get(i).getCursAnt();
         rowData[3]=listaVal.get(i).getCursCump();
         rowData[4]=listaVal.get(i).getCursVanz();
         rowData[5]=listaVal.get(i).getComision();
         rowData[6]=listaVal.get(i).getData();
         rowData[7]=listaVal.get(i).getCodc();
         model.addRow(rowData);
         }
    }
    
    
    public void loadTabelLog(){
        DefaultTableModel model = (DefaultTableModel) tabelLog.getModel();
        model.setRowCount(0);
        List<Comenzi> listaVal = new ComLoader().load();
        Object rowData[] = new Object[8];
         for (int i = 0; i < listaVal.size(); i++) {
         rowData[0]=listaVal.get(i).getIdC();
         rowData[1]=listaVal.get(i).getUser();
         rowData[2]=listaVal.get(i).getNume();
         rowData[3]=listaVal.get(i).getRol();
         rowData[4]=listaVal.get(i).getDenC();
         rowData[5]=listaVal.get(i).getDataC();
         model.addRow(rowData);
         }
    }

    
    
    
    
    public void fieldResetValuta(){
        textCodv.setText(null);
        textCodc.setText(null);
        textComis.setText(null);
        textCursAnt.setText(null);
        textCursCump.setText(null);
        textCursVanz.setText(null);
        textDen.setText(null);
        
    }
    
    public void loadTabelB(){
        DefaultTableModel model = (DefaultTableModel) tabelB.getModel();
        model.setRowCount(0);
        List<Beneficiar> listaBef = new BenefLoader().load();
        Object rowData[] = new Object[9];
         for (int i = 0; i < listaBef.size(); i++) {
         rowData[0]=listaBef.get(i).getCodBe();
         rowData[1]=listaBef.get(i).getNume();
         rowData[2]=listaBef.get(i).getPren();
         rowData[3]=listaBef.get(i).getSuma();
         rowData[4]=listaBef.get(i).getValSchimb();
         rowData[5]=listaBef.get(i).getValPrimita();
         rowData[6]=listaBef.get(i).getComBen();
         rowData[7]=listaBef.get(i).getData();
         rowData[8]=listaBef.get(i).getIdc();
         model.addRow(rowData);
         }
    }
         
    public void loadComboB(){
        boxVal.removeAllItems();
        List<ValutaDisp> listaVal= new ValutaDispLoad().loadCombo();
        boxVal.addItem("Selecteaza");
        for(int i=0;i<listaVal.size();i++){
        boxVal.addItem(String.valueOf(listaVal.get(i).getDenumire()));
        }
        }
    
    
    public void fieldResetBenef(){
        textCodBe.setText(null);
        textNume.setText(null);
        textPren.setText(null);
        textVs.setText(null);
        textVp.setText(null);
        textSuma.setText(null);
        textCo.setText(null);
        textCodcBe.setText(null);
        
    }
    
    
    
    public void loadTabelL(){
        DefaultTableModel model = (DefaultTableModel) tabelL.getModel();
        model.setRowCount(0);
        List<Locatii> listaVal = new LocLoader().load();
        Object rowData[] = new Object[5];
         for (int i = 0; i < listaVal.size(); i++) {
         rowData[0]=listaVal.get(i).getCodc();
         rowData[1]=listaVal.get(i).getNumeL();
         rowData[2]=listaVal.get(i).getStrada();
         rowData[3]=listaVal.get(i).getOras();
         rowData[4]=listaVal.get(i).getJudet();
         model.addRow(rowData);
         }
    }
    
    
    public void fieldResetLocatii(){
        textcodL.setText(null);
        textNumeL.setText(null);
        textStrada.setText(null);
        textOras.setText(null);
        textJudet.setText(null);
    }
    
    private void showData() throws SQLException, ClassNotFoundException{
       try (Connection conn = ConnectionJDBC.getConnection())
        {
            String sql = "select sum(suma),valutaschimb from beneficiar group by valutaschimb";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            int index=0;
            while (result.next()) {
                int suma=result.getInt(1);
                String clasa = result.getString(2);
                
               pieChart1.addData(new ModelPieChart(clasa, suma, getColor(index++)));
                
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
   }
    
    private Color getColor(int index){
    Color [] color = new Color [] {new Color(255,102,102),new Color(58,55,227),new Color(206,215,33),new Color(29,217,214),new Color(43,34,250),new Color(200,169,86),new Color(255,102,0)};
    return color[index];
    
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modelPieChart1 = new bdproiect.ModelPieChart();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        butLoc = new javax.swing.JButton();
        butValuta = new javax.swing.JButton();
        butSchimb = new javax.swing.JButton();
        comenzi = new javax.swing.JButton();
        butRap = new javax.swing.JButton();
        tabValuta = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        butonAd = new javax.swing.JButton();
        textCodv = new javax.swing.JTextField();
        butCauta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textDen = new javax.swing.JTextField();
        textCursAnt = new javax.swing.JTextField();
        textCursCump = new javax.swing.JTextField();
        textCursVanz = new javax.swing.JTextField();
        textComis = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textCodc = new javax.swing.JTextField();
        butSterg = new javax.swing.JButton();
        butUp = new javax.swing.JButton();
        butExpV = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        textCauta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelV = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelB = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        textCodBe = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textNume = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textPren = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textSuma = new javax.swing.JTextField();
        textVs = new javax.swing.JTextField();
        textVp = new javax.swing.JTextField();
        textCo = new javax.swing.JTextField();
        textCodcBe = new javax.swing.JTextField();
        butAdB = new javax.swing.JButton();
        butStrB = new javax.swing.JButton();
        butUpB = new javax.swing.JButton();
        butExp = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        textCautaS = new javax.swing.JTextField();
        butCautaS = new javax.swing.JButton();
        boxVal = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelL = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        textcodL = new javax.swing.JTextField();
        textNumeL = new javax.swing.JTextField();
        textStrada = new javax.swing.JTextField();
        textOras = new javax.swing.JTextField();
        textJudet = new javax.swing.JTextField();
        butadL = new javax.swing.JButton();
        butExpL = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelLog = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabelRB = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabelRV = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        pieChart1 = new bdproiect.PieChart();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1250, 800));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 255));

        jButton1.setText("LogOut");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(204, 204, 255));
        jTextField1.setFont(new java.awt.Font("Modern No. 20", 0, 18)); // NOI18N
        jTextField1.setText("PFN Exchange");

        butLoc.setText("Locatiile");
        butLoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butLocMouseClicked(evt);
            }
        });

        butValuta.setText("Valuta");
        butValuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butValutaMouseClicked(evt);
            }
        });
        butValuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butValutaActionPerformed(evt);
            }
        });

        butSchimb.setText("Schimba");
        butSchimb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butSchimbMouseClicked(evt);
            }
        });
        butSchimb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSchimbActionPerformed(evt);
            }
        });

        comenzi.setText("Istoric");
        comenzi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comenziMouseClicked(evt);
            }
        });

        butRap.setText("Rapoarte");
        butRap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butRapMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(butValuta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(butSchimb, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(butLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(comenzi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(butRap, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butValuta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butSchimb, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comenzi, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butRap, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        butonAd.setText("Adauga");
        butonAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butonAdActionPerformed(evt);
            }
        });

        textCodv.setEditable(false);
        textCodv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodvActionPerformed(evt);
            }
        });

        butCauta.setText("Cauta");
        butCauta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCautaActionPerformed(evt);
            }
        });

        jLabel1.setText("CodV");

        jLabel2.setText("Moneda");

        jLabel3.setText("Curs Anterior");

        jLabel4.setText("Curs Cumparare");

        jLabel5.setText("Curs Vanzare");

        jLabel6.setText("Comision");

        textDen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDenActionPerformed(evt);
            }
        });

        textCursAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCursAntActionPerformed(evt);
            }
        });

        jLabel7.setText("CodC");

        butSterg.setText("Sterge");
        butSterg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butStergActionPerformed(evt);
            }
        });

        butUp.setText("Update");
        butUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butUpActionPerformed(evt);
            }
        });

        butExpV.setText("Export");
        butExpV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExpVActionPerformed(evt);
            }
        });

        jLabel15.setText("Alege o moneda");

        tabelV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodV", "Moneda", "Curs Anterior", "Curs Cumparare", "Curs Vanzare", "Comision", "Ziua", "CodC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelV);
        if (tabelV.getColumnModel().getColumnCount() > 0) {
            tabelV.getColumnModel().getColumn(0).setResizable(false);
            tabelV.getColumnModel().getColumn(1).setResizable(false);
            tabelV.getColumnModel().getColumn(2).setResizable(false);
            tabelV.getColumnModel().getColumn(3).setResizable(false);
            tabelV.getColumnModel().getColumn(4).setResizable(false);
            tabelV.getColumnModel().getColumn(5).setResizable(false);
            tabelV.getColumnModel().getColumn(6).setResizable(false);
            tabelV.getColumnModel().getColumn(7).setResizable(false);
        }
        loadTabelV();

        jButton5.setText("Excel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(17, 1010, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textDen)
                            .addComponent(textCursAnt)
                            .addComponent(textCursCump)
                            .addComponent(textCursVanz)
                            .addComponent(textComis)
                            .addComponent(textCodc)
                            .addComponent(textCodv, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(butExpV)
                        .addGap(31, 31, 31)
                        .addComponent(jButton5)
                        .addGap(48, 48, 48)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(butSterg)
                            .addComponent(butonAd)
                            .addComponent(butUp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(textCauta, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(butCauta))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textCodv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(textCauta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butonAd)
                    .addComponent(butCauta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butSterg))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textCursAnt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butUp))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textCursCump, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textCursVanz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(textComis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(textCodc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(butExpV)
                            .addComponent(jButton5)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(74, 74, 74))
        );

        tabValuta.addTab("Valuta Disponibila", jPanel2);

        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        tabelB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodBe", "Nume", "Prenume", "Suma", "Valuta Schimb", "Valuta Primita", "Comision", "Data", "CodC"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelB);
        if (tabelB.getColumnModel().getColumnCount() > 0) {
            tabelB.getColumnModel().getColumn(0).setResizable(false);
            tabelB.getColumnModel().getColumn(1).setResizable(false);
            tabelB.getColumnModel().getColumn(2).setResizable(false);
            tabelB.getColumnModel().getColumn(3).setResizable(false);
            tabelB.getColumnModel().getColumn(4).setResizable(false);
            tabelB.getColumnModel().getColumn(5).setResizable(false);
            tabelB.getColumnModel().getColumn(6).setResizable(false);
            tabelB.getColumnModel().getColumn(7).setResizable(false);
            tabelB.getColumnModel().getColumn(8).setResizable(false);
        }
        loadTabelB();

        jLabel8.setText("CodBe");

        textCodBe.setEditable(false);

        jLabel9.setText("Nume");

        jLabel10.setText("Prenume");

        jLabel11.setText("Suma");

        jLabel12.setText("Valuta Schimb");

        jLabel13.setText("Valuta Primita");

        jLabel14.setText("Comision");

        jLabel16.setText("CodC");

        butAdB.setText("Adauga");
        butAdB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAdBActionPerformed(evt);
            }
        });

        butStrB.setText("Sterge");
        butStrB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butStrBActionPerformed(evt);
            }
        });

        butUpB.setText("Update");
        butUpB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butUpBActionPerformed(evt);
            }
        });

        butExp.setText("Export");
        butExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butExpActionPerformed(evt);
            }
        });

        jLabel17.setText("Alege un Nume/ValSchimb");

        butCautaS.setText("Cauta");
        butCautaS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butCautaSMouseClicked(evt);
            }
        });

        jButton6.setText("Excel");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(butAdB)
                        .addGap(18, 18, 18)
                        .addComponent(butStrB)
                        .addGap(18, 18, 18)
                        .addComponent(butUpB)
                        .addGap(18, 18, 18)
                        .addComponent(butExp)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textCodcBe, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textVp, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(textCo)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textCodBe, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(textNume)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textVs)
                            .addComponent(textPren)
                            .addComponent(textSuma))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(boxVal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textCautaS, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butCautaS)
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textCodBe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(textCautaS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butCautaS))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(textNume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textPren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(boxVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textSuma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(textVs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(textVp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(textCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(textCodcBe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butAdB)
                    .addComponent(butStrB)
                    .addComponent(butUpB)
                    .addComponent(butExp)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        boxVal.addItem("Selecteaza");
        List<ValutaDisp> listaVal= new ValutaDispLoad().loadCombo();
        for(int i=0;i<listaVal.size();i++){
            boxVal.addItem(String.valueOf(listaVal.get(i).getDenumire()));
        }

        tabValuta.addTab("Schimb Valutar", jPanel3);

        tabelL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodC", "Nume", "Strada", "Oras", "Judet"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelLMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelL);
        if (tabelL.getColumnModel().getColumnCount() > 0) {
            tabelL.getColumnModel().getColumn(0).setResizable(false);
            tabelL.getColumnModel().getColumn(1).setResizable(false);
            tabelL.getColumnModel().getColumn(2).setResizable(false);
            tabelL.getColumnModel().getColumn(3).setResizable(false);
            tabelL.getColumnModel().getColumn(4).setResizable(false);
        }
        loadTabelL();

        jLabel18.setText("CodC");

        jLabel19.setText("Nume");

        jLabel20.setText("Strada");

        jLabel21.setText("Oras");

        jLabel22.setText("Judet");

        textcodL.setEditable(false);

        textNumeL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNumeLActionPerformed(evt);
            }
        });

        butadL.setText("Adauga");
        butadL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butadLActionPerformed(evt);
            }
        });

        butExpL.setText("Export");
        butExpL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butExpLMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(38, 38, 38)
                                .addComponent(textJudet))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(36, 36, 36)
                                .addComponent(textcodL, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(33, 33, 33)
                                .addComponent(textNumeL, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textOras, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textStrada, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(butadL)
                        .addGap(18, 18, 18)
                        .addComponent(butExpL)))
                .addGap(96, 96, 96)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(295, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(textcodL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(textNumeL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(textStrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(textOras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(textJudet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butadL)
                            .addComponent(butExpL))))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        tabValuta.addTab("Locatii", jPanel4);

        tabelLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IdCom", "User", "Nume", "Rol", "Comanda Utilizata", "Data"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tabelLog);
        if (tabelLog.getColumnModel().getColumnCount() > 0) {
            tabelLog.getColumnModel().getColumn(0).setResizable(false);
            tabelLog.getColumnModel().getColumn(1).setResizable(false);
            tabelLog.getColumnModel().getColumn(2).setResizable(false);
            tabelLog.getColumnModel().getColumn(3).setResizable(false);
            tabelLog.getColumnModel().getColumn(4).setResizable(false);
            tabelLog.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        tabValuta.addTab("Istoric", jPanel5);

        jButton3.setText("Raport Valuta Schimb");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        tabelRB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Valuta Schimb", "Total Suma"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tabelRB);
        if (tabelRB.getColumnModel().getColumnCount() > 0) {
            tabelRB.getColumnModel().getColumn(0).setResizable(false);
            tabelRB.getColumnModel().getColumn(1).setResizable(false);
        }

        tabelRV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Locatii", "Total  Valute"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tabelRV);
        if (tabelRV.getColumnModel().getColumnCount() > 0) {
            tabelRV.getColumnModel().getColumn(0).setResizable(false);
            tabelRV.getColumnModel().getColumn(1).setResizable(false);
        }

        jButton4.setText("Raport Total Valute");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Chart Valute Schimbate");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jButton3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pieChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                                .addComponent(jLabel23)
                                .addGap(120, 120, 120)))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(83, 83, 83))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(318, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pieChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        tabValuta.addTab("Rapoarte", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabValuta)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabValuta, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(431, Short.MAX_VALUE))
        );

        tabValuta.setVisible(false);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton2MouseClicked

    private void butCautaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCautaActionPerformed
        // TODO add your handling code here:
        if(textCauta.getText().equals("")){
            loadTabelV();
        }
        else{
        DefaultTableModel model = (DefaultTableModel) tabelV.getModel();
        model.setRowCount(0);
        List<ValutaDisp> listaVal = new ValutaDispLoad().filtrare(textCauta.getText());
        Object rowData[] = new Object[8];
         for (int i = 0; i < listaVal.size(); i++) {
         rowData[0]=listaVal.get(i).getCodV();
         rowData[1]=listaVal.get(i).getDenumire();
         rowData[2]=listaVal.get(i).getCursAnt();
         rowData[3]=listaVal.get(i).getCursCump();
         rowData[4]=listaVal.get(i).getCursVanz();
         rowData[5]=listaVal.get(i).getComision();
         rowData[6]=listaVal.get(i).getData();
         rowData[7]=listaVal.get(i).getCodc();
         model.addRow(rowData);
         }
    }//GEN-LAST:event_butCautaActionPerformed
    }
    
    private void textCodvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodvActionPerformed

    private void butonAdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butonAdActionPerformed
        // TODO add your handling code here:
        ValutaDisp val= new ValutaDisp();
        val.setDenumire(textDen.getText());
        val.setCursAnt(Double.parseDouble(textCursAnt.getText()));
        val.setCursCump(Double.parseDouble(textCursCump.getText()));
        val.setCursVanz(Double.parseDouble(textCursVanz.getText()));
        val.setComision(Integer.parseInt(textComis.getText()));
        val.setCodc(Integer.parseInt(textCodc.getText()));
        java.util.Date datau=new java.util.Date();
        val.setData(datau);
       
        ValutaDispLoad.insertValuta(val);
        loadTabelV();
        fieldResetValuta();
        loadComboB();
        String comanda="Adaugare Valuta";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
        
    }//GEN-LAST:event_butonAdActionPerformed

    private void textDenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDenActionPerformed

    private void textCursAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCursAntActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCursAntActionPerformed

    private void tabelVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelVMouseClicked
        // TODO add your handling code here:
        int indexRand=tabelV.getSelectedRow();
        textCodv.setText(tabelV.getModel().getValueAt(indexRand, 0).toString());
        textDen.setText(tabelV.getModel().getValueAt(indexRand, 1).toString());
        textCursAnt.setText(tabelV.getModel().getValueAt(indexRand, 2).toString());
        textCursCump.setText(tabelV.getModel().getValueAt(indexRand, 3).toString());
        textCursVanz.setText(tabelV.getModel().getValueAt(indexRand, 4).toString());
        textComis.setText(tabelV.getModel().getValueAt(indexRand, 5).toString());
        textCodc.setText(tabelV.getModel().getValueAt(indexRand, 7).toString());
        //textCodc.setText(tabelV.getModel().getValueAt(indexRand, 0).toString());
        
    }//GEN-LAST:event_tabelVMouseClicked

    private void butStergActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butStergActionPerformed
        // TODO add your handling code here:
        int indexRand=tabelV.getSelectedRow();
        if(indexRand==-1){
            showMessageDialog(null,"Selecteaza un rand!");
        }
        else{
            int id=(int) tabelV.getModel().getValueAt(indexRand, 0);
        
        ValutaDispLoad.deleteValuta(id);
        loadTabelV();
        fieldResetValuta();
        loadComboB();
        String comanda="Stergere Valuta";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
        }
    }//GEN-LAST:event_butStergActionPerformed

    private void butValutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butValutaMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_butValutaMouseClicked

    private void butValutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butValutaActionPerformed
        // TODO add your handling code here:
        tabValuta.setVisible(true);
        tabValuta.setSelectedIndex(0);
    }//GEN-LAST:event_butValutaActionPerformed

    private void butUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butUpActionPerformed
        // TODO add your handling code here:
        ValutaDisp valuta=new ValutaDisp();
        valuta.setCodV(Integer.parseInt(textCodv.getText()));
        valuta.setDenumire(textDen.getText());
        valuta.setCursAnt(Double.parseDouble(textCursAnt.getText()));
        valuta.setCursCump(Double.parseDouble(textCursCump.getText()));
        valuta.setCursVanz(Double.parseDouble(textCursVanz.getText()));
        valuta.setComision(Integer.parseInt(textComis.getText()));
        valuta.setCodc(Integer.parseInt(textCodc.getText()));
        ValutaDispLoad.updateValuta(valuta, Integer.parseInt(textCodv.getText()));
        
       loadTabelV();
       fieldResetValuta();
       loadComboB();
       String comanda="Update Valuta";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
    }//GEN-LAST:event_butUpActionPerformed

    private void butSchimbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butSchimbMouseClicked
        // TODO add your handling code here:
        tabValuta.setVisible(true);
        tabValuta.setSelectedIndex(1);
    }//GEN-LAST:event_butSchimbMouseClicked

    private void butLocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butLocMouseClicked
        // TODO add your handling code here:
        tabValuta.setVisible(true);
        tabValuta.setSelectedIndex(2);
    }//GEN-LAST:event_butLocMouseClicked

    private void butAdBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAdBActionPerformed
        // TODO add your handling code here:
        Beneficiar benef=new Beneficiar();
        benef.setNume(textNume.getText());
        benef.setPren(textPren.getText());
        benef.setSuma(Integer.parseInt(textSuma.getText()));
        benef.setValSchimb(textVs.getText());
        benef.setValPrimita(textVp.getText());
        benef.setComBen(Integer.parseInt(textCo.getText()));
        java.util.Date datau=new java.util.Date();
        benef.setData(datau);
        benef.setIdc(Integer.parseInt(textCodcBe.getText()));
        
        BenefLoader.insertBenef(benef);
        loadTabelB();
        fieldResetBenef();
        String comanda="Adaugare Beneficiar";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
    }//GEN-LAST:event_butAdBActionPerformed

    private void tabelBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBMouseClicked
        // TODO add your handling code here:
        int indexR=tabelB.getSelectedRow();
        textCodBe.setText(tabelB.getModel().getValueAt(indexR, 0).toString());
        textNume.setText(tabelB.getModel().getValueAt(indexR, 1).toString());
        textPren.setText(tabelB.getModel().getValueAt(indexR, 2).toString());
        textSuma.setText(tabelB.getModel().getValueAt(indexR, 3).toString());
        textVs.setText(tabelB.getModel().getValueAt(indexR, 4).toString());
        textVp.setText(tabelB.getModel().getValueAt(indexR, 5).toString());
        textCo.setText(tabelB.getModel().getValueAt(indexR, 6).toString());
        textCodcBe.setText(tabelB.getModel().getValueAt(indexR, 8).toString());
    }//GEN-LAST:event_tabelBMouseClicked

    private void butStrBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butStrBActionPerformed
        // TODO add your handling code here:
        int indexRand=tabelB.getSelectedRow();
        if(indexRand==-1){
            showMessageDialog(null,"Selecteaza un rand!");
        }
        else{
            int id=(int) tabelB.getModel().getValueAt(indexRand, 0);
        
        BenefLoader.deleteBenef(id);
        loadTabelB();
        fieldResetBenef();
        String comanda="Stergere Beneficiar";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
        }
    }//GEN-LAST:event_butStrBActionPerformed

    private void butUpBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butUpBActionPerformed
        // TODO add your handling code here:
        Beneficiar benef=new Beneficiar();
        
        benef.setCodBe(Integer.parseInt(textCodBe.getText()));
        benef.setNume(textNume.getText());
        benef.setPren(textPren.getText());
        benef.setSuma(Integer.parseInt(textSuma.getText()));
        benef.setValSchimb(textVs.getText());
        benef.setValPrimita(textVp.getText());
        benef.setComBen(Integer.parseInt(textCo.getText()));
        benef.setIdc(Integer.parseInt(textCodcBe.getText()));
        BenefLoader.updateBenef(benef, Integer.parseInt(textCodBe.getText()));
        
        loadTabelB();
        fieldResetBenef();
        String comanda="Update Beneficiar";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
    }//GEN-LAST:event_butUpBActionPerformed

    private void butExpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExpActionPerformed
        try {
            // TODO add your handling code here:
            tabelB.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_butExpActionPerformed

    private void butExpVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butExpVActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            
            tabelV.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_butExpVActionPerformed

    private void textNumeLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNumeLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNumeLActionPerformed

    private void butExpLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butExpLMouseClicked
        try {
            // TODO add your handling code here:
            tabelL.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_butExpLMouseClicked

    private void tabelLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelLMouseClicked
        // TODO add your handling code here:
        int indexR=tabelL.getSelectedRow();
        textcodL.setText(tabelL.getModel().getValueAt(indexR, 0).toString());
        textNumeL.setText(tabelL.getModel().getValueAt(indexR, 1).toString());
        textStrada.setText(tabelL.getModel().getValueAt(indexR, 2).toString());
        textOras.setText(tabelL.getModel().getValueAt(indexR, 3).toString());
        textJudet.setText(tabelL.getModel().getValueAt(indexR, 4).toString());
    }//GEN-LAST:event_tabelLMouseClicked

    private void butadLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butadLActionPerformed
        // TODO add your handling code here:
        Locatii loc=new Locatii();
        
        loc.setNumeL(textNumeL.getText());
        loc.setStrada(textStrada.getText());
        loc.setOras(textOras.getText());
        loc.setJudet(textJudet.getText());
        
        LocLoader.insertLoc(loc);;
        loadTabelL();
        fieldResetLocatii();
        String comanda="Adaugare Locatie";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
    }//GEN-LAST:event_butadLActionPerformed

    private void butSchimbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSchimbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_butSchimbActionPerformed

    private void butCautaSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butCautaSMouseClicked
        // TODO add your handling code here:
        
    if(textCautaS.getText().equals("")){
        if(boxVal.getSelectedIndex()==0){
            loadTabelB();
        }
        else{
        DefaultTableModel model = (DefaultTableModel) tabelB.getModel();
        model.setRowCount(0);
        List<Beneficiar> listaBef = new BenefLoader().loadModel((String)(boxVal.getSelectedItem()));
        Object rowData[] = new Object[9];
         for (int i = 0; i < listaBef.size(); i++) {
         if(listaBef.get(i).getValSchimb().equals(boxVal.getSelectedItem())){
         rowData[0]=listaBef.get(i).getCodBe();
         rowData[1]=listaBef.get(i).getNume();
         rowData[2]=listaBef.get(i).getPren();
         rowData[3]=listaBef.get(i).getSuma();
         rowData[4]=listaBef.get(i).getValSchimb();
         rowData[5]=listaBef.get(i).getValPrimita();
         rowData[6]=listaBef.get(i).getComBen();
         rowData[7]=listaBef.get(i).getData();
         rowData[8]=listaBef.get(i).getIdc();
         model.addRow(rowData);
         }
         }
            }
    }else{
        if(boxVal.getSelectedIndex()==0){
        DefaultTableModel model = (DefaultTableModel) tabelB.getModel();
        model.setRowCount(0);
        List<Beneficiar> listaBef = new BenefLoader().loadModel(textCautaS.getText());
        Object rowData[] = new Object[9];
         for (int i = 0; i < listaBef.size(); i++) {
         rowData[0]=listaBef.get(i).getCodBe();
         rowData[1]=listaBef.get(i).getNume();
         rowData[2]=listaBef.get(i).getPren();
         rowData[3]=listaBef.get(i).getSuma();
         rowData[4]=listaBef.get(i).getValSchimb();
         rowData[5]=listaBef.get(i).getValPrimita();
         rowData[6]=listaBef.get(i).getComBen();
         rowData[7]=listaBef.get(i).getData();
         rowData[8]=listaBef.get(i).getIdc();
         model.addRow(rowData);
        }
        }else{
        DefaultTableModel model = (DefaultTableModel) tabelB.getModel();
        model.setRowCount(0);
        List<Beneficiar> listaBef = new BenefLoader().loadModel(textCautaS.getText());
        Object rowData[] = new Object[9];
         for (int i = 0; i < listaBef.size(); i++) {
             if(listaBef.get(i).getValSchimb().equals(boxVal.getSelectedItem())){
         rowData[0]=listaBef.get(i).getCodBe();
         rowData[1]=listaBef.get(i).getNume();
         rowData[2]=listaBef.get(i).getPren();
         rowData[3]=listaBef.get(i).getSuma();
         rowData[4]=listaBef.get(i).getValSchimb();
         rowData[5]=listaBef.get(i).getValPrimita();
         rowData[6]=listaBef.get(i).getComBen();
         rowData[7]=listaBef.get(i).getData();
         rowData[8]=listaBef.get(i).getIdc();
         model.addRow(rowData);
             }
         }
    }               
            }    
        String comanda="Cautare Beneficiar";
        try {
            ComLoader.adaugaCom(ILogare.util.getId(), comanda);
        } catch (SQLException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
        }
        loadTabelLog();
    }//GEN-LAST:event_butCautaSMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ILogare m=new ILogare();
        m.setExtendedState(NORMAL);
        m.show();
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comenziMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comenziMouseClicked
        // TODO add your handling code here:
        tabValuta.setVisible(true);
        tabValuta.setSelectedIndex(3);
    }//GEN-LAST:event_comenziMouseClicked

    private void butRapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butRapMouseClicked
        // TODO add your handling code here:
        tabValuta.setVisible(true);
        tabValuta.setSelectedIndex(4);
    }//GEN-LAST:event_butRapMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabelRB.getModel();
        model.setRowCount(0);
        List<Beneficiar> listaBef = new BenefLoader().loadRaport();
        Object rowData[] = new Object[2];
         for (int i = 0; i < listaBef.size(); i++) {
         rowData[0]=listaBef.get(i).getValSchimb();
         rowData[1]=listaBef.get(i).getSuma();
         
         model.addRow(rowData);
         }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tabelRV.getModel();
        model.setRowCount(0);
        List<ValutaDisp> listaBef = new ValutaDispLoad().loadRaport();
        Object rowData[] = new Object[2];
         for (int i = 0; i < listaBef.size(); i++) {
         rowData[0]=listaBef.get(i).getCodc();
         rowData[1]=listaBef.get(i).getDenumire();
         
         model.addRow(rowData);
         }
    }//GEN-LAST:event_jButton4MouseClicked

    public void openFile(String file){
        try{
            File path= new File(file);
            Desktop.getDesktop().open(path);
        }catch(IOException ioe){
            System.out.println(ioe);
        }
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try{
            JFileChooser jFileChooser=new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile=jFileChooser.getSelectedFile();
            if(saveFile !=null){
                saveFile=new File(saveFile.toString()+".xlsx");
                Workbook wb=new XSSFWorkbook();
                Sheet sheet=(Sheet) wb.createSheet("tabel1");
                XSSFRow rowcol=(XSSFRow) sheet.createRow(0);
                for(int i=0;i<tabelV.getColumnCount();i++){
                Cell cell=rowcol.createCell(i);
                cell.setCellValue(tabelV.getColumnName(i)); 
            }
            for(int j=0;j<tabelV.getRowCount();j++){
                XSSFRow row=(XSSFRow) sheet.createRow(j+1);
                for(int k=0;k<tabelV.getColumnCount();k++){
                    Cell cell=row.createCell(k); 
                    if(tabelV.getValueAt(j,k)!= null){
                        cell.setCellValue(tabelV.getValueAt(j, k).toString());
                    }
            } 
            }
            FileOutputStream out= new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error generare");
            }
           
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException io){
            System.out.println(io);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
                try{
            JFileChooser jFileChooser=new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile=jFileChooser.getSelectedFile();
            if(saveFile !=null){
                saveFile=new File(saveFile.toString()+".xlsx");
                Workbook wb=new XSSFWorkbook();
                Sheet sheet=(Sheet) wb.createSheet("tabel1");
                XSSFRow rowcol=(XSSFRow) sheet.createRow(0);
                for(int i=0;i<tabelB.getColumnCount();i++){
                Cell cell=rowcol.createCell(i);
                cell.setCellValue(tabelB.getColumnName(i)); 
            }
            for(int j=0;j<tabelB.getRowCount();j++){
                XSSFRow row=(XSSFRow) sheet.createRow(j+1);
                for(int k=0;k<tabelB.getColumnCount();k++){
                    Cell cell=row.createCell(k); 
                    if(tabelB.getValueAt(j,k)!= null){
                        cell.setCellValue(tabelB.getValueAt(j, k).toString());
                    }
            } 
            }
            FileOutputStream out= new FileOutputStream(new File(saveFile.toString()));
            wb.write(out);
            wb.close();
            out.close();
            openFile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error generare");
            }
           
        }catch(FileNotFoundException e){
            System.out.println(e);
        }catch(IOException io){
            System.out.println(io);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Iprincip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Iprincip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Iprincip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Iprincip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Iprincip().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Iprincip.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
//       ILogare m=new ILogare();
//        m.setExtendedState(NORMAL);
//        m.show();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxVal;
    private javax.swing.JButton butAdB;
    private javax.swing.JButton butCauta;
    private javax.swing.JButton butCautaS;
    public static javax.swing.JButton butExp;
    public static javax.swing.JButton butExpL;
    public static javax.swing.JButton butExpV;
    private javax.swing.JButton butLoc;
    private javax.swing.JButton butRap;
    private javax.swing.JButton butSchimb;
    public static javax.swing.JButton butSterg;
    public static javax.swing.JButton butStrB;
    public static javax.swing.JButton butUp;
    public static javax.swing.JButton butUpB;
    private javax.swing.JButton butValuta;
    public static javax.swing.JButton butadL;
    public static javax.swing.JButton butonAd;
    private javax.swing.JButton comenzi;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    public static javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    public static javax.swing.JLabel jLabel18;
    public static javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel20;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextField jTextField1;
    private bdproiect.ModelPieChart modelPieChart1;
    private bdproiect.PieChart pieChart1;
    private javax.swing.JTabbedPane tabValuta;
    private javax.swing.JTable tabelB;
    private javax.swing.JTable tabelL;
    private javax.swing.JTable tabelLog;
    private javax.swing.JTable tabelRB;
    private javax.swing.JTable tabelRV;
    private javax.swing.JTable tabelV;
    private javax.swing.JTextField textCauta;
    private javax.swing.JTextField textCautaS;
    public static javax.swing.JTextField textCo;
    public static javax.swing.JTextField textCodBe;
    private javax.swing.JTextField textCodc;
    public static javax.swing.JTextField textCodcBe;
    private javax.swing.JTextField textCodv;
    private javax.swing.JTextField textComis;
    private javax.swing.JTextField textCursAnt;
    private javax.swing.JTextField textCursCump;
    private javax.swing.JTextField textCursVanz;
    private javax.swing.JTextField textDen;
    public static javax.swing.JTextField textJudet;
    public static javax.swing.JTextField textNume;
    public static javax.swing.JTextField textNumeL;
    public static javax.swing.JTextField textOras;
    public static javax.swing.JTextField textPren;
    public static javax.swing.JTextField textStrada;
    public static javax.swing.JTextField textSuma;
    public static javax.swing.JTextField textVp;
    public static javax.swing.JTextField textVs;
    public static javax.swing.JTextField textcodL;
    // End of variables declaration//GEN-END:variables
}
