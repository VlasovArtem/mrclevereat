package org.avlasov.mrclevereat.entity.user;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.logs.WeightLog;

import java.util.List;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class DietData extends Base {

    //from 1 to 10. 10 - high activity 7 days a week, 1 - no activity.
    private byte activityScore;
    private double targetWeight;
    private byte gramsPerWeek;
    private List<WeightLog> weightLogs;

    public byte getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(byte activityScore) {
        this.activityScore = activityScore;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public byte getGramsPerWeek() {
        return gramsPerWeek;
    }

    public void setGramsPerWeek(byte gramsPerWeek) {
        this.gramsPerWeek = gramsPerWeek;
    }

    public List<WeightLog> getWeightLogs() {
        return weightLogs;
    }

    public void setWeightLogs(List<WeightLog> weightLogs) {
        this.weightLogs = weightLogs;
    }
}
