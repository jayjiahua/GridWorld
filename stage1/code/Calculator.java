import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class Calculator extends JFrame {
    /**
     * simple calculator
     */
    private static final long serialVersionUID = 1L;

    private JTextField left_operand = new JTextField("0");
    private JTextField operator = new JTextField("");
    private JTextField right_operand = new JTextField("0");
    private JTextField equal_operator = new JTextField("=");
    private JTextField result = new JTextField();
    private JButton add_btn = new JButton("+");
    private JButton sub_btn = new JButton("-");
    private JButton mul_btn = new JButton("*");
    private JButton div_btn = new JButton("/");
    private JButton cal_btn = new JButton("OK");
    private JComponent[] components = {left_operand, operator, right_operand, equal_operator, 
                                        result, add_btn, sub_btn, mul_btn, div_btn, cal_btn};
    
    public Calculator() {
        super("My Calculaotor");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Pane's layout
        Container cp = this.getContentPane();

        cp.setLayout(new GridLayout(2, 5, 10, 10));
        
        
        // set display style of the text field
        operator.setEditable(false);
        equal_operator.setEditable(false);
        result.setEditable(false);
        
        left_operand.setHorizontalAlignment(JTextField.CENTER);
        left_operand.setPreferredSize(new Dimension(70, 70));
        operator.setHorizontalAlignment(JTextField.CENTER);
        right_operand.setHorizontalAlignment(JTextField.CENTER);
        equal_operator.setHorizontalAlignment(JTextField.CENTER);
        result.setHorizontalAlignment(JTextField.CENTER);
        
        // add event listener
        add_btn.addActionListener(new OperatorSetter("+"));
        sub_btn.addActionListener(new OperatorSetter("-"));
        mul_btn.addActionListener(new OperatorSetter("*"));
        div_btn.addActionListener(new OperatorSetter("/"));
        cal_btn.addActionListener(new OperatorSetter("="));
        
        left_operand.addKeyListener(new buttonControler());
        right_operand.addKeyListener(new buttonControler());
        
        // add them into content panel
        for (int i = 0 ; i < components.length ; i++) {
            cp.add(components[i]);
        }
        
        left_operand.addKeyListener(new buttonControler());
        
        // show the window
        this.pack();
        this.setVisible(true);

    }
    
    /* to control the "=" button when will enabled/disabled */
    private class buttonControler implements KeyListener 
    {
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            if (left_operand.getText().isEmpty() || right_operand.getText().isEmpty()) {
                cal_btn.setEnabled(false);
            } else {
                cal_btn.setEnabled(true);
            }
        }
        
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            if (left_operand.getText().isEmpty() || right_operand.getText().isEmpty()) {
                cal_btn.setEnabled(false);
            } else {
                cal_btn.setEnabled(true);
            }
        }
        
        public void keyPressed(KeyEvent e) {
            // TODO Auto-generated method stub

        }
    }
    
    /* inner class to handle the button clicking events */
    private class OperatorSetter implements ActionListener
    {
        public OperatorSetter(String op)
        { 
            _operator = op;
        }
    
        /**
         * Actions
         */
        public void actionPerformed(ActionEvent event)
        {
            if (_operator != "=") {
                operator.setText(_operator);
            } else {
                result.setText(getResult());
            }
        }
    
        // pressing the "=" button, then calculate the result
        public String getResult() {
            BigDecimal ans = new BigDecimal(0);
            try {
                BigDecimal left = new BigDecimal(left_operand.getText());
                BigDecimal right = new BigDecimal(right_operand.getText());
                String op = operator.getText();
                switch (op) {
                    case "+":
                        ans = left.add(right);
                        break;
                    case "-":
                        ans = left.subtract(right);
                        break;
                    case "*":
                        ans = left.multiply(right);
                        break;
                    case "/":
                        ans = left.divide(right, 8, BigDecimal.ROUND_HALF_UP);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                // TODO: handle exception if you input invalid operand.
                return "NaN";
            }

            String ans_str = ans.toString();
            // remove the useless "0" at the end of the decimal string
            if(ans_str.indexOf(".") > 0){  
                ans_str = ans_str.replaceAll("0+?$", ""); 
                ans_str = ans_str.replaceAll("[.]$", ""); 
            }
            return ans_str;
        }
        
        private String _operator;
    }
    
    public static void main(String[] args) {
        new Calculator();
    }
}



