package ciolty.Implementation.entities;

public class CostChangeData {
    public int id;
    public int infrastructureCost;
    public int productionCost;

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public CostChangeData() {
        super();
    }

    public CostChangeData(int id, int infrastructureCost, int productionCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
        this.productionCost = productionCost;
    }
}
