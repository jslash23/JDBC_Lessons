package lesson4.hw1;

import java.util.Arrays;

public class Storage {
    Long id;
    private File[] files = new File[20];
    String formatsSupported;
    String storageCountry;
    Long storageMaxSize;

    public Storage(Long id, File[] files, String formatsSupported, String storageCountry, Long storageMaxSize) {
        this.id = id;
        this.files = files;
        this.formatsSupported = formatsSupported;
        this.storageCountry = storageCountry;
        this.storageMaxSize = storageMaxSize;
    }

    public Long getId() {
        return id;
    }

    public File[] getFiles() {
        return files;
    }

    public String getFormatsSupported() {
        return formatsSupported;
    }

    public String getStorageCountry() {
        return storageCountry;
    }

    public Long getStorageMaxSize() {
        return storageMaxSize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFiles(File[] files) {
        this.files = files;
    }

    public void setFormatsSupported(String formatsSupported) {
        this.formatsSupported = formatsSupported;
    }

    public void setStorageCountry(String storageCountry) {
        this.storageCountry = storageCountry;
    }

    public void setStorageMaxSize(Long storageMaxSize) {
        this.storageMaxSize = storageMaxSize;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", files=" + Arrays.toString(files) +
                ", formatsSupported=" + formatsSupported +
                ", storageCountry='" + storageCountry + '\'' +
                ", storageMaxSize=" + storageMaxSize +
                '}';
    }
}
