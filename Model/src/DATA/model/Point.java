/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.model;

/**
 *
 * @author Admin
 */
public class Point implements Cloneable{
    private int id;
    private double alcohol,malic_acid ,ash ,ash_alcanity,
            magnesium,total_phenol,flavonoids,nonflavonoids_Phenols,
            proanthocyanins,color_intensity,hue,od280,proline;

    public Point() {
    }

    public Point(int id, double alcohol, double malic_acid, double ash, 
            double ash_alcanity, double magnesium, double total_phenol, 
            double flavonoids, double nonflavonoids_Phenols, double proanthocyanins, 
            double color_intensity, double hue, double od280, double proline) {
        this.id = id;
        this.alcohol = alcohol;
        this.malic_acid = malic_acid;
        this.ash = ash;
        this.ash_alcanity = ash_alcanity;
        this.magnesium = magnesium;
        this.total_phenol = total_phenol;
        this.flavonoids = flavonoids;
        this.nonflavonoids_Phenols = nonflavonoids_Phenols;
        this.proanthocyanins = proanthocyanins;
        this.color_intensity = color_intensity;
        this.hue = hue;
        this.od280 = od280;
        this.proline = proline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getMalic_acid() {
        return malic_acid;
    }

    public void setMalic_acid(double malic_acid) {
        this.malic_acid = malic_acid;
    }

    public double getAsh() {
        return ash;
    }

    public void setAsh(double ash) {
        this.ash = ash;
    }

    public double getAsh_alcanity() {
        return ash_alcanity;
    }

    public void setAsh_alcanity(double ash_alcanity) {
        this.ash_alcanity = ash_alcanity;
    }

    public double getMagnesium() {
        return magnesium;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getTotal_phenol() {
        return total_phenol;
    }

    public void setTotal_phenol(double total_phenol) {
        this.total_phenol = total_phenol;
    }

    public double getFlavonoids() {
        return flavonoids;
    }

    public void setFlavonoids(double flavonoids) {
        this.flavonoids = flavonoids;
    }

    public double getNonflavonoids_Phenols() {
        return nonflavonoids_Phenols;
    }

    public void setNonflavonoids_Phenols(double nonflavonoids_Phenols) {
        this.nonflavonoids_Phenols = nonflavonoids_Phenols;
    }

    public double getProanthocyanins() {
        return proanthocyanins;
    }

    public void setProanthocyanins(double proanthocyanins) {
        this.proanthocyanins = proanthocyanins;
    }

    public double getColor_intensity() {
        return color_intensity;
    }

    public void setColor_intensity(double color_intensity) {
        this.color_intensity = color_intensity;
    }

    public double getHue() {
        return hue;
    }

    public void setHue(double hue) {
        this.hue = hue;
    }

    public double getOd280() {
        return od280;
    }

    public void setOd280(double od280) {
        this.od280 = od280;
    }

    public double getProline() {
        return proline;
    }

    public void setProline(double proline) {
        this.proline = proline;
    }
    
    // Hàm thực hiện việc tính khoảng cách sử dụng công thức Euclide
    public double calcDist(Point des){
        double ans ;
        ans = Math.sqrt(Math.pow(this.alcohol - des.getAlcohol(),2)+
                Math.pow(this.malic_acid - des.getMalic_acid(),2)+
                Math.pow(this.ash - des.getAsh(),2)+
                Math.pow(this.ash_alcanity - des.getAsh_alcanity(),2)+
                Math.pow(this.magnesium- des.getMagnesium(),2)+
                Math.pow(this.total_phenol- des.getTotal_phenol(),2)+
                Math.pow(this.flavonoids- des.getFlavonoids(),2)+
                Math.pow(this.proanthocyanins- des.getProanthocyanins(),2)+
                Math.pow(this.color_intensity- des.getColor_intensity(),2)+
                Math.pow(this.hue- des.getHue(),2)+
                Math.pow(this.od280- des.getOd280(),2)+
                Math.pow(this.proline- des.getProline(),2)+
                Math.pow(this.nonflavonoids_Phenols- des.getNonflavonoids_Phenols(),2));
        return ans ;
    }
    
    public static double calcDist2(Point des2, Point des){
        double ans ;
        ans = Math.sqrt(Math.pow(des2.getAlcohol() - des.getAlcohol(),2)+
                Math.pow(des2.getMalic_acid() - des.getMalic_acid(),2)+
                Math.pow(des2.getAsh() - des.getAsh(),2)+
                Math.pow(des2.getAsh_alcanity() - des.getAsh_alcanity(),2)+
                Math.pow(des2.getMagnesium()- des.getMagnesium(),2)+
                Math.pow(des2.getTotal_phenol()- des.getTotal_phenol(),2)+
                Math.pow(des2.getFlavonoids()- des.getFlavonoids(),2)+
                Math.pow(des2.getProanthocyanins()- des.getProanthocyanins(),2)+
                Math.pow(des2.getColor_intensity()- des.getColor_intensity(),2)+
                Math.pow(des2.getHue()- des.getHue(),2)+
                Math.pow(des2.getOd280()- des.getOd280(),2)+
                Math.pow(des2.getProline()- des.getProline(),2)+
                Math.pow(des2.getNonflavonoids_Phenols()- des.getNonflavonoids_Phenols(),2));
        return ans ;
    }
    
    // Hàm thực hiện việc in ra điểm
    public static void printCentroid(Point p) {
        System.out.print("[ ");
        System.out.print( (double) Math.round(p.getAlcohol() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getMalic_acid() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getAsh() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getAsh_alcanity() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getMagnesium() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getTotal_phenol() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getFlavonoids() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getNonflavonoids_Phenols() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getProanthocyanins() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getColor_intensity() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getHue() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getOd280() * 100) / 100 + "  ");
        System.out.print( (double) Math.round(p.getProline() * 100) / 100 + "  ");
        System.out.print("]");
        System.out.println();
    }
    
    // Hàm thực hiện so sánh 2 điểm
    public static boolean ComparePoint(Point a , Point b){
        if(a.getAlcohol()!=b.getAlcohol()) return false ;
        if(a.getMalic_acid()!=b.getMalic_acid()) return false ;
        if(a.getAsh()!=b.getAsh()) return false ;
        if(a.getAsh_alcanity()!=b.getAsh_alcanity()) return false ;
        if(a.getMagnesium()!=b.getMagnesium()) return false ;
        if(a.getTotal_phenol()!=b.getTotal_phenol()) return false ;
        if(a.getFlavonoids()!=b.getFlavonoids()) return false ;
        if(a.getProanthocyanins()!=b.getProanthocyanins()) return false ;
        if(a.getColor_intensity()!=b.getColor_intensity()) return false ;
        if(a.getHue()!=b.getHue()) return false ;
        if(a.getOd280()!=b.getOd280()) return false ;
        if(a.getProline()!=b.getProline()) return false ;
        return true;
    }
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
