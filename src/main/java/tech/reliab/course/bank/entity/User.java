package tech.reliab.course.bank.entity;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long id;
    String fullName;
    Date dateOfBirth;
    String workplace;
    Integer monthlyIncome;
    String bankUsed;
    Long creditAccountId;
    Long paymentAccountId;
    Integer creditRating;
}
