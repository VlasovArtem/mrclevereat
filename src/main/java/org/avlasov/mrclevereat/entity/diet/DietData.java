package org.avlasov.mrclevereat.entity.diet;

import org.avlasov.mrclevereat.entity.logs.WeightLog;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
public class DietData {

    //from 1 to 10. 10 - high activity 7 days a week, 1 - no activity.
    private byte activityScore;
    private double targetWeight;
    private int gramsPerWeek;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<WeightLog> weightLogs;

    private DietData() {}

    public byte getActivityScore() {
        return activityScore;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public int getGramsPerWeek() {
        return gramsPerWeek;
    }

    public List<WeightLog> getWeightLogs() {
        return weightLogs;
    }

    public static DietDataBuilder builder() {
        return new DietDataBuilder();
    }

    public static class DietDataBuilder {
        private byte activityScore;
        private double targetWeight;
        private int gramsPerWeek;
        private List<WeightLog> weightLogs;

        public DietDataBuilder activityScore(byte activityScore) {
            this.activityScore = activityScore;
            return this;
        }

        public DietDataBuilder targetWeight(double targetWeight) {
            this.targetWeight = targetWeight;
            return this;
        }

        public DietDataBuilder gramsPerWeek(int gramsPerWeek) {
            this.gramsPerWeek = gramsPerWeek;
            return this;
        }

        public DietDataBuilder weightLogs(List<WeightLog> weightLogs) {
            this.weightLogs = weightLogs;
            return this;
        }

        public DietData build() {
            DietData dietData = new DietData();
            dietData.activityScore = activityScore;
            dietData.gramsPerWeek = gramsPerWeek;
            dietData.targetWeight = targetWeight;
            dietData.weightLogs = weightLogs;
            return dietData;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DietData)) return false;
        DietData dietData = (DietData) o;
        return activityScore == dietData.activityScore &&
                Double.compare(dietData.targetWeight, targetWeight) == 0 &&
                gramsPerWeek == dietData.gramsPerWeek &&
                Objects.equals(weightLogs, dietData.weightLogs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityScore, targetWeight, gramsPerWeek, weightLogs);
    }
}
