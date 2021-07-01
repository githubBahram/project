package com.parsdeveloper.shopping.model.dto;

import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private Long imageId;
    private MultipartFile image;
    private String Description;
    private Long categoryId;
    private String categoryName;
    private String imageLocation;
    private String imageName;
    private Product product;
    private Collection<ProductImage> productImageList;
    private List<String> imageLocationList;
    private Long price;
    private Long discountValue;
    private Long discountUnit;

    public ProductDto() {
    }

    public ProductDto(Long id,String name, Long price, Long discountValue, Long discountUnit, Long productImageId) {
        //this.imageLocationList = productImageList.stream().map(ProductImage::getLocation).collect(Collectors.toList());
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountValue = discountValue;
        this.discountUnit = discountUnit;
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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
