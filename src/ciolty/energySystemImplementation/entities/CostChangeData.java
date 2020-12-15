package ciolty.energySystemImplementation.entities;

public final class CostChangeData {
    private int id;
    private int infrastructureCost;
    private int productionCost;

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

    public CostChangeData(final int id, final int infrastructureCost,
                          final int productionCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
        this.productionCost = productionCost;
    }
}
