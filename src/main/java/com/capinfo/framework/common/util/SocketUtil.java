package com.capinfo.framework.common.util;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by Green Live on 2016/9/8.
 */
public class SocketUtil {
    public static void sendNotify(int dataId) throws Exception {
        Socket socket = null;
        try {
            socket = new Socket("101.201.140.21", 4001);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("@WS@" + dataId);
            socket.close();
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    socket = null;
                }
            }
        }
    }
}
