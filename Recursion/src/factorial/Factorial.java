package factorial;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Factorial extends JPanel {

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    private static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);
    private static final JTextArea line1 = new JTextArea("public void factorial(int n) {", 1, 30);
    private static final JTextArea line2 = new JTextArea("    if(n <= 1) {", 1, 15);
    private static final JTextArea line3 = new JTextArea("        return 1;", 1, 15);
    private static final JTextArea line4 = new JTextArea("    } else {", 1, 15);
    private static final JTextArea line5 = new JTextArea("        int subFactorial = factorial(n-1);", 1, 15);
    private static final JTextArea line6 = new JTextArea("        return n * subFactorial;", 1, 15);
    private static final JTextArea line7 = new JTextArea("    }", 1, 15);
    private static final JTextArea line8 = new JTextArea("}", 1, 15);
    private static final JTextArea empty = new JTextArea(17, 1);
    private static final JTextArea[] code = {line1, line2, line3, line4, line5, line6, line7, line8};
    
    private JPanel _box = new JPanel();
    private JPanel _card = new JPanel();
    private JPanel _flow = new JPanel();
    private JTextField input = new JTextField(5);
    private JButton start = new JButton("Start");
    private JButton next = new JButton("Next");
    private JButton previous = new JButton("Previous");
    private BorderLayout border = new BorderLayout();
    private JFrame frame;
    private int[] index;
    private boolean returning = false;
    private int lineIndex = 0;
    private int step = 1;
    private int n;

    public Factorial() {
        init();
    }

    public void init() {
        input.setFont(new Font("Consolas", Font.PLAIN, 18));
        input.setOpaque(false);
        previous.setEnabled(false);
        next.setEnabled(false);

        _flow.add(input);
        _flow.add(start);
        _flow.add(previous);
        _flow.add(next);
        _flow.setAlignmentX(RIGHT_ALIGNMENT);
        _flow.setBorder(new EmptyBorder(25, 20, 20, 0));
        _flow.setBackground(Color.white);

        _box.setLayout(new BoxLayout(_box, BoxLayout.Y_AXIS));
        _box.setBackground(Color.white);
        _box.add(_flow);

        for (int i = 0; i < code.length; i++) {
            code[i].setFont(new Font("Consolas", Font.BOLD, 24));
            code[i].setEditable(false);
            code[i].setLineWrap(true);
            code[i].setOpaque(false);
            code[i].setBorder(new EmptyBorder(0, 10, 0, 0));
            _box.add(code[i]);
        }
        empty.setOpaque(false);
        empty.setEditable(false);
        empty.setLineWrap(true);
        _box.add(empty);

        _card.setLayout(new CardLayout());
        _card.setBackground(Color.white);
        _card.setBorder(new EmptyBorder(10, 0, 10, 20));

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    n = Integer.parseInt(input.getText());
                    start.setEnabled(false);
                    input.setEnabled(false);
                    previous.setEnabled(true);
                    next.setEnabled(true);
                    if (n > 20) {
                        n = 20;
                        input.setText("20");
                    }
                    index = new int[(n * 4) + (3 * ((2 * n) - 2))];
                    for (int i = 0; i < n; i++) {
                        if (i == n - 1) {
                            index[i * 4] = 0;
                            index[i * 4 + 1] = 1;
                            index[i * 4 + 2] = 2;
                            index[i * 4 + 3] = -1;
                        } else {
                            index[i * 4] = 0;
                            index[i * 4 + 1] = 1;
                            index[i * 4 + 2] = 3;
                            index[i * 4 + 3] = 4;
                        }
                    }
                    for (int i = 0; i < ((2 * n) - 2); i++) {
                        index[i * 3 + 4 * n] = 4;
                        index[i * 3 + 4 * n + 1] = 5;
                        index[i * 3 + 4 * n + 2] = -1;
                    }
                    for (int i = 0; i < (n * 3 - 1); i++) {
                        FactorialOutput add = new FactorialOutput(i + 1, Integer.parseInt(input.getText()), 0, 0);
                        _card.add(add, Integer.toString(i));
                    }
                    code[index[lineIndex]].setForeground(Color.red);
                    frame.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Input Error");
                    input.setText("");
                }
            }
        });

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineIndex++;
                if (lineIndex >= (n * 4) + (2 * ((2 * n) - 2)) - (n - 1)) {
                    lineIndex--;
                    reset();
                } else {
                    if (index[lineIndex] == -1 && index[lineIndex - 1] == 2) {
                        returning = true;
                    }
                    if (!returning && index[lineIndex] == 0) {
                        show();
                    }
                    if (returning && (index[lineIndex - 1] == 2 || index[lineIndex - 1] == 5 || index[lineIndex - 1] == 4)) {
                        show();
                    }
                    for (int i = 0; i < code.length; i++) {
                        if (i == index[lineIndex]) {
                            code[i].setForeground(Color.red);
                        } else {
                            code[i].setForeground(Color.black);
                        }
                    }
                    frame.setVisible(true);
                }
            }
        });

        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lineIndex--;
                if (lineIndex < 0) {
                    lineIndex = 0;
                }
                if (index[lineIndex] == 2 && index[lineIndex + 1] == -1) {
                    returning = false;
                }
                if (!returning && (index[lineIndex] == 4 || index[lineIndex] == 2)) {
                    step -= 2;
                    show();
                }
                if (returning && (index[lineIndex + 1] == -1 || index[lineIndex + 1] == 5)) {
                    step -= 2;
                    show();
                }
                for (int i = 0; i < code.length; i++) {
                    if (i == index[lineIndex]) {
                        code[i].setForeground(Color.red);
                    } else {
                        code[i].setForeground(Color.black);
                    }
                }
            }
        });

        frame = new JFrame("Factorial Recursion");
        frame.setMaximumSize(SIZE);
        frame.setMinimumSize(SIZE);
        frame.setPreferredSize(SIZE);

        frame.setLayout(border);
        frame.add(_box, BorderLayout.WEST);
        frame.add(_card, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void show() {
        CardLayout c = (CardLayout) _card.getLayout();
        c.show(_card, Integer.toString(step));
        step++;
    }

    public void reset() {
        if (JOptionPane.showConfirmDialog(frame, "Reset?") == 0) {
            input.setText("");
            input.setEnabled(true);
            start.setEnabled(true);
            step = 1;
            lineIndex = 0;
            returning = false;
            _card.removeAll();
        }
    }

    public static void main(String[] args) {
        new Factorial();
    }
}