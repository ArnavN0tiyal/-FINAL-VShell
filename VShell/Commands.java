package VShell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Commands {
    static String usercmd;
    static final Runtime run = Runtime.getRuntime();
    static Process proc;
    static int exit = 1;
    static final Scanner sc = new Scanner(System.in);
    static String name = "shell";

    public static void getInfo() {
        if (usercmd.endsWith("os")) {
            System.out.println("Os: " + System.getProperty("os.name") + "\n" + "Os version: " + System.getProperty("os.version") + "\n" + "Os architecture: " + System.getProperty("os.arch"));
        } else if (usercmd.endsWith("java")) {
            System.out.println("Java installation directory: " + System.getProperty("java.home") + "\n" + "JVM version: " + System.getProperty("java.runtime.version") + "\n" + "JVM implementation name: " + System.getProperty("java.vm.name") + "\n" + "Java compiler: " + System.getProperty("java.compiler"));
        }
    }

    public static void rename() throws IOException {
        String[] tokens = usercmd.split("\\s");
        name = tokens[1];
    }

    public static void commandprompt() {
        try {
            String processedCmd = usercmd.replace("cmd -c", "").trim();
            String[] cmdBuild = { "cmd", "/C", processedCmd };
            ProcessBuilder builder = new ProcessBuilder(cmdBuild);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
    }

    public static void powershell() throws IOException {
        try {
            String processedPS = usercmd.replace("cmd -p", "").trim();
            String[] psBuild = { "powershell", "/C", processedPS };
            ProcessBuilder builder = new ProcessBuilder(psBuild);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("ERROR! " + e);
        }
    }

    public static void help() {
        System.out.println("About:\n\nWelcome to VShell (Virtual Shell)! This is a custom \"shell\" fully made in java by ArnavN0tiyal. Commands in this \"shell\" are - print, time, eval, run, ascii, colour, shutdown and exit.\n\nHow to use commands:\n\nFirst is print, which prints to the console. To use this command, first type \"print\" then the string, like - [print hello world].\nSecond is time, which *obviously* shows the time.\nThird is eval, which evalutes statements like - [eval 2 + 2] Output - 4.\nFourth is run, which runs programs like - [run cmd].\nFifth is ascii, which shows the ascii value of characters like - [ascii I].\nSixth is shutdown, which shuts down your computer in certain ways like if I want to restart, do - [shutdown /r] etc, bascially the command prompt shutdown method.\nSeventh is colour, which changes the text colour like - [colour red].\nEighth is clear, which clears the console.\nNinth is dirfiles, which shows the files in the presetted directory, change the directory yourself.\nTenth is shver, which shows the version of the VShell.\nEleventh is cmd, it has two types - [cmd -c] and - [cmd -p]. First one runs command prompt commands and second one runs powershll commands.\nTwelfth is rename, which renames your shell username like - [rename user].\nThirteenth is getInfo, which gives info about your os and about your installation of java like - [getInfo os] or [getInfo java].");
    }

    public static void shellversion() {
        System.out.println("Version: 1.2.1\nRelease: 24/3/6");
    }

    public static void print() {
        String processed = usercmd.replace("print", "").trim();
        System.out.println(processed);
    }

    public static void time() {
        LocalTime time = LocalTime.now();
        System.out.println("The current time is: " + time);
    }

    public static void eval() {
        String processed = usercmd.replace("eval", "").trim();
        String[] tokens = processed.split("\\s");
        try {
            double num1 = Double.parseDouble(tokens[0]);
            String op = tokens[1];
            double num2 = Double.parseDouble(tokens[2]);
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
        } catch (Exception e) {
            System.err.println("ERROR! Write statements like - [2 + 2] and usable operators are - [+, -, *, /]");
        }
    }

    public static void run() throws IOException {
        if (usercmd.endsWith("edge")) {
            proc = run.exec(new String[] { "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe" });
        } else if (usercmd.endsWith("cmd")) {
            proc = run.exec(new String[] { "cmd", "/C", "start" });
        } else if (usercmd.endsWith("code")) {
            proc = run.exec(new String[] { "cmd", "/C", "code" });
        } else if (usercmd.endsWith("arc")) {
            proc = run.exec(new String[] { "cmd", "/C", "arc" });
        } else if (usercmd.endsWith("cursor")) {
            proc = run.exec(new String[] { "cmd", "/C", "cursor" });
        } else if (usercmd.endsWith("vi")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "vi" });
        } else if (usercmd.endsWith("vim")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "vim" });
        } else if (usercmd.endsWith("emacs")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "emacs" });
        } else if (usercmd.endsWith("nano")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "nano" });
        } else if (usercmd.endsWith("powershell")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "powershell" });
        } else if (usercmd.endsWith("powershell ise")) {
            proc = run.exec(new String[] { "cmd", "/C", "powershell ise" });
        } else if (usercmd.endsWith("calculator")) {
            proc = run.exec(new String[] { "cmd", "/C", "start", "calc" });
        } else {
            System.out.println(
                    "ERROR! Program not recognized, use [edge, cmd, code, arc, cursor, vi, vim, emacs, nano, powershell, powershell ise, calculator]");
        }
    }

    public static void shutdown() throws IOException {
        if (usercmd.endsWith("i")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/i" });
        } else if (usercmd.endsWith("l")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/l" });
        } else if (usercmd.endsWith("s")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/s" });
        } else if (usercmd.endsWith("r")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/r" });
        } else if (usercmd.endsWith("g")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/g" });
        } else if (usercmd.endsWith("a")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/a" });
        } else if (usercmd.endsWith("p")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/p" });
        } else if (usercmd.endsWith("h")) {
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
        if (usercmd.endsWith("reset")) {
            System.out.print("\033[0m");
        } else if (usercmd.endsWith("black")) {
            System.out.print("\033[0;30m");
        } else if (usercmd.endsWith("red")) {
            System.out.print("\033[0;31m");
        } else if (usercmd.endsWith("orange")) {
            System.out.print("\033[0;33m");
        } else if (usercmd.endsWith("blue")) {
            System.out.print("\033[0;34m");
        } else if (usercmd.endsWith("green")) {
            System.out.print("\033[0;92m");
        } else {
            System.out.println("ERROR, Colour not recognized, use [reset, black, red, orange, blue, green]");
        }
    }

    public static void ascii() throws IOException {
        char valueinput = usercmd.charAt(6);
        int asciivalue = valueinput;
        System.out.println(asciivalue);
    }
}
