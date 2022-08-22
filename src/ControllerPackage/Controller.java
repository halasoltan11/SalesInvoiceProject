/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControllerPackage;

import ModelPackage.HeaderInvoice;
import ModelPackage.InvoiceModelTable;
import ModelPackage.Item;
import ModelPackage.ItemsTableModel;
import ViewPackage.DesignFrame;
import ViewPackage.InvoiceForm;
import ViewPackage.ItemsForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Line;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author halas
 */
public class Controller implements ActionListener , ListSelectionListener{

    
    //privateprivate InvoiceFrame frame;
    
    
    
    private DesignFrame frame;
    private InvoiceForm invoiceForm;
    private ItemsForm itemForm;
    
    public Controller(DesignFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String action = e.getActionCommand();
            System.out.println("Action Is  : " + action);
            switch(action)
            {
                case "Create New Invoice":
                    createInvoice();
                    break;
                case "Delete Invoice":
                    deleteInvoice();
                    break;
                case "New Item":
                    newItem();
                    break;
                case "Delete Item":
                    deleteItem();
                    break;
                case "Load":
                    loadFile();
                    break;
                case "Save":
                    saveFile();
                    break;
                    
                    case "OK for Adding Invoice":
                   AddInvoiceOK();
                    break;
                    
                    case "Cancel New Invoice":
                    CancelInvoice();
                    break;
                             
                    case "OK for Adding Item":
                    AddItemOK();
                    break;
                    
                    case "Cancel New Item":
                    CancelItem();
                    break;
                    
                    
                default:
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void createInvoice() {
       invoiceForm= new InvoiceForm(frame);
        invoiceForm.setVisible(true);
    }

    private void deleteInvoice() {
       int selectedIndexRow = frame.getHeaderTable().getSelectedRow();
       if(selectedIndexRow!=-1)
       {
           frame.getInvoices().remove(selectedIndexRow);
           frame.getInvoicesModelTable().fireTableDataChanged();
           
       }
    }

    private void newItem() {
         itemForm= new ItemsForm(frame);
        itemForm.setVisible(true);
    }

    private void deleteItem() {
      int selectedInvoice = frame.getHeaderTable().getSelectedRow();
        int selectedrow = frame.getItemtable().getSelectedRow();
        if(selectedInvoice!=1 && selectedrow!=-1)
        {
            HeaderInvoice invoices = frame.getInvoices().get(selectedInvoice);
            invoices.getItems().remove(selectedrow);
           ItemsTableModel itemTableModel = new ItemsTableModel(invoices.getItems());
           frame.getItemtable().setModel(itemTableModel);
          itemTableModel.fireTableDataChanged();
         // frame.getInvoicesModelTable().fireTableDataChanged();
        }   
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int selectedIndex = frame.getHeaderTable().getSelectedRow();
        if(selectedIndex!= -1){
        System.out.println("The Row selection is : " + selectedIndex);
        HeaderInvoice currentInvoice = frame.getInvoices().get(selectedIndex);
        frame.getInvNumLb().setText(""+currentInvoice.getNum());
        frame.getInvDateLb().setText(currentInvoice.getDate());
        frame.getClientNameLb().setText(currentInvoice.getName());
        frame.getInvTotalLb().setText(""+currentInvoice.getTotal());
        ItemsTableModel itemTableModel = new ItemsTableModel(currentInvoice.getItems());
        frame.getItemtable().setModel(itemTableModel);
        itemTableModel.fireTableDataChanged();
    }
   }

    private void loadFile() throws IOException {
        JFileChooser fch = new JFileChooser();
       int result = fch.showOpenDialog(frame);
       if(result == JFileChooser.APPROVE_OPTION)
       {
           File headerInvoiceFile = fch.getSelectedFile();
        Path FileHeaderPath = Paths.get(headerInvoiceFile.getAbsolutePath());
           List<String>headerInvoiceLines = Files.readAllLines(FileHeaderPath);
           System.out.println("Invoices are loading");
           ArrayList<HeaderInvoice> arrayInvoices = new ArrayList<>();
         for (String headerInvoiceLine : headerInvoiceLines ) {
             String[] headers =headerInvoiceLine.split(",");
             int invoiceNumber =Integer.parseInt(headers[0]);
             String invDate =headers[1];
             String clientName =headers[2];
             HeaderInvoice invoice = new HeaderInvoice(invoiceNumber,clientName ,invDate);
             arrayInvoices.add(invoice);
         }
          System.out.println("Check point");
                result = fch.showOpenDialog(frame);
                if(result == JFileChooser.APPROVE_OPTION) {
                    File linesFile = fch.getSelectedFile();
                    Path linesPath = Paths.get(linesFile.getAbsolutePath());
                    List<String> lineLines = Files.readAllLines(linesPath);
                    System.out.println("Lines is being read");
                    for (String lineLine : lineLines) {
                        String lineParts[] = lineLine.split(",");
                        int invoiceNum = Integer.parseInt(lineParts[0]);
                        String itemName = lineParts[1];
                        double itemPrice = Double.parseDouble(lineParts[2]);
                        int count = Integer.parseInt(lineParts[3]);
                        HeaderInvoice inv = null;
                        for (HeaderInvoice invoice : arrayInvoices) {
                            if (invoice.getNum() == invoiceNum) {
                                inv = invoice;
                                break;
                            }
                        }
                        
                        Item item = new Item(inv , itemName,  count,itemPrice );
                        inv.getItems().add(item);
                    }
                    System.out.println("Check point");
                }
                frame.setInvoices(arrayInvoices);
                InvoiceModelTable invoicesModelTable = new InvoiceModelTable(arrayInvoices);
                frame.setInvoicesModelTable(invoicesModelTable);
                frame.getHeaderTable().setModel(invoicesModelTable);
                frame.getInvoicesModelTable().fireTableDataChanged();
            }    
        }  


        
    

    private void saveFile() {
        
         ArrayList<HeaderInvoice> invoices = frame.getInvoices();
        String headers = "";
        String lines = "";
        for (HeaderInvoice invoice : invoices) {
            String invoiceCSV = invoice.getAsCSVFormat();
            headers += invoiceCSV;
            headers += "\n";

            for (Item item : invoice.getItems()) {
                String lineCSV = item.getAsCSVFormat();
                lines += lineCSV;
                lines += "\n";
            }
        }
        
        System.out.println("Check point");
        try {
            JFileChooser fch = new JFileChooser();
            int result = fch.showSaveDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = fch.getSelectedFile();
                FileWriter headerFwriter = new FileWriter(headerFile);
                headerFwriter.write(headers);
                headerFwriter.flush();
                headerFwriter.close();
                result = fch.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File ItemFile = fch.getSelectedFile();
                    FileWriter itemFwriter = new FileWriter(ItemFile);
                    itemFwriter.write(lines);
                    itemFwriter.flush();
                    itemFwriter.close();
                }
            }
        } catch (Exception ex) {

        }
        
        
        
    }

