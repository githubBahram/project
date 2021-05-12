package com.parsdeveloper.shopping.model.commons.helper;


import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.tuple.ValueGenerator;


public class SequenceFieldGenerator implements ValueGenerator<String> {

    @Override
    public String generateValue(Session session, Object owner) {

        NativeQuery nativeQuery = session.createNativeQuery("select SQ_HCP_CODE_GENERATOR.nextval as num from dual");

        return nativeQuery.uniqueResult().toString();

    }
}
