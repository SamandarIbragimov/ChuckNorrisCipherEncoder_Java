package chucknorris;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static StringBuilder encode(String s){
        s = convertStringToBinary(s);
        StringBuilder sb = new StringBuilder("");
        char c = s.charAt(0);
        if(c=='0')
            sb.append("00 0");
        else
            sb.append("0 0");
        for(int i=1;i<s.length();i++){
            if(s.charAt(i)==c){
                sb.append("0");
            }
            else{
                sb.append(" ");
                if(s.charAt(i)=='0'){
                    sb.append("00 0");
                    c='0';
                } else{
                    sb.append("0 0");
                    c='1';
                }
            }
        }
        return sb;
    }

    public static void decode(String s){
        StringBuilder sb = new StringBuilder("");
        String[] arr = s.split(" ");
        for(int i=0;i<arr.length;i+=2){
            if(arr[i].equals("0")){
                for(int j=0;j<arr[i+1].length();j++){
                    sb.append('1');
                }
            } else {
                sb.append(arr[i + 1]);
            }
        }
        String str = sb.toString();
        sb=new StringBuilder("");
        Arrays.stream(str.split("(?<=\\G.{7})")).forEach(l -> System.out.print((char) Integer.parseInt(l, 2)));
        System.out.print('\n');
    }
    public static String convertStringToBinary(String input) {

        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%7s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
                            .replaceAll(" ", "0")                         // zero pads
            );
        }
        return result.toString();

    }

    public static boolean valid(String s){
        if(s.matches("^[0 ]+$")==false)
            return false;
        String[] arr = s.split(" ");
        if(arr.length%2!=0)
            return false;
        for(int i=0;i<arr.length;i+=2){
            if(arr[i].equals("0")==false && arr[i].equals("00")==false)
                return false;
        }
        int k=0;
        for(int i=1;i<arr.length;i+=2){
            k+=arr[i].length();
        }
        if(k % 7 != 0)
            return false;


        return true;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String option,menu = "Please input operation (encode/decode/exit):";
        while(true){
            System.out.println(menu);
            option = sc.nextLine();
            if(option.equals("encode")){
                System.out.println("Input string:");
                String s = sc.nextLine();
                System.out.println("Encoded string:");
                System.out.println(encode(s));
            }else if(option.equals("decode")){
                System.out.println("Input encoded string:");
                String s = sc.nextLine();
                if(valid(s)){
                    System.out.println("Decoded string:");
                    decode(s);
                } else
                    System.out.println("Encoded string is not valid.");
            }else if(option.equals("exit")){
                System.out.println("Bye!"); break;
            }
            else
                System.out.println("There is no '" + option + "' operation");
        }
    }
}