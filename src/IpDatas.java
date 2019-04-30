import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: ip数据
 * @author: xutao
 * @create: 2019-04-29 16:06
 **/
public class IpDatas {

    private List<IpModel> ips=new ArrayList<>();

    public void init(){
        try {
            FileInputStream file=new FileInputStream(this.getClass().getClassLoader().getResource("ip.txt").getFile());
            InputStreamReader reader=new InputStreamReader(file,"gbk");
            BufferedReader bf=new BufferedReader(reader);
            String line;
            String pattern= "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s+(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})\\s+(.*)";
            // 创建 Pattern 对象
            Pattern r = Pattern.compile(pattern);
            long index=1;
           while ((line=bf.readLine())!=null){
               Matcher m = r.matcher(line);
               if(!m.matches()) continue;
               String ip=m.group(1);
               String addr=m.group(3).replace("CZ88.NET","").trim();
               addr=addr.split(" ")[0];
               Long ipl=IPUtil.ipToLong(ip);
               IpModel md=new IpModel(ipl,addr,index++);
                ips.add(md);
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findAddr(String ip){
        int l=0,r=this.ips.size()-1;
        long a=IPUtil.ipToLong(ip);
        //二分法查找list中小于等于a的最大值的索引
        if(a<ips.get(l).getIp()||a>ips.get(r).getIp())
            return "not found";
        int m=(l+r)/2;
        while (l<r){
           IpModel md=this.ips.get(m);
           if(md.getIp()<=a){
               l=m;
               if(md.getIp()==a||this.ips.get(m+1).getIp()>a){
                   return md.getId()+":"+md.getName();
               }
           }else{
               r=m;
               IpModel md1=  this.ips.get(m-1);
               if(md1.getIp()<a){
                   return md1.getId()+":"+md1.getName();
               }
           }
            m = (l + r) / 2;
        }
         return "not found";
    }


    public static void main(String[] args) {
        IpDatas datas=    new IpDatas();
        datas.init();
        long begin=System.currentTimeMillis();
        String ret=datas.findAddr("39.105.98.88");
        long end =System.currentTimeMillis();
        System.out.println("时长："+(end-begin)+"ms");
        System.out.println(ret);
    }
}
