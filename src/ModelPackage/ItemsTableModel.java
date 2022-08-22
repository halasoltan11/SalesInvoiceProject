/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelPackage;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class ItemsTableModel extends AbstractTableModel{
private ArrayList<Item> items ;
private String[] columns = {"No.", "Item Name", "Item Price", "Item Count" , "Item Total"};

    public ItemsTableModel(ArrayList<Item> items) {
        this.items = items;
    }

    public ArrayList<Item> getItems() {
        return items;
    }



    @Override
    public int getRowCount() {
          return  items.size();
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
       Item item = items.get(rowIndex);
       switch (columnIndex){
           case 0: return item.getInv();
           case 1: return item.getName();
           case 2: return item.getPrice();
           case 3: return item.getTotal();
           default:return "";
       }
    }
    
}
