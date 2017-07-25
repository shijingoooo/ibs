package com.donut.server.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;

public class CheckImg extends HttpServlet
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    // 设置字体
    private Font mFont = new Font("Times New Roman", Font.CENTER_BASELINE, 24);

    // 在这里定义了验证码图片的宽度
    private int width = 100;

    // 定义验证码图片的高度
    private int height = 36;

    // 定义验证码图片文字长度
    private int codeNum = 4;

    // 随机生成的字条串
    private StringBuffer randomCode = new StringBuffer();

    private char[] mapTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };

    // 获取随机颜色
    private Color getRandColor(int fc, int bc)
    {
        Random random = new Random();
        if (fc > 255)
        {
            fc = 255;
        }

        if (bc > 255)
        {
            bc = 255;
        }

        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        // 得到随机颜色
        return new Color(r, g, b);
    }

    // 生成客户端的响应
    public String creatImage(OutputStream os)
    {
        {

            randomCode = new StringBuffer();
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            Random random = new Random();
            g.setColor(getRandColor(160, 200));

            // 随机产生254条干扰直线，使图象中的验证码不易被解析程序分析到
            g.setColor(getRandColor(110, 240));
            // 填充背景
            g.fillRect(1, 1, width - 1, height - 1);
            // 为图形验证码绘制边框
            g.setColor(Color.WHITE);
            g.drawRect(0, 0, width - 1, height - 1);
            g.setColor(getRandColor(160, 200));

            // 随机生产12跳图片干扰线条，使验证码图片中的字符不被轻易识别
            g.setColor(Color.BLACK);
            for (int i = 0; i < 12; i++)
            {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(63);
                int yl = random.nextInt(64);
                g.drawLine(x, y, x + xl, y + yl);
            }
            g.setColor(getRandColor(160, 200));

            // 设置字体
            g.setFont(mFont);

            // 随机生产codeNum个数字验证码
            for (int i = 0; i < codeNum; i++)
            {
                // 得到随机产生的验证码
                String strRand = String.valueOf(mapTable[random.nextInt(35)]);

                // 用随机产生的颜色将验证码绘制到图像中
                g.setColor(new Color(20 + random.nextInt(110), 20 + random
                        .nextInt(110), 20 + random.nextInt(110)));
                g.drawString(strRand, (i + 1) * 18, 28);

                // 将产生的四个随机数组合
                randomCode.append(strRand);
            }

            // 将最终生产的验证码图片
            try
            {
                ImageIO.write(image, "jpeg", os);
                os.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
                return "";
            }
            return randomCode.toString();
        }
    }

}
