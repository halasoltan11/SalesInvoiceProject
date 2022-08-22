/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelPackage;

import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author halas
 */
public class HeaderInvoice {
    private int num ;
    private String name;
    private String date;
    private ArrayList<Item> items;

    public HeaderInvoice(int num, String name, String date) {
        this.num = num;
        this.name = name;
        this.date = date;
    }

  

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal()
  {
      double total = 0.0;
      for(Item item: getItems()){
          total +=item.getTotal();
      }
      return total;
  }  
    public ArrayList<Item> getItems() {
        if(items == null)
        { 
            items =new ArrayList();
        }
        return items;
    }

    @Override
    public String toString() {
        return "HeaderInvoice{" + "num=" + num + ", name=" + name + ", date=" + date + ", items=" + items + '}';
    }

 public String getAsCSVFormat() {
        return num + "," + date + "," + name;
    }
    
}
