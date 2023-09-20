package com.wayakeji.common.mybatis.annotation;

import java.lang.annotation.*;

@Inherited
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptDecryptField {

}
