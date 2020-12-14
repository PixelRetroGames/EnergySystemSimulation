package ciolty.Implementation.actions;

import ciolty.Implementation.repositories.ImplementedUnitOfWork;
import ciolty.engine.action.ActionAbstract;
import ciolty.engine.database.UnitOfWork;

public abstract class ImplementedAction extends ActionAbstract {
    @Override
    public final ImplementedUnitOfWork getUnitOfWork() {
        return (ImplementedUnitOfWork) unitOfWork;
    }
}
