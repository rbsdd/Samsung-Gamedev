import java.util.Random;
import java.util.Scanner;

public class Monster {
    private String image = "\uD83E\uDDDF\u200D";
    private final int x, y;
    Random r = new Random();

    Monster(int sizeBoard) {
        this.y = r.nextInt(sizeBoard - 1);
        this.x = r.nextInt(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean conflictPerson(int perX, int perY) {
        return perY - 1 == this.y && perX - 1 == this.x;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean taskMonster(int difficultGame) {
        System.out.println("Внимание! Вопрос:");
        Scanner sc = new Scanner(System.in);
        int x, y, trueAnswer;
        
        switch (difficultGame) {
            case 1 -> {
                x = r.nextInt(100);
                y = r.nextInt(100);
                trueAnswer = x + y;
                System.out.println("Реши пример: " + x + " + " + y + " = ?");
            }
            case 2 -> {
                x = r.nextInt(100);
                y = r.nextInt(100);
                trueAnswer = x - y;
                System.out.println("Реши пример: " + x + " - " + y + " = ?");
            }
            case 3 -> {
                x = r.nextInt(100);
                y = r.nextInt(100);
                trueAnswer = x * y;
                System.out.println("Реши пример: " + x + " * " + y + " = ?");
            }
            default -> {
                System.out.println("Некорректная сложность!");
                sc.close();
                return false;
            }
        }
        
        int ans = sc.nextInt();
        if (trueAnswer == ans) {
            System.out.println("Верно! Ты победил монстра");
            sc.close();
            return true;
        }
        System.out.println("Ты проиграл эту битву!");
        sc.close();
        return false;
    }
}
