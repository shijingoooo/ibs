package com.donut.server.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TransAddr2Json
{
    public static void main(String[] args)
    {
        try
        {

            FileInputStream fis = new FileInputStream(new File("D:/data.csv"));
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("GBK"));
            BufferedReader br = new BufferedReader(isr);
            
            FileWriter fw = new FileWriter(new File("D:/outjson.js"));
            BufferedWriter bw = new BufferedWriter(fw);
            
            String line = null;
            String lastcity=null;
            String lastPro=null;
            bw.append("{");
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
                        bw.append(",\""+town+"\":\""+town+"\"");
                    }else
                    {
                        if(lastcity!=null)
                        {
                            bw.append("}},");
                        }
                        lastcity = city;
                        bw.append("\""+city+"\":{val:\""+city+"\",items:{");
                        bw.append("\""+town+"\":\""+town+"\"");
                    }
                }else
                {
                    if(lastPro!=null)
                    {
                        bw.append("}}");
                        bw.append("}},");
                    }
                    
                    lastPro = provice;
                    lastcity = city;
                    bw.append("\""+provice+"\":{val:\""+provice+"\",items:{");
                    bw.append("\""+city+"\":{val:\""+city+"\",items:{");
                    bw.append("\""+town+"\":\""+town+"\"");
                }
            }
            bw.append("}}");
            bw.append("}}}");
            br.close();
            bw.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
}
