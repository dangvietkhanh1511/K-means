/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Clust implements Cloneable{
    private Point centroid ;
    private ArrayList<Point> list_point;

    public Clust() {
        this.list_point = new ArrayList<>() ;
    }
    
    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public ArrayList<Point> getList_point() {
        return list_point;
    }

    
    public void setList_point(ArrayList<Point> list_point) {
        this.list_point = list_point;
    }
    
    // Hàm thực hiện việc tính centroid cho cụm
    public void calcCentroid(){
        ArrayList<Point> temp = this.list_point;
        double alcohol = 0, malic_acid = 0, ash = 0, ash_alcanity = 0, magnesium = 0, total_phenol = 0, flavonoids = 0,
                nonflavonoids_Phenols = 0, proanthocyanins = 0, color_intensity = 0, hue = 0, od280 = 0, proline = 0;
        for (Point i : temp) {
            alcohol += i.getAlcohol();
            malic_acid += i.getMalic_acid();
            ash += i.getAsh();
            ash_alcanity += i.getAsh_alcanity();
            magnesium += i.getMagnesium();
            total_phenol += i.getTotal_phenol();
            flavonoids += i.getFlavonoids();
            nonflavonoids_Phenols += i.getNonflavonoids_Phenols();
            proanthocyanins += i.getProanthocyanins();
            color_intensity += i.getColor_intensity();
            hue += i.getHue();
            od280 += i.getOd280();
            proline += i.getProline();

        }
        Point centroid1 = new Point(0, alcohol / temp.size(), malic_acid / temp.size(), ash / temp.size(),
                ash_alcanity / temp.size(), magnesium / temp.size(), total_phenol / temp.size(),
                flavonoids / temp.size(), nonflavonoids_Phenols / temp.size(), proanthocyanins / temp.size(),
                color_intensity / temp.size(), hue / temp.size(), od280 / temp.size(), proline / temp.size());

        this.centroid = centroid1;
    }
    
    // Hàm thực hiện việc tính SSE cho 1 cụm
    public double calcSSE(){
        double ans = 0;
        ArrayList<Point> temp = this.list_point;
        for(Point i : temp){
            ans += Math.pow(centroid.calcDist(i),2);
        }
        return ans ;
    }
    
    public boolean check(Clust x){
        if(this.centroid!=x.getCentroid()) return false ;
        else
        return true;
    }
    
    // Hàm thực hiện clone Clust
    @Override
    public Object clone() throws CloneNotSupportedException{
        Clust clone = (Clust) super.clone();
        clone.centroid = (Point) clone.centroid.clone();
        clone.list_point = (ArrayList<Point>) clone.list_point.clone();
        return clone;
    }
    
}


