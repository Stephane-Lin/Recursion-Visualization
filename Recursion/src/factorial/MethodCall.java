package factorial;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MethodCall extends JComponent {

    private JTextArea line1 = new JTextArea();
    private JTextArea line2 = new JTextArea();
    private JTextArea line3 = new JTextArea();
    private JTextArea line4 = new JTextArea();
    private JPanel _methodCall = new JPanel();
    public JTextArea[] call = {line1, line2, line3, line4};

    public MethodCall(String s1, String s2, String s3, String s4, Color c) {
        call[0].setText(s1);
        call[1].setText(s2);
        call[2].setText(s3);
        call[3].setText(s4);

        _methodCall.setLayout(new BoxLayout(_methodCall, BoxLayout.Y_AXIS));

        for (int i = 0; i < call.length; i++) {
            call[i].setLineWrap(true);
            call[i].setFont(new Font("Consolas", Font.PLAIN, 18));
            call[i].setForeground(c);
            _methodCall.add(call[i]);
        }
    }

    public JPanel getMethodCall() {
        return _methodCall;
    }
}