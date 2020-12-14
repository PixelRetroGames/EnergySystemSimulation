package ciolty.Implementation.entities;

public class ContractData {
    private int consumerId;
    private int distributorId;
    private int price;
    private int remainedContractMonths;

    @Override
    public String toString() {
        return "ContractData{" +
                "consumerId=" + consumerId +
                ", distributorId=" + distributorId +
                ", price=" + price +
                ", remainedContractMonths=" + remainedContractMonths +
                '}';
    }

    public ContractData() {
    }

    public ContractData(int consumerId, int distributorId, int price, int remainedContractMonths) {
        this.consumerId = consumerId;
        this.distributorId = distributorId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(int consumerId) {
        this.consumerId = consumerId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public void setRemainedContractMonths(int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
