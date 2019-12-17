import java.util.*;

public class Concurrent {
    public static void main(String[] args) {
        List list =Collections.synchronizedList(new ArrayList());
        for(int i=0;i<10;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
