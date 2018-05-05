package com.xiaow.mh.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhangnengwen on 18/2/24.
 */
public class DemoIO {

    public static void main(String[] args){
        try {
            StringBuffer stringBuffer = new StringBuffer();
            char[] buf = new char[1024];
            FileReader fileReader = new FileReader("/Users/zhangnengwen/test/luceneFile/虫子.txt");
            int i = 1;
            while (i > 0) {
                i = fileReader.read(buf);
                stringBuffer.append(buf);
            }
            stringBuffer.toString();

            BufferedInputStream bi = new BufferedInputStream(new FileInputStream("/Users/zhangnengwen/test/luceneFile/虫子.txt"));
            bi.read();
        }catch (Exception e) {

        }
        Integer i = null;
        if (i == 1) {
            System.out.print("ok");
        }
    }

    public void selector() throws IOException {
        //构造一个HeapByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            Set selectedKeys = selector.selectedKeys();
            Iterator it = selectedKeys.iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();
                if((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                    ServerSocketChannel ssChannel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                    it.remove();
                } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ){
                    SocketChannel sc = (SocketChannel) key.channel();
                    while (true) {
                        buffer.clear();
                        int n = sc.read(buffer);
                        if (n <= 0) {
                            break;
                        }
                        buffer.flip();
                    }
                    it.remove();
                }

            }
        }
    }
}
