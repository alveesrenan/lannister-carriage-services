package com.avenuecode.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity(name = "routes")
@NoArgsConstructor
@AllArgsConstructor
public class Routes {

    @Id
    @SequenceGenerator(name = "ROUTE_SEQ", sequenceName = "ROUTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROUTE_SEQ")
    private Long id;

    @NotNull
    private String source;

    @NotNull
    private String target;

    @NotNull
    private int distance;
}
