import java.awt.* ;
import javax.swing.*;

public class MyForm extends JFrame {
    MyForm() {
        super("Belajar GUI"); // judul
        setSize(600, 500); // ukuran popup GUI
        setDefaultCloseOperation(EXIT_ON_CLOSE); // operasi tutup
        getContentPane().setBackground(Color.PINK); // warna latar belakang
        setLocationRelativeTo(null); // posisi di tengah layar
        setVisible(true);
    }

    public static void main(String[] args) {
        MyForm form = new MyForm();
    }
}