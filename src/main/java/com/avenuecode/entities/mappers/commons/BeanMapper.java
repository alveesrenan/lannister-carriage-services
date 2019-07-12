package com.avenuecode.entities.mappers.commons;

import java.util.List;

public interface BeanMapper<S, T> {
    T toTarget(S source);
    List<T> toTarget(List<S> sources);
    S fromSource(T target);
    List<S> fromSource(List<T> targets);
}
