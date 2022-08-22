/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewPackage;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author halas
 */
public class ItemsForm extends JDialog{
    
    private JTextField itemNameTF;
    private JTextField itemCountTF;
    private JTextField itemPriceTF;
    private JLabel itemNameLb;
    private JLabel itemCountLb;
    private JLabel itemPriceLb;
    private JButton okBtn1;
    private JButton cancelBtn;
    
    
    public ItemsForm (DesignFrame frame)
    {
        itemNameLb =new JLabel("Item Name");
        itemNameTF = new JTextField(20);
        
        itemCountLb =new JLabel("Item Count");
        itemCountTF = new JTextField(20);
        
         itemPriceLb =new JLabel("Item price");
        itemPriceTF = new JTextField(20);
        
        
        okBtn1 = new JButton("Add");
        cancelBtn = new JButton("Cancel");

        
        
         okBtn1.setActionCommand("OK for Adding Item");
        cancelBtn.setActionCommand("Cancel New Item");
        
        okBtn1.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        
       setLayout(new GridLayout(4,2));
       
       
       add(itemNameLb);
       add(itemNameTF);
       add(itemCountLb);
       add(itemCountTF);
       add(itemPriceLb);
       add(itemPriceTF);
       add(okBtn1);
       add(cancelBtn);
       pack();
        
    }

    public JTextField getItemNameTF() {
        return itemNameTF;
    }

    public JTextField getItemCountTF() {
        return itemCountTF;
    }

    public JTextField getItemPriceTF() {
        return itemPriceTF;
    }
    
    
}
