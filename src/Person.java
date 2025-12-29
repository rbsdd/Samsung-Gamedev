import java.util.Random;

public class Person {
    protected int x, y;
    private String image = "\uD83E\uDDD9\u200D";
    private int live = 3;
    Random r = new Random();

    Person(int sizeBoard) {
        y = sizeBoard;
        int n = r.nextInt(sizeBoard) + 1;
        x = n;
    }

    Person(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Person() {
        this(1, 1);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLive() {
        return live;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean moveCorrect(int x, int y) {
        if (x < 1 || x > 5 || y < 1 || y > 5) return false;
        return (Math.abs(this.x - x) == 1 && this.y == y) || 
               (Math.abs(this.y - y) == 1 && this.x == x);
    }

    void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void downLive() {
        live--;
        System.out.println("Вы потеряли жизнь! Осталось жизней: " + live);
    }
}
