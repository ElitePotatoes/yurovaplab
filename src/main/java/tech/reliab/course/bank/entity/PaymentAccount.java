package tech.reliab.course.bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentAccount {
    Long id;
    Long userId;
    String bankName;
    Integer currentAmount;
}
