/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import DATA.model.Point;

/**
 *
 * @author Admin
 */
public class DAO {
public ArrayList<Point> getData() {
        String file = "src\\DATA\\data\\wine-clustering.csv";
        BufferedReader reader = null;
        String line = "";
        ArrayList<String[]> arr = new ArrayList<String[]>();
        ArrayList< Point > arrPoint = new ArrayList<Point>();
        
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                arr.add(row);
            }
            for (int i = 1; i < arr.size(); i++) {
                Point temp = new Point();
                for (String j : arr.get(i)) {
                    temp = new Point(i, Double.parseDouble(arr.get(i)[0]),
                            Double.parseDouble(arr.get(i)[1]), 
                            Double.parseDouble(arr.get(i)[2]), 
                            Double.parseDouble(arr.get(i)[3]), 
                            Double.parseDouble(arr.get(i)[4]), 
                            Double.parseDouble(arr.get(i)[5]), 
                            Double.parseDouble(arr.get(i)[6]), 
                            Double.parseDouble(arr.get(i)[7]), 
                            Double.parseDouble(arr.get(i)[8]), 
                            Double.parseDouble(arr.get(i)[9]), 
                            Double.parseDouble(arr.get(i)[10]), 
                            Double.parseDouble(arr.get(i)[11]), 
                            Double.parseDouble(arr.get(i)[12]));
                }
                arrPoint.add(temp);
            }
            // arrPoint là arrayList các Point (tất cả có 178 point)
            return arrPoint;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
