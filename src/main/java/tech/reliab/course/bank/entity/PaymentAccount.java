package tech.reliab.course.bank.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAccount {
    Long id;
    Long userId;
    String bankName;
    Integer currentAmount;
}
