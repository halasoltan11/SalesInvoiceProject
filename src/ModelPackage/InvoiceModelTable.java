/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelPackage;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author halas
 */
public class InvoiceModelTable extends AbstractTableModel{
    private ArrayList<HeaderInvoice> invoices;
    private String[] columns = {"No.", "Date", "Customer", "Total"};
 
    public InvoiceModelTable(ArrayList<HeaderInvoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public int getRowCount() {
        return  invoices.size();
    }

    @Override
    public int getColumnCount() {
     return  columns.length;
    }
    
    @Override
     public String getColumnName(int column) {
        return columns[column];
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      HeaderInvoice invoice = invoices.get(rowIndex);
        
        switch (columnIndex) {
            case 0: return invoice.getNum();
            case 1: return invoice.getDate();
            case 2: return invoice.getName();
            case 3: return invoice.getTotal();
            default : return "";
        }   
    }
    
}
