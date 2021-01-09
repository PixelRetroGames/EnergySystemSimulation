package ciolty.energySystemImplementation.entities;

import java.util.ArrayList;
import java.util.List;

public final class DistributorData {
    private int id;
    private int contractLength;
    private int budget;
    private int infrastructureCost;
    private int energyNeededKW;
    private String producerStrategy;

    private int productionCost;
    private int contractPrice;
    private boolean isBankrupt;
    private final List<ContractData> activeContracts = new ArrayList<>();
    private List<Integer> producersIds = new ArrayList<>();

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        infrastructureCost = initialInfrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public void setInitialBudget(final int initialBudget) {
        budget = initialBudget;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(final int contractPrice) {
        this.contractPrice = contractPrice;
    }

    public int getNumberOfClients() {
        return activeContracts.size();
    }

    public int getBudget() {
        return budget;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public DistributorData() {
        super();
    }

    public DistributorData(DistributorData data) {
        id = data.getId();
        contractLength = data.getId();
        budget = data.getBudget();
        infrastructureCost = data.getInfrastructureCost();
        energyNeededKW = data.getEnergyNeededKW();
        producerStrategy = data.getProducerStrategy();
    }

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInitialBudget() {
        return budget;
    }

    public int getInitialInfrastructureCost() {
        return infrastructureCost;
    }

    public List<ContractData> getActiveContracts() {
        return activeContracts;
    }

    public List<Integer> getProducersIds() {
        return producersIds;
    }
}
