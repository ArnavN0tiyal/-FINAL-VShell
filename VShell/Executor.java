package VShell;

import java.io.IOException;

public class Executor extends Commands {
    public static void main(String[] args) throws IOException {
        do {
            System.out.print("[" + name + "]$ ");
            usercmd = sc.nextLine();
            executeCmd(usercmd);
        } while (exit != 1);
    }

    private static void executeCmd(String cmd) throws IOException {
        if (cmd.startsWith("print")) {
            print();
        } else if (usercmd.startsWith("run")) {
            run();
        } else if (cmd.startsWith("colour")) {
            colour();
        } else if (cmd.startsWith("ascii")) {
            ascii();
        } else if (cmd.startsWith("shutdown")) {
            shutdown();
        } else if (cmd.startsWith("eval")) {
            evalType();
        } else if (cmd.startsWith("cmd")) {
            shellType();
        } else if (cmd.startsWith("rename")) {
            rename();
        } else if (cmd.startsWith("getInfo")) {
            getInfo();
        } else if (cmd.startsWith("crDir")) {
            createDir();
        } else if (cmd.startsWith("rmDir")) {
            removeDir();
        } else if (cmd.startsWith("sd")) {
            setdirectory();
        } else {
            switch (cmd) {
                case "date":
                    date();
                    break;
                case "dateTime":
                    dateTime();
                    break;
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
                    exit = 1;
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
                        System.err.println("Unknown command");
                    }
                    break;
            }
        }
    }
}
