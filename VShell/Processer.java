package VShell;

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
        System.out.println("About:\n\nWelcome to VShell (Virtual Shell)! This is a custom \"shell\" fully made in java by ArnavN0tiyal. Version of shell is 1.3.3. Release date is 3/12/24 and total number of usable commands are 21. Commands in this \"shell\" are - print, time, eval, run, ascii, shutdown, colour, clear, rename, dirFiles, vsver, cmd, getInfo, crDir, rmDir, date, dateTime, sd, text, exit, help.\n\nHow to use commands:\n\nFirst is print, which prints to the console. To use this command, first type \"print\" then the string, like - [print hello world].\n\nSecond is time, which *obviously* shows the time.\n\nThird is eval, which evaluates statements like for basic statements - [eval -b 2 + 2], for scientific statements - [eval -s log 10] and for trigonometric statements - [eval -t sin 24] .\n\nFourth is run, which runs programs like - [run cmd].\n\nFifth is ascii, which shows the ascii value of characters like - [ascii I].\n\nSixth is shutdown, which shuts down your computer in certain ways like if I want to restart, do - [shutdown -r] etc, bascially the command prompt shutdown method.\n\nSeventh is colour, which changes the text colour like - [colour red].\n\nEighth is clear, which clears the console.\n\nNinth is dirFiles, which shows the files in a directory.\n\nTenth is vsver, which shows the version of the VShell.\n\nEleventh is cmd, it has three types - [cmd -b], [cmd -c], [cmd -p]. First one runs bash commands (Note - You will need wsl to use this on windows), the second one runs command prompt commands and the third one runs powershell commands.\n\nTwelfth is rename, which renames your shell username like - [rename user].\n\nThirteenth is getInfo, which gives info about your os and about your installation of java like - [getInfo os] or [getInfo java].\n\nFourteenth is crDir, which create a directory like - [crDir VShell_source].\n\nFifteenth is rmDir, which removes a directory like - [rmDir VShell_source].\n\nSixteenth is date, which shows the date.\n\nSeventeenth is dateTime, which shows the date and time.\n\nEighteenth is sd, which sets location of the area where dirFiles will print the contents of the folder.\n\nNintheenth is text, which turns on or off the location and shell name printing like - [text off] or - [text on]\n\nTwentieth is exit, which exits the shell.\n\nTwenty first is help, which you are using right now. It gives help about the shell");
    }

    public static void vshellVersion() {
        System.out.println("Version: 1.3.3\nRelease: 3/13/24");
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
