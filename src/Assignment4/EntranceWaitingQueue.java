package Assignment4;

public class EntranceWaitingQueue {

    private CommonPoolInputQueue commonPoolInputQueue;
    private AdventurePoolInputQueue adventurePoolInputQueue;

    public EntranceWaitingQueue(CommonPoolInputQueue commonPoolInputQueue, AdventurePoolInputQueue adventurePoolInputQueue) {
        this.commonPoolInputQueue = commonPoolInputQueue;
        this.adventurePoolInputQueue = adventurePoolInputQueue;
    }

    public void putInAdventurePool(Customer customer) {
        synchronized (adventurePoolInputQueue) {
            try {

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void putInCommonPool(Customer temp) {
        synchronized (commonPoolInputQueue) {
            try {

            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
