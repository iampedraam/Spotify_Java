package Classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataBase {
    public static String[] users, passwords, birthdays, accTypes, genders, artName;
    private static int numUsers = 0, currentUserIndex = 0;

    static { //User Data size
        users = new String[50];
        passwords = new String[50];
        birthdays = new String[50];
        accTypes = new String[50];
        genders = new String[50];
        artName = new String[50];
    }

    public static void setUser(int index, String newUser) {
        users[index] = newUser;
    }

    public static void setPass(int index, String newPass) {
        passwords[index] = newPass;
    }

    public static void setBirthdays(int index, String day, String month, String year) {
        birthdays[index] = day + " - " + month + " - " + year;
    }

    public static void setGender(int index, String gender) {
        genders[index] = gender;
    }
    public static void setArtName(int index, String name){
        artName[index]=name;
    }
    public static String getArtName(int index){
        return artName[index];
    }

    public static void setAccTypes(int index, String accTyped) {
        accTypes[index] = accTyped;
    }

    public static int getNumUsers() {
        return numUsers;
    }

    public static void increaseUsers() {
        numUsers++;
    }

    public static int getCurrentUserIndex() {
        return currentUserIndex;
    }

    public static void setCurrentUserIndex(int index) {
        currentUserIndex = index;
    }

    public static void saveData(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (int i = 0; i < numUsers; i++) {
                writer.println(users[i] + "," + passwords[i] + "," + birthdays[i] + "," + accTypes[i] + "," + genders[i] + "," + artName[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadData(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            int index = 0;
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                users[index] = data[0];
                passwords[index] = data[1];
                birthdays[index] = data[2];
                accTypes[index] = data[3];
                genders[index] = data[4];
                artName[index] = data[5];
                index++;
            }
            numUsers = index;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
