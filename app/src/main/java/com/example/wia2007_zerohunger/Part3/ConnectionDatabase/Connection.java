package com.example.wia2007_zerohunger.Part3.ConnectionDatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "connection_table")
public class Connection {
    @PrimaryKey(autoGenerate = true)
    public int connectionId;

    public int connectionImageId;
    public String connectionName;
    public double connectionDistance;
    public String connectionProduct;

    public int product1ImageId;
    public double product1Price;

    public int product2ImageId;
    public double product2Price;

    public Connection(int connectionImageId, String connectionName, double connectionDistance,
                      String connectionProduct, int product1ImageId, double product1Price, int product2ImageId,
                      double product2Price) {
        this.connectionImageId = connectionImageId;
        this.connectionName = connectionName;
        this.connectionDistance = connectionDistance;
        this.connectionProduct = connectionProduct;
        this.product1ImageId = product1ImageId;
        this.product1Price = product1Price;
        this.product2ImageId = product2ImageId;
        this.product2Price = product2Price;
    }

    public int getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(int connectionId) {
        this.connectionId = connectionId;
    }

    public int getConnectionImageId() {
        return connectionImageId;
    }

    public void setConnectionImageId(int connectionImageId) {
        this.connectionImageId = connectionImageId;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public double getConnectionDistance() {
        return connectionDistance;
    }

    public void setConnectionDistance(double connectionDistance) {
        this.connectionDistance = connectionDistance;
    }

    public String getConnectionProduct() {
        return connectionProduct;
    }

    public void setConnectionProduct(String connectionProduct) {
        this.connectionProduct = connectionProduct;
    }

    public int getProduct1ImageId() {
        return product1ImageId;
    }

    public void setProduct1ImageId(int product1ImageId) {
        this.product1ImageId = product1ImageId;
    }

    public double getProduct1Price() {
        return product1Price;
    }

    public void setProduct1Price(double product1Price) {
        this.product1Price = product1Price;
    }

    public int getProduct2ImageId() {
        return product2ImageId;
    }

    public void setProduct2ImageId(int product2ImageId) {
        this.product2ImageId = product2ImageId;
    }

    public double getProduct2Price() {
        return product2Price;
    }

    public void setProduct2Price(double product2Price) {
        this.product2Price = product2Price;
    }
}
