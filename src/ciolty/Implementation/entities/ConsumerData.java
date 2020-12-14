package ciolty.Implementation.entities;

public class ConsumerData {
    private int id;
    private int initialBudget;
    private int monthlyIncome;
    private ContractData contract = new ContractData();
    private ContractData unpaidBill = new ContractData();
    private boolean isBankrupt;

    public int getBudget() {
        return initialBudget;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public void setBudget(int budget) {
        initialBudget = budget;
    }

    public ContractData getContract() {
        return contract;
    }

    public void setContract(ContractData contract) {
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public ConsumerData() {

    }

    public ConsumerData(ConsumerData other) {
        id = other.id;
        initialBudget = other.initialBudget;
        monthlyIncome = other.monthlyIncome;
    }

    public ConsumerData(int id, int initialBudget, int monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    public ContractData getUnpaidBill() {
        return unpaidBill;
    }

    public void setUnpaidBill(ContractData unpaidBill) {
        this.unpaidBill = unpaidBill;
    }
}
