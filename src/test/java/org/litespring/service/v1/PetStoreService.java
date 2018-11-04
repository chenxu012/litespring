package org.litespring.service.v1;

public class PetStoreService {

    private String value;
    private PetPro petPro;

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
}
