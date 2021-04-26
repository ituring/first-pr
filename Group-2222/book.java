package book;
import java.util.Random;
public class book {
	private int id;              //图书编号
    private String name;         //书名
    private String work;       //图书状态
    private int outtimes;           //借出次数
    private String outtime;        //借出时间
    private String retime;      //归还时间

    public int getid() {
        return id;
    }
    public void setid(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public String getwork() {
        return work;
    }
    public void setwork(String work) {
        this.work = work;
    }

    public int getouttimes() {
        return outtimes;
    }
    public void setouttimes(int outtimes) {
        this.outtimes = outtimes;
    }

    public String getouttime() {
        return outtime;
    }
    public void setouttime(String outtime) {
        this.outtime = outtime;
    }

    public String getretime() {
        return retime;
    }
    public void setretime(String retime) {
        this.retime = retime;
    }

    public book(){
    	
    }                                  

    public book(int id, String name) {         
        this.id = id;
        this.name = name;
        setwork("可借");
    }
    public void print(){                             
        if (this.getwork().equals("已借出"))
            System.out.println(id + "\t\t" + name + "\t\t" + work+ "\t\t"  + outtime+ "\t\t");
        else
            System.out.println(id + "\t\t" + name + "\t\t" + work);
    }
    public void lend(String outtime) {             
        setwork("已借出");
        setouttime(outtime);
        this.outtimes++;
    }

    public void returnBack(String retime) {    
        setwork("可借");
        setretime(retime);
    }


}