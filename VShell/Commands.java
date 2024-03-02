package VShell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Commands {
    static String cmd;
    static final Runtime run = Runtime.getRuntime();
    static Process proc;
    static int exit = 1;
    static final Scanner sc = new Scanner(System.in);

    public static void help() {
        System.out.println("About:\nWelcome to VShell (Virtual Shell)! This is a custom \"shell\" fully made in java by ArnavN0tiyal. Commands in this \"shell\" are - print, time, eval, run, ascii, colour, shutdown and exit.\nHow to use commands:\nCommands are pretty straightforward to use.\nFirst is print, which prints to the console. To use this command, first type \"print\" then the string, like - [print hello world].\nSecond is time, which *obviously* shows the time.\nThird is eval, which evalutes statements like - [eval 2 + 2] Output - 4.\nFourth is run, which runs programs like - [run cmd].\nFifth is ascii, which shows the ascii value of characters like - [ascii I].\nSixth is shutdown, which shuts down your computer in certain ways like if I want to restart, do - [shutdown /r] etc, bascially the command prompt shutdown method.\nSeventh is colour, which changes the text colour like - [colour red].\nEighth is clear, which clears the console.\nNinth is dirfiles, which shows the files in the presetted directory, change the directory yourself.\nLast but not the least, tenth is shver, which shows the version of the VShell.");
    }

    public static void shellversion() {
        System.out.println("Version: 1.0\nRelease: 24/3/2");
    }

    public static void print() {
        String processed = cmd.replace("print", "");
        System.out.println(processed.trim());
    }

    public static void time() {
        LocalTime time = LocalTime.now();
        System.out.println("The current time is: " + time + "\n");
    }

    public static void eval() {
        String processed = cmd.replace("eval", "").trim();
        String[] tokens = processed.split("\\s");
        try {
            int num1 = Integer.parseInt(tokens[0]);
            String op = tokens[1];
            int num2 = Integer.parseInt(tokens[2]);
            switch (op) {
                case "+":
                    System.out.println(num1 + num2);
                    break;
                case "-":
                    System.out.println(num1 - num2);
                    break;
                case "*":
                    System.out.println(num1 * num2);
                    break;
                case "/":
                    System.out.println(num1 / num2);
                    break;
            }
        } catch (Throwable error) {
            System.err.println("ERROR! Use statements like - [2 + 2] and usable operators are - [+, -, *, /]");
        }
    }

    public static void run() throws IOException {
        if (cmd.endsWith("edge")) {
            proc = run.exec(new String[] { "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe" });
        } else if (cmd.endsWith("cmd")) {
            proc = run.exec(new String[] { "cmd", "/C", "start" });
        } else if (cmd.endsWith("code")) {
            proc = run.exec(new String[] { "cmd", "/C", "code" });
        } else if (cmd.endsWith("arc")) {
            proc = run.exec(new String[] { "cmd", "/C", "arc" });
        } else if (cmd.endsWith("cursor")) {
            proc = run.exec(new String[] { "cmd", "/C", "cursor" });
        } else if (cmd.endsWith("vi")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "vi" });
        } else if (cmd.endsWith("vim")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "vim" });
        } else if (cmd.endsWith("emacs")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "emacs" });
        } else if (cmd.endsWith("nano")) {
            proc = run.exec(new String[] { "cmde", "/C", "start", "nano" });
        } else if (cmd.endsWith("powershell")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "powershell" });
        } else if (cmd.endsWith("powershell ise")) {
            proc = run.exec(new String[] { "cmd", "/C", "powershell ise" });
        } else {
            System.out.println(
                    "ERROR! Program not recognized, use [edge, cmd, code, arc, cursor, vi, vim, emacs, nano, powershell, powershell ise]");
        }
    }

    public static void shutdown() throws IOException {
        if (cmd.endsWith("i")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/i" });
        } else if (cmd.endsWith("l")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/l" });
        } else if (cmd.endsWith("s")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/s" });
        } else if (cmd.endsWith("r")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/r" });
        } else if (cmd.endsWith("g")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/g" });
        } else if (cmd.endsWith("a")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/a" });
        } else if (cmd.endsWith("p")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/p" });
        } else if (cmd.endsWith("h")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/h" });
        } else {
            System.out.println("ERROR! Shutdown type not recognized, use [/i, /l, /s, /r, /g, /a, /p, /h]");
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Set<String> listFiles(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {
            return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public static void dirfiles() throws IOException {
        System.out.println(listFiles("C:\\"));
    }

    public static void colour() {
        if (cmd.endsWith("reset")) {
            System.out.print("\033[0m");
        } else if (cmd.endsWith("black")) {
            System.out.print("\033[0;30m");
        } else if (cmd.endsWith("red")) {
            System.out.print("\033[0;31m");
        } else if (cmd.endsWith("orange")) {
            System.out.print("\033[0;33m");
        } else if (cmd.endsWith("blue")) {
            System.out.print("\033[0;34m");
        } else if (cmd.endsWith("green")) {
            System.out.print("\033[0;92m");
        } else {
            System.out.println("ERROR, Colour not recognized, use [reset, black, red, orange, blue, green]");
        }
    }

    public static void ascii() throws IOException {
        char valueinput = cmd.charAt(6);
        int asciivalue = valueinput;
        System.out.println(asciivalue);
    }
}
