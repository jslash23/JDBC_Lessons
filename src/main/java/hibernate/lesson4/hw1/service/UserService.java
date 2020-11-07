package hibernate.lesson4.hw1.service;

import hibernate.lesson4.hw1.exceptions.BadRequestException;
import hibernate.lesson4.hw1.model.User;
import hibernate.lesson4.hw1.repository.UserRepository;

import java.util.Scanner;

public class UserService  {

    private UserRepository userRepository = new UserRepository();

    public void registerUser(User user, String userPath) throws Exception {

        //check business logic
        //объекты не должны содержать пустых полей
        //if busines logic ok return user
        // else return exeption, потому что логика не прошла



        //возвращаем то что вернул метод registerUser нашего userRepository

            if (validate(user) && validateUserAge() ) {
                //1.Проверим что пользователю есть 18 лет
               // validateUserAge();
                //2.Проверим что пользователя нет в базе данных
                //
                ///validateUserExistInDb(user);

                 userRepository.registerUser(user, userPath);
            }
       else{
                throw new BadRequestException("you filds can't be null!");
            }
    }



    public boolean validate(User user) {

        //Проверим входные поля юзера
        // User newUser = new User(010,"Dimon","","Ukraine", UserType.USER,false);
        if (!user.getUserName().isEmpty() && !user.getCountry().isEmpty() &&
                !(user.getUserType().toString().isEmpty())){
            System.out.println("All user fields is right");
            return true;
        }

        return false;
    }
    private static boolean validateUserAge(){
        //просим ввести возраст юзера
        //юзер вводит возраст
        //если возраст меньше 18 лет то возвращаем фолс
        //если введено не числовое значение то эксепшн

        try {
            Scanner scanner = new Scanner(System.in);

            //using scanner
            System.out.println("Please enter you age");
            String strage =  scanner.nextLine();


            if (strage.matches("^[0-9]+$")){
                int age = Integer.parseInt(strage);
                if (age  >= 18){
                    return true;
                }
            }
        }
        catch (Exception e){
            System.out.println("You enter invalid data!");
        }
        return false;
    }
}
