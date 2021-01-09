package ciolty.energySystemImplementation.entities;

public final class DistributorChangeData {
    private int id;
    private int infrastructureCost;

    public int getId() {
        return id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public DistributorChangeData() {
        super();
    }

    public DistributorChangeData(final int id, final int infrastructureCost) {
        this.id = id;
        this.infrastructureCost = infrastructureCost;
    }
}
