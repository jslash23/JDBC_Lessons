package hibernate.lesson4.hw1.repository;

import hibernate.lesson4.hw1.model.User;
import hibernate.lesson4.hw1.model.UserType;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class UserRepository {

    //1.проверим есть ли пользователь в системе
    //замапим все строки из файла UserDb.txt

    //2.сравним айдишники входного юзера и замапленых юзеров
    //если сходятся то кидаем эксепшн что такой юзер уже есть в бд
    //если нет то запишем юзера в базу данных с новым сгенерированным ай ди


    public static void registerUser(User user, String userPath) throws Exception {
        //save user to db (file)
        //юзер на вход передаётся без поля id потому что айдишник генерем мы
        //юзер ай ди не вводит


      int write =  writeToUserDb(readFromFile(userPath), user, userPath);//Маппинг запроса комнат в объект User c валидацией

    }

    private static StringBuffer readFromFile(String path) throws Exception {//

        StringBuffer res = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                res.append(line);//тут возможно не целый файл а записана какая то ерунда это нужно проверить
                res.append("\n");
            }

        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.out.println("Reading from file " + path + " failed");
        }

        return res;
    }


    public static int writeToUserDb(StringBuffer content, User user, String userPath) throws Exception {
        ArrayList<User> users = new ArrayList<>();


        User user1 = new User(0, "", "", "", UserType.USER);
        //User user2 = new User(0, "", "", "", UserType.USER, false);

        users.add(user1);
        //users.add(user2);
        User usName;

        // byte[] convBytes = lines[i].getBytes(); //

        String convString = content.toString();
        String[] lines = convString.trim().split(",");
        try {

            for (int i = 1; i < lines.length; ) {
                //пока не будет конца строки проходимся по всем стрингам

                    user1.setUserName(user.getUserName());
                //validate fields password
                i++;

                    user1.setPassword(user.getPassword());


                //validate fields Country
                i++;
                    user1.setCountry(user.getCountry());

                i++;

                    user1.setUserType(user.getUserType());


                        //если валидация сработала то запишем входные значения юзера в репозиторий
                        if ((user1.getUserName().equals(user.getUserName())) &&
                                user1.getPassword().equals(user.getPassword()) &&
                                user1.getCountry().equals(user.getCountry()) &&
                                user1.getUserType().equals(user.getUserType())) {

                            System.err.println("User : " + user.getUserName() + " already exists in Repository" );
                            return -1;
                            }
                i++;//тут переходим к индексу userName
                i++;
                i++;
                        }

        }catch (Exception e) {
            System.err.println("Fild id contains invalid data");
        }
        writeUserToRepository(user, userPath);
        return 0;
    }

    private static void writeUserToRepository(User user, String userPath) throws Exception {
        //1.преобразуем входные данные юзера в стринг
        //сгенерируем новый айдишник
        //2.запишем наш стринг в файл UserDb.txt


        try (BufferedWriter br = new BufferedWriter(new FileWriter(userPath, true))) {



            try {
                //сгенерируем айдишник и запишем его в файл
                long id = (CreateId(0, 200));

                br.append(Long.toString(id));
                br.append(",");

                br.append(user.getUserName());
                br.append(",");

                br.append(user.getPassword());
                br.append(",");

                br.append(user.getCountry());
                br.append(",");

                br.append(user.getUserType().toString());
                br.append(",");

                br.append("\n");


            } catch (Exception e) {
                System.err.println("Can't write to file");
            }
        }
    }

    public static Integer CreateId(Integer min, Integer max) {
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        return i;
    }
}


