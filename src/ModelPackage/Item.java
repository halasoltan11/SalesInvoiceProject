/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModelPackage;

/**
 *
 * @author halas
 */
public class Item {
    private HeaderInvoice inv;
    private String name ;
    private int count;
    private double price;

    public Item(HeaderInvoice inv, String name, int count, double price) {
        this.inv = inv;
        this.name = name;
        this.count = count;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HeaderInvoice getInv() {
        return inv;
    }

    public void setInv(HeaderInvoice inv) {
        this.inv = inv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ItemInvoices{" + "name=" + name + ", count=" + count + ", price=" + price + '}';
    }
  public double getTotal()
  {
      return price * count;
  }
  
   public String getAsCSVFormat() {
        return inv.getNum() + "," + name + "," + price + "," + count;
    }
  
}
