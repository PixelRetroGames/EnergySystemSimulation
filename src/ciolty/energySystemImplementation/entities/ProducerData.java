package ciolty.energySystemImplementation.entities;

import java.util.List;

public class ProducerData {
    private int id;
    private String energyType;
    private int maxDistributors;
    private int priceKW;
    private int energyPerDistributor;

    private boolean priceChanged;
    private List<Integer> registeredDistributors;
    private List<List<Integer>> allTimeRegisteredDistributors;

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

    public int getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(int priceKW) {
        this.priceKW = priceKW;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public boolean isPriceChanged() {
        return priceChanged;
    }

    public void setPriceChanged(boolean priceChanged) {
        this.priceChanged = priceChanged;
    }

    public List<Integer> getRegisteredDistributors() {
        return registeredDistributors;
    }

    public void setRegisteredDistributors(List<Integer> registeredDistributors) {
        this.registeredDistributors = registeredDistributors;
    }

    public List<List<Integer>> getAllTimeRegisteredDistributors() {
        return allTimeRegisteredDistributors;
    }

    public void setAllTimeRegisteredDistributors(List<List<Integer>> allTimeRegisteredDistributors) {
        this.allTimeRegisteredDistributors = allTimeRegisteredDistributors;
    }
}
