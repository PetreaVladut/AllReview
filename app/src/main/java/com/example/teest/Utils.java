package com.example.teest;

import android.content.Context;

import java.io.FileNotFoundException;

public class Utils {
    public int search_user(String email, String password, Context context) throws FileNotFoundException {
        try {
            FileUtilities ceva=new FileUtilities("passwords.txt","r", context);
            String s=ceva.read_file();
            String[] lines = s.split("\n"); // split the string into an array of lines
            for (String line : lines) {
                String[] parts = line.split(", "); // split the line by comma and space
                //String email_pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                //Pattern p = Pattern.compile(email_pattern); // compile the regex pattern
                //Matcher m = p.matcher(parts[0]);
                //if (m.find()) { // check if the line has the correct format
                // perform any necessary checks on the email and password here
                //if(parts[0]!=email)
                //System.out.println(parts[0]+'\n'+parts[1]+'\n');
                if(email.equals(parts[0]))
                {
                    if(!password.equals(parts[1].trim()))
                        return 1;
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 2;
    }
    public int add_user(String email, String password, String user, Context context)
    {
        try {
            FileUtilities ceva=new FileUtilities("passwords.txt","w", context);
            String s=email+", "+password+", "+user+'\n';
            ceva.append_file(s);
            String[] lines = s.split("\n"); // split the string into an array of lines
            for (String line : lines) {
                String[] parts = line.split(", "); // split the line by comma and space
                //String email_pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
                //Pattern p = Pattern.compile(email_pattern); // compile the regex pattern
                //Matcher m = p.matcher(parts[0]);
                //if (m.find()) { // check if the line has the correct format
                // perform any necessary checks on the email and password here
                //if(parts[0]!=email)
                //System.out.println(parts[0]+'\n'+parts[1]+'\n');
                if(email.equals(parts[0]))
                {
                    if(!password.equals(parts[1].trim()))
                        return 1;
                    return 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
