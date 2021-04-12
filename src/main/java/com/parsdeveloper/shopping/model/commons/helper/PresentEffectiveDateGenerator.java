package com.parsdeveloper.shopping.model.commons.helper;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;
import org.joda.time.LocalDate;

import java.util.Date;

public class PresentEffectiveDateGenerator implements ValueGenerator<Date> {

    @Override
    public Date generateValue(Session session, Object owner) {
        if (owner instanceof EffectiveModel) {
            if (((EffectiveModel) owner).getEffectiveDate() == null) {
                return LocalDate.now().toDate();
            } else {
                return ((EffectiveModel) owner).getEffectiveDate();
            }
        }
        throw new IllegalStateException("PresentEffectiveDateGenerator : your entity must implement EffectiveModel to use this generator");
    }

}