package tech.reliab.course.bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAtm {
    Long id;
    String name;
    String address;
    String status;
    Long bankId;
    Long bankOfficeId;
    Long employeeId;
    Boolean issuesMoney;
    Boolean depositMoney;
    Integer totalMoney;
    Integer costMaintenance;
}
