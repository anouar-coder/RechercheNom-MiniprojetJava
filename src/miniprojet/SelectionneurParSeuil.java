package miniprojet;

import java.util.List;
import java.util.stream.Collectors;

public class SelectionneurParSeuil implements Selectionneur {
    private double seuil;
    private int maxResultats; // Added to support maximum number of results

    // Existing constructor
    public SelectionneurParSeuil(double seuil) {
        this.seuil = seuil;
        this.maxResultats = Integer.MAX_VALUE; // Default to no limit
    }

    // New constructor to support threshold and max results
    public SelectionneurParSeuil(double seuil, int maxResultats) {
        this.seuil = seuil;
        this.maxResultats = maxResultats > 0 ? maxResultats : Integer.MAX_VALUE;
    }

    @Override
    public List<CoupleScore> selectionner(List<CoupleScore> scores) {
        return scores.stream()
                .filter(cs -> cs.getScore() >= seuil)
                .sorted((cs1, cs2) -> Double.compare(cs2.getScore(), cs1.getScore())) // Sort in descending order
                .limit(maxResultats) // Limit to maxResultats
                .collect(Collectors.toList());
    }

    // Getter and setter for seuil (if needed)
    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }

    // Getter and setter for maxResultats (if needed)
    public int getMaxResultats() {
        return maxResultats;
    }

    public void setMaxResultats(int maxResultats) {
        this.maxResultats = maxResultats > 0 ? maxResultats : Integer.MAX_VALUE;
    }
}