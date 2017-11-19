/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daksa
 */
public class Hotel extends Koneksi
{
    private String vid;
    private String vtanggal;
    private String vnama;
    private String vnomor;
    private String vjenis;
    private int vinap;
    private double vjumlah;
    private double vpajak;
    private double vgrand;
    private double vbayar;
    private double vkembali;
    
    public Hotel() throws SQLException
    {
        vid        = "";
        vtanggal   = "";
        vnama      = "";
        vnomor     = "";
        vjenis     = "";
        vinap      = 0;
        vjumlah    = 0;
        vpajak     = 0;
        vgrand     = 0;
        vbayar     = 0;
        vkembali   = 0;
        koneksi();
    }
    
    public void setId(String id)
    {
        vid = id;
        
    }
    
    public String getId()
    {
        return vid;
    }
    
    public void setTanggal(String tanggal)
    {
        vtanggal = tanggal;
        
    }
    
    public String getTanggal()
    {
        return vtanggal;
    }
    
    public void setNama(String nama)
    {
        vnama = nama;
        
    }
    
    public String getNama()
    {
        return vnama;
    }
    
    public void setNomor(String nomor)
    {
        vnomor = nomor;
        
    }
    
    public String getNomor()
    {
        return vnomor;
    }
    
    public void setJenis(String jenis)
    {
        vjenis = jenis;
        
    }
    
    public String getJenis()
    {
        return vjenis;
    }
    
    public int setInap(int inap)
    {
        vinap = inap;
        
        return vinap;
    }
    
    public int getInap()
    {
        return vinap;
    }
    
    public void setJumlah(double jumlah)
    {
        vjumlah = jumlah;
        
    }
    
    public double getJumlah()
    {
        return vjumlah;
    }
    
    public void setPajak(double pajak)
    {
        vpajak = pajak;
        
    }
    
    public double getPajak()
    {
        return vpajak;
    }
    
    public void setGrand(double grand)
    {
        vgrand = grand;
        
    }
    
    public double getGrand()
    {
        return vgrand;
    }
    
    public double setBayar(double bayar)
    {
        vbayar = bayar;
        return vbayar;
        
    }
    
    public double getBayar()
    {
        return vbayar;
    }
    
    public void setKembali(double kembali)
    {
        vkembali = kembali;
        
    }
    
    public double getKembali()
    {
        return vkembali;
    }
    
    public ResultSet ViewById()
    {
        ResultSet rs;
        String sql;
        sql = "select * from transaksi where idtransaksi = '" + vid + "' ";
        try
        {
          rs = QuerySql(sql);
        } catch(SQLException ex)
        {
            rs = null;
            Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet ViewByNama() throws SQLException
    {
        ResultSet rs;
        String sql;
        sql = "select * from transaksi where namacustomer like '" + vnama + "%' ";
        rs = QuerySql(sql);
        return rs;
    }
    
    public ResultSet ViewByAll() throws SQLException
    {
        ResultSet rs;
        String sql;
        sql = "select * from transaksi ";
        rs = QuerySql(sql);
        return rs;
    }
    
    public boolean Insert() throws SQLException
    {
        boolean h = false;
        String sql;
        sql = "insert into transaksi(idtransaksi, tanggal, namacustomer, nomorktp, jenisid, lamainap, jumlahuang, pajak, grandtotal, bayar, kembali)";
        sql +=" values('" + vid +"','" + vtanggal + "','" + vnama + "','" + vnomor + "','" + vjenis + "','" + vinap + "','" + vjumlah + "','" + vpajak + "','" + vgrand + "','" + vbayar + "', " + vkembali + ")";
        h = Executesql(sql);
        return h;
    }
    
    public boolean Update() throws SQLException
    {
        boolean h = false;
        String sql;
        sql = "update transaksi set tanggal = '" + vtanggal + "', namacustomer ='" + vnama + "', nomorktp = '"
                + vnomor + "', lamainap = '"+ vinap + "', jumlahuang ='" + vjumlah + "', pajak = '"
                + vpajak + "', bayar = '" + vbayar + "', kembali ='" + vkembali + "'";
        sql += " where idtransaksi = '" + vid + "' ";
        h = Executesql(sql);
        return h;
    }
    
    public boolean Delete () throws SQLException
    {
        boolean h = false;
        String sql;
        sql = "delete from transaksi ";
        sql += " where idtransaksi = '" + vid + "' ";
        h = Executesql(sql);
        return h;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
    }
    
    
    
    
}
