package com.avenuecode.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Entity(name = "graph")
@NoArgsConstructor
@AllArgsConstructor
public class Graph {

    @Id
    @SequenceGenerator(name = "GRAPH_SEQ", sequenceName = "GRAPH_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRAPH_SEQ")
    @Column(name = "graph_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "graph_id")
    @NotNull
    private List<Routes> routes;
}
