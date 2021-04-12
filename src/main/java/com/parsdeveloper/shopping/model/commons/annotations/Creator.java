package com.parsdeveloper.shopping.model.commons.annotations;


import com.parsdeveloper.shopping.model.commons.helper.CreatorGeneration;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ValueGenerationType(generatedBy = CreatorGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Creator {

}