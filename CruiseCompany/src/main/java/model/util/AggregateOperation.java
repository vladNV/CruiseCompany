package model.util;

import model.entity.Entity;

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
}
