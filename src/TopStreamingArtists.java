import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

class Single {
    public int position;
    public String track;
    public String artist;
    public int streams;
    public String url;
    public Single next;

    public Single(int ps, String st, String sa, int ss, String su){
        position = ps;
        track = st;
        artist = sa;
        streams = ss;
        url = su;
    }

    public void displayLink() // display ourself
    {
        System.out.printf("%d %-60s %-20s %-15d %-40s", position, track, artist, streams, url);

    }
}

/* The List TopStreamingArtists is composed of a series of artist names */
class TopStreamingArtists {
    private Single first;
    private Single last;

    public TopStreamingArtists(){
        first = null;
    }

    public boolean isEmpty(){
        return (first == null);
    }

    public void insertFirst(int ps, String st, String sa, int ss, String su)
    { // make new link
        Single newLink = new Single(ps, st, sa, ss, su);
        newLink.next = first; // newLink --> old first
        first = newLink; // first --> newLink
    }
    public void insertLast(int ps, String st, String sa, int ss, String su)
    {
        Single newLink = new Single(ps, st, sa, ss, su);
        if( isEmpty() ) // if empty list,
            first = newLink; // first --> newLink
        else
            last.next = newLink; // old last --> newLink
            last = newLink; // newLink <-- last
    }
    public Single deleteFirst() // delete first item
    { // (assumes list not empty)
        Single temp = first; // save reference to link
        first = first.next; // delete it: first-->old next
        return temp; // return deleted link
    }

    public void displayList()
    {
        Single current = first; // start at beginning of list
        while(current != null) // until end of list,
        {
            current.displayLink(); // print data
            current = current.next; // move to next link
            System.out.println("");
        }
        System.out.println("");
    }

    public static void main(String [ ] args) {
        TopStreamingArtists artistNames = new TopStreamingArtists();
        String csvFile = "C:\\Users\\franc\\IdeaProjects\\Top Streaming Artists\\src\\regional-us-weekly-2020-01-17--2020-01-24.csv";
        String inputLine;

        try {
            Scanner inputStream = new Scanner(new FileReader(csvFile));
            inputLine = inputStream.nextLine();
            while (inputStream.hasNextLine()) {
                inputLine = inputStream.nextLine();
                String[] tempArray = inputLine.split(",");
                int tempPosition = Integer.parseInt(tempArray[0]);
                String tempTrack = tempArray[1];
                String tempArtist = tempArray[2];
                int tempStreams = Integer.parseInt(tempArray[3]);
                String tempUrl = tempArray[4];

                artistNames.insertLast(tempPosition, tempTrack, tempArtist, tempStreams, tempUrl);
            }
            inputStream.close();
       }catch (FileNotFoundException e) {
        e.printStackTrace();
       }catch(NumberFormatException ex) {
            System.out.println("Error. Please input a valid number :-");
        }
        artistNames.displayList();
      //  Collections.sort(artistNames, Collections.reverseOrder());
       // artistNames.displayList();
       }





}


