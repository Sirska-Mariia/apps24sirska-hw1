package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;

import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);

        double[] temperatureSeries2 = {1,2,3,4,5,6,7,8,9,10};
        TemperatureSeriesAnalysis array = new TemperatureSeriesAnalysis(temperatureSeries2);
        double t = array.average();
        double k = 5.5;
        assertEquals(k, t, 0.00001);

        double[] temperatureSeries3 = {6,2,3,1};
        TemperatureSeriesAnalysis array2 = new TemperatureSeriesAnalysis(temperatureSeries3);
        double t2 = array2.deviation();
        //double k2 = array2.average();
        assertEquals(1.870828, t2, 0.00001);
       // assertEquals(3, k2, 0.00001);


        double t3 = array.findTempClosestToValue(11);
        double k3 = 10;
        assertEquals(k3, t3, 0.00001);

        double t4 = array.findTempClosestToZero();
        double k4 = 1;
        assertEquals(k4, t4, 0.00001);

        double [] t10 = array.findTempsGreaterThen(7);
        assertEquals(t10.length, 4);

        double [] t11 = array.findTempsLessThen(7);
        assertEquals(t11.length, 6);


        double [] t12 = array.findTempsInRange(4,7);
      //  int k12 = 4;
        assertEquals(t12.length, 4);

        double t5 = array2.max();
        //double k5 = 6;
        assertEquals(6, t5, 0.00001);

        double t6 = array2.min();
      //  double k6 = 1;
        assertEquals(1, t6, 0.00001);


        double [] temperatureSeries4 = {1,6,5,3,7,0,-2,9};

        TemperatureSeriesAnalysis array8 = new TemperatureSeriesAnalysis(temperatureSeries4);
        double [] t8 = array8.sortTemps();
        double [] k8 = {-2, 0,1,3,5,6,7,9};
        assertEquals(k8[1], t8[1], 0.00001);
        assertEquals(k8[2], t8[2], 0.00001);
        assertEquals(k8[3], t8[3], 0.00001);
        assertEquals(k8[0], t8[0], 0.00001);
        assertEquals(k8[4], t8[4], 0.00001);
        assertEquals(k8[5], t8[5], 0.00001);
        assertEquals(k8[6], t8[6], 0.00001);
        assertEquals(k8[7], t8[7], 0.00001);

        int k9 = array8.addTemps(temperatureSeries3);
        assertEquals(k9, 12);

        array.reset();
        int t7 = array.getLn();
        assertEquals(t7, 0);

        TempSummaryStatistics a = array8.summaryStatistics();
        assertEquals(a.getAvgTemp(), array8.average(), 0.0001);
        assertEquals(a.getDevTemp(), array8.deviation(), 0.0001);
        assertEquals(a.getMaxTemp(), array8.max(), 0.0001);
        assertEquals(a.getMinTemp(), array8.min(), 0.0001);


       // TempSummaryStatistics b = array.summaryStatistics();
       assertThrows(IllegalArgumentException.class, () -> {
        array.summaryStatistics();  // Invalid temperature below -273°C
       });



      // TemperatureSeriesAnalysis wrong_ar = new TemperatureSeriesAnalysis({1,2,-278,88});
       assertThrows(InputMismatchException.class, () -> {
        array8.addTemps(new double[]{1,2,-278,88});
    });

        


        
        
       // double[] temperatureSeries3 = {6,2,3,1};

    }

//    @Test
//    public void testAverageWithOneElementArray() {
//        // setup input data and expected result
//        double[] temperatureSeries = {-1.0};
//        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
//        double expResult = -1.0;
//
//        // call tested method
//        double actualResult = seriesAnalysis.average();
//
//        // compare expected result with actual result
//        assertEquals(expResult, actualResult, 0.00001);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void testAverageWithEmptyArray() {
//        double[] temperatureSeries = {};
//        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
//
//        // expect exception here
//        seriesAnalysis.average();
//    }
//
//    @Test
//    public void testAverage() {
//        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
//        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
//        double expResult = 1.0;
//
//        double actualResult = seriesAnalysis.average();
//
//        assertEquals(expResult, actualResult, 0.00001);
//    }
    

}
