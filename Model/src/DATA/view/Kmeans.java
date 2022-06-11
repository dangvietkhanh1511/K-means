/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.view;

import DATA.control.DAO;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import DATA.model.Clust;
import DATA.model.Point;

/**
 *
 * @author Admin
 */
public class Kmeans implements Cloneable{
    private ArrayList<Point> all_point; // Dữ liệu tất cả các điểm được đọc vào
    private int k_clust; // K cụm cần phân chia

    // Contructor khởi tạo 
    public Kmeans(int k_clust , ArrayList<Point> all_point) {
        this.all_point = all_point;
        this.k_clust=k_clust;
    }
    

    // Hàm thực hiện việc khởi tạo K điểm tâm cụm Random ban đầu
    public ArrayList<Point> RandomPoint(){
        ArrayList<Point> list = new ArrayList<>();
        Random rd = new Random();
        Set a = new HashSet();
        while(list.size()<k_clust){
            int i = rd.nextInt(all_point.size()-1) ;
            int size = a.size();
            a.add(i);
            if(size<a.size()){
                list.add(all_point.get(i));
            }
        }
        return list ;
    }
    
    // Hàm thực hiện so sánh tâm cụm của các Clust sau khi thực hiện Kmeans
    public boolean CompareClust(ArrayList<Clust> a , ArrayList<Clust> b){
        for(int i =0;i<a.size();i++){
            if(!Point.ComparePoint(a.get(i).getCentroid(), b.get(i).getCentroid()))
                return false ;
        }
        return true;
    }
    
    
    // Hàm thực hiện khởi tạo centroid với Kmeans++
    public ArrayList<Point> InitKmeanPlus(){
        ArrayList<Point> list = new ArrayList<>();
        Random rd  = new Random() ;
        int i = rd.nextInt(all_point.size()-1);   
        list.add((Point)this.all_point.get(i)); // Bước 1 : chọn ngẫu nhiên 1 điểm làm centroid .
        while(list.size()<k_clust){
            ArrayList<Double> min_dist_to_nearest_centroid = new ArrayList<>() ;
            for(Point j: all_point){
                // B2 :tinh khoang cach tung diem toi centroid gan nhat
                double min_dist = 10000000 ;
                for(Point q : list){
                    double temp = j.calcDist(q);
                    if(min_dist> temp){
                        min_dist = temp ;
                    }
                }
                min_dist_to_nearest_centroid.add(min_dist); // Luu lai k/c vua tinh
            }
            int pick_index = 0 ;
            double min_dist_in_list = 0 ;
            // Buoc 3 : chon diem co k/c lon nhat so voi cac centroid san co
            for(int l =0;l<min_dist_to_nearest_centroid.size();l++){
                if(min_dist_to_nearest_centroid.get(l)>min_dist_in_list){
                   pick_index = l;
                   min_dist_in_list = min_dist_to_nearest_centroid.get(l);         
                }
            }
            list.add(all_point.get(pick_index));
        }
        return list ;
    }
    
    // Hàm thực hiện thuật toán Kmeans để phân cụm
    public ArrayList<Clust> Clustering(Boolean mode) {
        boolean stop = true; // Khởi tạo biến dừng khi tâm cụm không thay đổi
        ArrayList<Point> initCentroid = new ArrayList<>();
        if(mode == true) {
            // Khởi tạo  K centroid bằng Kmeans++
            initCentroid = InitKmeanPlus() ;
        }
        else {
            // Khởi tạo ngẫu nhiên K centroid
           initCentroid = RandomPoint() ;
        }
        
        // Khởi tạo danh sách các cụm để lưu kết quả mỗi lần chạy
        ArrayList<Clust> temp = new ArrayList<Clust>(); 
        
        // Gán các tâm cụm ban đầu cho danh sách các cụm
        for(Point i : initCentroid){ 
            Clust x = new Clust();
            x.setCentroid(i);
            x.setList_point(new ArrayList<Point>());
            temp.add(x);
        }
        
        // Khởi tạo danh sách cụm cũ để thực hiện so sánh tâm
        ArrayList<Clust> old_clust = new ArrayList<>() ;
        old_clust.clear();
        try {
            for(Clust q : temp){
                Clust clone = (Clust)q.clone();
                old_clust.add(clone);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Thực hiện lặp cho đến khi tâm cụm không đổi
        do {    
            // Thực hiện gan diem vao các clust đã khởi tạo
            for(Point i : all_point){
                // Sử dụng hàm tính khoảng cách để 
                //xác định xem điểm thuộc cụm nào tương ứng
                int min_index =0;
                double min_dist =1000000;
                // Thực hiện lặp và so sánh khoảng các tới từng tâm cụm 
                //để lấy ra điểm tương ứng và thêm vào cụm
                for(Clust j : temp){
                    // Nếu khoảng cách tới tâm cụm nào nhỏ nhất 
                    // thì ta lấy ra index của cụm đó
                    if(min_dist>j.getCentroid().calcDist(i)){
                        min_dist = j.getCentroid().calcDist(i);
                        min_index=temp.indexOf(j);
                    }
                }
                // Sau khi lấy được index của cụm thì 
                // thêm điểm đó vào trong danh sách cụm tương ứng
                Clust jClust= temp.get(min_index);
                ArrayList<Point> temp1 = jClust.getList_point();
                temp1.add(i);
                jClust.setList_point(temp1);
            }
            
            // Thực hiện việc tính lại tâm cụm cho từng cụm
            for(Clust i :temp){
                i.calcCentroid();
            }
            
            // So sánh xem tâm các cụm có thay đổi không
            stop = CompareClust(temp, old_clust);
            
            // Nếu có thay đổi thì ta gán lại giá trị cho mới cho old_clust và tiếp tục lặp
            old_clust.clear();
            try {
                for(Clust q : temp){
                    Clust clone = (Clust)q.clone();
                    old_clust.add(clone);
                }
                  
            } catch (Exception e) {
                e.printStackTrace();
            }
            for(Clust i : temp){
                i.setList_point(new ArrayList<Point>());
            }
        } while (!stop);
        // Trả về kết quả K cụm được phân chia
        return old_clust;
    }
}
