package com.safe.safe.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double balance;
}
