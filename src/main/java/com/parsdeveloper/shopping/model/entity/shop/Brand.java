package com.parsdeveloper.shopping.model.entity.shop;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "brand")
public class Brand  implements Serializable {

    private Long id;
    private String code;
    private String name;
    private BrandImage image;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    public BrandImage getImage() {
        return image;
    }

    public void setImage(BrandImage image) {
        this.image = image;
    }
}
