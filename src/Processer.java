package VShell.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Processer {
    static String usercmd;
    static final Runtime run = Runtime.getRuntime();
    static Process proc;
    static boolean exit = false;
    static final Scanner sc = new Scanner(System.in);
    static String name = "shell";
    static String location = "C:\\";
    static boolean text = true;

    class Evaluation {
        private static void evalBasic() {
            String processed = usercmd.replace("eval -b", "").trim();
            String[] tokens = processed.split("\\s");
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
        }

        private static void evalScientific() {
            String processed = usercmd.replace("eval -s", "").trim();
            String[] tokens = processed.split("\\s");
            double num1 = Double.parseDouble(tokens[1]);
            switch(tokens[0]) {
                case "abs":
                System.out.println(Math.abs(num1));
                break;
                case "max":
                double num2max = Double.parseDouble(tokens[2]);
                System.out.println(Math.max(num1, num2max));
                break;
                case "min":
                double num2min = Double.parseDouble(tokens[2]);
                System.out.println(Math.min(num1, num2min));
                break;
                case "sqrt":
                System.out.println(Math.sqrt(num1));
                break;
                case "pow":
                double num2pow = Double.parseDouble(tokens[2]);
                System.out.println(Math.pow(num1, num2pow));
                break;
                case "round":
                System.out.println(Math.round(num1));
                break;
                case "cbrt":
                System.out.println(Math.cbrt(num1));
                break;
                case "signum":
                System.out.println(Math.signum(num1));
                break;
                case "ceil":
                System.out.println(Math.ceil(num1));
                break;
                case "floor":
                System.out.println(Math.floor(num1));
                break;
                case "hypot":
                double num2hypot = Double.parseDouble(tokens[2]);
                System.out.println(Math.hypot(num1, num2hypot));
                break;
                case "ulp":
                System.out.println(Math.ulp(num1));
                break;
                case "exp":
                System.out.println(Math.exp(num1));
                break;
                case "expm1":
                System.out.println(Math.expm1(num1));
                break;
                case "log":
                System.out.println(Math.log(num1));
                break;
                case "log10":
                System.out.println(Math.log10(num1));
                break;
                case "log1p":
                System.out.println(Math.log1p(num1));
                break;
            }
        }

        private static void evaltrigonometric() {
            String processed = usercmd.replace("eval -t", "").trim();
            String[] tokens = processed.split("\\s");
            double num1 = Double.parseDouble(tokens[1]);
            switch(tokens[0]) {
                case "sin":
                System.out.println(Math.sin(num1));
                break;
                case "cos":
                System.out.println(Math.cos(num1));
                break;
                case "tan":
                System.out.println(Math.tan(num1));
                break;
                case "sinh":
                System.out.println(Math.sinh(num1));
                break;
                case "cosh":
                System.out.println(Math.cosh(num1));
                break;
                case "tanh":
                System.out.println(Math.tanh(num1));
                break;
                case "asin":
                System.out.println(Math.asin(num1));
                break;
                case "acos":
                System.out.println(Math.acos(num1));
                break;
                case "atan":
                System.out.println(Math.atan(num1));
                break;
                default:
                System.err.println("ERROR! Use functions - [sin, cos, tan, sinh, cosh, tanh, asin, acos, atan]");
                break;
            }
        }
    }

    class Shells {
        private static void bash() throws IOException {
            String processed = usercmd.replace("cmd -b", "").trim();
            String[] bashBuild = { "bash", "-c", processed };
            ProcessBuilder builder = new ProcessBuilder(bashBuild);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        private static void commandprompt() throws IOException {
            String processed = usercmd.replace("cmd -c", "").trim();
            String[] cmdBuild = { "cmd", "/C", processed };
            ProcessBuilder builder = new ProcessBuilder(cmdBuild);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        private static void powershell() throws IOException {
            String processed = usercmd.replace("cmd -p", "").trim();
            String[] psBuild = { "powershell", "/C", processed };
            ProcessBuilder builder = new ProcessBuilder(psBuild);

            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

    }

    public static void getInfo() {
        String[] tokens = usercmd.split("\\s");
        try {
            switch (tokens[1]) {
                case "os":
                System.out.println("Os: " + System.getProperty("os.name") + "\n" + "Os version: "+ System.getProperty("os.version") + "\n" + "Os architecture: " + System.getProperty("os.arch"));
                break;
                case "java":
                System.out.println("Java installation directory: " + System.getProperty("java.home") + "\n"+ "JVM version: " + System.getProperty("java.runtime.version") + "\n" + "JVM implementation name: "+ System.getProperty("java.vm.name") + "\n" + "Java compiler: "+ System.getProperty("java.compiler"));
                break;
                default:
                System.err.println("ERROR! Usable getInfo labels are - [os, java]");
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void rename() throws IOException {
        try {
            String[] tokens = usercmd.split("\\s");
            name = tokens[1];
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void shellType() throws IOException {
        String[] tokens = usercmd.split("\\s");
        try {
            switch (tokens[1]) {
                case "-b":
                    Shells.bash();
                    break;
                case "-c":
                    Shells.commandprompt();
                    break;
                case "-p":
                    Shells.powershell();
                    break;
                default:
                    System.err.println("ERROR! Use labels like - [-b, -c, -p]");
                    break;
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void help() {
        System.out.println("About:\n\nWelcome to VShell (Virtual Shell)! This is a custom \"shell\" fully made in java by ArnavN0tiyal. Version of shell is 1.3.5. Release date is 3/17/24 and total number of usable commands are 23. Commands in this \"shell\" are - print, time, eval, run, ascii, shutdown, colour, clear or cls, rename, dirFiles, vshver, cmd, getInfo, crDir, rmDir, date, dateTime, sd, text, exit, help, vshinit and legacyinit.\n\nHow to use commands:\n\nFirst is print, which prints to the console. To use this command, first type \"print\" then the string, like - [print hello world].\n\nSecond is time, which *obviously* shows the time.\n\nThird is eval, which evaluates statements like for basic statements - [eval -b 2 + 2], for scientific statements - [eval -s log 10] and for trigonometric statements - [eval -t sin 24] .\n\nFourth is run, which runs programs like - [run cmd].\n\nFifth is ascii, which shows the ascii value of characters like - [ascii I].\n\nSixth is shutdown, which shuts down your computer in certain ways like if I want to restart, do - [shutdown -r] etc, bascially the command prompt shutdown method.\n\nSeventh is colour, which changes the text colour like - [colour red].\n\nEighth is clear, which clears the console.\n\nNinth is dirFiles, which shows the files in a directory.\n\nTenth is vshver, which shows the version of the VShell.\n\nEleventh is cmd, it has three types - [cmd -b], [cmd -c], [cmd -p]. First one runs bash commands (Note - You will need wsl to use this on windows), the second one runs command prompt commands and the third one runs powershell commands.\n\nTwelfth is rename, which renames your shell username like - [rename user].\n\nThirteenth is getInfo, which gives info about your os and about your installation of java like - [getInfo os] or [getInfo java].\n\nFourteenth is crDir, which create a directory like - [crDir VShell_source].\n\nFifteenth is rmDir, which removes a directory like - [rmDir VShell_source].\n\nSixteenth is date, which shows the date.\n\nSeventeenth is dateTime, which shows the date and time.\n\nEighteenth is sd, which sets location of the area where dirFiles will print the contents of the folder.\n\nNintheenth is text, which turns on or off the location and shell name printing like - [text off] or - [text on]\n\nTwentieth is exit, which exits the shell.\n\nTwenty first is help, which you are using right now. It gives help about the shell\n\nTwenty second is vshinit, which initializes the shell\n\nTwenty third is legacyinit, which initializes the legacy shell");
    }

    public static void vshellVersion() {
        System.out.println("Version: 1.3.4\nRelease: 3/15/24");
    }

    public static void print() {
        try {
            String processed = usercmd.replace("print", "").trim();
            System.out.println(processed);
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void text() {
        String[] tokens = usercmd.split("\\s");
        try {
            switch(tokens[1]) {
                case "on":
                text = true;
                break;
                case "off":
                text = false;
                break;
                default:
                System.err.println("ERROR! Use lables like - [on, off]");
                break;
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void time() {
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("The current time is: " + time.format(timeFormatter));
    }

    public static void date() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/uu");
        System.out.println("The current date is: " + date.format(dateFormatter));
    }

    public static void dateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss");
        System.out.println("The current date and time is: " + dateTime.format(dateTimeFormatter));
    }

    public static void evalType() {
        String[] tokens = usercmd.split("\\s");
        try {
            switch (tokens[1]) {
                case "-b":
                Evaluation.evalBasic();
                break;
                case "-s":
                Evaluation.evalScientific();
                break;
                case "-t":
                Evaluation.evaltrigonometric();
                break;
                default:
                System.err.println("ERROR! Use label -b for basic statements, -s for scientific and -t for trigonometric. Write statements for basic statements - [eval -b 2 + 2], for scientific - [eval -s exp 2] and for trigonometric - [eval -t cos 2]");
                break;
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void run() throws IOException {
        String[] tokens = usercmd.split("\\s");
        try {
            switch (tokens[1]) {
                case "edge":
                proc = run.exec(new String[] { "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe" });
                break;
                case "cmd":
                proc = run.exec(new String[] { "cmd", "/C", "start" });
                break;
                case "code":
                proc = run.exec(new String[] { "cmd", "/C", "code" });
                break;
                case "arc":
                proc = run.exec(new String[] { "cmd", "/C", "arc" });
                break;
                case "cursor":
                proc = run.exec(new String[] { "cmd", "/C", "cursor" });
                break;
                case "vi":
                proc = run.exec(new String[]{ "cmd", "/C", "start", "vi"});
                break;
                case "vim":
                proc = run.exec(new String[] { "cmd", "/C", "start", "vim" });
                break;
                case "emacs":
                proc = run.exec(new String[] { "cmd", "/C", "start", "emacs" });
                break;
                case "nano":
                proc = run.exec(new String[] { "cmd", "/C", "start", "nano" });
                break;
                case "powershell":
                proc = run.exec(new String[] { "cmd", "/C", "start", "powershell" });
                break;
                case "powershell ise":
                proc = run.exec(new String[] { "cmd", "/C", "powershell ise" });
                break;
                case "calculator":
                proc = run.exec(new String[] { "cmd", "/C", "start", "calc" });
                break;
                case "explorer":
                proc = run.exec(new String[] { "cmd", "/C", "start", "explorer" });
                break;
                case "notepad":
                proc = run.exec(new String[]{ "cmd", "/C", "notepad" });
                break;
                default:
                System.err.println("ERROR! Program not recognized, use [edge, cmd, code, arc, cursor, vi, vim, emacs, nano, powershell, powershell ise, calculator]");
                break;
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void shutdown() throws IOException {
        String[] tokens = usercmd.split("\\s");
        try {
            switch (tokens[1]) {
                case "-i":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/i" });
                break;
                case "-l":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/l" });
                break;
                case "-s":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/s", "/t 0" });
                break;
                case "-r":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/r", "/t 0" });
                break;
                case "-g":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/g", "/t 0" });
                break;
                case "-a":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/a" });
                break;
                case "-p":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/p" });
                break;
                case "-h":
                proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/h", "/t 0" });
                break;
                default:
                System.err.println("ERROR! Shutdown type not recognized, use [-i, -l, -s, -r, -g, -a, -p, -h]");
                break;
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Set<String> listFiles(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString).collect(Collectors.toSet());}
    }

    public static void dirFiles() {
        try {
            System.out.println(listFiles(location));
        } catch (Exception e) {
            System.out.println("ERROR! Insufficent tokens or folder/file can't be found");
        }
    }

    public static void setdirectory() {
        try {
            String[] tokens = usercmd.split("\\s");
            if (tokens.length == 0) {
                System.out.println(location);
            } else {
                location = tokens[1];
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }
    }
    
    public static void createDir() throws IOException {
        String[] tokens = usercmd.split("\\s");
        proc = run.exec(new String[] { "cmd", "/C", "mkdir " + tokens[1] });
    }

    public static void removeDir() throws IOException {
        String[] tokens = usercmd.split("\\s");
        proc = run.exec(new String[] { "cmd", "/C", "rmdir " + tokens[1] });
    }

    public static void colour() {
        String[] tokens = usercmd.split("\\s");
        try {
            switch (tokens[1]) {
                case "reset":
                System.out.print("\033[0m");
                break;
                case "black":
                System.out.print("\033[0;30m");
                break;
                case "red":
                System.out.print("\033[0;31m");
                break;
                case "orange":
                System.out.print("\033[0;33m");
                break;
                case "blue":
                System.out.print("\033[0;34m");
                break;
                case "green":
                System.out.print("\033[0;92m");
                break;
                default:
                System.err.println("ERROR, Colour not recognized, use [reset, black, red, orange, blue, green]");
                break;
            }
        } catch (Exception e) {
            System.err.println("ERROR! Insufficent tokens");
        }

    }

    public static void ascii() throws IOException {
        char valueinput = usercmd.charAt(6);
        if (usercmd.length() > 7) {
            System.err.println("ERROR! Can only read one ascii char");
        } else {
            int asciivalue = valueinput;
            System.out.println(asciivalue);
        }
    } 

    public static void art() {
        System.out.println("\033[0;34m ______ ____  __         ____");
        System.out.println("| |  / / ___// /_  ___  / / /");
        System.out.println("| | / /\\__ \\/ __ \\/ _ \\/ / /");
        System.out.println("| |/ /___/ / / / /  __/ / /"); 
        System.out.println("|___//____/_/ /_/\\___/_/_/");  
        System.out.println("\033[0m\nType \"help\" for some context about the shell");
    }
}

class LegacyShell {
    static String cmd;
    static int exit = 1;
    static int print = 1;
    static String username = "User";
    static String brand = "Dell";
    static Process proc;
    static final Scanner sc = new Scanner(System.in);
    static final Runtime run = Runtime.getRuntime();
    static int choice;
    static String confirm;
    static int range = 10;
    static int guess;
    static int chance = (int) (1 + Math.random() * 1000);
    static int num;
    static int RPSCHOICE;
    static final String RESET = "\033[0m";
    static final String BLACK = "\033[0;30m";
    static final String RED = "\033[0;31m";
    static final String GREEN = "\033[0;34m";
    static final String BLUE = "\033[0;33m";
    static final String RED_BRIGHT = "\033[0;91m";
    static final String GREEN_BRIGHT = "\033[0;92m";
    static int computer_choice = (int) (1 + Math.random() * 3);
    static int chances;

    public static void init() throws IOException {
        System.out.println("Nevertx NeVerox[Version 1.2.2095.21095]\n(n) Nevertx. All rights unreserved.\n");
        do {
            if (print == 1) {
                System.out.print("C:\\" + username + "\\" + brand + ">");
            } else {
                System.out.print("> ");
            }
            cmd = sc.nextLine();
            executeCmd(cmd);
        } while (exit != 0);
    }

    public static void executeCmd(String cmd) throws IOException {
        if (cmd.startsWith("echo")) {
            echo();
        } else {
            switch (cmd) {
                case "help", "Help", "HELP":
                    help();
                    break;
                case "echo on", "Echo on", "Echo On", "ECHO ON":
                    echo_on();
                    break;
                case "echo off", "Echo off", "Echo Off", "ECHO OFF":
                    echo_off();
                    break;
                case "verify", "Verify", "VERIFY":
                    verify();
                    break;
                case "time", "Time", "TIME":
                    showtime();
                    break;
                case "exit", "Exit", "EXIT":
                    exit();
                    break;
                case "add", "Add", "ADD":
                    addition();
                    break;
                case "sub", "Sub", "SUB":
                    subtraction();
                    break;
                case "mul", "Mul", "MUL":
                    multiplication();
                    break;
                case "div", "Div", "DIV":
                    division();
                    break;
                case "username", "Username", "USERNAME":
                    username();
                    break;
                case "brandtype", "Brandtype", "BrandType", "BRANDTYPE":
                    brandtype();
                    break;
                case "open", "Open", "OPEN":
                    applications();
                    break;
                case "programs", "Programs", "PROGRAMS":
                    programs();
                    break;
                case "shutdown /?", "Shutdown /?", "SHUTDOWN /?", "shutdown", "Shutdown", "SHUTDOWN":
                    shutdown_help();
                    break;
                case "shutdown /i", "Shutdown /i", "SHUTDOWN /i":
                    shutdown_1();
                    break;
                case "shutdown /l", "Shutdown /l", "SHUTDOWN /l":
                    shutdown_2();
                    break;
                case "shutdown /s", "Shutdown /s", "SHUTDOWN /s":
                    shutdown_3();
                    break;
                case "shutdown /sg", "Shutdown /sg", "SHUTDOWN /sg":
                    shutdown_4();
                    break;
                case "shutdown /r", "Shutdown /r", "SHUTDOWN /r":
                    shutdown_5();
                    break;
                case "shutdown /g", "Shutdown /g", "SHUTDOWN /g":
                    shutdown_6();
                    break;
                case "shutdown /a", "Shutdown /a", "SHUTDOWN /a":
                    shutdown_7();
                    break;
                case "shutdown /p", "Shutdown /p", "SHUTDOWN /p":
                    shutdown_8();
                    break;
                case "shutdown /h", "Shutdown /h", "SHUTDOWN /h":
                    shutdown_9();
                case "jlist", "Jlist", "JList", "JLIST":
                    java_list();
                    break;
                case "jproject", "Jproject", "JProject", "JPROJECT":
                    java_project();
                    break;
                case "todoger", "Todoger", "TODOGER":
                    todo_Manager();
                    break;
                case "ascv", "Ascv", "ASCV":
                    ascii_value();
                    break;
                case "colour", "Colour", "COLOUR", "colour /?", "Colour /?", "COLOUR /?":
                    colour();
                    break;
                case "colour -1", "Colour -1", "COLOUR -1":
                    System.out.println(RESET);
                    break;
                case "colour 0", "Colour 0", "COLOUR 0":
                    System.out.println(BLACK);
                    break;
                case "colour 1", "Colour 1", "COLOUR 1":
                    System.out.println(RED);
                    break;
                case "colour 2", "Colour 2", "COLOUR 2":
                    System.out.println(GREEN);
                    break;
                case "colour 3", "Colour 3", "COLOUR 3":
                    System.out.println(BLUE);
                    break;
                case "colour 1B", "Colour 1B", "COLOUR 1B":
                    System.out.println(RED_BRIGHT);
                    break;
                case "colour 2B", "Colour 2B", "COLOUR 2B":
                    System.out.println(GREEN_BRIGHT);
                    break;
                case "numguess", "Numguess", "NumGuess", "NUMGUESS":
                    number_guessing__game();
                    break;
                case "clear", "Clear", "CLEAR":
                    clear();
                    break;
                case "rps", "Rps", "RPS":
                    RPS();
                    break;
                case "dir", "Dir", "DIR":
                    dir();
                    break;
                default:
                    if (!cmd.isEmpty()) {
                        System.out.println("\'" + cmd + "\'" + " is not recognized as a internal or external command.\n");
                    }
                    break;
            }
        }
    }

    public static void help() {
        System.out.println("ECHO         Prints a string on the console or turns on or off command echoing\nVERIFY       Verifies if that the files are correctly written to the Nevertx disk\nTIME         Shows the time\nEXIT         Exits the terminal\nADD          Adds two numbers\nSUB          Subtracts two numbers\nMUL          Multiplies two numbers\nDIV          Divides two numbers\nUSERNAME     Sets your username\nBRANDTYPE    Sets the user's computer brand\nOPEN         Opens a program. To see the list of programs that work, type \"programs\" in the command line\nSHUTDOWN     Allows proper and local shutdown of the computer\nJLIST        Gives a bit of help about java lists\nJPROJECT     Gives some projects to do in java\nTODOGER      Runs a todo manager\nASCV         Gives the ascii value of characters\nCOLOUR       Same as \"Colour /?\" which gives a list of colours usable\nNUMGUESS     Plays a number guessing game *Range of guessing numbers is custamizable*\nRPS          Plays rock paper scissors *Player or computer*\nCLEAR        Clears the terminal\nDIR          Shows the files in a folder using cmd\n");
    }

    public static void echo() {
        String processed = cmd.replace("echo", "");
        System.out.println(processed.trim() + "\n");
    }

    public static void echo_on() {
        print = 1;
    }

    public static void echo_off() {
        print = 0;
    }

    public static void verify() {
        if (chance >= 1) {
            System.out.println("Files are correctly written to the disk.\n");
        } else if (chance == 1) {
            System.out.println("Files aren't written correctly to the disk. Please restart the terminal.\n");
            exit();
        }
    }

    public static void showtime() {
        LocalTime time = LocalTime.now();
        System.out.println("The current time is: " + time + "\n");
    }

    public static void exit() {
        exit = 0;
    }

    public static void addition() {
        System.out.print("Type the first addend: ");
        double addend1 = sc.nextDouble();
        System.out.print("Type the second addend: ");
        double addend2 = sc.nextDouble();
        double sum = addend1 + addend2;
        System.out.println("The sum of " + addend1 + " and " + addend2 + " is " + sum + ".\n");
    }

    public static void subtraction() {
        System.out.print("Type the mineund: ");
        double mineund = sc.nextDouble();
        System.out.print("Type the subtrahend: ");
        double subtrahend = sc.nextDouble();
        double difference = mineund - subtrahend;
        System.out.println("The difference of " + mineund + " and " + subtrahend + " is " + difference + ".\n");
    }

    public static void multiplication() {
        System.out.print("Type the multiplier: ");
        double multiplier = sc.nextDouble();
        System.out.print("Type the multiplicand: ");
        double multiplicand = sc.nextDouble();
        double product = multiplier * multiplicand;
        System.out.println("The product of " + multiplier + " and " + multiplicand + " is " + product + ".\n");
    }

    public static void division() {
        System.out.print("Type the dividend: ");
        double dividend = sc.nextDouble();
        System.out.print("Type the divisor: ");
        double divisor = sc.nextDouble();
        if (divisor != 0) {
            double quotient = dividend / divisor;
            double remainder = dividend % divisor;
            System.out.println("The quotient of " + dividend + " and " + divisor + " is " + quotient
                    + " and the remainder is " + remainder + ".\n");
        } else {
            System.out.println("Can't divide by zero.\n");
        }
    }

    public static void username() {
        System.out.print("Type the username you want it to be: ");
        username = sc.nextLine();
        System.out.println("Successfully set the username to: " + username + ".\n");
    }

    public static void brandtype() {
        System.out.print("Type your laptop brand: ");
        brand = sc.nextLine();
        System.out.println("Successfully changed the laptop brand to: " + brand + ".\n");
    }

    public static void applications() {
        final Runtime run = Runtime.getRuntime();
        System.out.print("Type the program you want to open: ");
        String program = sc.nextLine();
        try {
            switch (program) {
                case "edge", "Edge", "EDGE":
                    proc = run
                            .exec(new String[] { "C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe" });
                    break;
                case "command prompt", "Command prompt", "Command Prompt", "COMMAND PROMPT", "cmd", "Cmd", "CMD":
                    proc = run.exec(new String[] { "cmd", "/C", "Start", "Nevertx Promopt" });
                    break;
                case "calculator", "CALCULATOR", "calc", "CALC":
                    proc = run.exec(new String[] { "calc", "/C", "Start" });
                    break;
                case "vscode", "Vscode", "VSCODE", "visual studio code", "Visual studio code", "Visual Studio Code",
                        "VISUAL STUDIO CODE", "code", "Code", "CODE":
                    proc = run.exec(new String[] { "cmd", "/K", "code" });
                    break;
                default:
                    System.out.println("Unknown Program. Type a vaild program name.\n");
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Failed to execute.\n");
        }
    }

    public static void programs() {
        System.out.println("1) EDGE\n2) EXPLORER\n3) VSCODE\n4) CALCULATOR\n");
    }

    public static void shutdown_help() {
        System.out.println(
                "How to use: shutdown /i, /l, /s, /sg, /r, /g, /a, /p, /h\nNo args    This is the same as doing /?\n/i         Shows the graphical user interface\n/?         Gives the type of shutdown the user can do\n/l         Logs off the computer\n/s         Complete shutdown of the computer\n/sg        Shutdowns the computer. Then on the next boot, if automatic sign in is enabled then it will automaticly sign in to the user's computer\n/r         Shutdowns then restarts\n/g         Shutdowns and restarts. After the system is rebooted, if automatic sign in is enabled, the user will automaticly sign in\n/a         Abort a system shutdown *Can only be used in time out period*\n/p         Turns off the computer without any warning or time out\n/h         Hibernate the local computer\n");
    }

    public static void shutdown_1() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/i" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_2() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/l" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_3() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/s" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_4() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/sg" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_5() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/r" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_6() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/g" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_7() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/a" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_8() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/p" });
        } else {
            System.out.print("\n");
        }
    }

    public static void shutdown_9() throws IOException {
        System.out.print("Are you sure?\nType yes or no: ");
        confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("yes")) {
            proc = run.exec(new String[] { "cmd", "/C", "Start", "shutdown", "/h" });
        } else {
            System.out.print("\n");
        }
    }

    public static void java_list() {
        System.out.println(
                "Math Functions:\n1) abs(x)\n2) acos(x)\n3) asin(x)\n4) atan(x)\n5) atan2(y,x)\n6) coordinates(x, y)\n7) brt(x)\n8) ceil(x)\n9) copySign(x, y)\n10) cos(x)\n11) cosh(x)\n12) exp(x)\n13) expm1(x)\n14) floor(x)\n15) getExponent(x)\n16) hypot(x, y)\n17) IEEEremainder(x, y)\n18) log(x)\n19) log10(x)\n20) log1p(x)\n21) doublemax(x, y)\n22) min(x, y)\n23) extAfter(x, y)\n24) nextUp(x)\n25) pow(x, y)\n26) random()\n27) round(x)\n28) rint(x)\n29) signum(x)\n30) sin(x)\n31) sinh(x)\n32) sqrt(x)\n33) tan(x)\n34) tanh(x)\n35) toDegrees(x)\n36) toRadians(x)\n37) ulp(x)");
        System.out.println(
                "ArrayLists:\nFormat - ArrayList<VarType> myArrayListName = new ArrayList<VarType>();\nFunctions:\n1) arraylist.add(\"Hello World\");\n2) arraylist.remove(0);\n3) arraylist.clear();\n4) arraylist.size();\n5) arraylist.get(0);\n6) arraylist.set(0, \"Hello World\")\nHow to loop through a arraylist:\nfor (int i = 0; i < arraylist.size(); i++) {\n System.out.println(arraylist.get(i));\n}\nor\nfor (String i : arraylist) {\n System.out.println(arraylist)\n}");
        System.out.println(
                "Linked List is the same as arraylists but with extra functions. They're:\n1) addFirst();\n2) addLast();\n3) removeFirst();\n4) removeLast();\n5) getFirst();\n6) getLast();");
        System.out.println(
                "Hashmap:\nFormat - Hashmap<VarType, VarType> myhashmapname = new Hashmap<VarType, VarType>();\nFunctions\n1) hashmap.put(\"Hello World\");\n2) hashmap.get(0);\n3) hashmap.remove(0);\n4) hashmap.clear();\n5) hashmap.size();\nHow to loop through hashmaps *Keys*:\nfor (String i : hashmap.keys()) {\n System.out.println(i)\n}\nValues:\nfor (String i : hashmap.values()) {\n System.out.println(i)\n}");
        System.out.println(
                "Hashset:\nFormat is same as arraylists or linkedlists\nFunctions:\n1) hashset.add(\"Hello World\");\n2) hashset.contains(\"Hello World\");\n3) hashset.remove(0);\n4) hashset.clear();\n5) hashset.size();\nLooping through a hashmap is also the same as arraylists and linkedlists");
        System.out.println(
                "Wrapper Classes:\nWe can't do ArrayList<int> array = new ArrayList<int>(); etc with any list type, instead, we use wrapper classes, like: Integer x = 10;, ArrayList<Boolean> array = new ArrayList<Boolean>(); etc. The types are:\n1) int to Integer\n2) float to Float\n3) double to Double\n4) boolean to Boolean\n5) char to Character\nWe can also get certain information about variables, Here are some functions which get certain information about variables:\n1) intvalue()\n2) byteValue()\n3) shortValue()\n4) longValue()\n5) floatValue()\n6) doubleValue()\n7) charValue()\n8) booleanValue()\n");
    }

    public static void java_project() {
        System.out.println(
                "Projects: 1) Number Guessing Game 2) Temperature Converter 3) Simple Chat Application 4) Guess the Word Game 5) Rock, Paper, Scissors Game 6) Basic ATM Simulator\n");
    }

    public static void todo_Manager() {
        ArrayList<String> Tasks = new ArrayList<String>();
        Iterator<String> it = Tasks.iterator();
        Tasks.add("Tasks:");
        System.out.println("Welcome to your todo manager");
        do {
            System.out.print(
                    "0) Exit\n1) View tasks\n2) Add task\n3) Remove task\nType the index number of the function you want to use: ");
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    choice = 0;
                    break;
                case 1:
                    if (Tasks.size() == 1) {
                        System.out.println("No tasks");
                    } else {
                        for (String i : Tasks) {
                            System.out.println(i);
                        }
                    }
                    break;
                case 2:
                    String line = sc.nextLine();
                    System.out.print("Type your task: ");
                    line = sc.nextLine();
                    Tasks.add(Tasks.size() + ". " + line);
                    System.out.println("Succesfully added the task: " + line);
                    break;
                case 3:
                    if (Tasks.size() == 1) {
                        System.out.println("No task to remove");
                    } else {
                        it.hasNext();
                        System.out.print("Type the index number of the task you want to remove: ");
                        int remove = sc.nextInt();
                        System.out.println("Successfully removed the task: " + Tasks.get(remove));
                        Tasks.remove(remove);
                    }
                    break;
                default:
                    System.out.println("Type a vaild choice number");
            }
        } while (choice != 0);
    }

    public static void ascii_value() throws IOException {
        System.out.print("Type the character you want to get the ascii value of: ");
        int ascii_value = System.in.read();
        sc.nextLine();
        System.out.println(ascii_value + "\n");
    }

    public static void colour() {
        System.out.println("-1 = Reset\n0 = Black\n1 = Red\n2 = Green\n3 = Blue\n1B = Red bright\n2B = Green bright\n");

    }

    public static void number_guessing__game() {
        System.out.print("Type the range from you want to guess the number: ");
        range = sc.nextInt();
        System.out.print("Type the number of chances allowed: ");
        chances = sc.nextInt();
        num = (int) (1 + Math.random() * range);
        do {
            System.out.println("Chances - " + chances);
            System.out.print("Type your number guess 1 - " + range + ": ");
            guess = sc.nextInt();
            if (chances != 0) {
                chances--;
            } else
                ;
            System.out.println(chances);
            if (guess > num) {
                System.out.println("Lower!");
            } else if (guess < num) {
                System.out.println("Higher!");
            } else if (chances == 0) {
                System.out.println("You used all the chances!\n");
            } else {
                System.out.println("You won!\n");
                sc.nextLine();
            }
        } while (guess != num || chances == 0);
    }

    public static void RPS() {
        do {
            int RPSCHOICE;
            System.out.print("0) Exit\n1) 1v1\n2) Computer\nType your choice: ");
            RPSCHOICE = sc.nextInt();
            switch (RPSCHOICE) {
                case 0:
                    break;
                case 1:
                    sc.nextLine();
                    System.out.print("Type the username of player 1: ");
                    String pl1username = sc.nextLine();
                    System.out.print("Type the username of player 2: ");
                    String pl2username = sc.nextLine();
                    System.out.print(pl1username + ": Rock paper scissors - Choose one: ");
                    String pl1choice = sc.nextLine();
                    if (pl1choice.equalsIgnoreCase("Rock") || pl1choice.equalsIgnoreCase("Paper")
                            || pl1choice.equalsIgnoreCase("Scissors")) {
                        clear();
                        System.out.print(pl2username + ": Rock paper scissors - Choose one: ");
                        String pl2choice = sc.nextLine();
                        if (pl2choice.equalsIgnoreCase("Rock") || pl2choice.equalsIgnoreCase("Paper")
                                || pl2choice.equalsIgnoreCase("Scissors")) {
                            clear();
                            if (pl1choice.equalsIgnoreCase("Paper") && pl2choice.equalsIgnoreCase("Rock")
                                    || pl1choice.equalsIgnoreCase("Scissors") && pl2choice.equalsIgnoreCase("Paper")
                                    || pl1choice.equalsIgnoreCase("Rock") && pl2choice.equalsIgnoreCase("Scissors")) {
                                System.out.println(pl1username + " has won!\n");
                            } else if (pl2choice.equalsIgnoreCase("Paper") && pl1choice.equalsIgnoreCase("Rock")
                                    || pl2choice.equalsIgnoreCase("Scissors") && pl1choice.equalsIgnoreCase("Paper")
                                    || pl2choice.equalsIgnoreCase("Rock") && pl1choice.equalsIgnoreCase("Scissors")) {
                                System.out.println(pl2username + " has won!\n");
                            } else if (pl1choice.equalsIgnoreCase(pl2choice)) {
                                System.out.println("It's a tie!\n");
                            }
                        } else {
                            System.out.println("Invalid Input");
                        }
                    } else {
                        System.out.println("Invalid Input");
                    }
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Rock paper scissors - Choose one: ");
                    pl1choice = sc.nextLine();
                    if (pl1choice.equalsIgnoreCase("Rock") || pl1choice.equalsIgnoreCase("Paper")
                            || pl1choice.equalsIgnoreCase("Scissors")) {
                        if (pl1choice.equalsIgnoreCase("Rock") && computer_choice == 1) {
                            System.out.println("The computer won since it chose paper!\n");
                        } else if (pl1choice.equalsIgnoreCase("Rock") && computer_choice == 2) {
                            System.out.println("You have won since the computer chose scissors!\n");
                        } else if (pl1choice.equalsIgnoreCase("Rock") && computer_choice == 3) {
                            System.out.println("Its a tie since the computer and you both chose rock!\n");
                        } else if (pl1choice.equalsIgnoreCase("Paper") && computer_choice == 1) {
                            System.out.println("The computer won since it chose scissors!\n");
                        } else if (pl1choice.equalsIgnoreCase("Paper") && computer_choice == 2) {
                            System.out.println("You won since the computer chose rock!\n");
                        } else if (pl1choice.equalsIgnoreCase("Paper") && computer_choice == 3) {
                            System.out.println("Its a tie since the computer and you both chose paper!\n");
                        } else if (pl1choice.equalsIgnoreCase("Scissors") && computer_choice == 1) {
                            System.out.println("The computer won since it chose rock!\n");
                        } else if (pl1choice.equalsIgnoreCase("Scissors") && computer_choice == 2) {
                            System.out.println("You won since the computer chose paper!\n");
                        } else if (pl1choice.equalsIgnoreCase("Scissors") && computer_choice == 3) {
                            System.out.println("Its a tie since the computer and you both chose scissors!\n");
                        } else {
                            System.out.println("Invalid Input");
                        }
                    }
                    break;
                default:
                    System.out.println("Invaild choice");
                    break;
            }
        } while (RPSCHOICE != 0);
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void dir() throws IOException {
        proc = run.exec(new String[] { "cmd", "/C", "Start", "dir" });
        System.out.println("");
    }
}