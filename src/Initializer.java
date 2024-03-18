package VShell.src;

import java.io.IOException;

public class Initializer extends Processer {
    public static void main(String[] args) throws IOException {
        vshinit();
    }

    // To use vshell in a outside context, the initializer is a function, which does cost me more lines
    public static void vshinit() throws IOException {
        art();
        do {
            if (text) {
                System.out.print("<" + location + "> <" + name + ">$ ");
            } else if (!text) {
                System.out.print("$ ");
            }
            usercmd = sc.nextLine();
            process(usercmd);
        } while (exit != true);
    }

    private static void process(String cmd) throws IOException {
        String[] tokens = cmd.split("\\s");
        switch (tokens[0]) {
            case "print":
            print();
            break;
            case "time":
            time();
            break;
            case "eval":
            evalType();
            break;
            case "run":
            run();
            break;
            case "ascii":
            ascii();
            break;
            case "shutdown":
            shutdown();
            break;
            case "colour":
            colour();
            break;
            case "clear", "cls":
            clear();
            break;
            case "rename":
            rename();
            break;
            case "dirFiles":
            dirFiles();
            break;
            case "vshver":
            vshellVersion();
            break;
            case "cmd":
            shellType();
            break;
            case "getInfo":
            getInfo();
            break;
            case "crDir":
            createDir();
            break;
            case "rmDir":
            removeDir();
            break;
            case "date":
            date();
            break;
            case "dateTime":
            dateTime();
            break;
            case "sd":
            setdirectory();
            break;
            case "text":
            text();
            break;
            case "exit":
            exit = true;
            sc.close();
            break;
            case "help":
            help();
            break;
            case "vshinit":
            vshinit();
            break;
            case "legacyinit":
            LegacyShell.init();
            break;
            default:
            if (!cmd.isEmpty()) {
                System.err.println("Unknown command");
            }
            break;
        }   
    }
}
