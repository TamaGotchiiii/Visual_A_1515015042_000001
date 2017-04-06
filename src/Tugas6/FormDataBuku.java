/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas6;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author acer
 */
//saya tidak seleksi harga apakah angka atau tidak
public class FormDataBuku extends javax.swing.JFrame {

    private DefaultTableModel model;
    private Connection con = koneksi.getConnection();
    private Statement stt;
    private ResultSet res;
    private boolean kebenaran=true;
    /**
     * Creates new form FormDataBuku
     */
    
    public FormDataBuku() {
        initComponents();
    }
    
    private void InitTable(){
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Judul");
        model.addColumn("Penulis");
        model.addColumn("Harga");
        
        jTable1.setModel(model);
    }
    
    private void TampilData(){
        try{
            String sql = "SELECT * FROM buku";
            stt = con.createStatement();
            res = stt.executeQuery(sql);
            while(res.next()){
                Object[] o = new Object[4];
                o[0] = res.getString("id");
                o[1] = res.getString("judul");
                o[2] = res.getString("penulis");
                o[3] = res.getString("harga");
                
                model.addRow(o);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void TambahData(String judul, String penulis, String harga){
        //sintaks dibawah adalah sintaks untuk mencari data
        //
        try{
            String sql = "INSERT INTO buku VALUES(NULL,'"+judul+"', '"+penulis+"','"+harga+"')";
            stt = con.createStatement();
            stt.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void UpdateData(String judul, String penulis, String harga, String id){
        try{
            String sql = "UPDATE buku SET judul='"+judul+"',penulis='"+penulis+"',harga ='"+harga+"' WHERE id = "+id+"";
            stt = con.createStatement();
            stt.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private void HapusData(String id){
        try{
            String sql = "DELETE FROM buku where id="+id+"";
            stt = con.createStatement();
            stt.executeUpdate(sql);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    private boolean Check(String judul, String penulis){
        //ini adalah sintaks untuk melakukan check
        //apakah data yang akan ditambahkan/ubah sudah ada atau tidak
        //saya tidak menggunakan method yang bertipe void karena saya butuh output dari method ini
        //supaya dapat diseleksi dimethod yang lain
        boolean hasil = false;
        try{
            String sql = "SELECT * FROM buku";
            stt = con.createStatement();
            res = stt.executeQuery(sql);
            while(res.next()){
                Object[] o = new Object[2];
                o[0] = res.getString("judul").toLowerCase();
                o[1] = res.getString("penulis").toLowerCase();
                //disini saya menjadikan lower case agar tidak terjadi salah persepsi perbandingan
                //dan program tetap memasukkan data yang sama karna beda besar dan kecil huruf
                if(o[0].equals(judul.toLowerCase()) && o[1].equals(penulis.toLowerCase())){
                    hasil=true;
                    break;
                }else{
                   hasil=false;
                }
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return hasil;
    }
    
    private void cariData(String cari, String tipe){

        int x=0;
        try{
                //disini saya menggunakan query yang mempunyai like supaya dapat menyeleksi
                //data yang mempunyai kata-kata yang sama
                String sql = "SELECT * FROM buku where "+tipe+" LIKE '%"+cari+"%'";
                stt = con.createStatement();
                res = stt.executeQuery(sql);
                
                //disini saya kebingungan bagaimana caranya saya menyeleksi jika
                //sintaks diatas tidak memiliki hasil jadi saya akali dengan
                //menggunakan perulangan res.next karena kondisi ini akan membuat
                //perulangan terjadi sampai data yang ditemukan sudah ditelusuri semua
                //x didalam digunakan untuk memberikan jumlah data yang ditemukan
                while(res.next()){
                    x++;
                }
                
                //jika x>0 artinya ada data yang ditemukan
                //karena sudah terjadi penelusuran hingga data yang ditemukan habis
                //maka penggunakan res.next tidak akan bisa dilakukan
                //maka saya memutar kembali penelusuran tersebut menggunakan
                //res.previous yang akan membuat data kembali ke indeks awal
                //jika x=0 maka tidak ada data yang ditemukan
                if(x>0){
                    while(res.previous()){
                    
                    }
                    
                    //method removeAll() saya gunakan untuk menghapus data pada table dan menampilkan
                    //data yang ditemukan saja
                    jTable1.removeAll();
                    InitTable();
                    while(res.next()){
                        Object[] o = new Object[4];
                            o[0] = res.getString("id");
                            o[1] = res.getString("judul");
                            o[2] = res.getString("penulis");
                            o[3] = res.getString("harga");
                        model.addRow(o);
                    }
                    jTable1.setModel(model);
                }else{
                    JOptionPane.showMessageDialog(null, "Data tidak ada");
                    InitTable();
                    TampilData();
                }
                
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Judul = new javax.swing.JTextField();
        CMBpenulis = new javax.swing.JComboBox<>();
        Harga = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        BtnSimpan = new javax.swing.JToggleButton();
        BtnHapus = new javax.swing.JToggleButton();
        BtnUbah = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        Pencarian = new javax.swing.JTextField();
        CMBcari = new javax.swing.JComboBox<>();
        btnCari = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(0, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Form Data Buku");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 255));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Judul");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Penulis");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Harga");

        CMBpenulis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tere Liye", "W.S Rendra", "Felix Shiauw", "Asma Nadia", "Dewi Lestari" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Judul)
                    .addComponent(CMBpenulis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Harga))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Judul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CMBpenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Harga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 153, 255));
        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        jPanel4.add(BtnSimpan);

        BtnHapus.setText("Hapus");
        BtnHapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnHapusMouseEntered(evt);
            }
        });
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        jPanel4.add(BtnHapus);

        BtnUbah.setText("Ubah");
        BtnUbah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnUbahMouseEntered(evt);
            }
        });
        BtnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUbahActionPerformed(evt);
            }
        });
        jPanel4.add(BtnUbah);

