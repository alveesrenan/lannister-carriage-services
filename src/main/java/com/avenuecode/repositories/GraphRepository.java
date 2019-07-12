package com.avenuecode.repositories;

import com.avenuecode.entities.Graph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraphRepository extends JpaRepository<Graph, Long> {
}
