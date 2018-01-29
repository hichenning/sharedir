package queen.test;

import queen.service.ReadConfigServcie;

import java.io.File;

public class Test {

    public static void main(String[] args) {
/*        Resouse resouse = new Resouse();
        List result = resouse.getChildList("E:\\A");
        System.out.println(result);
        String jsonStr = Object2JSON.Ojb2JsonStr(result);
        System.out.println(jsonStr);*/
//        ReadConfigServcie userinfoServcie = new ReadConfigServcie();
//        userinfoServcie.getUserinfo();
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.getProperty("user.home"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("").toString());

        System.out.println(System.getProperty("java.io.tmpdir"));
    }
}
