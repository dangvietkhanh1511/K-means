/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.BISECT;

import DATA.control.DAO;
import DATA.model.Clust;
import DATA.model.Point;
import DATA.view.Kmeans;
import DATA.view.RunView;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class Bisecting {
    // Hàm thực hiện phân cụm bằng Bisecting Kmeans
    public static ArrayList<Clust> clusterBisecting(int K, ArrayList<Point> all_point, Boolean mode) throws CloneNotSupportedException {
        Queue< Clust > L = new ArrayDeque<>();
        // Khởi tạo cụm đầu tiên gồm tất cả các điểm và add vào trong L
        Clust firstClust = new Clust();
        firstClust.setList_point(all_point);
        L.add(firstClust);

        // Thực hiện lặp cho đến khi số cụm trong L bằng K cụm được khởi tạo 
        while(L.size() < K) {
            double minSSE = 99999999; // Biến minSSE để lưu lại SSE nhỏ nhất của cách phân chia 
            // Tạo 2 cụm để sử dụng lưu tạm
            Clust cluster1 = new Clust();
            Clust cluster2 = new Clust(); 

            // Lấy ra cụm đầu tiên trong L
            Clust cluster = L.peek();
            // Lấy danh sách các điểm trong cụm đó
            ArrayList<Point> listPoint = cluster.getList_point();

            // Dùng kmeans chạy lặp 500 lần phân đôi
            for (int i = 0; i < 500; i++) {
                // Gọi hàm phân cụm K-means với K = 2
                Kmeans clustering = new Kmeans(2, listPoint);
                ArrayList<Clust> answer = new ArrayList<>();
                if(mode == true) {
                    // Phân cụm với khởi tạo Kmeans++
                    answer = clustering.Clustering(true);
                }
                else {
                    // Phân cụm với khởi tạo random
                    answer = clustering.Clustering(false);
                }
                
                // Lấy ra 2 cụm vừa được phân chia
                Clust clustTemp1 = (Clust) answer.get(0).clone();
                Clust clustTemp2 = (Clust) answer.get(1).clone();

                // Tính tổng SSE cho 2 cụm vừa được phân chưa
                double totalSSE = clustTemp1.calcSSE() +  clustTemp2.calcSSE();

                // Nếu totalSSE nhỏ hơn minSSE thì gán lại giá trị minSSE = totalSSE và lưu lại 2 cụm đó
                if(totalSSE < minSSE) {
                    minSSE = totalSSE;
                    cluster1 = (Clust) clustTemp1.clone();
                    cluster2 = (Clust) clustTemp2.clone();
                }
            }

            //Sau khi for thì xóa phần tử đầu tiên của L và thêm 2 cụm mới vào trong L
            L.poll();
            L.add(cluster1);
            L.add(cluster2);
        }
        // Khởi tạo resClusts để lưu danh sách kết quả cụm trong L
        ArrayList<Clust> resClusts = new ArrayList<>();

        // Lấy ra các cụm có trong L để add vào finalClusts
        while (L.size() > 0) {
            resClusts.add(L.poll());
        }
        RunView.printResult(resClusts);
        return resClusts;
    }
    
    // Hàm thực hiện phân cụm và đưa ra kết quả
    public static void clustering(int K, ArrayList<Point> all_point, Boolean mode) throws CloneNotSupportedException {
        // Khởi tạo finalClusts để lưu danh sách cụm cuối cùng 
        ArrayList<Clust> finalClusts = new ArrayList<>();
        double SSE = 999999999;
        double sumSSE = 0;
        
        // Vì khi chạy K mean khởi tạo điểm random -> có đáp án khác nhau
        // Sử dụng tính SSE để lấy ra kết quả tốt nhất để in ra 
        for (int j = 0; j < 5; j++) {
            sumSSE = 0;
            ArrayList<Clust> resClusts = new ArrayList<>();
            resClusts = clusterBisecting(K, all_point, mode);
            // Tính tổng SSE của mỗi cách phân cụm
            for (Clust clust : resClusts) {
                sumSSE += clust.calcSSE();
            }
            
            // Nếu sumSSE < SSE thì gán lại giá trị cho minSSE và lưu kết quả phân cụm đó
            if(sumSSE < SSE) {
                SSE = sumSSE;
                finalClusts.clear();
                for(Clust q : resClusts){
                    Clust clone = (Clust)q.clone();
                    finalClusts.add(clone);
                }
            }
        }
        
        // In ra centroid và ID của các điểm trong từng cụm của finalClusts
        RunView.printResult(finalClusts);
    }
    
    public static void main(String[] args) throws CloneNotSupportedException {
        // Đọc dữ liệu từ file csv
        DAO read = new DAO();
        ArrayList<Point> all_point = read.getData();
        
        // Khởi tạo K và hàng đợi L (Lưu các cụm sau khi được phân chia)
        int K = 3;
        
//        clusterBisecting(K, all_point, false);
        
        clusterBisecting(K, all_point, true);
    }
}
