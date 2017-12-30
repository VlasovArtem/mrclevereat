package org.avlasov.mrclevereat.entity.logs;

import org.avlasov.mrclevereat.entity.Base;

import javax.persistence.Entity;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Entity
public class WeightLog extends Base {

    private double weight;

    public WeightLog(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

}
