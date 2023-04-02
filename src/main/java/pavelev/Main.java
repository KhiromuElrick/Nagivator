package pavelev;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pavelev.Controller.Authorization;
import pavelev.Controller.Registration;
import pavelev.DB.utils.HibernateUtilCreate;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int num = in.nextInt();

        if(num == 1){

            Registration.reg();

        }
        else if(num==2){

            Authorization.aut();

        }



    }



}
