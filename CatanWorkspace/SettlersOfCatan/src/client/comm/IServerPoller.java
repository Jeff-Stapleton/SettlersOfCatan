package client.comm;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public interface IServerPoller extends Runnable {
    public void run();
    public void close();
}
