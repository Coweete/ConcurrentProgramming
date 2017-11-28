package Assignment3;

public class Truck implements Runnable {

    private int item;
    private double totalWeight;
    private double currentWeight;
    private double totalVolume;
    private double currentVolume;
    private GUISemaphore guiSemaphore;
    private Storage storage;
    private boolean isRunning = false;
    private boolean[] loading = new boolean[3];
    private String name;

    public Truck(Storage storage, GUISemaphore guiSemaphore, String name, double totalVolume, double totalWeight) {
        this.storage = storage;
        this.guiSemaphore = guiSemaphore;
        this.name = name;
        this.totalVolume = totalVolume;
        this.totalWeight = totalWeight;
        currentVolume = 0.0;
        currentWeight = 0.0;
        item = 0;
    }

    @Override
    public void run() {

        while (isRunning) {
            FoodItem foodItem = storage.getFromQueue();
            if (foodItem != null) {
                if (((foodItem.getVolume() + currentVolume) < totalVolume) && ((foodItem.getWeight() + currentWeight) < totalWeight)) {
                    currentVolume = totalVolume + foodItem.getVolume();
                    currentWeight = totalWeight + foodItem.getWeight();
                    item++;
                    printText(foodItem);
                } else {
                    checkIfContinue();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void checkIfContinue() {
        try {
            Thread.sleep(5000);
            loading = guiSemaphore.isContinueLoading();
            //Print out process on the correct label
            switch (name) {
                case "ica":
                    //Ica
                    if (loading[0]) {
                        totalVolume = 0;
                        totalWeight = 0;
                        item = 0;
                    } else {
                        isRunning = false;
                    }
                    break;
                case "coop":
                    //Coop
                    if (loading[1]) {
                        totalVolume = 0;
                        totalWeight = 0;
                        item = 0;
                    } else {
                        isRunning = false;
                    }
                    break;
                case "citygross":
                    //Citygross
                    if (loading[2]) {
                        totalVolume = 0;
                        totalWeight = 0;
                        item = 0;
                    } else {
                        isRunning = false;
                    }
                    break;
                default:
                    System.out.println("Default");
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printText(FoodItem foodItem) {
        switch (name) {
            case "ica":
                guiSemaphore.printIca(foodItem.getName() + "\n");
                guiSemaphore.printIcaNumbers(item, currentVolume, currentWeight);
                break;
            case "coop":
                guiSemaphore.printCoop(foodItem.getName() + "\n");
                guiSemaphore.printCoopNumbers(item, currentVolume, currentWeight);
                break;
            case "citygross":
                guiSemaphore.printCityGross(foodItem.getName() + "\n");
                guiSemaphore.printCoopNumbers(item, currentVolume, currentWeight);
                break;
            default:
                System.out.println("Default");
                break;
        }
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }
}
