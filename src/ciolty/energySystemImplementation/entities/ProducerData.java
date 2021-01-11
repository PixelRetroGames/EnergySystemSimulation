package ciolty.energySystemImplementation.entities;

import java.util.ArrayList;
import java.util.List;

public final class ProducerData {
    private int id;
    private String energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;

    private final List<Integer> registeredDistributors = new ArrayList<>();
    private final List<List<Integer>> allTimeRegisteredDistributors = new ArrayList<>();

    public ProducerData() {
    }

    public ProducerData(final ProducerData other) {
        id = other.id;
        energyType = other.energyType;
        maxDistributors = other.maxDistributors;
        priceKW = other.priceKW;
        energyPerDistributor = other.energyPerDistributor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<Integer> getRegisteredDistributors() {
        return registeredDistributors;
    }

    public List<List<Integer>> getAllTimeRegisteredDistributors() {
        return allTimeRegisteredDistributors;
    }
}
