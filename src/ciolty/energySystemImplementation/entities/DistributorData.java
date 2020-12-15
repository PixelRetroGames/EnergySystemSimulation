package ciolty.energySystemImplementation.entities;

import java.util.ArrayList;
import java.util.List;

public final class DistributorData {
    private int id;
    private int contractLength;
    private int budget;
    private int infrastructureCost;
    private int productionCost;

    private int contractPrice;
    private boolean isBankrupt;
    private final List<ContractData> activeContracts = new ArrayList<>();

    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        infrastructureCost = initialInfrastructureCost;
    }

    public void setInitialProductionCost(final int initialProductionCost) {
        productionCost = initialProductionCost;
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

    public DistributorData(final DistributorData other) {
        id = other.id;
        contractLength = other.contractLength;
        budget = other.budget;
        infrastructureCost = other.infrastructureCost;
        productionCost = other.productionCost;
        isBankrupt = other.isBankrupt;
        contractPrice = other.contractPrice;
    }

    public DistributorData(final int id, final int contractLength,
                           final int initialBudget, final int initialInfrastructureCost,
                           final int initialProductionCost) {
        this.id = id;
        this.contractLength = contractLength;
        this.budget = initialBudget;
        this.infrastructureCost = initialInfrastructureCost;
        this.productionCost = initialProductionCost;
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

    public int getInitialProductionCost() {
        return productionCost;
    }

    public List<ContractData> getActiveContracts() {
        return activeContracts;
    }
}
