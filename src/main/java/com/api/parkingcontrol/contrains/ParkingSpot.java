package com.api.parkingcontrol.contrains;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Anotação que será usada para validar um ParkingSpotDto 
 */
@Constraint(validatedBy = ParkingSpotValidator.class)
@Target({ METHOD, TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface ParkingSpot {
  String message() default "{Already registered!}";
  Class <?> [] groups() default {};
  Class <? extends Payload> [] payload() default {};
}