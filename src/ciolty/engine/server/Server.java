package ciolty.engine.server;

import ciolty.engine.action.Output;

/**
 *  Hides the logic for setting up the action controller and running actions
 */
public interface Server {
    /**
     * Runs all actions
     */
    void runAllActions();

    /**
     * @return output resulted from running
     */
    Output getOutput();
}
