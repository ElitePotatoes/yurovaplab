package tech.reliab.course.bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    Long id;
    String fullName;
    Date dateOfBirth;
    String post;
    Long bankId;
    Boolean officeWorkFormat;
    Long bankOfficeId;
    Boolean creditServices;
    Integer salary;
}
