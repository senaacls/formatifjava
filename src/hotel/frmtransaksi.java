/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;


import java.awt.Component;
import java.awt.Container;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;
/**
 *
 * @author daksa
 */
public class frmtransaksi extends javax.swing.JFrame {
    
    private boolean databaru;
    private Hotel cl;
    private Koneksi conn;
    NumberFormat formatter = new DecimalFormat("#0.00");    
    /**
     * Creates new form frmtransaksi
     */

    public frmtransaksi() throws SQLException 
    {
        initComponents();
        databaru = true;
        cl       = new Hotel();
        conn     = new Koneksi();
        GetData();
        tanggal();
        dropdown();
        setFalse();
    }
    
    public void clearTextFields (Container container)
    {
        for(Component c : container.getComponents())
        {
           if(c instanceof JTextField)
           {
            JTextField f = (JTextField) c;
            f.setText("");
           } 
        else if (c instanceof Container)
           clearTextFields((Container)c);
        }
    }
    
    
    private void GetData()  throws SQLException
    {
        Object[] kolom = {"Id", "Tanggal", "Nama", "NomorKtp", "Jenis", "Hari", "JumlahUang", "Pajak", "GrandTotal", "Bayar", "Kembali"};
        DefaultTableModel tabmode = new DefaultTableModel(null, kolom);
        cl.setNama(txtcari.getText());
        ResultSet rs = cl.ViewByNama();
        while(rs.next())
        {
            String data[]={rs.getString("idtransaksi"),rs.getString("tanggal"),rs.getString("namacustomer"),rs.getString("nomorktp"), rs.getString("jenisid"), rs.getString("lamainap"), rs.getString("jumlahuang"), rs.getString("pajak"), rs.getString("grandtotal"), rs.getString("grandtotal"), rs.getString("kembali")};
            tabmode.addRow(data);
        }
        jTable1.setModel(tabmode);
    }
    
