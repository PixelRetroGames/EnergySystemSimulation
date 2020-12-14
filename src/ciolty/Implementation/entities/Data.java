package ciolty.Implementation.entities;

import ciolty.engine.action.ActionData;

public class Data implements ActionData {
    protected String actionType;

    public Data(String actionType) {
        this.actionType = actionType;
    }

    public final String getActionType() {
        return actionType;
    }
}
