package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.repositories.EnergySystemUnitOfWork;
import ciolty.engine.action.ActionAbstract;

public abstract class ImplementedAction extends ActionAbstract {
    @Override
    public final EnergySystemUnitOfWork getUnitOfWork() {
        return (EnergySystemUnitOfWork) unitOfWork;
    }
}
