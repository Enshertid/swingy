package swingy.utils;

public enum Button {
    UP(87, "w"),
    DOWN(83, "s"),
    LEFT(65, "a"),
    RIGHT(68, "d"),
    ESC(27, "exit");

    private int code;
    private String name;

    Button(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public static Button getButton(int code) {
        for (Button button : Button.values()) {
            if (code == button.getCode())
                return button;
        }
        return null;
    }

    public static Button getButton(String name) {
        for (Button button : Button.values()) {
            if (button.getName().equals(name))
                return button;
        }
        return null;
    }

    public static boolean isStep(int code) {
        return code == Button.DOWN.getCode() ||
                code == Button.UP.getCode() ||
                code == Button.LEFT.getCode() ||
                code == Button.RIGHT.getCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
