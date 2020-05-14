package com.miu.waa.groupbravo.onlineshop.domain;


import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.customAnnotation.SerialNumber;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@NamedQueries({@NamedQuery(name= ProductRepository.QUERY_NAME.findByStatus,query=ProductRepository.QUERY.findByStatus),
        @NamedQuery(name=ProductRepository.QUERY_NAME.findByCategoryAndStatus,query=ProductRepository.QUERY.findByCategoryAndStatus)})
public class Product extends DomainClass {
    @Pattern(regexp = "SN[1-9]+", message = "{Pattern.Product.serialNumber.validation}")
    @SerialNumber
    private String serialNumber;

    private String productNumber;
    @NotEmpty
    @Size(min=2, max=30, message="{Size.Product.name.validation}")
    private String name;
    @NotEmpty
    @Size(min=6, max=50, message="{Size.Product.description.validation}")
    private String description;
    @NotNull
    private BigDecimal unitPrice= BigDecimal.ZERO;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="seller_id")
    private User seller;

    @Enumerated(EnumType.STRING)
    private EProductStatus productStatus;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="product_category_id")
    private ProductCategory productCategory;
    @Transient
    private MultipartFile multipartFile;

    private String file  ;
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
        this.multipartFile = multipartFile;
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
        return multipartFile;
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
