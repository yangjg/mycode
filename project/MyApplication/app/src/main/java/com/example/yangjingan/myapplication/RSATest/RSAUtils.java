
package com.example.yangjingan.myapplication.RSATest;
/**
 * Created by yangjingan on 17-12-28.
 */
public class RSAUtils  {



    public static  void doTest(int msg){

        //msg一定要小于 cert.n 才能保证加密和解密正确

        RSAUtils.Cert cert = RSAUtils.getCert();
        //加密后的数据
        int encodeMsg = RSAUtils.rsa(cert.n, cert.com_key, msg);
        //解密后的数据
        int decodeMsg =  RSAUtils.rsa(cert.n, cert.private_key, encodeMsg);

        if(msg != decodeMsg){
            //TODO  算法错误
        }
    }

    /**
     * http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html
     * http://www.ruanyifeng.com/blog/2013/07/rsa_algorithm_part_two.html
     * <p>
     * 整个RSA的基本知识可以从下面开始
     * 1. 欧拉定理： http://songshuhui.net/archives/84941
     * 2. 欧拉函数的推导： https://wenku.baidu.com/view/1880a2c72dc58bd63186bceb19e8b8f67c1cefbd.html
     * 3. 扩展欧几里得算法详解：http://blog.csdn.net/zhjchengfeng5/article/details/7786595
     * http://www.ruanyifeng.com/blog/2013/06/rsa_algorithm_part_one.html
     * 4. RSA正确性的推导： http://www.ruanyifeng.com/blog/2013/07/rsa_algorithm_part_two.html
     * 加密、解密算法
     * 只能加密和解密比 n 小的数，这个里的n就是 产生的公钥（n,e）和私钥(n,d)里面的n;
     * @param key     公钥或密钥
     * @param message 数据a
     * @return
     */
    public static int rsa(int n, int key, int message) {
        if (n < 1 || key < 1) {
            return 0;
        }
        //加密或者解密之后的数据
        int rsaMessage = 1;

        //加密核心算法
        // rsaMessage = Math.round(Math.pow(message, key)) % baseNum;把指数换成乘法.
        for (int i = 0; i < key; i++) {
            rsaMessage = (rsaMessage * message) % n;
        }
        return rsaMessage;
    }


    /**
     * http://blog.csdn.net/zhjchengfeng5/article/details/7786595
     * 扩展欧几里得函数
     *
     * @param a
     * @param b
     * @param x
     * @param y
     * @return
     */

    public static int egcd(int a, int b, IntObject x, IntObject y) {
        if (0 == b) {
            x.value = 1;
            y.value = 0;
            return a;
        }
        int ans = egcd(b, a % b, x, y);
        int temp = x.value;
        x.value = y.value;
        y.value = temp - a / b * y.value;
        return ans;
    }


    /**
     *
     /**
     * http://blog.csdn.net/zhjchengfeng5/article/details/7786595

     * @param e  传入公钥
     * @param fn  传入φ(n)，就是欧拉定理中的φ(n)；
     * @return
     */

    public static int calPrivateKey(int e, int fn) {
        IntObject x = new IntObject();
        IntObject y = new IntObject();
        int gcd = egcd(e, fn, x, y);
        if (1 % gcd != 0) return -1;
        fn = Math.abs(fn);
        int ans = x.value % fn;
        if (ans <= 0) ans += fn;
        return ans;
    }

    /***
     * 生成公钥和私钥
     * @return
     */
    public static Cert getCert(){
        int n = 73*23;
        int p = 73 -1 ;
        int q =23 -1 ;
        int fn = q*q;
        int e = 113 ;//随便去一个值只要与 fn互质就ok,这里的e就是公钥
        int d = calPrivateKey(e,fn);
        return new Cert(n,e,d);
    }


    public static class Cert{
        public int n;
        public int com_key;
        public int private_key;

        public Cert(int base,int cky,int pky){
            n = base ;
            com_key = cky ;
            private_key = pky ;
        }
    }


    public static class IntObject {
        public int value;
    }

}
