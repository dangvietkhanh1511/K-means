/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.view;

import DATA.BISECT.Bisecting;
import DATA.control.DAO;
import java.util.ArrayList;
import DATA.model.Clust;
import DATA.model.Point;


/**
 *
 * @author Admin
 */
public class RunView {
    // Hàm thực hiện in ra kết quả
    public static void printResult(ArrayList<Clust> res) {
        int j = 0;
        for(Clust i : res){
            // In ra tên từng cụm
            j++;
            System.out.println("Clust ("+j+") :");
            // In ra tâm cụm tương ứng
            System.out.print("Centroid: ");
            Point.printCentroid(i.getCentroid());
            
            // In ra Id của các điểm trong cụm tương ứng
            System.out.println("Id các điểm trong Clust ("+j+") :");
            ArrayList<Point> list = i.getList_point();
            list.forEach(q -> {
                System.out.print(q.getId() + " ");
            });
            System.out.println();
            System.out.println();
        }
    }
    
    public static void main(String arg[]) throws CloneNotSupportedException{
        DAO read = new DAO();
        ArrayList<Point> all_point = read.getData(); // Đọc dữ liệu từ file csv
        // Khởi tạo K cụm cần phân chia
        int K = 3;
        
        // Khởi tạo danh sách các cụm kết quả
        ArrayList<Clust> answer = new ArrayList<Clust>();
        // Khởi tạo biến minSSE để thực hiện tính SSE và lấy ra cách phân cụm tốt nhất
        double minSSE = 999999999;
        
        // Chạy thuật toán với khởi tạo random
//        Kmeans clustering = new Kmeans(K, all_point);
//        ArrayList<Clust> tempAnswer = clustering.Clustering(false);
//        printResult(tempAnswer);
        
        // Chạy thuật toán với khởi tạo Kmeans++
        Kmeans clustering2 = new Kmeans(K, all_point);
        ArrayList<Clust> tempAnswer2 = clustering2.Clustering(true);
        printResult(tempAnswer2);
        
        




        // Thực hiện lặp chạy Kmeans 5 lần để tìm ra cách phân cụm tốt nhất
//        for (int i = 0; i < 5; i++) {
//            double totalSSE = 0;
//            // Thực hiện Kmeans để phân cụm
//            
//            
//            // Tính tổng SSE của các cụm
//            for (Clust clust : tempAnswer) {
//                totalSSE += clust.calcSSE();
//            }
//            
//            // Nếu totalSSE < minSSE thì gán lại giá trị cho minSSE và lưu kết quả phân cụm đó
//            if(totalSSE < minSSE) {
//                minSSE = totalSSE;
//                answer.clear();
//                for(Clust q : tempAnswer){
//                    Clust clone = (Clust)q.clone();
//                    answer.add(clone);
//                }
//            }
//        }
//        // In ra kết quả phân cụm
//        printResult(answer);
    }
}
