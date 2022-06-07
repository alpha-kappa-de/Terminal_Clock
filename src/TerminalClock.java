import java.io.Console;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.logging.ConsoleHandler;

public class TerminalClock {
    private String[] SPACE;
    private String[] COLON;
    private String[] ZERO;
    private String[] ONE;
    private String[] TWO;
    private String[] THREE;
    private String[] FOUR;
    private String[] FIVE;
    private String[] SIX;
    private String[] SEVEN;
    private String[] EIGHT;
    private String[] NINE;


    /**
     * Constructor for a TerminalClock Object.
     * @param clockArtStyle The ArtStyle or font which will be used. Currently, there are these styles: 1.
     * @throws IllegalArgumentException if there is no ArtStyle with this number.
     */
    public TerminalClock(int clockArtStyle) throws IllegalArgumentException {
        if (clockArtStyle != 1) {
            throw new IllegalArgumentException("No clockArtyStyle with number" + clockArtStyle);
        } else {
            changeClockArtStyle(clockArtStyle);
        }
    }

    /**
     * This is the ArtStyle #1 and is used for the changeClockArtStyle method.
     * @param number the number's ClockArt1 Style you want. [0-9] for "0"-"9", [-1] for the ":" sign or [-2] for space.
     * @return String[] in the ClockArt1 Style.
     * @throws IllegalArgumentException if number is other than [-2, 9].
     */
    private String[] ClockArtStyle1(int number) throws IllegalArgumentException {
        return switch (number) {
            case 0 -> new String[]{
                    " XXXXXXX  ",
                    " XX   XX  ",
                    " XX   XX  ",
                    " XX   XX  ",
                    " XXXXXXX  "};
            case 1 -> new String[]{
                    "    XX    ",
                    "   XXX    ",
                    "    XX    ",
                    "    XX    ",
                    "    XX    "};
            case 2 -> new String[]{
                    " XXXXXX   ",
                    "      XX  ",
                    "  XXXXX   ",
                    " XX       ",
                    " XXXXXXX  "};
            case 3 -> new String[]{
                    " XXXXXX   ",
                    "      XX  ",
                    "  XXXXX   ",
                    "      XX  ",
                    " XXXXXX   "};
            case 4 -> new String[]{
                    " XX   XX  ",
                    " XX   XX  ",
                    " XXXXXXX  ",
                    "      XX  ",
                    "      XX  "};
            case 5 -> new String[]{
                    " XXXXXXX  ",
                    " XX       ",
                    " XXXXXXX  ",
                    "      XX  ",
                    " XXXXXXX  "};
            case 6 -> new String[]{
                    "  XXXXXX  ",
                    " XX       ",
                    " XXXXXXX  ",
                    " XX    XX ",
                    "  XXXXXX  "};
            case 7 -> new String[]{
                    " XXXXXXX  ",
                    "      XX  ",
                    "     XX   ",
                    "    XX    ",
                    "    XX    "};
            case 8 -> new String[]{
                    "  XXXXX   ",
                    " XX   XX  ",
                    "  XXXXX   ",
                    " XX   XX  ",
                    "  XXXXX   "};
            case 9 -> new String[]{
                    "  XXXXX   ",
                    " XX   XX  ",
                    "  XXXXXX  ",
                    "      XX  ",
                    "  XXXXX   "};
            case -1 -> new String[]{
                    "          ",
                    "    XX    ",
                    "          ",
                    "    XX    ",
                    "          "};
            case -2 -> new String[]{
                    "          ",
                    "          ",
                    "          ",
                    "          ",
                    "          "};
            default -> throw new IllegalArgumentException("number must be between -1 and 9");
        };
    }

    /**
     * Gets the ClockArt and sets it for this Object.
     * @param clockArtStyle The ArtStyle or font which will be used.
     * @throws IllegalArgumentException if there is no ArtStyle with this number.
     */
    public void changeClockArtStyle(int clockArtStyle) throws IllegalArgumentException {
        switch (clockArtStyle) {
            case 1 -> {
                SPACE = ClockArtStyle1(-2);
                COLON = ClockArtStyle1(-1);
                ZERO = ClockArtStyle1(0);
                ONE = ClockArtStyle1(1);
                TWO = ClockArtStyle1(2);
                THREE = ClockArtStyle1(3);
                FOUR = ClockArtStyle1(4);
                FIVE = ClockArtStyle1(5);
                SIX = ClockArtStyle1(6);
                SEVEN = ClockArtStyle1(7);
                EIGHT = ClockArtStyle1(8);
                NINE = ClockArtStyle1(9);
            }
            default -> throw new IllegalArgumentException("No clockArtyStyle with number " + clockArtStyle);
        }

    }

    /**
     * Prints the clock to stdout
     * @param printColon true to print the colon, false to print space instead
     */
    private void printClock(boolean printColon) {
        String now = LocalTime.now().format(DateTimeFormatter.ISO_TIME);

        for (int i = 0; i < SPACE.length; i++) {
            System.out.print(getNumberLine(Integer.parseInt(now.substring(0, 1)), i));
            System.out.print(getNumberLine(Integer.parseInt(now.substring(1, 2)), i));
            if (printColon) {
                System.out.print(getNumberLine(-1, i));
            } else {
                System.out.print(getNumberLine(-2, i));
            }
            System.out.print(getNumberLine(Integer.parseInt(now.substring(3, 4)), i));
            System.out.println(getNumberLine(Integer.parseInt(now.substring(4, 5)), i));
        }

    }

    /**
     * Returns a String for printing the Number in the current clockArtStyle
     * @param number the number which should be drawn. 0-9 for the numbers, -1 for the colon, -2 for space
     * @param line The Line of the
     * @return The line of the number in the current clockArtStyle
     * @throws IllegalArgumentException if there is no such line in the current clockArt Style or if number is out of bounds.
     */
    private String getNumberLine(int number, int line) throws IllegalArgumentException {
        if (line > COLON.length) {
            throw new IllegalArgumentException("there is no line " + line + "in this clockArtStyle");
        }

        return switch (number) {
            case -2 -> SPACE[line];
            case -1 -> COLON[line];
            case 0 -> ZERO[line];
            case 1 -> ONE[line];
            case 2 -> TWO[line];
            case 3 -> THREE[line];
            case 4 -> FOUR[line];
            case 5 -> FIVE[line];
            case 6 -> SIX[line];
            case 7 -> SEVEN[line];
            case 8 -> EIGHT[line];
            case 9 -> NINE[line];
            default -> throw new IllegalArgumentException("number can't be " + number);
        };
    }

    /**
     * Runs the clock and lets the colon blink every second.
     */
    public void run() {
        boolean printColon = true;

        while (true) {
            try {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            }
            catch (Exception ignore) {}

            printClock(printColon);
            printColon = !printColon;

            try {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException ignore) {}
        }

    }

}
