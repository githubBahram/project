package com.parsdeveloper.shopping.model.dto;

import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class ProductDto implements Serializable {

    private Long id;
    private String name;

    private MultipartFile image;
    private String Description;
    private Long categoryId;
    private String categoryName;
    private String imageLocation;

    private Product product;
    private Collection<ProductImage> productImageList;
    private List<String> imageLocationList;
    private Long price;
    private Long discountValue;
    private Long discountUnit;

    public ProductDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public Collection<ProductImage> getProductImageList() {
        return productImageList;
    }

    public void setProductImageList(Collection<ProductImage> productImageList) {
        this.productImageList = productImageList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<String> getImageLocationList() {
        return imageLocationList;
    }

    public void setImageLocationList(List<String> imageLocationList) {
        this.imageLocationList = imageLocationList;
    }

    public Long getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Long discountValue) {
        this.discountValue = discountValue;
    }

    public Long getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(Long discountUnit) {
        this.discountUnit = discountUnit;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public ProductDto map(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setCategoryName(product.getCategory().getName());
//        productDto.setImageId(product.getImage().getId());
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        return productDto;
    }
}
