package com.parsdeveloper.shopping.model.commons.annotations;

import com.parsdeveloper.shopping.model.commons.helper.ModifierGeneration;
import org.hibernate.annotations.ValueGenerationType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@ValueGenerationType(generatedBy = ModifierGeneration.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Modifier {
}
