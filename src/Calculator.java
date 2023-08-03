import javax.swing.*;
import java.awt.desktop.UserSessionEvent;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        User a = new User(new Worker());
        a.DoCalculate();
    }
}
interface Call{
    public void Callback(int ll);
}

class Worker implements Call{
    @Override
    public void Callback(int ll){
        if (ll == JOptionPane.NO_OPTION){
            try (FileWriter file = new FileWriter("output.txt", false)) {
                JOptionPane.showMessageDialog(null, "пожалуйста, введите выражение вида \"a <операция> b\" в консоль. Чтобы остановить программу, напишите stop");
                Scanner sc = new Scanner(System.in);
                String a[] = sc.nextLine().split(" ");
                while (!a[0].equals("stop")) {
                    file.write(String.join(" ", a) + " = ");
                    try {
                        double first = Double.parseDouble(a[0]);
                        double second = Double.parseDouble(a[2]);
                        if (!(a[1].equals("+") || a[1].equals("-") || a[1].equals("*") || a[1].equals("/"))) {
                            throw new Exception("Operation Error!");
                        }
                        if (a[1].equals("/") && second == 0) {
                            throw new Exception("Error! Division by zero");
                        }
                        file.write(Double.toString((a[1].equals("+")) ? first + second : (a[1].equals("-")) ? first - second : (a[1].equals("*")) ? first * second : first / second));
                    } catch (NumberFormatException errorInt) {
                        file.write("Error! Not number");
                    } catch (Exception b) {
                        file.write(b.getMessage());
                    }
                    finally {
                        a = sc.nextLine().split(" ");
                        file.write("\n");
                    }
                }
            } catch (IOException b){
                System.out.println("IOException");
            }
        }else {
            Scanner sc = new Scanner(System.in);
            JOptionPane.showMessageDialog(null, "Пожалуйста, введите путь к файлу в консоль");
            String path = sc.nextLine();
            File fil = new File(path);
            try (FileWriter file = new FileWriter("output.txt", false)) {

                try {

                    Scanner input = new Scanner(fil);
                    while (input.hasNextLine()) {
                        String a[] = input.nextLine().split(" ");
                        file.write(String.join(" ", a) + " = ");
                        try {
                            double first = Double.parseDouble(a[0]);
                            double second = Double.parseDouble(a[2]);
                            if (!(a[1].equals("+") || a[1].equals("-") || a[1].equals("*") || a[1].equals("/"))) {
                                throw new Exception("Operation Error!");
                            }
                            if (a[1].equals("/") && second == 0) {
                                throw new Exception("Error! Division by zero");
                            }
                            if (a[1].equals("+")) {
                                file.write(String.valueOf(first+second));
                            }
                            if(a[1].equals("-"))
                                file.write(Double.toString(first - second));
                            if(a[1].equals("*"))
                                file.write(Double.toString(first * second));
                            if(a[1].equals("/"))
                                file.write(Double.toString(first / second));
                        } catch (NumberFormatException errorInt) {
                            file.write("Error! Not number");
                        } catch (Exception b) {
                            String aboa = b.getMessage();
                            file.write(aboa);
                        }
                        finally {
                            file.write("\n");
                        }
                    }
                    } catch(FileNotFoundException err){
                        JOptionPane.showMessageDialog(null, "файл не найден");
                        User a = new User(new Worker());
                        a.DoCalculate();
                    }

            }
            catch (IOException ex){
                JOptionPane.showMessageDialog(null, "IOException");
            }
        }
    }
}
class User{
    Call call;
    User(Call call){
        this.call = call;
    }
    public void DoCalculate(){
        int answer = JOptionPane.showConfirmDialog(null, "если хотите считать пример из файла, нажмите YES, иначе нажмите NO", "Calculator", JOptionPane.YES_NO_OPTION);
        while(answer!=JOptionPane.YES_OPTION&&answer!=JOptionPane.NO_OPTION){
            answer = JOptionPane.showConfirmDialog(null, "если хотите считать пример из файла, нажмите YES, иначе нажмите NO", "Calculator", JOptionPane.YES_NO_OPTION);
        }
        call.Callback(answer);
    }


}

