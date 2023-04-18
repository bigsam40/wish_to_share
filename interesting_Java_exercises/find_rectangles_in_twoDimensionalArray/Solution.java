import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<String> rectangleDots;

    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{{1, 1, 0, 0}, {1, 1, 0, 0}, {1, 1, 0, 0}, {1, 1, 0, 1}};
        byte[][] a2 = new byte[][]{{1, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 1}};

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        rectangleDots = new ArrayList<>(); // создаём новый список для всех точек, где значение 1
        int rectangleCount = 0;
        for (int y = 0; y < a.length; y++) { //поиск "слева-направо", "сверху-вниз"
            for (int x = 0; x < a[y].length; x++) {
                if (a[y][x] == 1) { // если наткнулись на часть прямоугольника
                    //проверяем есть ли такая часть в списке rectangleDots
                    if (!rectangleDots.contains(String.format("%d,%d", x, y))) {
                        //если нет, то это новый прямоугольник
                        //вызываем метод, который добавит в список все точки этого прямоугольника
                        searchFullRectangle(a, x, y);
                        //и увеличиваем счётчик
                        rectangleCount++;
                    }
                }
            }
        }
        return rectangleCount;
    }

    public static void searchFullRectangle(byte[][] a, int x, int y) {
        //этот метод бежит по тому же самому массиву, но начинает путь с координат, которые получает в параметрах
        //каждый раз когда находит 1, добавляет координаты в список
        //когда на оси x встречается 0 или конец строки то переходит на следующую строку y
        //когда на очередной строке сразу же встречаем 0 или до этого была последняя строка, метод заканчивает работу
        int xInc = 0;
        int yInc = 0;
        while ((y + yInc) != a.length && a[y + yInc][x] != 0) {
            while ((x + xInc) != a[y].length && a[y + yInc][x + xInc] != 0) {
                rectangleDots.add(String.format("%d,%d", x + xInc, y + yInc));
                xInc++;
            }
            yInc++;
            xInc = 0;
        }
    }
}
