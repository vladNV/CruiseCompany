package model.util;

import model.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class AggregateOperation<I extends Number, E extends Entity>
        implements Entity{
    private I agg;
    private E entity;

    public AggregateOperation(I agg, E entity) {
        this.agg = agg;
        this.entity = entity;
    }

    public I getAgg() {
        return agg;
    }

    public E getEntity() {
        return entity;
    }

    public static <I extends Number, E extends Entity> List<E> entities(
            List<AggregateOperation<I, E>> list) {
        List<E> entities = new ArrayList<>();
        for (AggregateOperation<I, E> e : list) {
            entities.add(e.getEntity());
        }
        return entities;
    }
}