    private void tanggal()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        txttanggal.setText(dateFormat.format(cal.getTime()));
        txttanggal.setEditable(false);
    }
    
    public HashMap<String, String> populate() throws SQLException
    {
        HashMap<String, String> map = new HashMap<String, String>();
        ResultSet rs;
        String sql;
        sql = "select * from jeniskamar ";   
        rs = conn.QuerySql(sql);
        while (rs.next()) {
            String pat = rs.getString("jenisid");
            String name = rs.getString("jeniskamar");
            //cmbjenis.addItem(new ComboItem(name, pat).toString());
            ComboItem c = new ComboItem(pat,name);
            map.put(c.getName(), c.getId());
        }
        return map;
        
    }
    
    public void dropdown() throws SQLException
    {
        HashMap<String, String> map = populate();
        for(String s : map.keySet())
        {
           cmbjenis.addItem(s);
        }
    }
    
   
    
    public void harga() throws SQLException
    {   
        HashMap<String, String> map = populate();
        String cari = (map.get(cmbjenis.getSelectedItem().toString()));
        ResultSet rs;
        String sql;
        sql = "select * from jeniskamar where jenisid = '" + cari + "'";   
        rs = conn.QuerySql(sql);
        while (rs.next()) {
            Double harga = rs.getDouble("hargasewa");
            txtharga.setText(formatter.format(harga));
        }
    }
    
    private void hitung(){
        int inap;
        double harga, jml, pajak, grandtotal;
        inap        = cl.setInap(Integer.parseInt(txtinap.getText()));
        harga       = Double.parseDouble(txtharga.getText());
        jml         = inap * harga;
        pajak       = (21 * jml) / 100;
        grandtotal  = jml + pajak;

        this.txtjumlah.setText(formatter.format(jml));
        this.txtpajak.setText(formatter.format(pajak));
        this.txtgrandtotal.setText(formatter.format(grandtotal));
        
    }
    
    private void bayar(){
        double bayar, grandtotal, kembali;
        bayar        = cl.setBayar(Double.parseDouble(txtbayar.getText()));
        grandtotal   = Double.parseDouble(txtgrandtotal.getText());
        kembali      = bayar - grandtotal;
      
        this.txtkembali.setText(formatter.format(kembali));
        
    }
    
    private void setFalse()
    {
        txtharga.setEditable(false);
        txtjumlah.setEditable(false);
        txtpajak.setEditable(false);
        txtgrandtotal.setEditable(false);
        txtkembali.setEditable(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnama = new javax.swing.JTextField();
        txtnomor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbjenis = new javax.swing.JComboBox<>();
        txttanggal = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtinap = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtharga = new javax.swing.JTextField();
        txtjumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtgrandtotal = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtkembali = new javax.swing.JTextField();
        txtpajak = new javax.swing.JTextField();
        btnsimpan = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        btntambah = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Form Transaksi");
        jLabel7.setToolTipText("");
        jLabel7.setOpaque(true);

        jLabel1.setText("Cari");

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcariKeyPressed(evt);
            }
        });

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
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Id");

        jLabel3.setText("Tanggal");

        jLabel4.setText("Nama");

        jLabel5.setText("NomorKtp");

        jLabel6.setText("Jenis");

        cmbjenis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbjenisMouseClicked(evt);
            }
        });
        cmbjenis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbjenisActionPerformed(evt);
            }
        });
        cmbjenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbjenisKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbjenisKeyReleased(evt);
            }
        });

        jLabel8.setText("Lama Inap");

        txtinap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtinapActionPerformed(evt);
            }
        });
        txtinap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtinapKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtinapKeyReleased(evt);
            }
        });

        jLabel9.setText("Harga");

        jLabel10.setText("Jumlah Uang");

        jLabel11.setText("Pajak");

        jLabel12.setText("Grand Total");

        jLabel13.setText("Bayar");

        txtbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbayarActionPerformed(evt);
            }
        });
        txtbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbayarKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbayarKeyReleased(evt);
            }
        });

        jLabel14.setText("Kembali");

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        btntambah.setText("Tambah");
        btntambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntambahActionPerformed(evt);
            }
        });

        btncancel.setText("Cancel");

        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcari))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 948, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtjumlah)
                                    .addComponent(txtnomor)
                                    .addComponent(cmbjenis, 0, 179, Short.MAX_VALUE)
                                    .addComponent(txtnama)
                                    .addComponent(txttanggal)
                                    .addComponent(txtid)
                                    .addComponent(txtinap)
                                    .addComponent(txtharga))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtpajak, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                            .addComponent(txtgrandtotal)
                                            .addComponent(txtbayar)
                                            .addComponent(txtkembali)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnsimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnhapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btntambah)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btncancel))
                                    .addComponent(btnexit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11)
                    .addComponent(txtpajak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel12)
                    .addComponent(txtgrandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtnomor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(txtkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(cmbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtinap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnsimpan)
                                    .addComponent(btnhapus)
                                    .addComponent(btntambah)
                                    .addComponent(btncancel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnexit)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyPressed
        try {
            // TODO add your handling code here:
            GetData();
        } catch (SQLException ex) {
            Logger.getLogger(frmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtcariKeyPressed

    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnexitActionPerformed

    private void cmbjenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbjenisActionPerformed
        try {
            // TODO add your handling code here:
            this.harga();
        } catch (SQLException ex) {
            Logger.getLogger(frmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbjenisActionPerformed

    private void btntambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntambahActionPerformed
        // TODO add your handling code here
        databaru = true;
        this.txtid.setEditable(true);
        clearTextFields(this.getContentPane());
        this.tanggal();
        cmbjenis.setSelectedIndex(0);
        this.txtid.grabFocus();
        setFalse();
    }//GEN-LAST:event_btntambahActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        try
        {
            // TODO add your handling code here:
            HashMap<String, String> map = populate();
            String cari = (map.get(cmbjenis.getSelectedItem().toString()));
            cl.setId(txtid.getText());
            cl.setTanggal(txttanggal.getText());
            cl.setNama(txtnama.getText());
            cl.setNomor(txtnomor.getText());
            cl.setJenis(cari);
            cl.setInap(Integer.parseInt(txtinap.getText()));
            cl.setJumlah(Double.parseDouble(txtjumlah.getText()));
            cl.setPajak(Double.parseDouble(txtpajak.getText()));
            cl.setGrand(Double.parseDouble(txtgrandtotal.getText()));
            cl.setBayar(Double.parseDouble(txtbayar.getText()));
            cl.setKembali(Double.parseDouble(txtkembali.getText()));
            boolean h = false;
            try
            {
                if(databaru == true)
                {
                    h = cl.Insert();
                }
                else
                {
                    h = cl.Update();
                }
                JOptionPane.showMessageDialog(null,"Penyimpanan berhasil");
                this.GetData();
                clearTextFields(this.getContentPane());
                
            } catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(null, "Penyimpanan gagal" + ex.getMessage());
            }
        } catch(SQLException ex)
        {
            Logger.getLogger(frmtransaksi.class.getName()).log(Level.SEVERE,null, ex);
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void txtinapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtinapActionPerformed
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_txtinapActionPerformed

    private void txtinapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinapKeyReleased
        // TODO add your handling code here:
        hitung();
    }//GEN-LAST:event_txtinapKeyReleased

    private void txtinapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtinapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtinapKeyPressed

    private void txtbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbayarActionPerformed
        // TODO add your handling code here:
        bayar();
    }//GEN-LAST:event_txtbayarActionPerformed

    private void cmbjenisKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbjenisKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_cmbjenisKeyReleased

    private void txtbayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbayarKeyTyped

    private void txtbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyReleased
        // TODO add your handling code here:
        bayar();
    }//GEN-LAST:event_txtbayarKeyReleased

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
        if("".equals(this.txtid.getText()))
        {
            JOptionPane.showMessageDialog(null, "Silahkan klik pada grid untuk pilih data");
        }
        else
        {
            boolean h = false;
            try
            {
                cl.setId(this.txtid.getText());
                h = cl.Delete();
                JOptionPane.showMessageDialog(null, "Penghapusan data berhasil");
                GetData();
                clearTextFields(this.getContentPane());
            } catch(SQLException ex)
            {
                Logger.getLogger(frmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnhapusActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         databaru = false;
        int row = jTable1.getSelectedRow();
        String tabel_klik =(jTable1.getModel().getValueAt(row, 0).toString());
        cl.setId(tabel_klik);
        try
        {           
                ResultSet rs = cl.ViewById();
                if(rs.next())
                {
                    cl.setId(rs.getString("idtransaksi"));
                    cl.setTanggal(rs.getString("tanggal"));
                    cl.setNama(rs.getString("namacustomer"));
                    cl.setNomor(rs.getString("nomorktp"));
                    cl.setInap(Integer.parseInt(rs.getString("lamainap")));
                    cl.setJumlah(Double.parseDouble(rs.getString("jumlahuang")));
                    cl.setPajak(Double.parseDouble(rs.getString("pajak")));
                    cl.setGrand(Double.parseDouble(rs.getString("grandtotal")));
                    cl.setBayar(Double.parseDouble(rs.getString("bayar")));
                    cl.setKembali(Double.parseDouble(rs.getString("kembali")));
                    cl.setJenis(rs.getString("jenisid"));
                    
                    this.txtid.setText(cl.getId());
                    this.txttanggal.setText(cl.getTanggal());
                    this.txtnama.setText(cl.getNama());
                    this.txtnomor.setText(cl.getNomor());
                    this.txtinap.setText(Integer.toString(cl.getInap()));
                    this.txtjumlah.setText(Double.toString(cl.getJumlah()));
                    this.txtpajak.setText(Double.toString(cl.getPajak()));
                    this.txtgrandtotal.setText(Double.toString(cl.getGrand()));
                    this.txtbayar.setText(Double.toString(cl.getBayar()));
                    this.txtkembali.setText(Double.toString(cl.getKembali()));
                    this.cmbjenis.setSelectedItem(cl.getJenis());
                    this.txtid.setEditable(false);
                }
        }catch(SQLException ex)
                {
                    Logger.getLogger(frmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
    }//GEN-LAST:event_jTable1MouseClicked

    private void cmbjenisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbjenisMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cmbjenisMouseClicked

    private void cmbjenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbjenisKeyPressed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_cmbjenisKeyPressed

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
            java.util.logging.Logger.getLogger(frmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmtransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmtransaksi().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frmtransaksi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton btntambah;
    private javax.swing.JComboBox<String> cmbjenis;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtgrandtotal;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtinap;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkembali;
    private javax.swing.JTextField txtnama;
    private javax.swing.JTextField txtnomor;
    private javax.swing.JTextField txtpajak;
    private javax.swing.JTextField txttanggal;
    // End of variables declaration//GEN-END:variables
}
