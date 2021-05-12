package com.parsdeveloper.shopping.model.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parsdeveloper.shopping.model.commons.annotations.Creator;
import com.parsdeveloper.shopping.model.commons.annotations.Modifier;
import com.parsdeveloper.shopping.model.commons.annotations.ViewOption;
import com.parsdeveloper.shopping.model.dao.BaseModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class AuditModel<T extends Number> extends BaseModel<T> {

    private User creator;
    private Date creationDate;
    private User lastModifier;
    private Date lastModificationDate;
    private Integer version;

    @JsonIgnore
    @ViewOption(excludeView = true)
    @Creator
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID", nullable = false, updatable = false)
    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @JsonIgnore
    @ViewOption(excludeView = true)
    @CreationTimestamp
    @Column(name = "CREATION_DATE", nullable = false, updatable = false, columnDefinition = "DATE")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @JsonIgnore
    @ViewOption(excludeView = true)
    @Modifier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LAST_MODIFIER_ID", nullable = false)
    public User getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(User lastModifier) {
        this.lastModifier = lastModifier;
    }

    @JsonIgnore
    @ViewOption(excludeView = true)
    @UpdateTimestamp
    @Column(name = "LAST_MODIFICATION_DATE", nullable = false, columnDefinition = "DATE")
    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    @JsonIgnore
    @Version()
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
