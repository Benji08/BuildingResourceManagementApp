package admin;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
/**
 * Auxiliary class
 * It is used to store information from user about the attributes of a classroom
 */
public class ClassroomFilterData {
    private Integer fSeats;
    private Integer fComputers;
    private Integer fPrinters;
    private Integer fProjectors;
    private String fDate;
    private Integer fStart;
    private Integer fEnd;


    public Integer getfSeats() {
        return fSeats;
    }

    public void setfSeats(Integer fSeats) {
        this.fSeats = fSeats;
    }

    public Integer getfComputers() {
        return fComputers;
    }

    public void setfComputers(Integer fComputers) {
        this.fComputers = fComputers;
    }

    public Integer getfPrinters() {
        return fPrinters;
    }

    public void setfPrinters(Integer fPrinters) {
        this.fPrinters = fPrinters;
    }

    public Integer getfProjectors() {
        return fProjectors;
    }

    public void setfProjectors(Integer fProjectors) {
        this.fProjectors = fProjectors;
    }

    public String getfDate() {
        return fDate;
    }

    public void setfDate(String fDate) {
        this.fDate = fDate;
    }

    public Integer getfStart() {
        return fStart;
    }

    public void setfStart(Integer fStart) {
        this.fStart = fStart;
    }

    public Integer getfEnd() {
        return fEnd;
    }

    public void setfEnd(Integer fEnd) {
        this.fEnd = fEnd;
    }
}
