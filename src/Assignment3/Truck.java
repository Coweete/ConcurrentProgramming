package Assignment3;

public class Truck implements Runnable {

    private int currentItem, totalItem;
    private double totalWeight;
    private double currentWeight;
    private double totalVolume;
    private double currentVolume;
    private String name;
    private GUISemaphore guiSemaphore;
    private Storage storage;
    private boolean isRunning = false;
    private boolean[] loading = new boolean[3];


    public Truck(Storage storage, GUISemaphore guiSemaphore, String name, double totalVolume, double totalWeight, int totalItem) {
        this.storage = storage;
        this.guiSemaphore = guiSemaphore;
        this.name = name;
        this.totalVolume = totalVolume;
        this.totalWeight = totalWeight;
        this.totalItem = totalItem;
        currentVolume = 0.0;
        currentWeight = 0.0;
        currentItem = 0;
    }

    @Override
    public void run() {

        while (isRunning) {
            System.out.println("Running truck V1");
            FoodItem foodItem = storage.getFromQueue();
            if (foodItem != null) {
                if (((foodItem.getVolume() + currentVolume) < totalVolume) &&
                        ((foodItem.getWeight() + currentWeight) < totalWeight) &&
                        ((currentItem + 1) < totalItem)){
                    System.out.println("In if");
                    currentVolume = currentVolume + foodItem.getVolume();
                    currentWeight = currentWeight + foodItem.getWeight();
                    currentItem++;
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
        System.out.println("Check for continue?");
        try {
            loading = guiSemaphore.isContinueLoading();
            for (int i = 0; i < loading.length; i++) {
                System.out.println("i:" + loading[i]);
            }
            //Print out process on the correct label
            switch (name) {
                case "ica":
                    //Ica
                    if (loading[0]) {
                        currentVolume = 0;
                        currentWeight = 0;
                        currentItem = 0;
                        guiSemaphore.printIcaStatus("Full");
                        Thread.sleep(5000);
                        guiSemaphore.printIca("");
                        guiSemaphore.printIcaNumbers(0, 0, 0);
                    } else {
                        isRunning = false;
                    }
                    break;
                case "coop":
                    //Coop
                    if (loading[1]) {
                        currentVolume = 0;
                        currentWeight = 0;
                        currentItem = 0;
                        guiSemaphore.printCoopStatus("Full");
                        Thread.sleep(5000);
                        guiSemaphore.printCoop("");
                        guiSemaphore.printCoopNumbers(0, 0, 0);
                    } else {
                        isRunning = false;
                    }
                    break;
                case "citygross":
                    //Citygross
                    if (loading[2]) {
                        currentVolume = 0;
                        currentWeight = 0;
                        currentItem = 0;
                        guiSemaphore.printCityGrossStatus("Full");
                        Thread.sleep(5000);
                        guiSemaphore.printCityGross("");
                        guiSemaphore.printCityGrossNumbers(0, 0, 0);
                    } else {
                        isRunning = false;
                    }
                    break;
                default:
                    System.out.println("Default");
                    break;
            }
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printText(FoodItem foodItem) {
        System.out.println("Trying too print out info from truck");
        switch (name) {
            case "ica":
                guiSemaphore.printIca(foodItem.getName() + "\n");
                guiSemaphore.printIcaNumbers(currentItem, currentVolume, currentWeight);
                break;
            case "coop":
                guiSemaphore.printCoop(foodItem.getName() + "\n");
                guiSemaphore.printCoopNumbers(currentItem, currentVolume, currentWeight);
                break;
            case "citygross":
                guiSemaphore.printCityGross(foodItem.getName() + "\n");
                guiSemaphore.printCityGrossNumbers(currentItem, currentVolume, currentWeight);
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