        jToggleButton4.setText("Keluar");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jToggleButton4);

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

        CMBcari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Pencarian", "Judul", "Penulis", "Harga" }));

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        jToggleButton6.setText("Tampilkan Semua Data");
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CMBcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CMBcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari)
                    .addComponent(jToggleButton6))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //sintaks dibawah digunakan untuk menginisiasi tabel yang telah dibuat dan menampilkan data dari database
        InitTable();
        TampilData();
            
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentShown

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        String judul = Judul.getText();
        String penulis = CMBpenulis.getSelectedItem().toString();
        String harga = Harga.getText();
        //sintaks if dibawah digunakan untuk menyeleksi apakah textfield judul dan penulis kosong atau tidak, jika kosong
        //maka akan keluar pemberitahuan bahwa ada data yang belum diisi
        if(!"".equals(judul) && !"".equals(penulis)){
            //ini adalah penggunaan check, yang fungsinya adalah mencari apakah ada data yang sama
            boolean cek;
            cek=Check(judul, penulis);
            //jika cek menghasilkan false, maka berarti tidak ada data yang sama,selain itu program akan
            //mengeluarkan pemberitahuan data telah ada
            if(cek==false){
                TambahData(judul, penulis, harga);
                InitTable();
                TampilData();
            }else{
                JOptionPane.showMessageDialog(null, "Data telah ada");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ada Data yang belum terisi");
        }
// TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        //sintaks dibawah ini digunakan untuk mengisi textfield dan combobox secara otomatis
        //saat user memilih salah satu data yang ada pada table
        int baris = jTable1.getSelectedRow();
        
        String judul = jTable1.getValueAt(baris,1).toString();
        String penulis = jTable1.getValueAt(baris, 2).toString();
        String harga = jTable1.getValueAt(baris, 3).toString();
        
        Judul.setText(judul);
        CMBpenulis.setSelectedItem(penulis);
        Harga.setText(harga);
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void BtnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUbahActionPerformed
        int baris = jTable1.getSelectedRow();
        //sintaks diatas digunakan untuk mendapatkan baris yang dipilih oleh user
        
        //sintaks if dibawah digunakan untuk menyeleksi apakah ada data yang dipilih atau tidak
        //jika tidak program akan mengeluarkan pemberitahuan bahwa user belum memilih data
        if(baris>=0){
            //sintaks dibawah ini digunakan untuk mengambil nilai data yang ada pada textfield judul, harga dan combobox penulis
            String judul = Judul.getText();
            String penulis = CMBpenulis.getSelectedItem().toString();
            String harga = Harga.getText();
            //sintaks dibawah ini adalah untuk mendapatkan nilai id yang dari data yang telah dipilih oleh user
            String id = jTable1.getValueAt(baris, 0).toString();
            
            //sintaks dibawah ini digunakan untuk mengecek apakah data terjadi perubahan atau tidak, dan apakah perubahan
            //yang terjadi menyamai data yang telah ada
            boolean cek;
            cek=Check(judul, penulis);
            //jika hasil dari cek adalah false maka akan dilakukan update data,
            //selain itu program akan mengeluarkan pemberitahuan bahwa data tidak berubah atau data telah ada
            if(cek==false){
                UpdateData(judul, penulis, harga, id);
                InitTable();
                TampilData();
            }else{
                JOptionPane.showMessageDialog(null, "Data yang ingin diubah telah ada atau tidak ada perubahan");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin diubah");
        }
        
        

                // TODO add your handling code here:
    }//GEN-LAST:event_BtnUbahActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        int baris = jTable1.getSelectedRow();
        //sintaks diatas adalah untuk mengambil baris yang telah dipilih ditabel
        //sintaks if disini digunakan untuk menyeleksi apakah ada baris yang dipilih atau tidak
        //jika belum ada baris yang pilih maka program akan mengeluarkan, pemberitahuan
        //bahwa user belum memilih data
        if(baris>=0){
            //sintaks dibawah digunakan untuk mendapatkan nilai id yang dimiliki oleh
            //data yang telah dipilih oleh user
            String ID = jTable1.getValueAt(baris, 0).toString();
            //method HapusData ini digunakan untuk menghapus data yang telah dipilih tadi dengan parameter ID
            //sintaks initTable digunakan untuk merefresh table yang telah dilakukan penghapusan data
            //tampildata digunakan untuk menampilkan data yang ada pada database
            HapusData(ID);
            InitTable();
            TampilData();
        }else{
            JOptionPane.showMessageDialog(null, "Anda Belum memilih data");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        //supaya bisa keluar -,-
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        int pilihan=CMBcari.getSelectedIndex();
        String cari = Pencarian.getText();
        String tipe;
        //sintaks if dibawah digunakan untuk menyeleksi apakah cari sudah diisi apa tidak,
        //jika tidak maka program akan mengeluarkan pemberitahuan bahwa
        //Kolom pencarian kosong
        if(!"".equals(cari)){
            //sintaks if dibawah digunakan untuk menyeleksi
            //apakah user sudah memilih jenis pencarian yang diinginkannya
            //jika user belum memilih jenis pencarian maka akan ada pemberitahuan
            //bahwa belum ada jenis pencarian yang dipilih
            if(pilihan==1){
                //sebagai contoh user memilih pilihan dengan indeks 1 dari combo box,
                //maka tipe akan berisi judul yang nanti akan diolah dimethod cari data
                tipe="judul";
                cariData(cari, tipe);
            }else if(pilihan==2){
                tipe="penulis";
                cariData(cari, tipe);
            }else if(pilihan==3){
                tipe="harga";
                cariData(cari, tipe);
            }else{
                JOptionPane.showMessageDialog(null, "Pilih tipe pencarian");
            }        
        }else{
            JOptionPane.showMessageDialog(null, "Kolom Pencarian Kosong");
        }
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCariActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        //sintaks dibawah digunakan untuk melakukan refresh pada tabel agar menampilkan semua data
        InitTable();
        TampilData();
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void BtnHapusMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnHapusMouseEntered
        //sintaks ini digunakan untuk mendisable tombol hapus jika tidak ada data pada tabel
        int row = model.getRowCount();
        if(row==0){
            BtnHapus.setEnabled(false);
        }else{
            BtnHapus.setEnabled(true);
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnHapusMouseEntered

    private void BtnUbahMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnUbahMouseEntered
        //sintaks ini digunakan untuk mendisable tombol ubah jika tidak ada data didalamnya
        int row = model.getRowCount();
        
        if(row==0){
            BtnUbah.setEnabled(false);
        }else{
            BtnUbah.setEnabled(true);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnUbahMouseEntered

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
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton BtnHapus;
    private javax.swing.JToggleButton BtnSimpan;
    private javax.swing.JToggleButton BtnUbah;
    private javax.swing.JComboBox<String> CMBcari;
    private javax.swing.JComboBox<String> CMBpenulis;
    private javax.swing.JTextField Harga;
    private javax.swing.JTextField Judul;
    private javax.swing.JTextField Pencarian;
    private javax.swing.JToggleButton btnCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton6;
    // End of variables declaration//GEN-END:variables
}
