// Name: Jace Edwards
// Module 8 Assignment - File I/O

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Payroll {
   public static void main(String[] args) {
      String inputFile = "pay.csv";
      String outputFile = "output.csv";

      try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
           BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

         reader.readLine(); // Skip the input column headings.
         writer.write("EmpID,PayRate,HoursWorked,WeeklyPay");
         writer.newLine();

         String line;
         while ((line = reader.readLine()) != null) {
            if (line.trim().isEmpty()) {
               continue;
            }

            String[] columns = line.split(",");
            String employeeID = columns[0].trim();
            double payRate = Double.parseDouble(columns[1].trim());
            double hoursWorked = Double.parseDouble(columns[2].trim());
            double weeklyPay;

            if (hoursWorked > 40) {
               weeklyPay = (40 * payRate)
                     + ((hoursWorked - 40) * payRate * 1.5);
            } else {
               weeklyPay = hoursWorked * payRate;
            }

            String formattedPay = String.format(Locale.US, "$%.2f", weeklyPay);
            writer.write(employeeID + "," + columns[1].trim() + ","
                  + columns[2].trim() + "," + formattedPay);
            writer.newLine();
         }
      } catch (IOException e) {
         System.out.println("File error: " + e.getMessage());
         return;
      }

      System.out.println("Weekly pay written to " + outputFile);
   }
}
