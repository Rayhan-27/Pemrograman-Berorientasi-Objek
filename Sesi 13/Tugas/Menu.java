import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {

    static final String jdbc = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost/toko_retail";
    static final String username = "root";
    static final String password = "";

    static Connection con;
    static PreparedStatement ps;
    static ResultSet rs;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Class.forName(jdbc);
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        boolean jalan = true;

        while (jalan) {
            tampilkanMenu();
            System.out.print("Pilihan : ");
            String pilihan = sc.nextLine().trim();

            switch (pilihan) {
                case "1":
                    tampilSemuaData();
                    break;
                case "2":
                    tambahData();
                    break;
                case "3":
                    cariData();
                    break;
                case "4":
                    ubahData();
                    break;
                case "5":
                    hapusData();
                    break;
                case "0":
                    jalan = false;
                    System.out.println("Terima kasih, program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.\n");
            }
        }

        sc.close();
    }

    // ================== MENU ==================
    static void tampilkanMenu() {
        System.out.println("+-------------------------+");
        System.out.println("|     MENU TOKO RETAIL     |");
        System.out.println("+-------------------------+");
        System.out.println("| 1. Tampil Semua Data     |");
        System.out.println("| 2. Tambah Data           |");
        System.out.println("| 3. Cari Data             |");
        System.out.println("| 4. Ubah Data             |");
        System.out.println("| 5. Hapus Data            |");
        System.out.println("| 0. Keluar                |");
        System.out.println("+-------------------------+");
    }

    // ================== 1. TAMPIL SEMUA DATA (mirip ShowData.java) ==================
    static void tampilSemuaData() {
        String query = "select * from tbl_barang";

        try {
            con = DriverManager.getConnection(url, username, password);
            Statement state = con.createStatement();
            rs = state.executeQuery(query);

            System.out.println();
            int nomor = 0;

            while (rs.next()) {
                nomor++;
                System.out.println("No           : " + nomor);
                System.out.println("Kode Barang  : " + rs.getString("kode_barang"));
                System.out.println("Nama Barang  : " + rs.getString("nama_barang"));
                System.out.println("Harga        : " + rs.getInt("harga_barang"));
                System.out.println("Stok         : " + rs.getInt("stok_barang"));
                System.out.println("------------------------------------------");
            }

            if (nomor == 0) {
                System.out.println("Data masih kosong.");
            } else {
                System.out.println("Total Barang : " + nomor);
            }
            System.out.println();

            rs.close();
            state.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================== 2. TAMBAH DATA (mirip Insert.java) ==================
    static void tambahData() {
        System.out.println("\n--- Tambah Data Barang ---");

        System.out.print("Kode Barang  : ");
        String kode_barang = sc.nextLine();

        System.out.print("Nama Barang  : ");
        String nama_barang = sc.nextLine();

        System.out.print("Harga Barang : ");
        int harga_barang = Integer.parseInt(sc.nextLine());

        System.out.print("Stok Barang  : ");
        int stok_barang = Integer.parseInt(sc.nextLine());

        String query = "insert into tbl_barang values (?,?,?,?)";

        try {
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);

            ps.setString(1, kode_barang);
            ps.setString(2, nama_barang);
            ps.setInt(3, harga_barang);
            ps.setInt(4, stok_barang);

            if (ps.executeUpdate() > 0) {
                System.out.println("Proses Berhasil\n");
            } else {
                System.out.println("Proses Gagal\n");
            }

            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================== 3. CARI DATA (mirip Search.java) ==================
    static void cariData() {
        System.out.println("\n--- Cari Data Barang ---");

        System.out.print("Cari Nama Barang : ");
        String namaBarang = sc.nextLine();

        String query = "SELECT * FROM tbl_barang WHERE nama_barang LIKE ?";

        try {
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + namaBarang + "%");

            rs = ps.executeQuery();

            int nomor = 0;

            while (rs.next()) {
                nomor++;
                System.out.println("Nomor       : " + nomor);
                System.out.println("Kode Barang : " + rs.getString("kode_barang"));
                System.out.println("Nama Barang : " + rs.getString("nama_barang"));
                System.out.println("Harga       : " + rs.getInt("harga_barang"));
                System.out.println("Stok        : " + rs.getInt("stok_barang"));
                System.out.println("------------------------------------");
            }

            if (nomor == 0) {
                System.out.println("Data tidak ditemukan");
            } else {
                System.out.println("Total Barang : " + nomor);
            }
            System.out.println();

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================== 4. UBAH DATA ==================
    static void ubahData() {
        System.out.println("\n--- Ubah Data Barang ---");

        System.out.print("Isikan Kode Barang yang akan diubah : ");
        String kode_barang = sc.nextLine();

        try {
            con = DriverManager.getConnection(url, username, password);

            // Cek dulu apakah datanya ada
            String cekQuery = "select * from tbl_barang where kode_barang=?";
            ps = con.prepareStatement(cekQuery);
            ps.setString(1, kode_barang);
            rs = ps.executeQuery();

            if (!rs.next()) {
                System.out.println("Data dengan kode " + kode_barang + " tidak ditemukan.\n");
                rs.close();
                ps.close();
                con.close();
                return;
            }

            System.out.println("Data ditemukan -> Nama: " + rs.getString("nama_barang")
                    + ", Harga: " + rs.getInt("harga_barang")
                    + ", Stok: " + rs.getInt("stok_barang"));

            System.out.print("Nama Barang baru  : ");
            String nama_barang = sc.nextLine();

            System.out.print("Harga Barang baru : ");
            int harga_barang = Integer.parseInt(sc.nextLine());

            System.out.print("Stok Barang baru  : ");
            int stok_barang = Integer.parseInt(sc.nextLine());

            String updateQuery = "update tbl_barang set nama_barang=?, harga_barang=?, stok_barang=? where kode_barang=?";
            ps = con.prepareStatement(updateQuery);
            ps.setString(1, nama_barang);
            ps.setInt(2, harga_barang);
            ps.setInt(3, stok_barang);
            ps.setString(4, kode_barang);

            if (ps.executeUpdate() > 0) {
                System.out.println("Proses Berhasil\n");
            } else {
                System.out.println("Proses Gagal\n");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // ================== 5. HAPUS DATA (mirip Delete.java) ==================
    static void hapusData() {
        System.out.println("\n--- Hapus Data Barang ---");

        System.out.print("Isikan Kode Barang yang akan dihapus : ");
        String kode_barang = sc.nextLine();

        String query = "delete from tbl_barang where kode_barang=?";

        try {
            con = DriverManager.getConnection(url, username, password);
            ps = con.prepareStatement(query);

            ps.setString(1, kode_barang);

            if (ps.executeUpdate() > 0) {
                System.out.println("Proses Berhasil\n");
            } else {
                System.out.println("Data Tidak ditemukan\n");
            }

            ps.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

// CARA RUNNING java -cp ".;lib/mysqlconnector.jar" Menu