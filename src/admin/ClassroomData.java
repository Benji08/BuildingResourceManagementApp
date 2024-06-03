package admin;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Auxiliary class
 * It is used to store information retrieved from the database about the attributes of a given classroom
 */
public class ClassroomData {
    private final StringProperty classNumber;
    private final StringProperty floor;
    private final StringProperty numberOfSeats;
    private final IntegerProperty numberOfComputers;
    private final IntegerProperty numberOfPrinters;

    public int getNumberOfComputers() {
        return numberOfComputers.get();
    }

    public IntegerProperty numberOfComputersProperty() {
        return numberOfComputers;
    }

    public void setNumberOfComputers(int numberOfComputers) {
        this.numberOfComputers.set(numberOfComputers);
    }

    public int getNumberOfPrinters() {
        return numberOfPrinters.get();
    }

    public IntegerProperty numberOfPrintersProperty() {
        return numberOfPrinters;
    }

    public void setNumberOfPrinters(int numberOfPrinters) {
        this.numberOfPrinters.set(numberOfPrinters);
    }

    public ClassroomData(String classNumber, String floor, String numberOfSeats, Integer numberOfComputers, Integer numberOfPrinters){
        this.classNumber = new SimpleStringProperty(classNumber);
        this.floor = new SimpleStringProperty(floor);
        this.numberOfSeats = new SimpleStringProperty(numberOfSeats);
        this.numberOfComputers = new SimpleIntegerProperty(numberOfComputers);
        this.numberOfPrinters = new SimpleIntegerProperty(numberOfPrinters);
    }

    public String getClassNumber() {
        return classNumber.get();
    }

    public StringProperty classNumberProperty() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber.set(classNumber);
    }

    public String getFloor() {
        return floor.get();
    }

    public StringProperty floorProperty() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor.set(floor);
    }

    public String getNumberOfSeats() {
        return numberOfSeats.get();
    }

    public StringProperty numberOfSeatsProperty() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(String numberOfSeats) {
        this.numberOfSeats.set(numberOfSeats);
    }
}
