package com.parsdeveloper.shopping.model.entity.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
public class Category  implements Serializable {

    private Long id;
    private String name;
    private CategoryImage image;
    private Category root;
    private Category parent;
    private String description;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "root_id")
    public Category getRoot() {
        return root;
    }

    public void setRoot(Category root) {
        this.root = root;
    }

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
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
    public CategoryImage getImage() {
        return image;
    }

    public void setImage(CategoryImage image) {
        this.image = image;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
