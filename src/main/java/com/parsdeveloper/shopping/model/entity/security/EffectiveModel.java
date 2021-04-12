package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.commons.annotations.AutoValue;
import com.parsdeveloper.shopping.model.commons.helper.PresentEffectiveDateGenerator;
import org.hibernate.tuple.GenerationTiming;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

@MappedSuperclass
public class EffectiveModel<T extends Number> extends AuditModel<T> {

    private Date effectiveDate;
    private Date disableDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECTIVE_DATE", nullable = false)
    @NotNull
    @AutoValue(value = PresentEffectiveDateGenerator.class, generationTiming = GenerationTiming.INSERT)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DISABLE_DATE")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }
}
