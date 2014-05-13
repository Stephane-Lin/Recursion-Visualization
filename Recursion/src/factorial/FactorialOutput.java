package factorial;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class FactorialOutput extends JPanel {

    private int offset = 0;
    private int maxSteps;
    private long subFactorial = 1;
    private int x, y;

    public FactorialOutput(int currentStep, int n, int x, int y) {
        this.x = x;
        this.y = y;
        this.maxSteps = n * 3 - 1;
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.white);
        if (currentStep <= n) {
            for (int i = 0; i < 20; i++) {
                if (i < currentStep) {
                    add(new MethodCall("Method Call", "             n : " + n,
                            "Factorial(n-1) : ", "  Factorial(n) : ", Color.black).getMethodCall());
                    n--;
                } else {
                    add(new MethodCall("", "", "", "", Color.white).getMethodCall());
                }
            }
        } else {
            if (currentStep % 2 == ((n + 1) % 2)) {
                for (int j = 0; j < n; j++) {
                    if (currentStep == maxSteps) {
                        break;
                    } else {
                        maxSteps -= 2;
                        offset++;
                    }
                }
                for (int i = 0; i < 20; i++) {
                    if (i < offset) {
                        add(new MethodCall("Method Call", "             n : " + n,
                                "Factorial(n-1) : ", "  Factorial(n) : ", Color.black).getMethodCall());
                        n--;
                    } else if (i == offset) {
                        while (n != 1) {
                            subFactorial *= n;
                            n--;
                        }
                        add(new MethodCall("Return Value : " + subFactorial,
                                "", "", "", Color.red).getMethodCall());
                    } else {
                        add(new MethodCall("", "", "", "", Color.white)
                                .getMethodCall());
                    }
                }
            } else if (currentStep % 2 == (n % 2)) {
                maxSteps--;
                for (int j = 0; j < n; j++) {
                    if (currentStep == maxSteps) {
                        break;
                    } else {
                        maxSteps -= 2;
                        offset++;
                    }
                }
                for (int i = 0; i < 20; i++) {
                    if (i < offset) {
                        add(new MethodCall("Method Call", "             n : " + n,
                                "Factorial(n-1) : ", "  Factorial(n) : ", Color.black).getMethodCall());
                        n--;
                    } else if (i == offset) {
                        int temp = n - 1;
                        while (temp != 1) {
                            subFactorial *= temp;
                            temp--;
                        }
                        add(new MethodCall("Method Call", "             n : " + n,
                                "Factorial(n-1) : " + subFactorial, "  Factorial(n) : " + n * subFactorial, Color.black)
                                .getMethodCall());
                    } else {
                        add(new MethodCall("", "", "", "", Color.white)
                                .getMethodCall());
                    }
                }
            }
        }
    }

    public void add(JComponent toAdd) {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        if (y > 6) {
            y = 0;
            x++;
        }
        c.gridx = x;
        c.gridy = y;
        c.weightx = 0.5;
        c.weighty = 1;
        c.insets = new Insets(0, 0, 0, 5);
        y++;
        this.add(toAdd, c);
    }
}