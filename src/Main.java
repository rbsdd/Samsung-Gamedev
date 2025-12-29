import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String castle = "\uD83C\uDFF0";
        int sizeBoard = 5;

        Person person = new Person(sizeBoard);
        int step = 0;

        String[][] board = new String[sizeBoard][sizeBoard];
        for (int y = 0; y < sizeBoard; y++) {
            for (int x = 0; x < sizeBoard; x++) {
                board[y][x] = "  ";
            }
        }

        int countMonster = sizeBoard * sizeBoard - sizeBoard - 5;
        Random r = new Random();

        Monster[] arrMonster = new Monster[countMonster + 1];
        int count = 0;
        Monster test;
        while (count < countMonster) {
            if (r.nextBoolean()) {
                test = new Monster(sizeBoard);
            } else {
                test = new BigMonster(sizeBoard);
            }
            if (board[test.getY()][test.getX()].equals("  ")) {
                board[test.getY()][test.getX()] = test.getImage();
                arrMonster[count] = test;
                count++;
            }
        }

        int castleX = r.nextInt(sizeBoard);
        int castleY = 0;

       
        while (!board[castleY][castleX].equals("  ")) {
            castleX = r.nextInt(sizeBoard);
        }
        board[castleY][castleX] = castle;

        System.out.println("Привет! Ты пойдёшь со мной на тёмный остров сражаться против монстров? (Напиши: ДА или НЕТ)");

        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
        System.out.println("Ваш ответ:\t" + answer);

        switch (answer.toUpperCase()) { 
            case "ДА" -> {
                System.out.println("Выбери сложность игры(от 1 до 3):");
                int difficultGame = sc.nextInt();
                System.out.println("Ваш вариант:\t" + difficultGame);
                while (person.getLive() > 0) { // Добавлено: условие по жизням
                    board[person.getY() - 1][person.getX() - 1] = person.getImage();
                    outputBoard(board, person.getLive());
                    System.out.println("Введите куда вы пойдёте (ход возможен только по вертикали и горизонтали на одну клетку;" +
                            "\nКоординаты персонажа - (x: " + person.getX() + ", y: " + person.getY() + "))");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    if (person.moveCorrect(x, y)) {
                        String next = board[y - 1][x - 1];
                        if (next.equals("  ")) {
                            board[person.getY() - 1][person.getX() - 1] = "  ";
                            person.move(x, y);
                            step++;
                            System.out.println("Ход правильный; Новые координаты: " + person.getX() + ", " + person.getY() +
                                    "\nХод номер: " + step);
                        } else if (next.equals(castle)) {
                            System.out.println("Вы победили!");
                            break;
                        } else {
                            boolean monsterDefeated = false;
                            for (Monster monster : arrMonster) {
                                if (monster != null && monster.conflictPerson(x, y)) {
                                    if (monster.taskMonster(difficultGame)) {
                                        board[person.getY() - 1][person.getX() - 1] = "  ";
                                        person.move(x, y);
                                        monsterDefeated = true;
                                        // Удаляем монстра с поля
                                        board[y - 1][x - 1] = "  ";
                                    } else {
                                        person.downLive();
                                    }
                                    break;
                                }
                            }
                            if (!monsterDefeated) {
                                System.out.println("Некорректный ход!");
                            }
                        }
                    } else {
                        System.out.println("Некорректный ход(читай внимательно)");
                    }
                }
                if (person.getLive() <= 0) {
                    System.out.println("Игра окончена! У вас закончились жизни.");
                }
            }
            case "НЕТ" -> System.out.println("Жаль, приходи еще!");
            default -> System.out.println("Данные введены некорректно");
        }
        sc.close();
    }

    static void outputBoard(String[][] board, int live) {
        String leftBlock = "| ";
        String rightBlock = "|";
        String wall = "+ —— + —— + —— + —— + —— +";

        for (String[] raw : board) {
            System.out.println(wall);
            for (String col : raw) {
                System.out.print(leftBlock + col + " ");
            }
            System.out.println(rightBlock);
        }
        System.out.println(wall);
        System.out.println("Количество жизней:\t" + live + "\n");
    }
}
