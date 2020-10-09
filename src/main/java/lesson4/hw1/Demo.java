package lesson4.hw1;

import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) throws Exception {

        String formats = "txt, doc, xdoc";

        File book1 = new File(10L, "Novels", "txt", 50L);
        File book2 = new File(11L, "TomSoyer", "txt", 50L);
        File book3 = new File(12L, "Komiks", "doc", 50L);
        File book4 = new File(12L, "Huika", "doc", 50L);

        File book5 = new File(12L, "toy", "doc", 50L);

        ArrayList<File> files = new ArrayList<>();
        files.add(book1);
        files.add(book2);
        files.add(book3);


        FileDAO fileDAO = new FileDAO();
        StorageDAO storageDAO = new StorageDAO();

        ArrayList<Storage> storages = new ArrayList<>();


        Storage firtStorage = new Storage(1L, null, formats, "Ukraine", 100L);
        Storage secondStorage = new Storage(2L, null, formats, "Poland", 100L);

        //Testing Contoller metods
        Controller.putAll(secondStorage, files);
        System.out.println(secondStorage);

        Controller.put(firtStorage, book1);
        System.out.println(firtStorage);

        Controller.delete(secondStorage, book1);

        Controller.transferAll(secondStorage, firtStorage);
        System.out.println(firtStorage);

        Controller.transferFile(secondStorage, firtStorage, 2l);
        System.out.println(firtStorage);

        //Testing FileDAO
        fileDAO.save(book4);
        System.out.println(fileDAO.getAllFiles());

        System.out.println(fileDAO.findById(12));
        fileDAO.save(book3);
        System.out.println(fileDAO.findById(12));
        fileDAO.update(book5);
        fileDAO.delete(12);

        //Testing StorageDAO
        storageDAO.save(firtStorage);
        storageDAO.delete(1);
        storageDAO.update(secondStorage);
        System.out.println(storageDAO.findById(12));
    }
}
