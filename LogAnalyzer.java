/**
 * Read web server data and analyse hourly access patterns.
 * 
 * Extra Credit Branch - the following methods have been added to this branch:
 * 
 * quietestDay - returns the quietest day
 * busiestDay - returns the busiest day
 * totalAccessesPerMonth - returns the total accesses per month
 * quietestMonth - returns the quietest month
 * busiestMonth - returns the busiest month
 * averageAccessesPerMonth - returns the average accesses per month 
 * across all years.
 * 
 * @author Nolan Canto
 * @version    2025.04.07
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    // Where to calculate the daily access counts.
    private int[] dayCounts;
    // Where to calculate the monthy access counts.
    private int[] monthCounts;

    /**
     * Create an object to analyze hourly web accesses.
     * 
     * @param filename the name of the file being analyzed
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly, daily, and monthly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[28];
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }
    
    /**
     * Returns the number of accesses recorded in the log file.
     * 
     * @return number of accesses
     */
    public int numberOfAccesses() {
        int total = 0;
        for (int hourCount : hourCounts) {
            total += hourCount;
        }
        return total;
    }
    
    /**
     * Returns total accesses for an entire 28-day month.
     * 
     * @return total accesses in month.
     */
    public int totalAccessesPerMonth() {
        int total = 0;
        for (int count : dayCounts) {
            total += count;
        }
        return total;
    }
    
    /**
     * Returns average accesses for an entire 28-day month across all years.
     * 
     * @return average accesses in month.
     */
    public double averageAccessesPerMonth() {
        int total = 0;
        for (int count : monthCounts) {
            total += count;
        }
        return total / 12.0;
    }
    
    /**
     * Returns the busiest recorded hour.
     * 
     * @return busiest hour.
     */
    public int busiestHour() {
        int count = 0;
        int max = hourCounts[0];
        for (int index = 0; index < hourCounts.length; index++) {
            if (max < hourCounts[index]) {
                max = hourCounts[index];
                count = index;
            }
        }
        return count;
    }
    
    /**
     * Returns the quietest recorded hour.
     * 
     * @return quietest hour.
     */
    public int quietestHour() {
        int count = 0;
        int min = hourCounts[0];
        for (int index = 0; index < hourCounts.length; index++) {
            if (hourCounts[index] < min) {
                min = hourCounts[index];
                count = index;
            }
        }
        return count;
    }
    
    /**
     * Returns the busiest recorded day.
     * 
     * @return busiest day.
     */
    public int busiestDay() {
        int count = 0;
        int max = dayCounts[0];
        for (int index = 1; index < dayCounts.length; index++) {
            if (dayCounts[index] > max) {
                max = dayCounts[index];
                count = index;
            }
        }
        return count;
    }
     
    /**
     * Returns the quietest recorded day.
     * 
     * @return quietest day.
     */
    public int quietestDay() {
        int count = 0;
        int min = dayCounts[0];
        for (int index = 1; index < dayCounts.length; index++) {
            if (dayCounts[index] < min) {
                min = dayCounts[index];
                count = index;
            }
        }
        return count;
    }
    
    /**
     * Returns the busiest recorded month.
     * 
     * @return busiest month.
     */
    public int busiestMonth() {
        int count = 0;
        int max = monthCounts[0];
        for (int index = 1; index < monthCounts.length; index++) {
            if (monthCounts[index] > max) {
                max = monthCounts[index];
                count = index;
            }
        }
        return count;
    }
    
    /**
     * Returns the quietest recorded day.
     * 
     * @return quietest day.
     */
    public int quietestMonth() {
        int count = 0;
        int min = monthCounts[0];
        for (int index = 1; index < monthCounts.length; index++) {
            if (monthCounts[index] < min) {
                min = monthCounts[index];
                count = index;
            }
        }
        return count;
    }
    
    /**
     * Returns the busiest two-hour period recorded.
     * 
     * @return the first hour of the busiest two-hour period.
     */
    public int busiestTwoHour() {
        int count = 0;
        int max = 0;
        for (int index = 0; index < hourCounts.length; index++) {
            int nextIndex = (index + 1) % 24;
            int total = hourCounts[index] + hourCounts[nextIndex];
            if (total > max) {
                max = total;
                count = index;
            }
        }
        return count;
        
    }
    
    /**
     * Analyze the access data from the log file.
     * 
     * This method was originally analyzeHourlyData. It has been edited to
     * accomodate daily and monthy data alongside hourly data.
     */
    public void analyzeData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            int day = entry.getDay() - 1; // prevents the quietest day from always being zero.
            int month = entry.getMonth() - 1;
            if (day >= 0 && day < dayCounts.length) {
                dayCounts[day]++;
            }
            if (month >= 0 && month < monthCounts.length) {
                monthCounts[month]++;
            }
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
