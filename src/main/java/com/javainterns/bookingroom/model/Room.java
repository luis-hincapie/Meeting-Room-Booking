package com.javainterns.bookingroom.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String location;

  @Column(nullable = false)
  private Integer capacity;

  @Column(nullable = false)
  private Integer startTime;

  @Column(nullable = false)
  private Integer finishTime;

  @Column(nullable = false)
  private Boolean isActive;

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<Booking> bookingList;
  
}
