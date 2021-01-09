package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.debugger.DebugLogger;
import ciolty.energySystemImplementation.entities.ProducerData;
import ciolty.engine.database.Filter;

import java.util.List;

public class ProducersResetPriceChangedAction extends ImplementedAction {
    private List<ProducerData> producers;
    @Override
    public String start() {
        producers = getUnitOfWork().getProducerRepository().find(object -> ((ProducerData) object).isPriceChanged());
        return null;
    }

    @Override
    public String execute() {
        DebugLogger.log("Producers price changed reset!");
        producers.forEach(producer -> producer.setPriceChanged(false));
        return null;
    }
}
