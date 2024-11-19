package tech.reliab.course.bank.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
