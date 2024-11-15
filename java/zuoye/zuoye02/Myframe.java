import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
/*我把原来的stu2.text文件由ANSI转为了utf-8，用来避免乱码*/
public class Myframe extends JFrame {
  //注意是Jframe 不是frame 继承出错很麻烦的
  static  List<String> list=null;
  JLabel name;
  JLabel img;
  JButton but1;
  JButton but2;
  int i=0;
  Timer myTimer=new Timer(200, new change());
//用的是swing里的线程，它在初始化时，可以设置1.周期 想实现的2.动作监听器函数
  
    public Myframe(){
      
      File dir=new File("D:/作业02/img");
      File[] dirContents=dir.listFiles();//打开了图片文件夹
      setTitle("点名程序");
      setLayout(new BorderLayout());

      img=new JLabel("请开始点名！",JLabel.CENTER);
      img.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);//显示图片的label
      name=new JLabel("...",JLabel.CENTER);
      name.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);//显示学号姓名的label
      but1=new JButton("随机点名");
      but1.addActionListener(new button());//点名按钮
      
  
      this.getContentPane().add(img,BorderLayout.CENTER);//图片放中间
      this.getContentPane().add(but1,BorderLayout.SOUTH);//学号姓名放最上面
      this.getContentPane().add(name,BorderLayout.NORTH);//按钮放最下面
      
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setBounds(500,100,400,650);
      setVisible(true);

      }
  class button implements ActionListener{
        public void actionPerformed(ActionEvent arg0){
         if(myTimer.isRunning()){myTimer.stop();}
         else{myTimer.start();}
          
          img.setText("");}
        }//按钮要实现的函数，如果线程执行，就停下，否则就执行，实现开关功能
 class change implements ActionListener{

  public void actionPerformed(ActionEvent arg0){

    int n=(int)(Math.random()*list.size());
    String url="D:/作业02/img/"+list.get(n).substring(0,12)+".jpg";
    Icon a=new ImageIcon(url);
    name.setText(list.get(n));
    img.setIcon(a);
  }

}
      public static void main(String[] args) {
        new Myframe();
        //第一步，图片改名√
        //第一点五步，lable显示图片
        //第二步，监听器的函数：线程启动与停止  

        
          try {
            Thread.sleep(500); // 线程睡眠0.5秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          try(FileReader rd=new FileReader("D:\\作业02\\stu2.txt");
          BufferedReader reader=new BufferedReader(rd);
         ){
            
           list = new ArrayList<>();
             String line;
              while((line=reader.readLine())!=null){list.add(line);}
              }
         catch(IOException e){
          e.printStackTrace();
          System.out.println("找不到文件");
         }
 //文件批量重命名，由于做一次就够了，所以注释掉
      /*    File dir=new File("D:/作业02/img");

        if (dir.isDirectory()){

          File[] dirContents=dir.listFiles();
          for(int i=0; i<dirContents.length;i++)
          { 
            dirContents[i].renameTo(new File("D:/作业02/img/"+list.get(i).substring(0,12)+".jpg"));
        }
        } */
    } 
  
    }
  

