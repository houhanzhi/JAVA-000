package test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @date 2020-10-19 13:34
 */
public class HelloClassLoader extends ClassLoader {


    public static void main(String[] args) {
        try {
            Object hello = new HelloClassLoader().findClass("Hello").newInstance();
            Method method = hello.getClass().getMethod("hello");
            method.setAccessible(true);
            method.invoke(hello);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        try {
            byte[] result = getClassFromPath();
            if (result == null) {
                throw new FileNotFoundException(name);
            } else {
                // defineClass方法将字节码转化为类
                for (int i = 0; i < result.length; i++) {
                    byte b = (byte) ((result[i] + 1) * (-1));
                    result[i] = b;
                }
                createClassFile(result);
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromPath() {
        // 从自定义路径中加载指定类，返回类的字节码文件
        InputStream in = null;
        ByteArrayOutputStream out = null;
        String path = "/Users/houhanzhi/java进阶/jvmDemo/src/main/java/test/Hello.xlass";
        try {
            in = new FileInputStream(path);
            out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int len = 0;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void createClassFile(byte[] result) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("/Users/houhanzhi/java进阶/jvmDemo/src/main/java/test/Hello.class"));
        fos.write(result);
        fos.flush();
        fos.close();
    }


}
