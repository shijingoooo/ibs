package com.donut.server.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TransAddr2XML
{
    public static void main(String[] args)
    {
        try
        {

            FileInputStream fis = new FileInputStream(new File("D:/data.csv"));
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("GBK"));
            BufferedReader br = new BufferedReader(isr);
            
            FileWriter fw = new FileWriter(new File("D:/out.xml"));
            BufferedWriter bw = new BufferedWriter(fw);
            
            String line = null;
            String lastcity=null;
            String lastPro=null;
            while ((line = br.readLine()) != null)
            {
                String []data = line.split(",");
                String provice = data[1];
                String city = data[5];
                String town = data[8];
                if(provice.equals(lastPro))
                {
                    if(city.equals(lastcity))
                    {
                        bw.append("    <district name=\""+town+"\" zipcode=\"100000\" />");
                        bw.newLine();
                    }else
                    {
                        if(lastcity!=null)
                        {
                            bw.append("  </city>");
                            bw.newLine();
                        }
                        lastcity = city;
                        bw.append("  <city name=\""+city+"\">");
                        bw.newLine();
                        bw.append("    <district name=\""+town+"\" zipcode=\"100000\" />");
                        bw.newLine();
                    }
                }else
                {
                    if(lastPro!=null)
                    {
                        bw.append("  </city>");
                        bw.newLine();
                        bw.append("</province>");
                        bw.newLine();
                    }
                    
                    lastPro = provice;
                    lastcity = city;
                    bw.append("<province name=\""+provice+"\">");
                    bw.newLine();
                    bw.append("  <city name=\""+city+"\">");
                    bw.newLine();
                    bw.append("    <district name=\""+town+"\" zipcode=\"100000\" />");
                    bw.newLine();
                }
            }
            bw.append("  </city>");
            bw.append("</province>");
            br.close();
            bw.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
