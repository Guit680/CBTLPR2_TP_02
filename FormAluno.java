import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FormAluno extends JFrame implements ActionListener {

    private JLabel    lblNome      = new JLabel("Nome:");
    private JLabel    lblIdade     = new JLabel("Idade:");
    private JLabel    lblEndereco  = new JLabel("Endereço:");

    private JTextField txtNome     = new JTextField(15);
    private JTextField txtIdade    = new JTextField(15);
    private JTextField txtEndereco = new JTextField(15);

    private JButton btnOk      = new JButton("Ok");
    private JButton btnLimpar  = new JButton("Limpar");
    private JButton btnMostrar = new JButton("Mostrar");
    private JButton btnSair    = new JButton("Sair");

    private List<Aluno> listaAlunos = new ArrayList<>();

    public FormAluno() {
        super("TP02 - LP2I4");
        setSize(400, 180);
        setLocation(50, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel(new GridLayout(3, 2, 10, 10));
        painelSuperior.add(lblNome);
        painelSuperior.add(txtNome);
        painelSuperior.add(lblIdade);
        painelSuperior.add(txtIdade);
        painelSuperior.add(lblEndereco);
        painelSuperior.add(txtEndereco);

        JPanel painelInferior = new JPanel(new GridLayout(1, 4));
        painelInferior.add(btnOk);
        painelInferior.add(btnLimpar);
        painelInferior.add(btnMostrar);
        painelInferior.add(btnSair);

        btnOk.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnSair.addActionListener(this);

        add(painelSuperior, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnOk) {
            try {
                if (txtNome.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                        "O campo Nome não pode ser vazio.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Aluno aluno = new Aluno();
                aluno.setNome(txtNome.getText().trim());
                aluno.setIdade(Integer.parseInt(txtIdade.getText().trim()));
                aluno.setEndereco(txtEndereco.getText().trim());
                aluno.setUuid(UUID.randomUUID());

                listaAlunos.add(aluno);

                JOptionPane.showMessageDialog(this,
                    "Aluno \"" + aluno.getNome() + "\" cadastrado com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                limparCampos();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Idade inválida! Digite um número inteiro.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnLimpar) {
            limparCampos();

        } else if (e.getSource() == btnMostrar) {
            mostrarAlunos();

        } else if (e.getSource() == btnSair) {
            System.exit(0);
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        txtEndereco.setText("");
    }

    private void mostrarAlunos() {
        if (listaAlunos.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nenhum aluno cadastrado.",
                "Mensagem", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder mensagem = new StringBuilder("Resultado\n");
        for (Aluno aluno : listaAlunos) {
            mensagem.append("Id: ")
                    .append(aluno.getUuid())
                    .append("  Nome: ")
                    .append(aluno.getNome())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(this, mensagem.toString(),
            "Mensagem", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new FormAluno();
    }
}
