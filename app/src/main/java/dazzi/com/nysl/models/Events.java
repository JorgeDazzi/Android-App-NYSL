package dazzi.com.nysl.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Events {


        public String date;
        public String teamHomeName;
        public String teamVisitorName;
        public String homeImg;
        public String visitorImg;


        public String getGoogleMapLink(){
            String gUrl = "https://www.google.com/maps/search/";

            gUrl+= this.teamHomeName;
            gUrl+= "+Stadium";

            return gUrl;
        }

        public String getGameTime() throws ParseException {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date date = dateFormat.parse(this.date);
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            String dateStr = formatter.format(date);

            return dateStr;
        }

}
