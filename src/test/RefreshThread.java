package test;

/**
 * Created by ZC on 2019/7/1.
 */
public class RefreshThread implements Runnable{

    @Override
    public void run() {

        while (true){
            try {
                //刷新列表
                JTableOperation jTableOperation = new JTableOperation();
                Thread.sleep(5000);
                jTableOperation.reloadJTable(GlobalObject.getjTable());
                //三秒刷新
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}