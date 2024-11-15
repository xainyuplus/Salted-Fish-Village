package salted.fish;
import org.apache.poi.hslf.usermodel.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.math.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


 public class Myppt extends JFrame {
JLabel L1;
JPanel p2;
JButton b1;
JButton b2;
JButton b3;
JButton b4;
JButton b5;
String path;
Icon[] list=new Icon[30];
int num=0;
int size=1;
    public Myppt(){
    	  //try {
    		//  ppt= new HSLFSlideShow(new FileInputStream("C:\\Users\\29812\\Desktop\\java\\作业03读入ppt文件并显示\\1_1.ppt"));
  	//	} 
    //	  catch (IOException e) {
  			// TODO Auto-generated catch block
  		//	e.printStackTrace();
  		//}
    	//  SlideShow  show=new SlideShow(ppt);
    	//  PictureData[] _pictures = show.getPictureData(); 
    	L1=new JLabel();
    	p2=new JPanel();
    	b1=new JButton("导入文件");b1.addActionListener(new choose());
    	b2=new JButton("首页");b2.addActionListener(new change(1));
    	b3=new JButton("上一页");b3.addActionListener(new change(2));
    	b4=new JButton("下一页");b4.addActionListener(new change(3));
    	b5=new JButton("末页");b5.addActionListener(new change(4));
    	p2.add(b1);
    	p2.add(b2);
    	p2.add(b3);
    	p2.add(b4);
    	p2.add(b5);
    	
    	L1.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
    	p2.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
    	getContentPane().add(L1,BorderLayout.NORTH);
        getContentPane().add(p2,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,100,745,630);
        setVisible(true);
      
    }
    class choose implements ActionListener{
    	 public void actionPerformed(ActionEvent arg0){
    		
    		 JDialog dialog = new JDialog();
             dialog.setModal(true);

             JFileChooser fileChooser = new JFileChooser();
             int returnValue = fileChooser.showOpenDialog(dialog);

             if (returnValue == JFileChooser.APPROVE_OPTION) {
                 File selectedFile = fileChooser.getSelectedFile();
                path = selectedFile.getAbsolutePath();
                System.out.println("选择了文件："+path);
                int n=path.lastIndexOf("\\");
                String filePath=path.substring(0,n+1);
                String fileName=path.substring(n+1);
                System.out.println(filePath+"   "+fileName);
                if(fileName.indexOf(".ppt")==-1||fileName.indexOf(".pptx")!=-1) {
                	L1.setText("文件类型错误！仅支持.ppt文件(最好不要很大)，请重新导入");
                	
                }
                else {
                	L1.setText("加载中，请稍后");
                	transPPTToPic( filePath,  fileName);
                	L1.setText("");
                	L1.setIcon(list[0]);

                
                	
                }
             }

             dialog.dispose(); // Close the JDialog
    		  }
    	
    	
    }
    class change implements ActionListener{
    	int i;
    	public change(int n) {
    		i=n;
    	}
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		// TODO Auto-generated method stub
    		if(i==1) {		num=0;L1.setIcon(list[num]);							}
    		else if(i==2) { num--;num%=size;if(num==-1) {num=0;}L1.setIcon(list[num]);                                              }
    		else if(i==3) {num++;num%=size;L1.setIcon(list[num]);}
    		else if(i==4) {num=size-1;L1.setIcon(list[num]);}
    		
    	}
    	
    	
    }
  
    private void mkdir(String path){
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
    }
    
    public void transPPTToPic(String filePath, String fileName){
        // 生成输出
        String outRoot = filePath + fileName.substring(0, fileName.indexOf('.')) + File.separator;
        System.err.printf("图片输出路径为:%s\n", outRoot);
        // 不存在则创建文件夹
        mkdir(outRoot);
        // ppt读取路径
        String pptName = filePath + fileName;
        System.err.printf("PPT读取路径为:%s\n", pptName);
        FileInputStream fis = null;

        try{
            // 获取系统可用字体
            GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
            String[] fontNames  = e.getAvailableFontFamilyNames();

            // 读取ppt
            fis = new FileInputStream(new File(pptName));
            HSLFSlideShow ppt = new HSLFSlideShow(fis);


            /*
             * 解析PPT基本内容
             * */
            Dimension sheet = ppt.getPageSize();
            int width = sheet.width, height = sheet.height;
            List<HSLFSlide> pages = ppt.getSlides();

            System.err.printf("ppt基本信息: 共%s页,尺寸: %s , %s\n", pages.size(), width, height);
            size=pages.size();
            BufferedImage img      = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D    graphics = img.createGraphics();
            int i = 1;
            // 逐页遍历
            for(HSLFSlide slide : pages){

                // 清空画板
                graphics.setPaint(Color.white);
                graphics.fill(new Rectangle2D.Float(0, 0, width, height));
                slide.draw(graphics);
                // 输出为图片
                File f = new File(outRoot + i++ + ".png");
                System.out.printf("输出图片：%s\n", f.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(f);
                javax.imageio.ImageIO.write(img, "PNG", fos);
                fos.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        File dir=new File(outRoot);
        File[] dirContents=dir.listFiles();
        for(int i=0; i<dirContents.length;i++)
        { 
         list[i]=new ImageIcon(dirContents[i].getAbsolutePath());
        
      }
    }

	public static void main(String[] args) {
        Myppt a=new Myppt();
       // a.transPPTToPic("D:\\作业03读入ppt文件并显示\\", "1_1.ppt");
    }

}
