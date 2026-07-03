import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataMahasiswa extends JFrame {

    private JTextField txtNim;
    private JTextField txtNama;
    private JTextField txtProdi;
    private JTextArea txtOutput;
    private JButton btnTampilkan;
    private JButton btnReset;

    public BiodataMahasiswa() {
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // ===== Panel Input Data =====
        JPanel panelInput = new JPanel();
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));
        panelInput.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label & Field NIM
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panelInput.add(new JLabel("NIM"), gbc);

        txtNim = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1;
        panelInput.add(txtNim, gbc);

        // Label & Field Nama
        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0;
        panelInput.add(new JLabel("Nama"), gbc);

        txtNama = new JTextField();
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1;
        panelInput.add(txtNama, gbc);

        // Label & Field Program Studi
        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0;
        panelInput.add(new JLabel("Program Studi"), gbc);

        txtProdi = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2; gbc.weightx = 1;
        panelInput.add(txtProdi, gbc);

        add(panelInput, BorderLayout.NORTH);

        // ===== Panel Tombol =====
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");

        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // ===== Panel Output =====
        JPanel panelOutput = new JPanel(new BorderLayout());
        panelOutput.setBorder(BorderFactory.createTitledBorder("Output"));

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        txtOutput.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(txtOutput);
        panelOutput.add(scrollPane, BorderLayout.CENTER);

        // Gabungkan panel tombol dan output
        JPanel panelTengahBawah = new JPanel(new BorderLayout());
        panelTengahBawah.add(panelTombol, BorderLayout.NORTH);
        panelTengahBawah.add(panelOutput, BorderLayout.CENTER);

        add(panelTengahBawah, BorderLayout.CENTER);

        // ===== Aksi Tombol Tampilkan =====
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = txtNim.getText().trim();
                String nama = txtNama.getText().trim();
                String prodi = txtProdi.getText().trim();

                StringBuilder sb = new StringBuilder();
                sb.append("========== BIODATA MAHASISWA ==========\n\n\n");
                sb.append(String.format("%-13s: %s\n", "NIM", nim));
                sb.append(String.format("%-13s: %s\n", "Nama", nama));
                sb.append(String.format("%-13s: %s\n", "Program Studi", prodi));

                txtOutput.setText(sb.toString());
            }
        });

        // ===== Aksi Tombol Reset =====
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNim.setText("");
                txtNama.setText("");
                txtProdi.setText("");
                txtOutput.setText("");
                txtNim.requestFocus();
            }
        });
    }

    public static void main(String[] args) {
        // Menjalankan aplikasi di Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BiodataMahasiswa().setVisible(true);
            }
        });
    }
}