    private void AddInvoiceOK() {
        String date  = invoiceForm.getInvDateTF().getText();
        String cuctomer = invoiceForm.getCustNameTF().getText();
        int num = frame.getInvoiceNum();
        try{
          String [] dateValues = date.split("-");
          if(dateValues.length < 3){
               JOptionPane.showMessageDialog(frame, "Invalid Date format", "Error", JOptionPane.ERROR_MESSAGE);
          }else{
              int day = Integer.parseInt(dateValues[0]);
              int month = Integer.parseInt(dateValues[1]);
              int year = Integer.parseInt(dateValues[2]);
              if(day > 31 || month > 12){
           JOptionPane.showMessageDialog(frame, "Invalid Date format", "Error", JOptionPane.ERROR_MESSAGE);
              }else{
            HeaderInvoice invoices = new HeaderInvoice(num, cuctomer, date);
            frame.getInvoices().add(invoices);
            frame.getInvoicesModelTable().fireTableDataChanged();
            invoiceForm.setVisible(false);
            invoiceForm.dispose();
            invoiceForm=null;
          }
        }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(frame, "Invalid Date format", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void CancelInvoice() {
        invoiceForm.setVisible(false);
        invoiceForm.dispose();
        invoiceForm=null;
    }

    private void AddItemOK() {
        
        String item = itemForm.getItemNameTF().getText();
        String countStr = itemForm.getItemCountTF().getText();
        String priceStr = itemForm.getItemPriceTF().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = frame.getHeaderTable().getSelectedRow();
        if (selectedInvoice != -1) {
            HeaderInvoice invoice = frame.getInvoices().get(selectedInvoice);
            Item items = new Item(  invoice,item, count,price);
            invoice.getItems().add(items);
            ItemsTableModel itemsTableModel = (ItemsTableModel) frame.getItemtable().getModel();
            //linesTableModel.getLines().add(line);
            itemsTableModel.fireTableDataChanged();
            frame.getInvoicesModelTable().fireTableDataChanged();
        }
                
        /*String itemName = itemForm.getItemNameTF().getText();
        int count = Integer.parseInt( itemForm.getItemCountTF().getText());
        double price = Double.parseDouble(itemForm.getItemPriceTF().getText());
        
        int selectedInvoice = frame.getHeaderTable().getSelectedRow();
        if (selectedInvoice != -1) {
            HeaderInvoice invoice = frame.getInvoices().get(selectedInvoice);
            Item item = new Item(invoice,itemName,count,price );             
            invoice.getItems().add(item);
            ItemsTableModel itemTableModel =(ItemsTableModel)frame.getItemtable().getModel();
            //linesTableModel.getLines().add(line);
            itemTableModel.fireTableDataChanged();
            frame.getInvoicesModelTable().fireTableDataChanged();
        }*/
             
        itemForm.setVisible(false);
        itemForm.dispose();
        itemForm = null;
    }

    private void CancelItem() {
        itemForm.setVisible(false);
        itemForm.dispose();
        itemForm = null;
    }

    


    
}
