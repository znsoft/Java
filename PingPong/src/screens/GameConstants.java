package screens;

public interface GameConstants
{
    // Размеры стола
    public final int X0 = 0;
    public final int Y0 = 0;
    public final int TABLE_WIDTH = 320;
    public final int TABLE_HEIGHT = 220;
    public final int TABLE_TOP = 12;
    public final int TABLE_BOTTOM = 180;

    public final int FIELD_WIDTH = TABLE_WIDTH-20;
    public final int FIELD_HEIGHT = TABLE_HEIGHT-20;
    public final int FIELD_X0 = 10;
    public final int FIELD_Y0 = 10;
    public final int FIELD_LEFT = 20;
    public final int FIELD_TOP = 20;
    public final int FIELD_BOTTOM = TABLE_HEIGHT-10;
    // Шаг перемещения мяча в пикселях!
    //public final int BALL_INCREMENT = 10;
    public final int RACKET_INCREMENT = 1; // 2
    // Максимальные и минимальные координаты мяча
    public final int BALL_SIZE = 10;
    public final int BALL_MIN_X = FIELD_LEFT-BALL_SIZE;
    public final int BALL_MIN_Y = FIELD_TOP-BALL_SIZE;
    public final int BALL_MAX_X = TABLE_WIDTH - BALL_SIZE;
    public final int BALL_MAX_Y = FIELD_BOTTOM - BALL_SIZE;
    // Начальные координаты мяча
    public final int BALL_START_X = TABLE_WIDTH/2 - BALL_SIZE/2;
    public final int BALL_START_Y = TABLE_HEIGHT/2- BALL_SIZE/2;
    //Размеры, расположения и шаг перемещения ракеток
    public final int KID_RACKET_X = FIELD_WIDTH;
    public final int KID_RACKET_Y_START = FIELD_HEIGHT/2;
    public final int COMPUTER_RACKET_X = FIELD_X0 + 5;
    public final int COMPUTER_RACKET_Y_START = FIELD_HEIGHT / 2;
    public final int RACKET_LENGTH = 30;
    public final int RACKET_WIDTH = 5;
    public final int WINNING_SCORE = 10;
    // Замедлить быстрые компьютеры – измените это значение,
    // если понадобится
    public final int SLEEP_TIME = 10; //время в миллисекундах 10
}
