import java.io.BufferedReader;//文件读取的包
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = ""; // 存储输入
        System.out.println("please input: ");
        System.out.println("wc.exe -c file.c    //返回文件 file.c 的字符数");
        System.out.println("wc.exe -w file.c    //返回文件 file.c 的词的数目  ");
        System.out.println("wc.exe -l file.c    //返回文件 file.c 的行数");

        while (true) {
            Scanner s = new Scanner(System.in);
            input = s.nextLine();//得到输入命令
            String[] paramAndPath = input.split(" "); // 分割命令
            if(!paramAndPath[0].equals("wc.exe") || paramAndPath.length!=3) {
                System.out.println("error command");
                continue;
            }
            //获取命令
            String parameter =paramAndPath[1];
            // 获取文件名
            String fileName = paramAndPath[2];
            int[] result=counter(fileName);//计数
            output(parameter,result);//输出结果
        }
    }
    //输出函数
    private static void output(String parameter,int[] result) {
        if(result[0]==-1) return;
        switch(parameter) {
            case "-l":
                System.out.println("line count："+result[0]);
                break;
            case "-w":
                System.out.println("word count："+result[1]);
                break;
            case "-c":
                System.out.println("char count："+result[2]);
                break;
            default:
                System.out.println("error command");
        }
        System.out.println("");
    }
    //计数
    private static int[] counter(String fileName) {
        int[] result= {0,0,0};//result[0]对应行数；result[1]对应单词数；result[2]对应字符数
        File file = new File(fileName);
        if (file.exists()) {
            try {
                //文件流读取文件
                FileInputStream fis = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String line = "";
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    result[0]++;
                    sb.append(line);
                    result[2] += line.length();
                }
                result[1] = sb.toString().split("\\s+").length;//
                br.close();
                isr.close();
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("please enter correct path");
            result[0]=-1;
        }
        return result;
    }
}