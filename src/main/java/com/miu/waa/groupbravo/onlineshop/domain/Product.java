package com.miu.waa.groupbravo.onlineshop.domain;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product extends DomainClass {
    private String serialNumber;
    private String productNumber;
    private String name;
    private String description;
    private BigDecimal unitPrice= BigDecimal.ZERO;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="seller_id")

    private User seller;
    @Enumerated(EnumType.STRING)
    private EProductStatus productStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="product_category_id")
    private ProductCategory productCategory;
    @Transient
    private org.springframework.web.multipart.MultipartFile MultipartFile;
    private String file;
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public EProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(EProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public void setMultipartFile(org.springframework.web.multipart.MultipartFile multipartFile) {
        MultipartFile = multipartFile;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public org.springframework.web.multipart.MultipartFile getMultipartFile() {
        return MultipartFile;
    }

    public String getFile() {
        return file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return new EqualsBuilder()
                .append(serialNumber, product.serialNumber)
                .append(seller, product.seller)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(serialNumber)
                .append(seller)
                .toHashCode();
    }
}
