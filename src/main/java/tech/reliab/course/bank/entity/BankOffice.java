package tech.reliab.course.bank.entity;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankOffice {
    Long id;
    String name;
    String address;
    Boolean status;
    Boolean freePlaceForAtm;
    Integer numberAtms;
    Boolean creditServices;
    Boolean issuesMoney;
    Boolean depositMoney;
    Integer totalMoney;
    Integer rent;
    Long bankId;
}
