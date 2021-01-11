
import java.text.DecimalFormat;


class Retirement {

// Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) {
        
        int years = 0;
        double startingbalance = 0;
        double returnrate = 0;
        double addedsavings =0;
        double retirementage=0;
        double yearlyexpenses=0;
        double inflation=0;
        int age=0;

// testing for [args[0]] numberexception not enough params
        
        int argscount = args.length;
// argument parsing
        try {
            years = Integer.parseInt(args[0]);
            startingbalance = Double.parseDouble(args[1])*1000000;
            returnrate = Double.parseDouble(args[2]) / 100;
            addedsavings = Double.parseDouble(args[3]);
            retirementage = Double.parseDouble(args[4]);
            yearlyexpenses = Double.parseDouble(args[5]);
            age = Integer.parseInt(args[6]);
            inflation=Double.parseDouble(args[7]);
            
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception) {
            alignargs();
        }

        

// testing for argument order
        if (argscount != 8) {
            alignargs();
        }
        if (years > 60 || years < 10) {
            alignargs();
        }

// variable initialization
      
        int x = 0;
        double workingsalary = 184000;
        double mutliplyer = 1 + returnrate;
        DecimalFormat decimalFormat = new DecimalFormat("$ ###,###,###.00");

        System.out.println(ANSI_BOLD);
        System.out.println(ANSI_BLUE + "Retirement Age: " + retirementage + "  Return Rate: " + (returnrate * 100)
                + "%  " + " Starting Balance: " + decimalFormat.format(startingbalance));
        System.out.println(ANSI_RESET);

        while (x <= years) {
            String tablestring = ("Age : " + (age) + ": ");

            if (age < retirementage) {
                startingbalance = (startingbalance + addedsavings) * mutliplyer;
                workingsalary= (workingsalary*1.04);
                System.out.print(ANSI_GREEN + ANSI_BOLD + tablestring + decimalFormat.format(startingbalance));
                System.out.println("\t\t Annual Salary: " + decimalFormat.format(workingsalary));
                System.out.print(ANSI_RESET);
            }

            if (age >= retirementage) {
                yearlyexpenses= (yearlyexpenses*(1+(inflation/100)));
                if (age >= 65 && age < 67) {
                    startingbalance = ((startingbalance * mutliplyer)- yearlyexpenses);
                    System.out.print(ANSI_CYAN + ANSI_BOLD + tablestring + decimalFormat.format(startingbalance));
                    System.out.println("  \t    " + " Income No Soc/Sav: " + decimalFormat.format(yearlyexpenses));

                } else {
                    startingbalance = ((startingbalance * mutliplyer) -yearlyexpenses + (12 * 3000));
                    System.out.print(ANSI_RED + ANSI_BOLD + tablestring + decimalFormat.format(startingbalance));
                    System.out.println("  \t" + "Yearly Expenses With Soc: " + decimalFormat.format(yearlyexpenses));

                }

            }
           
        
            age++;
            x++;

        }
        System.out.println(ANSI_RESET);
        System.out.println(ANSI_YELLOW + ANSI_BOLD);
        System.out.println("Projected Terminal Value:\t " + decimalFormat.format(startingbalance));

        System.out.println();
        System.out.println("Projected Ending Income:\t " + decimalFormat.format(yearlyexpenses));
        System.out.println(ANSI_RESET);
    }

    private static void alignargs() {

// sorting arguments to align with inputs

        System.out.printf("%n" + ANSI_YELLOW + ANSI_BOLD
                + "Proper Usage: (years>10 startingbalance return<100%% savings<=64000 retirementage>55 yearlyexpenses inflation)%n");
        System.out.println(ANSI_RESET);
        System.exit(1);
    }

}
