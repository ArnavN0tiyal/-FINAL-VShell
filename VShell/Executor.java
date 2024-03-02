package VShell;

import java.io.IOException;

public class Executor extends Commands {
    public static void main(String[] args) throws IOException {
        do {
            System.out.print("[shell]$ ");
            cmd = sc.nextLine();
            executeCmd(cmd);
        } while (exit != 0);
    }

    private static void executeCmd(String cmd) throws IOException {
        if (cmd.startsWith("print")) {
            print();
        } else if (cmd.startsWith("run")) {
            run();
        } else if (cmd.startsWith("colour")) {
            colour();
        } else if (cmd.startsWith("ascii")) {
            ascii();
        } else if (cmd.startsWith("shutdown")) {
            shutdown();
        } else if (cmd.startsWith("eval")) {
            eval();
        } else {
            switch (cmd) {
                case "dirfiles":
                    dirfiles();
                    break;
                case "help":
                    help();
                    break;
                case "time":
                    time();
                    break;
                case "exit":
                    exit = 0;
                    sc.close();
                    break;
                case "clear":
                    clear();
                    break;
                case "shver":
                    shellversion();
                    break;
                default:
                    if (!cmd.isEmpty()) {
                        System.out.println("Unknown command");
                    }
                    break;
            }
        }
    }
}