import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;

class Editor extends JFrame implements ActionListener {
    JTextPane textPane;
    JFrame f;

    Editor() {
        f = new JFrame("Editor");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }

        textPane = new JTextPane();

        JMenuBar mb = new JMenuBar();

        JMenu m1 = new JMenu("File");
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);

        JMenu m2 = new JMenu("Edit");
        JMenuItem mi4 = new JMenuItem("cut");
        JMenuItem mi5 = new JMenuItem("copy");
        JMenuItem mi6 = new JMenuItem("paste");

        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

        JMenu m3 = new JMenu("Format");
        JMenuItem mi7 = new JMenuItem("Bold");
        JMenuItem mi8 = new JMenuItem("Italic");
        JMenuItem mi10 = new JMenuItem("Underline");
        JMenuItem mi11 = new JMenuItem("Font Size");
        JMenuItem mi12 = new JMenuItem("Font Color");

        mi7.addActionListener(this);
        mi8.addActionListener(this);
        mi10.addActionListener(this);
        mi11.addActionListener(this);
        mi12.addActionListener(this);

        m3.add(mi7);
        m3.add(mi8);
        m3.add(mi10);
        m3.add(mi11);
        m3.add(mi12);

        JMenuItem mc = new JMenuItem("close");
        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(mc);

        f.setJMenuBar(mb);
        f.add(new JScrollPane(textPane));
        f.setSize(500, 500);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("cut")) {
            textPane.cut();
        } else if (s.equals("copy")) {
            textPane.copy();
        } else if (s.equals("paste")) {
            textPane.paste();
        } else if (s.equals("Save")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(fi, false);
                    BufferedWriter w = new BufferedWriter(wr);
                    w.write(textPane.getText());
                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("Print")) {
            try {
                textPane.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(f, evt.getMessage());
            }
        } else if (s.equals("Open")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);
                    sl = br.readLine();

                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    textPane.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(f, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(f, "the user cancelled the operation");
        } else if (s.equals("New")) {
            textPane.setText("");
        } else if (s.equals("close")) {
            f.setVisible(false);
        } else if (s.equals("Bold")) {
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet bold = new SimpleAttributeSet();
            StyleConstants.setBold(bold, true);
            textPane.setCharacterAttributes(bold, false);
        } else if (s.equals("Italic")) {
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet italic = new SimpleAttributeSet();
            StyleConstants.setItalic(italic, true);
            textPane.setCharacterAttributes(italic, false);
        } else if (s.equals("Underline")) {
            StyledDocument doc = textPane.getStyledDocument();
            SimpleAttributeSet underline = new SimpleAttributeSet();
            StyleConstants.setUnderline(underline, true);
            textPane.setCharacterAttributes(underline, false);
        } else if (s.equals("Font Size")) {
            String size = JOptionPane.showInputDialog(f, "Enter font size:", "12");
            if (size != null) {
                int fontSize = Integer.parseInt(size);
                StyledDocument doc = textPane.getStyledDocument();
                SimpleAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setFontSize(attr, fontSize);
                textPane.setCharacterAttributes(attr, false);
            }
        } else if (s.equals("Font Color")) {
            Color color = JColorChooser.showDialog(f, "Choose Font Color", Color.BLACK);
            if (color != null) {
                StyledDocument doc = textPane.getStyledDocument();
                SimpleAttributeSet attr = new SimpleAttributeSet();
                StyleConstants.setForeground(attr, color);
                textPane.setCharacterAttributes(attr, false);
            }
        }
    }

    public static void main(String args[]) {
        new Editor();
    }
}
