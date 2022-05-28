package com.example.DisplayProducts;

public class ProductForm {
    private String productUrl;
    private String imgUrl;
    private String vendorName;
    private String productName;
    private String productPrice;


    public ProductForm() {
        super();
    }

    public ProductForm(String productUrl, String imgUrl, String vendorName, String productName, String productPrice) {
        super();
        this.productUrl = productUrl;
        this.imgUrl = imgUrl;
        this.vendorName = vendorName;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl;}

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

}
