package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.DistributorData;
import ciolty.energySystemImplementation.entities.ProducerData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class MonthEndedAction extends ImplementedAction {
    private List<DistributorData> distributors;
    private List<ProducerData> producers;

    @Override
    public String start() {
        distributors = getUnitOfWork().getDistributorRepository().getActiveDistributors();
        producers = getUnitOfWork().getProducerRepository().getAll();
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Month ended!");
        distributors.forEach(distributor -> {
            distributor.getActiveContracts().forEach(contract -> {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            });
        });

        producers.forEach(producer -> {
            producer.getRegisteredDistributors().sort(Comparator.comparingInt(o -> o));
            producer.getAllTimeRegisteredDistributors()
                    .add(new ArrayList<>(producer.getRegisteredDistributors()));
        });
        return null;
    }
}
