package com.vkr.webapp.entity;


import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "saved_results")
public class SavedResult extends BaseEntity {

    @NotNull
    @Column
    private Integer periodNum;

    @NotNull
    @Column
    private Integer interestRate;

    @NotNull
    @Column
    private Integer initSum;

    @NotNull
    @Column
    private Integer futureValue;

    @NotNull
    @Column
    private Integer accrPerNum;

    @NotNull
    @Column
    private Integer annualIncome;

    @NotNull
    @Column
    private Integer totalAmount;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "invest_index_id", nullable = false)
    private InvestIndex investIndex;
}
