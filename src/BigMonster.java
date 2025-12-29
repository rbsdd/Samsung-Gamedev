import java.util.Random;
import java.util.Scanner;

public class BigMonster extends Monster {
    private String image = "\uD83D\uDC79";
    Random r = new Random(); // Добавлено: создание Random

    BigMonster(int sizeBoard) {
        super(sizeBoard);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean taskMonster(int difficultGame) {
        System.out.println("Внимание! Вопрос:");
        if (difficultGame == 1) {
            return super.taskMonster(difficultGame); // Исправлено: вызов метода родителя
        } else {
            int x = r.nextInt(10 * difficultGame - 10, 10 * difficultGame); // Исправлены границы
            int y = r.nextInt(50 * difficultGame - 50, 50 * difficultGame);
            int z = r.nextInt(100 * difficultGame - 100, 100 * difficultGame);
            int trueAnswer = x * y - z;
            System.out.println("Реши пример: " + x + " * " + y + " - " + z + " = ?");
            Scanner sc = new Scanner(System.in);
            int ans = sc.nextInt();
            if (trueAnswer == ans) {
                System.out.println("Верно! Ты победил монстра!");
                return true;
            }
            System.out.println("Ты проиграл эту битву!");
            return false;
        }
    }
}
