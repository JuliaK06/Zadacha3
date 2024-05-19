package bg.smg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;


public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<Clothes> list = new PriorityBlockingQueue<>();
        Path resourceDirectory = Paths.get("src","resources");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();

        Thread t1 = new Thread(() -> {

            try(Scanner sc=new Scanner(new File(absolutePath+ "/guess.txt"))) {

                while (sc.hasNext()) {
                    String name = sc.next();
                    String type = sc.next();
                    double price = sc.nextDouble();
                    Clothes c1 = new Clothes(name, type, price);
                    list.put(c1);

                }
                sc.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        });
        Thread t2 = new Thread(() -> {
            File f = new File(absolutePath + "/calvin_klein.txt");
            Scanner sc = null;
            try {
                sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String name = sc.next();
                    String type = sc.next();
                    double price = sc.nextDouble();
                    Clothes c1 = new Clothes(name, type, price);
                    list.put(c1);
                }  sc.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        });

        Thread t3 = new Thread(() -> {
            File f = new File(absolutePath+ "/trussardi.txt");

            try {
             Scanner   sc = new Scanner(f);
                while (sc.hasNext()) {
                    String name = sc.next();
                    String type = sc.next();
                    double price = sc.nextDouble();
                    Clothes c1 = new Clothes(name, type, price);
                    list.put(c1);
                }sc.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        });
t1.start();
t2.start();
t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        File f2=new File(absolutePath +"/output.txt");
        try{
        PrintWriter output=new PrintWriter(f2);
        for(int i=0;i<10;i++){

            output.println(list.poll().toString());
        }
        output.close();
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
