package tech.reliab.course.bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bank {
    Long id;
    String name;
    Integer numberOffices;
    Integer numberAtms;
    Integer numberEmployees;
    Integer numberUsers;
    Integer bankRating;
    Integer totalMoney;
    Float interestRate;
}
