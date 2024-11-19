package tech.reliab.course.bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreditAccount {
    Long id;
    Long userId;
    String bankName;
    Date creditStartDate;
    Date creditEndDate;
    Integer creditMonthlyDuration;
    Integer creditAmount;
    Integer monthlyPayment;
    Float interestRate;
    Long employeeId;
    Long paymentAccountId;
}
