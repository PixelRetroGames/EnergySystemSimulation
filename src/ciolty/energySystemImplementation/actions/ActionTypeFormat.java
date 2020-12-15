package ciolty.energySystemImplementation.actions;

import ciolty.energySystemImplementation.entities.Data;
import ciolty.engine.factory.TypeFormat;

public class ActionTypeFormat implements TypeFormat {
    @Override
    public final String getFormat(final Object object) {
        Data data = (Data) object;
        return data.getActionType();
    }
}
