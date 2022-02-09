package swingy.view.console;

import swingy.view.View;

public class ConsoleView implements View {

    @Override
    public void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
