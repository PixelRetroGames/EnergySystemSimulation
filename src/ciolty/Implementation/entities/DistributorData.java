package ciolty.Implementation.entities;

import java.util.ArrayList;
import java.util.List;

public class DistributorData {
    public int id;
    public int contractLength;
    public int initialBudget;
    public int initialInfrastructureCost;
    public int initialProductionCost;

    private int contractPrice;
    private boolean isBankrupt;
    private final List<ContractData> activeContracts = new ArrayList<>();

    public void setInfrastructureCost(int infrastructureCost) {
        initialInfrastructureCost = infrastructureCost;
    }

    public void setProductionCost(int productionCost) {
        initialProductionCost = productionCost;
    }

    public void setBudget(int budget) {
        initialBudget = budget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getContractPrice() {
        return contractPrice;
    }

    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }

    public int getNumberOfClients() {
        return activeContracts.size();
    }

    public int getBudget() {
        return initialBudget;
    }

    public int getInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public int getProductionCost() {
        return initialProductionCost;
    }

    public DistributorData() {
        super();
    }

    public DistributorData(DistributorData other) {
        id = other.id;
        contractLength = other.contractLength;
        initialBudget = other.initialBudget;
        initialInfrastructureCost = other.initialInfrastructureCost;
        initialProductionCost = other.initialProductionCost;
    }

    public DistributorData(int id, int contractLength, int initialBudget, int initialInfrastructureCost, int initialProductionCost) {
        this.id = id;
        this.contractLength = contractLength;
        this.initialBudget = initialBudget;
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.initialProductionCost = initialProductionCost;
    }

    public int getId() {
        return id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    public List<ContractData> getActiveContracts() {
        return activeContracts;
    }
}
