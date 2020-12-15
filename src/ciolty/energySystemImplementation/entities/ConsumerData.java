package ciolty.energySystemImplementation.entities;

public final class ConsumerData {
    private int id;
    private int budget;
    private int monthlyIncome;
    private ContractData contract = new ContractData();
    private ContractData unpaidBill = new ContractData();
    private boolean isBankrupt;

    public int getBudget() {
        return budget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public ContractData getContract() {
        return contract;
    }

    public void setContract(final ContractData contract) {
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getInitialBudget() {
        return budget;
    }

    public void setInitialBudget(final int initialBudget) {
        this.budget = initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public ConsumerData() {

    }

    public ConsumerData(final ConsumerData other) {
        id = other.id;
        budget = other.budget;
        monthlyIncome = other.monthlyIncome;
    }

    public ConsumerData(final int id, final int initialBudget,
                        final int monthlyIncome) {
        this.id = id;
        this.budget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    public ContractData getUnpaidBill() {
        return unpaidBill;
    }

    public void setUnpaidBill(final ContractData unpaidBill) {
        this.unpaidBill = unpaidBill;
    }
}
