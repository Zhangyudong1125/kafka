package com.yudong.kafka.serialize;

import kafka.serializer.Decoder;
import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by zyd on 14-10-20.
 * kafka消息类型转换
 */
@Slf4j
public class MessageSerializer<V> implements Encoder<V>,Decoder<V> {

    public MessageSerializer(){}

    public MessageSerializer(VerifiableProperties properties){
        log.debug("serializer properties ：{}",properties);
    }

    @Override
    public V fromBytes(byte[] bytes) {
        V obj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bis = new ByteArrayInputStream (bytes);
            ois = new ObjectInputStream (bis);
            obj = (V)ois.readObject();

        } catch (IOException ex) {
            log.error("message unSerializer IOException：{}",ex);
        } catch (ClassNotFoundException ex) {
            log.error("message serializer ClassNotFoundException：{}",ex);
        }finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                log.error("message unSerializer IOException：{}",e);
            }
        }
        return obj;
    }

    @Override
    public byte[] toBytes(V o) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(o);
            oos.flush();
            bytes = bos.toByteArray ();
        } catch (IOException ex) {
            log.error("message serializer IOException：{}",ex);
        }finally {
            try {
                if (oos != null) {
                    oos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                log.error("message serializer IOException：{}",e);
            }
        }
        return bytes;
    }

}
