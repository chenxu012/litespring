package org.litespring.service.v1;

public class PetStoreService {

    private String value;
    private PetPro petPro;
    private int version;
    private boolean flag;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public PetPro getPetPro() {
        return petPro;
    }

    public void setPetPro(PetPro petPro) {
        this.petPro = petPro;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
