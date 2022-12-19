package com.vkr.webapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vkr.webapp.entity.enums.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonIgnore
    @OneToOne(mappedBy = "role", fetch = FetchType.LAZY)
    private User user;
}
