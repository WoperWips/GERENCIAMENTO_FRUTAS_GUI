package scr.lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Classe principal para gerenciar frutas usando uma interface gráfica.
 */
public class FrutaManagerGUI {
    private ArrayList<String> frutas; // Lista para armazenar as frutas
    private DefaultListModel<String> listModel; // Modelo de dados para a lista de frutas na GUI
    private JList<String> list; // Componente gráfico para exibir a lista de frutas

    /**
     * Construtor que configura a interface gráfica e inicializa os componentes.
     */
    public FrutaManagerGUI() {
        frutas = new ArrayList<>(); // Inicializa a lista de frutas
        listModel = new DefaultListModel<>(); // Inicializa o modelo da lista

        // Configura a janela principal da interface gráfica
        JFrame frame = new JFrame("Gerenciador de Frutas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());

        // Painel para conter os botões e campo de entrada de texto
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Campo de texto para entrada de frutas
        JTextField frutaField = new JTextField(15);
        panel.add(frutaField);

        // Botão para adicionar frutas
        JButton addButton = new JButton("Adicionar");
        panel.add(addButton);

        // Botão para modificar frutas, inicialmente desativado
        JButton modifyButton = new JButton("Modificar");
        modifyButton.setEnabled(false);
        panel.add(modifyButton);

        // Botão para remover frutas, inicialmente desativado
        JButton removeButton = new JButton("Remover");
        removeButton.setEnabled(false);
        panel.add(removeButton);

        // Adiciona o painel de entrada e botões ao topo da janela
        frame.add(panel, BorderLayout.NORTH);

        // Componente de lista para exibir as frutas
        list = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(list); // Componente de rolagem para a lista
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botão para listar todas as frutas
        JButton listButton = new JButton("Listar Frutas");
        frame.add(listButton, BorderLayout.SOUTH);

        // Listener para o botão "Adicionar" que adiciona frutas à lista
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fruta = frutaField.getText(); // Obtém o texto inserido
                if (!fruta.isEmpty()) {
                    frutas.add(fruta); // Adiciona a fruta à lista de frutas
                    listModel.addElement(fruta); // Adiciona a fruta ao modelo da lista
                    frutaField.setText(""); // Limpa o campo de entrada
                    JOptionPane.showMessageDialog(frame, fruta + " foi adicionada");
                }
            }
        });

        // Listener para o botão "Modificar" que modifica uma fruta selecionada
        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Índice da fruta selecionada
                if (selectedIndex != -1) {
                    String frutaSelecionada = listModel.getElementAt(selectedIndex); // Obtém a fruta selecionada
                    // Solicita um novo nome para a fruta
                    String novaFruta = JOptionPane.showInputDialog(frame, "Modificar " + frutaSelecionada + " para:", frutaSelecionada);
                    if (novaFruta != null && !novaFruta.isEmpty()) {
                        frutas.set(selectedIndex, novaFruta); // Atualiza a lista de frutas
                        listModel.set(selectedIndex, novaFruta); // Atualiza o modelo da lista
                        JOptionPane.showMessageDialog(frame, "Fruta " + frutaSelecionada + " foi modificada para " + novaFruta);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para modificar.");
                }
            }
        });

        // Listener para o botão "Remover" que remove uma fruta selecionada
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex(); // Índice da fruta selecionada
                if (selectedIndex != -1) {
                    String frutaRemovida = frutas.remove(selectedIndex); // Remove da lista de frutas
                    listModel.remove(selectedIndex); // Remove do modelo da lista
                    JOptionPane.showMessageDialog(frame, frutaRemovida + " foi removida.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione uma fruta para remover.");
                }
            }
        });

        // Listener para o botão "Listar Frutas" que exibe todas as frutas
        listButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (frutas.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nenhuma fruta na lista.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Frutas: " + frutas);
                }
            }
        });

        // Listener de seleção de item para habilitar/desabilitar botões
        list.addListSelectionListener(e -> {
            boolean selectionExists = !list.isSelectionEmpty();
            removeButton.setEnabled(selectionExists);
            modifyButton.setEnabled(selectionExists);
        });

        // Torna a janela visível
        frame.setVisible(true);
    }

    /**
     * Método main para executar FrutaManagerGUI isoladamente.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrutaManagerGUI());
    }
}
