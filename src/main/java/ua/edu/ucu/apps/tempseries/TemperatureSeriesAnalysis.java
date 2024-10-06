package ua.edu.ucu.apps.tempseries;
import java.util.InputMismatchException;

public final class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int ln;
    
    public TemperatureSeriesAnalysis() {
        this.temperatureSeries = new double[0];  
        this.ln = 0;
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checktemp(temperatureSeries);
        this.temperatureSeries = temperatureSeries.clone();
        this.ln = temperatureSeries.length;
        
    }

    public int getLn() {
        return ln;
    }

    public void  checktemp(double[] temperaturesSeries) {
        for (double temp : temperaturesSeries) {
            final double LOWESTTEMP = -273;
            if (temp < LOWESTTEMP) {
                throw new InputMismatchException("");
            }
        }
    }

    public void  checkexp(double[] temperaturesSeries) {
        if (ln == 0) {
            throw new IllegalArgumentException("");
        }
    }

    public double average() {
        checkexp(temperatureSeries);
        double sum = 0;
        for (int i = 0; i < ln; i++) {
            sum += temperatureSeries[i];
        }
        return sum/ln;
    }

    public double deviation() {
        checkexp(temperatureSeries);
        double mean = average();
        double sum = 0;
        for (int i = 0; i < ln; i++) {
            sum += (temperatureSeries[i]-mean) * (temperatureSeries[i]-mean);
        } 
        return Math.sqrt(sum/temperatureSeries.length);
    }


    public double comp(double opt, boolean minus) {
        checkexp(temperatureSeries);
        double right = opt;
        for (int i = 0; i < ln; i++) {
            if (((!minus) && (temperatureSeries[i] < right)) || ((minus) 
            && (temperatureSeries[i] > right))) {
                right = temperatureSeries[i];
            }
        }
        return right;
    }

    public double min() {
        return comp(Double.POSITIVE_INFINITY, false);
       // checkexp(temperatureSeries);
       // double least = Double.POSITIVE_INFINITY;
       // for (int i = 0; i < ln; i++) {
       //     if (temperatureSeries[i] < least) {
       //         least = temperatureSeries[i];
       //     }
       // }
       // return least;
    }

    public double max() {
        return comp(Double.NEGATIVE_INFINITY, true);
       // checkexp(temperatureSeries);
       // double most = -273;
       // for (int i = 0; i < ln; i++) {
       //     if (temperatureSeries[i] > most) {
       //        most = temperatureSeries[i];
       //     }
       // }    
       // return most;
        
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
       // checkexp(temperatureSeries);
       // double close = temperatureSeries[0];
       // for (int i = 1; i < ln; i++) {
       //     if ((Math.abs(temperatureSeries[i]) < close) 
       //     || ((Math.abs(temperatureSeries[i]) == close) 
       //     && (temperatureSeries[i] > close))) {
       //        close = temperatureSeries[i];
       //     }
       // }    
       // return close;

      //  return 0;
    }

    public double findTempClosestToValue(double tempValue) {
        checkexp(temperatureSeries);
        double close = Math.abs(temperatureSeries[0] - tempValue);
        for (int i = 1; i < ln; i++) {
            if ((Math.abs(temperatureSeries[i] - tempValue) < close) 
            || ((Math.abs(temperatureSeries[i] - tempValue) == close) 
            && (temperatureSeries[i] - tempValue > close))) {
               close = temperatureSeries[i];
            }
        }    
        return close;
    }
    
    public boolean cond(double tempa, double tempb, int i) {
        final double IMPOS_N = -300;
        if ((tempa !=  IMPOS_N) && (tempb != IMPOS_N)) {
            return ((temperatureSeries[i] >= tempa)
            && (temperatureSeries[i] <= tempb));
            }
        else if (tempa != IMPOS_N) {
            return (temperatureSeries[i] >= tempa);  
            }
        else if (tempb != IMPOS_N) {
            return (temperatureSeries[i] < tempb);
            }
        return false;
    }

    public double[] findthen(double tempa, double tempb) {
        int cout = 0;
        for (int i = 0; i < ln; i++) {
            if (cond(tempa, tempb, i)) {
                cout++;   
            } 
        }
        double [] arr = new double[cout];
        int t = 0;
        for (int i = 0; i < ln; i++) {
                if (cond(tempa, tempb, i)) {
                    arr[t] = temperatureSeries[i];   
                    t++; 
                }
            }
        return arr;

    }

    public double[] findTempsLessThen(double tempValue) {
        final double IMP_LESS = -300;
        return findthen(IMP_LESS, tempValue);
      // int cout = 0;
      // for (int i = 0; i < ln; i++) {
      //     if (temperatureSeries[i] < tempValue) {
      //         cout++;   
      //     } 
      // }
      // double [] arr = new double[cout];
      // int t = 0;
      // for (int i = 0; i < ln; i++) {
      //         if (temperatureSeries[i] < tempValue) {
      //             arr[t] = temperatureSeries[i];   
      //             t++; 
      //         }
      //     }
      // return arr;
        //return null;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        final double IMPO_GR = -300;
        return findthen(tempValue, IMPO_GR);
      // int cout = 0;
      // for (int i = 0; i < ln; i++) {
      //     if (temperatureSeries[i] >= tempValue) {
      //         cout++;   
      //     } 
      // }
      // double [] arr = new double[cout];
      // int t = 0;
      // for (int i = 0; i < ln; i++) {
      //         if (temperatureSeries[i] >= tempValue) {
      //             arr[t] = temperatureSeries[i];   
      //             t++; 
      //         }    
      // }
      // return arr;
      //// return null;
    }

    public double[] findTempsInRange(double lowerBound, double upperBound) {
        return findthen(lowerBound, upperBound);
       // int cout = 0;
       // for (int i = 0; i < ln; i++) {
       //     if ((temperatureSeries[i] >= lowerBound)
       //     && (temperatureSeries[i] <= upperBound)) {
       //         cout++;   
       //     } 
       // }
       // double [] arr = new double[cout];
       // int t = 0;
       // for (int i = 0; i < ln; i++) {
       //     if ((temperatureSeries[i] >= lowerBound) 
       //     && (temperatureSeries[i] <= upperBound)) {
       //             arr[t] = temperatureSeries[i];   
       //             t++; 
       //         }    
       // }
       // return arr;
    }

    public void reset() {
        this.temperatureSeries = new double[0];
        this.ln = temperatureSeries.length;
    }

    public double[] sortTemps() {
        double [] nw = new double [ln];
        for (int r = 0; r < ln; r++) {
            nw[r] = temperatureSeries[r];
        }
        
        int cout = 1;
        while (cout != 0) {
            cout = 0;
            for (int i = 0; i < ln-1; i++) {
                if (nw[i] > nw[i+1]) {
                    double k = nw[i];
                    nw[i] = nw[i+1];
                    nw[i+1] = k;
                    cout++;
                }   
            }
        }
        return nw;
    }

    public TempSummaryStatistics summaryStatistics() {
        double avgTemp = this.average();
        double devTemp = this.deviation();
        double minTemp = this.min();
        double maxTemp = this.max();
        TempSummaryStatistics st = new 
        TempSummaryStatistics(avgTemp, devTemp, minTemp, maxTemp);
        return st;
    }

    public int addTemps(double... temps) {
        checktemp(temps);
        double [] nw = new double[ln + temps.length];

        for (int i = 0; i < ln; i++) {
           nw[i] = temperatureSeries[i];
        }

        temperatureSeries = nw;

        int u = temps.length;

        for (int i = 0; i < u; i++) {
            temperatureSeries[ln++] =  temps[i];
        }
        
        return ln;
    }

    public static void main(String[] args) {
    }
}
//double []array 




