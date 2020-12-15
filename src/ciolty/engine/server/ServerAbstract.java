package ciolty.engine.server;

import ciolty.engine.action.ActionController;
import ciolty.engine.action.Output;
import ciolty.engine.database.UnitOfWork;

public abstract class ServerAbstract implements Server {
    protected Output output;
    protected UnitOfWork unitOfWork;
    protected ActionController actionController;
}
