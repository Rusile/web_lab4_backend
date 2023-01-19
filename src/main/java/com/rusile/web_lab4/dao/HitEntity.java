package com.rusile.web_lab4.dao;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "hits")
public class HitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @NotNull
    @Column(name = "x", nullable = false)
    private Double x;

    @NotNull
    @Column(name = "y", nullable = false)
    private Double y;

    @NotNull
    @Column(name = "r", nullable = false)
    private Double r;

    @NotNull
    @Column(name = "check_date", nullable = false)
    private OffsetDateTime  checkDate;

    @NotNull
    @Column(name = "execution_time", nullable = false)
    private Long executionTime;

    @NotNull
    @Column(name = "status", nullable = false)
    private Boolean status = false;
}