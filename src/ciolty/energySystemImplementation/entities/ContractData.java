package ciolty.energySystemImplementation.entities;

public final class ContractData {
    private int consumerId;
    private int distributorId;
    private int price;
    private int remainedContractMonths;

    @Override
    public String toString() {
        return "ContractData{"
                + "consumerId=" + consumerId
                + ", distributorId=" + distributorId
                + ", price=" + price
                + ", remainedContractMonths=" + remainedContractMonths
                + '}';
    }

    public ContractData() {
    }

    public ContractData(final int consumerId, final int distributorId,
                        final int price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.distributorId = distributorId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
