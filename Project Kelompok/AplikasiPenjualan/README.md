# Aplikasi Penjualan Berbasis Java GUI dan MySQL

## 1. Struktur Folder

```
AplikasiPenjualan/
├── src/
│   ├── koneksi/
│   │   └── Koneksi.java
│   ├── model/
│   │   ├── Person.java
│   │   ├── User.java
│   │   ├── Pelanggan.java
│   │   ├── Barang.java
│   │   └── Transaksi.java
│   ├── exception/
│   │   └── StokTidakCukupException.java
│   └── gui/
│       ├── LoginForm.java
│       ├── MainFrame.java
│       ├── FormUser.java
│       ├── FormBarang.java
│       ├── FormPelanggan.java
│       ├── FormTransaksi.java
│       └── FormLaporan.java
├── lib/
│   └── (taruh file mysql-connector-j-x.x.x.jar di sini)
└── database/
    └── db_penjualan.sql
```

Kalau pakai IDE (NetBeans / Eclipse / IntelliJ), cukup import folder `src` sebagai Source Folder / Source Root, package-nya (`koneksi`, `model`, `exception`, `gui`) akan otomatis kebaca dari nama folder.

## 2. Setup Database

1. Buka MySQL (via phpMyAdmin, MySQL Workbench, atau terminal `mysql -u root -p`).
2. Jalankan seluruh isi file `database/db_penjualan.sql`. File ini sudah berisi:
   - Pembuatan database `db_penjualan`
   - 5 tabel (`user`, `barang`, `pelanggan`, `transaksi`, `detail_transaksi`)
   - Data contoh (sample data) biar bisa langsung dicoba
   - 1 Trigger (`trg_kurangi_stok`) — otomatis mengurangi stok tiap ada transaksi baru
   - 1 Function (`fn_hitung_total_transaksi`) — menghitung ulang total transaksi
   - 2 Stored Procedure (`sp_tambah_transaksi`, `sp_tambah_detail_transaksi`) — dipanggil dari Java pas simpan transaksi
   - 1 View (`view_laporan_penjualan`) — dipakai di Form Laporan

**Catatan penting:** kalau pakai terminal biasa, jangan lupa jalankan dengan `DELIMITER $$` seperti di file .sql (bukan cuma copy-paste ke satu baris), karena stored procedure/function/trigger butuh delimiter khusus. Kalau pakai phpMyAdmin, tinggal import file .sql-nya langsung, biasanya delimiter otomatis kehandle.

## 3. Setup Koneksi JDBC

1. Download **MySQL Connector/J** (driver JDBC resmi dari MySQL), taruh file `.jar`-nya di folder `lib/`.
2. Tambahkan jar tersebut ke Library/Classpath project di IDE kalian:
   - **NetBeans**: klik kanan project → Properties → Libraries → Add JAR/Folder
   - **Eclipse**: klik kanan project → Build Path → Add External Archives
   - **IntelliJ**: File → Project Structure → Libraries → tambahkan jar
3. Buka `src/koneksi/Koneksi.java`, sesuaikan bagian ini dengan MySQL di komputer kalian:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/db_penjualan";
   private static final String USER = "root";
   private static final String PASSWORD = ""; // isi sesuai password MySQL kalian
   ```

## 4. Cara Menjalankan

1. Pastikan MySQL server sudah nyala (XAMPP/Laragon/MySQL Service).
2. Compile dan jalankan `src/gui/LoginForm.java` (ini file yang punya method `main`).
3. Login pakai akun contoh yang sudah ada di database:
   - Username: `admin` — Password: `admin123`
   - Username: `kasir1` — Password: `kasir123`
4. Setelah login berhasil, akan muncul menu utama untuk akses semua fitur.

## 5. Alur Testing yang Disarankan

1. Login
2. Cek/tambah Data Barang & Data Pelanggan dulu (biar ada data buat transaksi)
3. Coba fitur Pencarian di Data Barang / Pelanggan
4. Buka Transaksi Penjualan → pilih pelanggan, pilih barang, tambah ke keranjang, simpan
5. Cek Data Barang lagi → pastikan stok sudah berkurang otomatis (bukti trigger jalan)
6. Buka Laporan Penjualan → cek data transaksi tadi muncul (bukti view jalan), coba tombol "Hitung Total (Function)" pakai ID Transaksi yang baru dibuat

## 6. Pembagian Tugas untuk 3 Orang (Live Coding)

| Orang | Tanggung Jawab | File yang dikerjakan |
|---|---|---|
| **A** | Setup project, koneksi JDBC, Login, CRUD User | `Koneksi.java`, `Person.java`, `User.java`, `LoginForm.java`, `FormUser.java`, `MainFrame.java` |
| **B** | CRUD & Pencarian Barang, CRUD & Pencarian Pelanggan | `Barang.java`, `Pelanggan.java`, `FormBarang.java`, `FormPelanggan.java` |
| **C** | Transaksi Penjualan, Laporan Penjualan, Exception Handling | `Transaksi.java`, `StokTidakCukupException.java`, `FormTransaksi.java`, `FormLaporan.java` |

Database (`db_penjualan.sql` — stored procedure, function, trigger, view) bisa dikerjakan bareng-bareng atau oleh 1 orang yang paling available, karena itu terpisah dari coding Java.

## 7. Konsep PBO yang Sudah Diterapkan

- **Class & Object**: semua di package `model`
- **Enkapsulasi**: atribut private + getter/setter di semua model
- **Inheritance**: `User` dan `Pelanggan` extends `Person`
- **Polimorfisme**: method `getInfo()` di-override berbeda-beda di `User` dan `Pelanggan`
- **Package**: `koneksi`, `model`, `exception`, `gui`
- **Exception Handling**: `try-catch` di semua akses database + custom exception `StokTidakCukupException`
