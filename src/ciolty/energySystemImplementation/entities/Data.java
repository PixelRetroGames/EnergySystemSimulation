package ciolty.energySystemImplementation.entities;

import ciolty.engine.action.ActionData;

public class Data implements ActionData {
    protected final String actionType;

    public Data(final String actionType) {
        this.actionType = actionType;
    }

    public final String getActionType() {
        return actionType;
    }
}
