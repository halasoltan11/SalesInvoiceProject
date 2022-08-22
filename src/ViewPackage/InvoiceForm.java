/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewPackage;

import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author halas
 */
public class InvoiceForm extends JDialog{

    private JTextField invDateTF;
    private JTextField custNameTF;
    private JLabel invDateLb;
    private JLabel custNameLb;
    private JButton okBtn;
    private JButton cancelBtn;
    
    
    public InvoiceForm(DesignFrame frame) {
        custNameLb = new JLabel("Customer Name");
        custNameTF = new JTextField(20);
        
        invDateLb = new JLabel("Invoice Date");
        invDateTF = new JTextField(20);
        
        okBtn =new JButton("Ok");
        cancelBtn =new JButton("Cancel");
        
        okBtn.setActionCommand("OK for Adding Invoice");
        cancelBtn.setActionCommand("Cancel New Invoice");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        
       setLayout(new GridLayout(3,2));
       
       add(custNameLb);
       add(custNameTF);
       add(invDateLb);
       add(invDateTF);
       add(okBtn);
       add(cancelBtn);
       pack();
       
    }

    public JTextField getInvDateTF() {
        return invDateTF;
    }

    public JTextField getCustNameTF() {
        return custNameTF;
    }

   
    
    
}
