import javax.swing.JOptionPane;

public class InputBelajar {
    public static void main(String[] args) {

        // Pertanyaan untuk input
        String pelajaran = JOptionPane.showInputDialog("Anda sedang belajar apa?");

        // Hasil pesan input
        JOptionPane.showMessageDialog(null, 
            "Belajar " + pelajaran + " sangat mudah");

    }
}