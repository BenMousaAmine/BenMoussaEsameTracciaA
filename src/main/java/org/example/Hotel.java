package org.example;

public class Hotel {
    private int id ;
    private String desc ;
    private String name ;
    private double price ;
    private boolean suite ;


    public Hotel(int id, String desc, String name, double price, boolean suite) {
      this.setId(id);
      this.setDesc(desc);
      this.setName(name);
      this.setPrice(price);
      this.setSuite(suite);
    }




    public void setId(int id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        if (desc.length()>0){
            this.desc = desc;
        }
    }

    public void setName(String name) {
        if(name.length()>0){
            this.name = name;
        }

    }

    public void setPrice(double price) {
        if(price>0){
            this.price = price;
        }

    }

    public void setSuite(boolean suite) {
        this.suite = suite;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSuite() {
        return suite;
    }
}
