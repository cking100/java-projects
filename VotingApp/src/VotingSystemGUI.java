import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VotingSystemGUI extends JFrame {
    private VotingSystem votingSystem;
    private JPanel votingPanel;

    public VotingSystemGUI() {
        votingSystem = new VotingSystem();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Voting System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Voting System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Candidate"));
        
        JLabel nameLabel = new JLabel("Candidate Name:");
        JTextField nameField = new JTextField(15);
        JLabel partyLabel = new JLabel("Party:");
        JTextField partyField = new JTextField(15);
        JButton addButton = new JButton("Add Candidate");

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(partyLabel, gbc);
        gbc.gridx = 1;
        inputPanel.add(partyField, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        inputPanel.add(addButton, gbc);

        add(inputPanel, BorderLayout.WEST);

    
        votingPanel = new JPanel();
        votingPanel.setLayout(new BoxLayout(votingPanel, BoxLayout.Y_AXIS));
        votingPanel.setBorder(BorderFactory.createTitledBorder("Vote for Candidates"));
        add(votingPanel, BorderLayout.CENTER);


        JButton resultsButton = new JButton("Show Results");
        resultsButton.setFont(new Font("Arial", Font.BOLD, 16));
        resultsButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(resultsButton, BorderLayout.SOUTH);

    
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String party = partyField.getText();
                
                if (!name.isEmpty() && !party.isEmpty()) {
                    votingSystem.addCandidate(new Candidate(name, party));
                    updateVotingPanel();
                    nameField.setText("");
                    partyField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter both name and party.");
                }
            }
        });

        resultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder results = new StringBuilder();
                results.append("<html><body><h2>Voting Results:</h2>");
                for (Candidate candidate : votingSystem.getCandidates()) {
                    results.append("<p>").append(candidate.getName())
                           .append(" (").append(candidate.getParty())
                           .append("): ").append(candidate.getVotes())
                           .append(" votes</p>");
                }
                results.append("</body></html>");
                JOptionPane.showMessageDialog(null, results.toString());
            }
        });

        updateVotingPanel();
    }

    private void updateVotingPanel() {
        votingPanel.removeAll();
        for (Candidate candidate : votingSystem.getCandidates()) {
            JButton voteButton = new JButton("Vote for " + candidate.getName() + " (" + candidate.getParty() + ")");
            voteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            voteButton.setFont(new Font("Arial", Font.PLAIN, 14));
            voteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    votingSystem.voteForCandidate(candidate.getName());
                    JOptionPane.showMessageDialog(null, "You voted for " + candidate.getName());
                }
            });
            votingPanel.add(voteButton);
        }
        votingPanel.revalidate();
        votingPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VotingSystemGUI().setVisible(true);
            }
        });
    }
}
