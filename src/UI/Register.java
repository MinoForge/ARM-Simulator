package UI;

import javafx.beans.property.*;

//TODO Like everthing. Move packages, make used throughout. Write stuff.
public class Register {
    private IntegerProperty regNum;
    private LongProperty trueVal;
    private StringProperty binVal;
    private StringProperty octVal;
    private StringProperty decVal;
    private StringProperty hexVal;
    private byte[] bytes;
    private short length;


    public Register(Integer regNum) {
        this.regNum = new SimpleIntegerProperty(regNum);
        this.trueVal = new SimpleLongProperty();
        this.binVal = new SimpleStringProperty();
        this.octVal = new SimpleStringProperty();
        this.decVal = new SimpleStringProperty();
        this.hexVal = new SimpleStringProperty();
        this.setVals(1589L);
    }

    public Integer getRegNum() {
        return regNum.getValue();
    }

    public String getHexVal() {
        return hexVal.get();
    }

    public String getBinVal() {
        return binVal.get();
    }

    public String getDecVal() {
        return decVal.get();
    }

    public Long getTrueVal() {
        return trueVal.getValue();
    }

    public void setVals(Long newVal) {
        trueVal.set(newVal);
        String bin = Long.toString(newVal, 2);
        String dec = Long.toString(newVal, 10);
        String hex = Long.toString(newVal, 16);
//        System.out.println(bin + " :: " + dec + " :: " + hex);
        binVal.set(bin);
        decVal.set(dec);
        hexVal.set(hex);
    }

    public IntegerProperty regNumProperty() {
        return regNum;
    }

    public LongProperty trueValProperty() {
        return trueVal;
    }

    public StringProperty binValProperty() {
        return binVal;
    }

    public StringProperty decValProperty() {
        return decVal;
    }

    public StringProperty hexValProperty() {
        return hexVal;
    }
}
