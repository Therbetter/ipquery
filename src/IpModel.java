/**
 * @description:
 * @author: xutao
 * @create: 2019-04-29 16:10
 **/
public class IpModel {

    private long id;

    private long ip;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IpModel(long ip, String name,long id) {
        this.ip = ip;
        this.name = name;
        this.id=id;
    }

    public long getIp() {
        return ip;
    }

    public void setIp(long ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
