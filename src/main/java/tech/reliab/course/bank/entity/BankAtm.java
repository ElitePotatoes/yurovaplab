package tech.reliab.course.bank.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
