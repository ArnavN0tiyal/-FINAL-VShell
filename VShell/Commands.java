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

public class Commands {
    static String usercmd;
    static final Runtime run = Runtime.getRuntime();
    static Process proc;
    static int exit = 1;
    static final Scanner sc = new Scanner(System.in);
    static String name = "shell";

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
                default:
                    System.err.println("ERROR! Use label -b for basic statements, -s for scientific and -t for trigonometric. Write statements for basic statements - [eval -b 2 + 2], for scientific - [eval -s exp 2] and for trignometric - [eval -t cos 2]");
                    break;
            }
        }
        //*  Sorry that these are unreadable, can't make it more readable since switch cases don't work on them

        private static void evalScientific() {
            String processed = usercmd.replace("eval -s", "").trim();
            String[] tokens = processed.split("\\s");
            double num1 = Double.parseDouble(tokens[1]);
            if (processed.startsWith("abs")) {
                System.out.println(Math.abs(num1));
            } else if (processed.startsWith("max")) {
                double num2 = Double.parseDouble(tokens[2]);
                System.out.println(Math.max(num1, num2));
            } else if (processed.startsWith("min")) {
                double num2 = Double.parseDouble(tokens[2]);
                System.out.println(Math.min(num1, num2));
            } else if (processed.startsWith("sqrt")) {
                System.out.println(Math.sqrt(num1));
            } else if (processed.startsWith("pow")) {
                double num2 = Double.parseDouble(tokens[2]);
                System.out.println(Math.pow(num1, num2));
            } else if (processed.startsWith("round")) {
                System.out.println(Math.round(num1));
            } else if (processed.startsWith("cbrt")) {
                System.out.println(Math.cbrt(num1));
            } else if (processed.startsWith("signum")) {
                System.out.println(Math.signum(num1));
            } else if (processed.startsWith("ceil")) {
                System.out.println(Math.ceil(num1));
            } else if (processed.startsWith("floor")) {
                System.out.println(Math.floor(num1));
            } else if (processed.startsWith("hypot")) {
                double num2 = Double.parseDouble(tokens[2]);
                System.out.println(Math.hypot(num1, num2));
            } else if (processed.startsWith("ulp")) {
                System.out.println(Math.ulp(num1));
            } else if (processed.startsWith("exp")) {
                System.out.println(Math.exp(num1));
            } else if (processed.startsWith("expm1")) {
                System.out.println(Math.expm1(num1));
            } else if (processed.startsWith("log")) {
                System.out.println(Math.log(num1));
            } else if (processed.startsWith("log10")) {
                System.out.println(Math.log10(num1));
            } else if (processed.startsWith("log1p")) {
                System.out.println(Math.log1p(num1));
            } else {
                System.err.println( "ERROR! Note that some functions require two numbers instead of one and avaliable functions are - [max, min, abs, sqrt, cbrt, pow, round, signum, ceil, floor, hypot, ulp, exp, expm1, log, log10 , log1p]");
            }
        }

        private static void evalTrigonometric() {
            String processed = usercmd.replace("eval -t", "").trim();
            String[] tokens = processed.split("\\s");
            Double num1 = Double.parseDouble(tokens[1]);
            if (processed.startsWith("sin")) {
                System.out.println(Math.sin(num1));
            } else if (processed.startsWith("cos")) {
                System.out.println(Math.cos(num1));
            } else if (processed.startsWith("tan")) {
                System.out.println(Math.tan(num1));
            } else if (processed.startsWith("tan")) {
                System.out.println(Math.tan(num1));
            } else if (processed.startsWith("sinh")) {
                System.out.println(Math.sinh(num1));
            } else if (processed.startsWith("cosh")) {
                System.out.println(Math.cosh(num1));
            } else if (processed.startsWith("tanh")) {
                System.out.println(Math.tanh(num1));
            } else if (processed.startsWith("asin")) {
                System.out.println(Math.asin(num1));
            } else if (processed.startsWith("acos")) {
                System.out.println(Math.acos(num1));
            } else if (processed.startsWith("atan")) {
                System.out.println(Math.atan(num1));
            } else {
                System.err.println("ERROR! Use functions - [sin, cos, tan, sinh, cosh, tanh, asin, acos, atan]");
            }
        }
    }

    class Shells {
        private static void bash() {
            try {
                String processed = usercmd.replace("cmd -b", "").trim();
                String[] bashBuild = { "bash", "-c", processed };
                ProcessBuilder builder = new ProcessBuilder(bashBuild);

                Process process = builder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.err.println("ERROR! " + e);
            }
        }

        private static void commandprompt() {
            try {
                String processed = usercmd.replace("cmd -c", "").trim();
                String[] cmdBuild = { "cmd", "/C", processed };
                ProcessBuilder builder = new ProcessBuilder(cmdBuild);

                Process process = builder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.err.println("ERROR! " + e);
            }
        }

        private static void powershell() throws IOException {
            try {
                String processed = usercmd.replace("cmd -p", "").trim();
                String[] psBuild = { "powershell", "/C", processed };
                ProcessBuilder builder = new ProcessBuilder(psBuild);

                Process process = builder.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.err.println("ERROR! " + e);
            }
        }

    }

    public static void getInfo() {
        if (usercmd.endsWith("os")) {
            System.out.println("Os: " + System.getProperty("os.name") + "\n" + "Os version: "+ System.getProperty("os.version") + "\n" + "Os architecture: " + System.getProperty("os.arch"));
        } else if (usercmd.endsWith("java")) {
            System.out.println("Java installation directory: " + System.getProperty("java.home") + "\n"+ "JVM version: " + System.getProperty("java.runtime.version") + "\n" + "JVM implementation name: "+ System.getProperty("java.vm.name") + "\n" + "Java compiler: "+ System.getProperty("java.compiler"));
        }
    }

    public static void rename() throws IOException {
        String[] tokens = usercmd.split("\\s");
        name = tokens[1];
    }

    public static void shellType() throws IOException {
        String[] tokens = usercmd.split("\\s");
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
    }

    public static void help() {
        System.out.println("About:\n\nWelcome to VShell (Virtual Shell)! This is a custom \"shell\" fully made in java by ArnavN0tiyal. Version of shell is 1.3, release date is 3/8/24 and total number of usable commands are 17. Commands in this \"shell\" are - print, time, date, dateTime, eval, run, ascii, colour, shutdown, clear, cmd, rename, getInfo, crDir, rmDir.\n\nHow to use commands:\n\nFirst is print, which prints to the console. To use this command, first type \"print\" then the string, like - [print hello world].\n\nSecond is time, which *obviously* shows the time.\n\nThird is eval, which evaluates statements like for basic statements - [eval -b 2 + 2], for scientific statements - [eval -s log 10] and for trigonometric statements - [eval -t tan 2] .\n\nFourth is run, which runs programs like - [run cmd].\n\nFifth is ascii, which shows the ascii value of characters like - [ascii I].\n\nSixth is shutdown, which shuts down your computer in certain ways like if I want to restart, do - [shutdown /r] etc, bascially the command prompt shutdown method.\n\nSeventh is colour, which changes the text colour like - [colour red].\n\nEighth is clear, which clears the console.\n\nNinth is dirfiles, which shows the files in the presetted directory, change the directory yourself.\n\nTenth is shver, which shows the version of the VShell.\n\nEleventh is cmd, it has three types - [cmd -b], [cmd -c], [cmd -p]. First one runs bash commands (Note - You will need wsl to use this on windows), the second one runs command prompt commands and the third one runs powershell commands.\n\nTwelfth is rename, which renames your shell username like - [rename user].\n\nThirteenth is getInfo, which gives info about your os and about your installation of java like - [getInfo os] or [getInfo java].\n\nFourteenth is crDir, which create a directory like - [crDir VShell_source].\n\nFifteenth is rmDir, which removes a directory like - [rmDir VShell_source].\n\nSixteenth is  date, which shows the date.\n\nSeventeenth is dateTime, which shows the date and time.");
    }

    public static void shellversion() {
        System.out.println("Version: 1.3\nRelease: 3/8/24");
    }

    public static void print() {
        String processed = usercmd.replace("print", "").trim();
        System.out.println(processed);
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("mm-dd-yy HH:mm:ss");
        System.out.println("The current date and time is: " + dateTime.format(dateTimeFormatter));
    }

    public static void evalType() {
        String[] tokens = usercmd.split("\\s");
        switch (tokens[1]) {
            case "-b":
                Evaluation.evalBasic();
                break;
            case "-s":
                Evaluation.evalScientific();
                break;
            case "-t":
                Evaluation.evalTrignometric();
                break;
            default:
                System.out.println("ERROR! Use lables like - [-b, -s, -t, -te]");
                break;
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
            System.err.println("ERROR! Program not recognized, use [edge, cmd, code, arc, cursor, vi, vim, emacs, nano, powershell, powershell ise, calculator]");
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
            System.err.println("ERROR! Shutdown type not recognized, use [/i, /l, /s, /r, /g, /a, /p, /h]");
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Set<String> listFiles(String dir) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(dir))) {return stream.filter(file -> !Files.isDirectory(file)).map(Path::getFileName).map(Path::toString).collect(Collectors.toSet());}
    }

    public static void dirfiles() throws IOException {
        System.out.println(listFiles("C:\\"));
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
            System.err.println("ERROR, Colour not recognized, use [reset, black, red, orange, blue, green]");
        }
    }

    public static void ascii() throws IOException {
        char valueinput = usercmd.charAt(6);
        int asciivalue = valueinput;
        System.out.println(asciivalue);
        if (usercmd.length() > 1) {
            System.err.println("ERROR! Can only read one ascii char");
        }
    }
}
