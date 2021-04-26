package book;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class shop {
	Scanner scanner = new Scanner(System.in);
    List<book> list = new ArrayList<book>();

    public shop(){
    	
    }                                 

    public void addBook() {                  
        System.out.println("---> 新增图书");
        System.out.print("请输入图书名称：");
        String Name = scanner.next();
        int addid = list.size()+1;
        book b = new book(addid,Name);           
        list.add(b);
        System.out.println("新增《"+Name+"》成功！");
    }
    public void selectBook(){                       
        System.out.println("---> 查找图书");
        System.out.println("编号\t名称\t状态\t\t借出日期");
        for (book b : list) {
            b.print();
        }
    }
    public void deleteBook(){                        
        System.out.println("---> 删除图书");
        System.out.print("请输入图书名称:");
        String name = scanner.next();
        for(book b : list) {
            if (b.getname().equals(name)) {
                if (b.getwork().equals("已借出")) {
                    System.out.println("《"+name+"》为借出状态，不能借出！");
                    break;
                }else {
                    int index = list.indexOf(b);
                    list.remove(index);
                    System.out.println("删除《"+name+"》成功");
                    break;
                }
            }
        }
    }
    public void lendBook(){                          
        System.out.println("---> 借出图书");
        String goon;
        do{
            System.out.print("单价:1元/天,是否继续？(Y/N):");
             goon = scanner.next();
        }while (goon=="Y");

        System.out.print("请输入图书名称:");
        String dvdname = scanner.next();
        for(book b : list) {
            if (b.getwork().equals("已借")) {
                System.out.println("《"+dvdname+"》为借出状态，不能借出！");
                break;
            }else {
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");  
                String lenddate = df.format(new Date());
                b.lend(lenddate);
                System.out.println("借出《"+dvdname+"》成功！");
                break;
            }
        }
    }
    public void returnBook(){                        
        System.out.println("---> 归还图书");
        System.out.print("请输入图书名称:");
        String name = scanner.next();
        for(book b : list) {
            if (b.getname().equals(name)) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");  
                String returndate = df.format(new Date());
                b.returnBack(returndate);
                System.out.println("归还《"+name+"》成功！");
                System.out.println("借出日期为："+b.getouttime());
                System.out.println("归还日期为："+b.getretime());
                try {
                    Date parse1 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(b.getouttime());
                    Date parse2 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").parse(b.getretime());
//                    Long Lenddatetimestamp = parse1.getTime();
//                    Long Returndatetimestamp = parse2.getTime();
//                    Long timeprice = Returndatetimestamp - Lenddatetimestamp;
//                    System.out.println(Returndatetimestamp);
//                    System.out.println(Lenddatetimestamp);
//                    System.out.println("应付租金"+timeprice+"元");
                    }
                     catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    public void borrowinglist(){
        list.sort(new Comparator<book>() {
            public int compare(book b1, book b2) {		            
                return b2.getouttimes() - b1.getouttimes();
            }
        });
        System.out.println("---> 排行榜");
        System.out.println("次数\t名称");
        for(book b : list) {
            System.out.println(b.getouttimes() + "\t\t《" + b.getname()+"》");
        }
    }
    public void saveBook() {
        File file =new File("book.txt");
        Writer out;
        try {
            out = new FileWriter(file);
            out.write("编号\t名称\t状态\t\t借出日期");
            for(book b : list) {
                out.write("\r\n");
                out.write(b.getid()+"\t\t" + b.getname()+"\t\t" + b.getwork() + "\t"+ b.getouttimes());
            }
            out.flush();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("保存成功！");
    }
}
