package denstream.zikaebola;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class SaveDailyData {

	/////////////////////////////////////////////////////////////////////////////////
	//
	//
	//
	/////////////////////////////////////////////////////////////////////////////////
	public static void writeDailyDataWithClusterInfo(
			String outfileName_DailyCluster,
			int iDay, String header,
			ArrayList<String> daily_tweet,
			HashMap<Integer, Integer> cluster_id_hash,
			HashMap<Integer, Integer> recluster_id_hash,
			moa.clusterers.denstream.WithDBSCAN den)
	{
		Integer day_idx = iDay+1;
        String outfileName_cur_day_sink = outfileName_DailyCluster + "DenStream"+ day_idx.toString()+".csv";
        
        try {
			BufferedWriter outBufWriter_sink =new BufferedWriter(new FileWriter(outfileName_cur_day_sink));
			
			outBufWriter_sink.write("T,tw_id,mention,mood,lang,hashtag,length,word_count,x,y,retweeted_id,retweeted_x,retweeted_y,dist,sink_cluster,source_cluster\n");
			
			for (int iTweet=0; iTweet<daily_tweet.size(); iTweet++)
			{
				String str = daily_tweet.get(iTweet);
				{
					Integer cluster_id_value = cluster_id_hash.get(iTweet);
					if (cluster_id_value != null)
					{
						Integer recluster_id_value = recluster_id_hash.get(iTweet);
						
						String newStr = str + ", " + cluster_id_value.toString();
						newStr = newStr + ", " + recluster_id_value.toString();
						outBufWriter_sink.write(newStr);
						outBufWriter_sink.write("\n");
					}
				}
			}
			
			outBufWriter_sink.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
