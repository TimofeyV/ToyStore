package Controller;
import Model.Market;
import java.util.Scanner;
import Data.WriteToFile;


public class MarketController {
    private static final String AGAIN = "Попробуйте еще раз\n";
    Market market;

    public MarketController(){
        this.market = new Market();
    }

    public void startGame(){
        WriteToFile.writeUserData("Игра началась!");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while(flag) {
            System.out.println("Выберите действие:");
            System.out.println(
                    "1 - добавить игрушку для розыгрыша\n"
                            + "2 - удалить все игрушки\n"
                            + "3 - показать все игрушки\n"
                            + "4 - запустить розыгрыш\n"
                            + "5 - прекратить игру");

            switch (scanner.nextLine()){
                case "1":
                    try{
                        System.out.println("Назовите игрушку и ее вес через пробел('имя' 'вес'):");
                        String input = scanner.nextLine();
                        int lastIndexOf = input.lastIndexOf(" ");
                        String name = input.substring(0, lastIndexOf);
                        int dropChance = Integer.parseInt(input.substring(lastIndexOf+1));
                        market.addToy(name, dropChance);
                        WriteToFile.writeUserData("Добавлена игрушка '"+ name + "' с весом " + dropChance);
                    }catch (NumberFormatException e){
                        System.out.println("Вес введен в неверном формате\n" + AGAIN);
                        break;
                    }catch (StringIndexOutOfBoundsException e){
                        System.out.println("Неверное кол-во параметров\n" + AGAIN);
                        break;
                    }
                    break;
                case "2":
                    try{
                        market.removeAll();
                        WriteToFile.writeUserData("Список игрушек очищен");
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage() + "\n"+AGAIN);
                        break;
                    }
                    break;
                case "3":
                    market.printAlL();
                    break;
                case "4":
                    try {
                        market.fullFillQueue();
                        for (int i = 0; i < Market.QUEUE_SIZE; i++) {
                            System.out.format("Игрок %d выиграл %s\n", i + 1, market.getToy().getName());
                            WriteToFile.writeUserData("Игрок " + i + 1 +" выиграл " + market.getToy().getName());
                        }
                    }catch (NullPointerException e){
                        System.out.println("Разыгрывать нечего, добавьте хотя бы 1 игрушку\n");
                        break;
                    }
                    break;
                case "5":
                    System.out.println("Игра завершена!");
                    WriteToFile.writeUserData("Игра завершена!");
                    flag = false;
            }
        }
    }
}