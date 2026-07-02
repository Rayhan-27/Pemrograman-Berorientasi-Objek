import java.awt.*;
import javax.swing.*;

public class MyForm3 extends JFrame {
    JButton b1 = new JButton("Tombol 1"); // membuat tombol baru
    JButton b2 = new JButton("Tombol 2");
    JButton b3 = new JButton("Tombol 3");
    JButton b4 = new JButton("Tombol 4");
    JButton b5 = new JButton("Tombol 5");

    MyForm3() {
        super("Belajar GUI");
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.PINK);
        setLocationRelativeTo(null);
        BorderLayout BL = new BorderLayout(5, 5); // membuat objek BorderLayout dengan jarak 5
        setLayout(BL);
        add("North", b1); // posisi tombol 1 di atas
        add("South", b2); // posisi tombol 2 di bawah
        add("East", b3); // posisi tombol 3 di kanan
        add("West", b4); // posisi tombol 4 di kiri
        add("Center", b5); // posisi tombol 5 di tengah
        setVisible(true);

    }

    public static void main(String[] args) {
        MyForm3 form = new MyForm3();
    }
}