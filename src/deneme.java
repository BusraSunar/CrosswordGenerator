import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class deneme {
    public static void main(String []args){
       /* ArrayList<String> arrayList= new ArrayList<>();
        arrayList.add("what");
        arrayList.add("where");
        arrayList.add("when");
        arrayList.add("that");
        arrayList.add("toast");
        arrayList.add("tthtttt");
        String patternString = ".h.t";
        ArrayList<String> foundaz= new ArrayList<>();

        for (String str : arrayList) {
            Pattern pi = Pattern.compile(patternString);
            Matcher ma = pi.matcher(str);

            if (ma.matches()) {
                foundaz.add(str);
            }

        }
        System.out.println(foundaz);*/
       /* Scanner scan = new Scanner("nullnull..null.");
        scan.useDelimiter("null");
        while(scan.hasNext()){
            System.out.println(scan.next());
        }
        scan.close();
*/
        /*String [][] tmp={{"null","null",".","null","null"}
                        ,{"null",".",".",".","null"}
                        ,{".",".",".","null","null"},
                        {"null",".","null",".","."},
                        {"null",".","null",".","."}};


        String[] str=new String[5];
        ArrayList<String > deneme= new ArrayList<>();
        deneme.add("the");
        deneme.add("law");
        deneme.add("of");
        deneme.add("by");
        int counter=0;
        for (int i=0;i<tmp.length;i++){
            for (int j=0;j<tmp.length;j++){
                str[j]=tmp[i][j];
                if (str[j].equals("null")) {
                    if (counter>1){
                        str[j]
                    }

                    counter = 0;
                }else
                    counter++;
            }


        }*/
        String[] str1={"aaaaa","aaa","aaaaaaaaaa"};
        for(int i=0;i<str1.length;i++)
        {
            for(int j=i+1;j<str1.length;j++)
            {
                if(str1[i].length()>str1[j].length())
                {
                    String temp= str1[i];
                    str1[i]=str1[j];
                    str1[j]=temp;
                }
            }
        }
        for(int i=0;i<str1.length;i++)
        {
            System.out.print(str1[i]+" ");
        }
    }

    }

