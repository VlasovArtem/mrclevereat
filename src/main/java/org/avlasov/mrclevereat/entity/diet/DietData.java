package org.avlasov.mrclevereat.entity.diet;

import org.avlasov.mrclevereat.entity.Base;
import org.avlasov.mrclevereat.entity.logs.WeightLog;
import org.avlasov.mrclevereat.entity.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Created By artemvlasov on 22/12/2017
 **/
@Entity
public class DietData extends Base {

    //from 1 to 10. 10 - high activity 7 days a week, 1 - no activity.
    private byte activityScore;
    private double targetWeight;
    private int gramsPerWeek;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_data_id")
    private List<WeightLog> weightLogs;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

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

    public User getOwner() {
        return owner;
    }

    public static DietDataBuilder builder() {
        return new DietDataBuilder();
    }

    public static class DietDataBuilder {
        private byte activityScore;
        private double targetWeight;
        private int gramsPerWeek;
        private List<WeightLog> weightLogs;
        private User owner;

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

        public DietDataBuilder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public DietData build() {
            DietData dietData = new DietData();
            dietData.activityScore = activityScore;
            dietData.gramsPerWeek = gramsPerWeek;
            dietData.targetWeight = targetWeight;
            dietData.weightLogs = weightLogs;
            dietData.owner = owner;
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
                Objects.equals(weightLogs, dietData.weightLogs) &&
                Objects.equals(owner, dietData.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activityScore, targetWeight, gramsPerWeek, weightLogs, owner);
    }
}
