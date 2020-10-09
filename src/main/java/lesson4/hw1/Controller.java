package lesson4.hw1;

import java.util.List;

public class Controller {

    /*

     Если операцию выполнить невозможно, выбрасывайте исключение (подумайте какое) с текстом ошибки.
      В тексте пишите ее причину и обязательно включайте id файла и id хранилища где возникла проблема
       Файлы считаем одинаковыми если у них равные id и имя
     */


    //добавляет файл в хранилище, гарантируется что файл уже есть в условной БД


    //добавляет список файлов в хранилище. гарантируется что файл уже есть в условной БД
    public static Storage putAll(Storage storage, List<File> files) throws Exception {
        Validate test = new Validate();

        //Проверка наличия  значений  в  storage и file
        for (File file: files) {
            if (!test.checkInputValue(file)) {////////////////////////////////////////////////////////////////////!!!!!!
                throw new Exception("storage is null or file in DB is null!");
            }
        }
        //Проверка наличия  значений  в  storage и file
        setAllFiles(storage, files);
        return storage;
    }


    //Нужно загрузить конкретный файл в конкретное хранилище
    public static Storage put(Storage storage, File file) throws Exception {

        Validate test = new Validate();
        //Проверка наличия  значений  в  storage и file
        if (!test.checkInputValue(storage, file)) {
            throw new Exception("storage is null or file in DB is null!");
        }
        //Проверка отсутствия загружаемого файла в хранилище
        if (test.findById(storage, file.getId())) {
            throw new Exception("File " + file.getId() + " already exist in storage " + storage.getId());
        }
        //Валидация формата файла и хранилища
        /*if (!test.checkFormat(storage, file)) {
            throw new Exception(file.getFormat() + " isn't supported in storage " + storage.getId());
        }*/
        //checkForFreePlace(storage)  проверка свободных ячеек в хранилище
        if (!test.checkForFreePlace(storage, storage.getFiles().length)) {
            throw new Exception("No free space in storage " + storage.getId());
        }
        //Проверка свободного места в хранилище
        if (!test.checkForSize(storage, file.getSize())) {
            throw new Exception("No free space in storage " + storage.getId());
        }
        //Проверка длины имени файла
        if (!test.checkForLengthName(storage, file.getName())) {
            throw new Exception("Name of file " + file.getName() + " can not be more than 10 simvols");
        }
        //запись файла в хранилище
        setFile(storage, file);
        return storage;
    }


    //Удаления нужного файла из заданого хранилища
    public static void delete(Storage storage, File file) throws Exception {
        Validate test = new Validate();
        //checkInputValue(storage, file);
        int index = 0;
        if (storage == null)
            throw new Exception("storage is null");
        if (file == null)
            throw new Exception("file in DB is null");

        if (!test.findById(storage, file.getId())) {
            throw new Exception(storage.getId() + " is not found");
        }
        for (File f : storage.getFiles()) {
            if (f != null && f.getId().equals(file.getId())) {//если файл с таким ай ди мы нашли

                storage.getFiles()[index] = null;
            }
            index++;
        }
    }

    //трансфер всех файлов
    public static Storage transferAll(Storage storageFrom, Storage storageTo) throws Exception {

        Validate test = new Validate();

        if (test.validateParams(storageFrom, storageTo)) {
            int count = 0;
            for (File fileFrom : storageFrom.getFiles()) {
                if (fileFrom != null) {
                    for (File fileTo : storageTo.getFiles()) {
                        if (fileTo == null) {//Если мы нашли пустую ячейку
                            // Проверка свободного места в хранилище
                            storageTo.getFiles()[count] = fileFrom;//ТО загружаем в эту ячейку наш файл
                            break;
                        }
                        count++;
                    }
                }
                count = 0;
            }
        }
        return storageTo;
    }


    //Найти файл по ай ди  в хранилище storageFrom  и загрузить его в хранилище storageTo
    public static Storage transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        Validate test = new Validate();
        //ищем файл по айди в хранилище storageFrom
        for (File fileFrom : storageFrom.getFiles()) {
            if (!test.findById(storageFrom, fileFrom.getId())) {
                throw new Exception("There exist file " + id + "in " + storageFrom.getId());
            }

            //проверяем нету ли уже такого файла в хранилище  storageTo
            for (File fileTo : storageTo.getFiles()) {
                if (test.findById(storageTo, fileFrom.getId())) {
                    throw new Exception("file exist" + id + "in " + storageTo.getId());
                }

               /* //проверка совместимости форматов файла и хранилища  storageTo
                if (!test.checkFormat(storageTo, fileFrom)) {
                    throw new Exception(fileFrom + " isn't supported in storage " + storageTo.getId());
                }*/

                //Проверка свободного места в хранилище storageTo
                if (!test.checkForFreePlace(storageTo, storageTo.getFiles().length)) {
                    throw new Exception("No free space in storage " + storageTo.getId());
                }

                //Запись файла из fileFrom в пустое место в хранилище storageTo
                setFile(storageTo, fileFrom);
                return storageTo;
            }
        }
        return storageTo;
    }

    //Загружаем наш файл в перую свободную ячейку
    private static Storage setFile(Storage storage, File file) throws Exception {

        int countPlaced = 0;
        for (File f : storage.getFiles()) {
            if (f != null)//если ячейка не пустая
                countPlaced++;//считаем сколько занятых ячеек
        }
        if (countPlaced > storage.getFiles().length)//если количество занятых ячеек больше длины
            // стораджа то ретёрн налл
            throw new Exception("there is no empty place in storage " + storage.getId());

        int index = 0;
        for (File f : storage.getFiles()) {
            //если ячейка пустая то индекс файла в который мы будем  записывать файл  будет = 0
            if (f == null) {
                storage.getFiles()[index] = file;
                return storage;
            }
            index++;
        }
        return storage;
    }

    //Загружаем наш файл в свободные  ячейки
    private static Storage setAllFiles(Storage storage, List<File> files) throws Exception {

        int countPlaced = 0;
        for (File f : storage.getFiles()) {
            if (f != null)//если ячейка не пустая
                countPlaced++;//считаем сколько занятых ячеек
        }
        if (countPlaced > storage.getFiles().length)//если количество занятых ячеек больше длины
            // стораджа то ретёрн налл
            throw new Exception("there is no empty place in storage " + storage.getId());

        int index = 0;
        for (File f : storage.getFiles()) {
            //если ячейка пустая то индекс файла в который мы будем  записывать файл  будет = 0
            for (File file :  files) {
                if (f == null) {
                    storage.getFiles()[index] = file;
                }
                index++;
            }

        }
        return storage;
    }

}

