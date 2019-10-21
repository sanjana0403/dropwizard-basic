package com.basic.helloworld.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "hello_world", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class HelloWorldEntity {

  @Id
  @Column(name = "internal_id", unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long internalId;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;
}
