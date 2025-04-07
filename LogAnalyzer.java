/**
 * Read web server data and analyse hourly access patterns.
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

    /**
     * Create an object to analyze hourly web accesses.
     * 
     * @param filename the name of the file being analyzed
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
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
     * Returns the busiest hour recorded.
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
     * Returns the quietest hour recorded.
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
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
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
