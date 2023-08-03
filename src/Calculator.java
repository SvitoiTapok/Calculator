import javax.swing.*;
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
            JOptionPane.showMessageDialog(null, "пожалуйста, введите выражение вида \"a <операция> b\" в консоль");
            Scanner sc = new Scanner(System.in);
            String a[] = sc.nextLine().split(" ");
            try {
                double first = Double.parseDouble(a[0]);
                double second = Double.parseDouble(a[2]);
                if (!(a[1].equals("+")||a[1].equals("-")||a[1].equals("*")||a[1].equals("/"))){
                    throw new Exception("Operation Error!");
                }
                if(a[1].equals("/")&&second==0){
                    throw new Exception("Error! Division by zero");
                }
                System.out.println((a[1].equals("+"))?first+second:(a[1].equals("-"))?first-second:(a[1].equals("*"))?first*second:first/second);
            }
            catch (NumberFormatException errorInt){
                System.out.println("Error! Not number");
            }
            catch (Exception b) {
                System.out.println(b.getMessage());
            }
        }else {
            File fil = new File("input.txt");
            try {
                Scanner input = new Scanner(fil);
                String a[] = input.nextLine().split(" ");
                try {
                    double first = Double.parseDouble(a[0]);
                    double second = Double.parseDouble(a[2]);
                    if (!(a[1].equals("+") || a[1].equals("-") || a[1].equals("*") || a[1].equals("/"))) {
                        throw new Exception("Operation Error!");
                    }
                    if (a[1].equals("/") && second == 0) {
                        throw new Exception("Error! Division by zero");
                    }
                    System.out.println((a[1].equals("+")) ? first + second : (a[1].equals("-")) ? first - second : (a[1].equals("*")) ? first * second : first / second);
                } catch (NumberFormatException errorInt) {
                    System.out.println("Error! Not number");
                } catch (Exception b) {
                    System.out.println(b.getMessage());
                }
            } catch (FileNotFoundException err) {
                JOptionPane.showMessageDialog(null, "файл не найден");
                Callback(JOptionPane.NO_OPTION);
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

