package screens;

public interface GameConstants {

    // Ðàçìåðû ñòîëà
    public final int TABLE_HEIGHT = 220; //440
    public final int TABLE_WIDTH = 320 ; // 640

    public final int FIELD_WIGTH = TABLE_WIDTH - 40;
    public final int FIELD_HEIGTH = TABLE_HEIGHT - 40;

    public final int FIELD_TOP = 20;
    public final int FIELD_BOTTOM = TABLE_HEIGHT -20;
    public final int FIELD_LEFT = 20;
    public final int FIELD_RIGHT = TABLE_WIDTH -20;

    // Ðàçìåðû, ðàñïîëîæåíèÿ è øàã ïåðåìåùåíèÿ ìÿ÷à è ðàêåòîê
    public final int RACKET_LENGTH = FIELD_HEIGTH/5;
    public final int RACKET_WIDTH = FIELD_WIGTH/60;
    public final int BALL_SIZE = RACKET_LENGTH/4;
    public final int KID_RACKET_X = FIELD_RIGHT - RACKET_WIDTH;
    public final int KID_RACKET_Y_START = FIELD_HEIGTH/2;
    public final int COMPUTER_RACKET_X = FIELD_LEFT;
    public final int COMPUTER_RACKET_Y_START = FIELD_HEIGTH/2;

    // Èçìåíÿåìûå âåëè÷èíû
    public final int RACKET_INCREMENT = 10; //3
    public final int BALL_INCREMENT = 10; //3

    public final int WINNING_SCORE = 21;
    public final int SLEEP_TIME = 10;

    // Ìàêñèìàëüíûå è ìèíèìàëüíûå êîîðäèíàòû ìÿ÷à
    public final int BALL_MIN_X = FIELD_LEFT;
    public final int BALL_MIN_Y = FIELD_TOP;
    public final int BALL_MAX_X = FIELD_RIGHT - BALL_SIZE;
    public final int BALL_MAX_Y = FIELD_BOTTOM - BALL_SIZE;

    // Íà÷àëüíûå êîîðäèíàòû ìÿ÷à
    public final int BALL_START_X = TABLE_WIDTH/2 - BALL_SIZE/2;
    public final int BALL_START_Y = TABLE_HEIGHT/2 - BALL_SIZE/2;

}