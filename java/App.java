import java.io.*;
import java.util.*;

public class App
{
  public static final String SEPARATOR = "\t";
  public static void main(String[] args)
  {
    if (args.length < 1) {
      System.out.println("Usages: java App <filename>");
      System.exit(1);
    }
    String logFile = args[0];
    BufferedReader br = null;
    try
    {
      boolean first = true;
      String line;
      br = new BufferedReader(new FileReader(logFile));
      HashMap<String, Integer> counterMap = new HashMap<String, Integer>();
      while ((line = br.readLine()) != null )
      {
        if (first == true){
          first = false; // skip header
          continue;
        }
        String[] items = line.split(SEPARATOR);
        String host = "", time = "", url = "", response = "";
        if (items.length > 1) host = items[0];
        if (items.length > 3) time = items[2];
        if (items.length > 5) url = items[4];
        if (items.length > 6) response = items[5];

        if (counterMap.get(host) != null)
        { // already added, bump counter
          Integer count = counterMap.get(host);
          counterMap.put(host, count + 1);
        } else { // add to map
          counterMap.put(host, new Integer(1));
        }
      }
      // Sort
      List<Map.Entry> list = new LinkedList<Map.Entry>(counterMap.entrySet());
      Collections.sort(list, new Comparator<Object>(){
        public int compare(Object o1, Object o2)
        {
          Integer i1 = (Integer) ((Map.Entry) o1).getValue();
          Integer i2 = (Integer) ((Map.Entry) o2).getValue();
          return i2.compareTo(i1);
        }
      });
      int i = 0;
      System.out.println("--- Top 10 Host/IP ---");
      for (Map.Entry entry: list)
      {
        i++;
        String host = (String) entry.getKey();
        Integer count = (Integer) entry.getValue();
        System.out.println("Host: " + host + " Count: " + count);
        if (i >= 10) break;
      }
    }catch(FileNotFoundException e){
      System.out.println("File " + logFile + "  not found");
    }catch(IOException e)
    {
      System.out.println("Error reading " + logFile);
    }finally{
      if (br != null)
      {
        try
        {
          br.close();
        }catch(IOException ioe)
        {
        }
      }
    }
  }
}
