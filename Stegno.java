
import java.io.*;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.math.BigInteger;
 
public class Stegno
{
    public static void main(String args[])throws IOException
    {
        int width=0;
        int height=0;
        int count=0; //ini. count to 0
        BufferedImage img = null,img2=null;
        File f = null;
        String s="";
        int l=0;
        String ans="";
        try{
        if(args[0].equals("-enc")){
        // read image
        try
        {
            f = new File(args[1]);
            img = ImageIO.read(f);
            s= new Scanner(new File(args[2])).useDelimiter("\\Z").next();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        byte[] bytes = s.getBytes();
        StringBuilder binary2 = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary2.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
     
        }
        String binary = binary2.toString();
         width = img.getWidth();
         height = img.getHeight();
        l=binary.length();
        
 
    
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if(y==0&&x==0){
                    img.setRGB(0,0,l);
                }
                else if(count<l){
                    int p=img.getRGB(x,y);
                    int a = (p>>24)&0xff;
                    int r = (p>>16)&0xff;
                    int g = (p>>8)&0xff;
                    int b = p&0xff;
                    char ch=binary.charAt(count);
                    if(ch=='1'){
                        b=b|1;
                      
                    }
                    else{
                        b &= ~(1 << 0);
                    }
                    p = (a<<24) | (r<<16) | (g<<8) | b;
                    img.setRGB(x,y,p);
                    count++;

                }
                
            }
        }
 
        // write image
        try
        {
            f = new File(args[3]);
            ImageIO.write(img, "png", f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
        if(args[0].equals("-dec")){
        try
        {
            f = new File(args[1]);
            img2 = ImageIO.read(f);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        String dec="";
        count=0;
        width = img2.getWidth();
        height = img2.getHeight();
        l=img2.getRGB(0,0);
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                if(y==0&&x==0){
                    
                }
                else if(count<l){
                    int p=img2.getRGB(x,y);
                    int b = p&0xff;
                    if(b%2==0){
                        dec=dec+"0";
                    }
                    else{
                        dec=dec+"1";
                    }
                    
                    count++;

                }
                
            }
        }
        
        for(int i = 0; i <= dec.length() - 8; i+=8)
        {
            int k = Integer.parseInt(dec.substring(i, i+8), 2);
            ans += (char) k;
            }         
            //System.out.println(ans);
            try{
            BufferedWriter out = new BufferedWriter(new FileWriter(args[2]));
            out.write(ans);   
            out.close();
        }
        catch(IOException e){

        }
    }
  }catch(Exception e){

  }
 }
}