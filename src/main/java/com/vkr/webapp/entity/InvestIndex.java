package com.vkr.webapp.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "invest_index")
public class InvestIndex extends BaseEntity {

    @Column
    @NotNull
    private String investName;

    @NotNull
    @OneToMany(mappedBy = "investIndex", cascade = CascadeType.ALL)
    List<SavedResult> savedResults;
}
