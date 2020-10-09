package lesson4.hw1;

public class Validate {
//check that input value is correct
    //check that file doesn't exist
    //check that storage support format
    //check that enough place
    //check that enough size
    //check that name of file < 10 simvolov

    public boolean validateParams(Storage storageFrom, Storage storageTo) throws Exception {
        return checkForMaxSize(storageFrom, storageTo) /*& checkFormat(storageFrom, storageTo)*/ & findById(storageFrom, storageTo);
    }

    public boolean checkForLengthName(Storage storage, String name) throws Exception {
        for (File file : storage.getFiles()) {
            if (file != null && name.length() > 10)
                return false;
        }
        return true;
    }
    public boolean checkInputValue(Storage storage, File file) throws Exception {
        if (storage == null && file == null) {
            return false;
        }
        return true;
        //throw new Exception("storage is null or file in DB is null! );
    }
    public boolean checkInputValue( File file) throws Exception {
        if ( file == null) {
            return false;
        }
        return true;
        //throw new Exception("storage is null or file in DB is null! );
    }


    private boolean checkForMaxSize(Storage storageFrom, Storage storageTo) {
        int sumFrom = 0;
        int sumTo = 0;
        for (File from : storageFrom.getFiles()) {
            if (from != null) {
                sumFrom += from.getSize();
            }
        }
        for (File to : storageTo.getFiles()) {
            if (to != null) {
                sumTo += to.getSize();
            }
        }
        return sumTo + sumFrom <= storageTo.getStorageMaxSize();
    }


   /* private boolean checkFormat(Storage storageFrom, Storage storageTo) {
        int count = 0;
        for (String from : storageFrom.getFormatsSupported()) {
            for (String to : storageTo.getFormatsSupported()) {

                if (from.equalsIgnoreCase(to)) {
                    count++;
                    break;
                }
            }
        }//если поддерживаются все  форматы стораджа фром то тру иначе фалс
        return count == storageFrom.getFormatsSupported().length;
    }*/

    //Валидация отсутствия файла fileFrom  в сторадже storageTo
    // если есть  файл с таким ай ди в хранилище получателе то мы должны идти
    // к проверке следующего файла
    private boolean findById(Storage storageFrom, Storage storageTo) throws Exception {

        for (File from : storageFrom.getFiles()) {
            for (File to : storageTo.getFiles()) {
                if (from != null && to != null && from.getId() == to.getId()) //если файл не налл и он есть в массиве
                    return false;
            }
        }
        //throw new Exception("File " + id + " already exist in storage " + storage.getId());
        return true;//если нет файла
    }
    //этот метод работает для добавления файла в хранилище, имена могут быть одинаковыми но разные ай ди
    public boolean findById(Storage storage, long id) throws Exception {

        for (File file : storage.getFiles()) {

            if (file != null && file.getId() == id) //если файл не налл и он есть в массиве
                return true;
            //throw new Exception("File " + id + " already exist in storage " + storage.getId());
        }
        return false;//если нет файла
    }
    //Проверяем свободное место (StorageSize)  в сторадже
    public boolean checkForSize(Storage storage, long fileSize) throws Exception {

        long usedSize = 0;
        for (File file : storage.getFiles()) {
            if (file != null)
                usedSize += file.getSize();

            if (usedSize + fileSize > storage.getStorageMaxSize())
                return false;
        }
        return true;
    }

   /* //валидация формата файла и хранилища куда будет грузится файл
    public boolean checkFormat(Storage storage, File file) {
        for (String format : storage.getFormatsSupported()) {
            if (format.equalsIgnoreCase(file.getFormat())) {
                return true;
            }
        }

        return false;
    }*/

    //проверка количества свободных ячеек для переноса всех файлов из одного стораджа в другой сторадж
    public boolean checkForFreePlace(Storage storage, int placeNeeded) throws Exception {
        int count = 0;
        for (File file : storage.getFiles()) {
            if (file == null)//если ячейка налл то каунт ++
                count++;

            if (count > placeNeeded)
                return false;
        }
        return true;
    }

}
