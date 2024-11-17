package tech.reliab.course.bank.entity;

import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
